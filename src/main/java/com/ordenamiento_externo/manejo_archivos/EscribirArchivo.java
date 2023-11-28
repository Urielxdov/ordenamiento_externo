package com.ordenamiento_externo.manejo_archivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EscribirArchivo {
    private String nombre_arch;
    private FileWriter fw;
    
    public EscribirArchivo(String nombre_arch){
        this.nombre_arch = nombre_arch;
    }
    
    public EscribirArchivo(String f, int n){
        this.nombre_arch = f;
        abrir_Esc();
        escribir(n);
        cerrar_Esc();
    }
    
    public void abrir_Esc(){
        try{
            fw = new FileWriter(nombre_arch);
        } catch (IOException ex){
            System.out.println("Error al crear el archivo");
        }
    }
    
    public void escribir(String linea){
        try{
            fw.write(linea + "\n");
        } catch (IOException ex){
            System.out.println("Imposible escribir en el archivo");
        }
    }
    
    public void escribir(int n){
        Random r = new Random();
        try{
            for(int i = 0; i < n; i++){
                fw.write(r.nextInt(1_000_000) + "\n");
            }
        } catch (IOException ex){
            System.out.println("Imposible escribir en el archivo");
        }
    }
    
    public void cerrar_Esc(){
        try{
            fw.flush();
            fw.close();
        } catch (IOException ex){
            System.out.println("Imposible cerrar el archivo");
        }
    }
}