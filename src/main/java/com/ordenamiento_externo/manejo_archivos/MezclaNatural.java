package com.ordenamiento_externo.manejo_archivos;

public class MezclaNatural {
    private String f, f1, f2;
    private int numeroDatos;

    /**
     * Constructor que genera n numero de numeros aleatorios en el archivo f
     * @param n cantidad de numeros aleatorios
     */
    public MezclaNatural(int n) {
        numeroDatos = n;
        f = "f.txt";
        f1 = "f1.txt";
        f2 = "f2.txt";
        new EscribirArchivo(f, n); // Crear el archivo con datos aleatorios
    }

    /**
     * Se inicializan los valores de las variables globales
     */
    public MezclaNatural() {
        numeroDatos = getNumeroDatos();
        f = "f.txt";
        f1 = "f1.txt";
        f2 = "f2.txt";
    }

    /**
     * Getter
     * @return numero de datos en el documento f
     */
    public int getNumeroDatos() {
        return numeroDatos;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        StringBuilder salida1 = new StringBuilder();
        StringBuilder salida2 = new StringBuilder();

        LeerArchivo lector = null;

        try {
            lector = new LeerArchivo(f);
            lector.abrir_Lect(f);
            numeroDatos = getNumeroDatos();
            int contador = 1;

            while (contador <= numeroDatos) {
                String renglon = lector.leerLinea();

                if (renglon != null) {
                    if (contador <= 5) {
                        agregarConComa(salida1, renglon);
                    } else if (contador >= numeroDatos - 4) {
                        agregarConComa(salida2, renglon);
                    }
                    contador++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lector != null) {
                try {
                    lector.cerrar_Lect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        quitarComa(salida1);
        quitarComa(salida2);

        salida.append(salida1).append(" ... ").append(salida2);
        return salida.toString();
    }

    private void agregarConComa(StringBuilder stringBuilder, String valor) {
        stringBuilder.append(valor).append(", ");
    }

    private void quitarComa(StringBuilder stringBuilder) {
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
    }

    public void particiona(int veces) {
        // Crear dos archivos para escritura f1 y f2
        EscribirArchivo escribef1 = new EscribirArchivo(f1);
        EscribirArchivo escribef2 = new EscribirArchivo(f2);
        // Abrir de lectura "f.txt"
        LeerArchivo leef = new LeerArchivo(f);
        escribef1.abrir_Esc();
        escribef2.abrir_Esc();
        leef.abrir_Lect(f);
        // Separar de manera ordenada los valores de f en f1 y f2
        String lin = "";
        // Contadores
        int contadorX = 0, contadorY = 0, contadorTotal = 0;

        while (contadorTotal <= Math.round(numeroDatos / veces)) {
            // Verificamos que no haya dado ya los valores correspondientes
            // a cada uno para no perder el orden
            if (!(contadorX < veces) && !(contadorY < veces)) {
                contadorTotal += 1;
                contadorX = 0;
                contadorY = 0;
            }

            // Actualizamos la lectura
            lin = leef.leerLinea();
            // Verificamos que la lectura no es nula
            if (lin != null) {
                // Verificamos que no se haya acabado la ronda de cada uno
                if (contadorX < veces) {
                    escribef1.escribir(lin);
                    contadorX += 1;
                } else {
                    escribef2.escribir(lin);
                    contadorY += 1;
                }
            } else { // No hay mas datos
                break;
            }

        }
        // Cerramos los archivos
        escribef1.cerrar_Esc();
        escribef2.cerrar_Esc();
        leef.cerrar_Lect();
    }

    public void fusiona(int veces) {
        // Iniciamos los archivos requeridos
        LeerArchivo leef1 = new LeerArchivo(f1);
        LeerArchivo leef2 = new LeerArchivo(f2);
        EscribirArchivo escribef = new EscribirArchivo(f);
        leef1.abrir_Lect(f1);
        leef2.abrir_Lect(f2);
        escribef.abrir_Esc();

        // Para leer y manipular los valores
        int x = 0, y = 0;
        String sx = leef1.leerLinea();
        String sy = leef2.leerLinea();

        // Contador de iteraciones
        int contadorX = 0, contadorY = 0, contadorTotal = 0;

        while (contadorTotal <= (numeroDatos / veces)) {

            // Verificamos que el primer archivo no este vacio
            if ((sx != null && contadorX < veces) || sy == null) {
                // Pasamos a numero
                x = Integer.parseInt(sx);
                // Verificamos que el segundo archivo no este vacio
                if (sy != null && contadorY < veces) {
                    // Pasamos a numero
                    y = Integer.parseInt(sy);
                    // Validamos cual escribir primero
                    if (x > y) {
                        escribef.escribir(sy);
                        sy = leef2.leerLinea();
                        contadorY += 1; 
                    } else {
                        escribef.escribir(sx);
                        sx = leef1.leerLinea();
                        contadorX += 1;
                    }
                } else {
                    // Es el unico que se puede escribir
                    escribef.escribir(sx);
                    sx = leef1.leerLinea();
                    contadorX += 1;
                }
            } else {
                // Ya que el primer archivo esta vacio solo queda verificar que el segundo no
                // este vacio
                if (sy != null && contadorY < veces) {
                    y = Integer.parseInt(sy);
                    escribef.escribir(sy);
                    sy = leef2.leerLinea();
                    contadorY += 1;
                }
            }

            // Verificamos que los contadores no superen el numero de veces
            // de lo contrario actualizamos los contadores y aumentamos el total
            if (!(contadorX < veces) && !(contadorY < veces)) {
                contadorTotal += 1;
                contadorX = 0;
                contadorY = 0;
            }

            if(sx == null && sy == null) break;
        }
        // Cerramos los archivos
        leef1.cerrar_Lect();
        leef2.cerrar_Lect();
        escribef.cerrar_Esc();
    }

    private void realizarMezclaNatural (MezclaNatural archivosMezcla, int veces) {
        archivosMezcla.particiona(veces);
        archivosMezcla.fusiona(veces);
    }

    public void ejecutar() {
        MezclaNatural prueba = new MezclaNatural(1_000_000);
        int total = prueba.getNumeroDatos();

        System.out.println("Inicio de la mezcla:");
        System.out.println(prueba);

        for (int veces = 1; veces < total; veces *= 2) {
            realizarMezclaNatural(prueba, veces);
        }


        System.out.println("Se ha terminado la mezcla: ");
        System.out.println(prueba);

    }

}