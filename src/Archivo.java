

/**
 * clase que recolecta los datos
 * @authot Alina Carías, Ignacio Mendez, Diego Soto, Ariela Mishaan
 * @date 07-10-2022
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Archivo{
    
    private File archivo;

    public Archivo (String nombreArchivo){
        archivo = new File(nombreArchivo);
        try{
            archivo.createNewFile();   
        } 
        catch(IOException e){
            //si el archivo no existe o está en uso
            e.printStackTrace();
        }
    }

    public void escribirArchivo(String linea){
        try{
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(linea);
            escritor.close();

        } 
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> leerArchivo(){
        Scanner lector;
        ArrayList<String> lineas = new ArrayList<String>();

        try{
            lector = new Scanner(archivo);
            while(lector.hasNextLine()){
                lineas.add(lector.nextLine());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return lineas;
    }

    public String leerArchivoString(){
        String texto = "";

        try{
            Scanner lector = new Scanner(archivo);
            while(lector.hasNextLine()){
                texto = texto + lector.nextLine() + "\n";
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return texto;
    }

    public void eliminarArchivo(){
        archivo.delete();
    }
}