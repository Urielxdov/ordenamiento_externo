package com.ordenamiento_externo.manejo_archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo {
    private String nombre_arch;
    private FileReader fr;
    private BufferedReader br;

    public LeerArchivo(String f) {
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado o no tiene permiso de lectura");
        }
    }

    public void abrir_Lect(String f) {
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado o no tiene permiso de lectura");
        }
    }

    public int contarLinea() {
        int contador = 0;
        try {
            while (br.readLine() != null) {
                contador++;
            }
            return contador;
        } catch (IOException ex) {
            System.out.println("Imposible leer...");
        }
        return contador;
    }

    public String leerLinea() {
        String renglon = "";
        try {
            if ((renglon = br.readLine()) != null) {
                return renglon;
            }
        } catch (IOException ex) {
            System.out.println("Imposible leer...");
            return null;
        }
        return renglon;
    }

    public void cerrar_Lect() {
        try {
            br.close();
            fr.close();
        } catch (IOException ex) {
            System.out.println("ERROR al cerrar el archivo");
        }
    }
}
