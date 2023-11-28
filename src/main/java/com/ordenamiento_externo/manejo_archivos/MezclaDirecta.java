package com.ordenamiento_externo.manejo_archivos;

public class MezclaDirecta {
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
