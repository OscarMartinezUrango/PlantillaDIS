package es.ufv.dis.final2022.back;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivo {


    public List<Persona> leerJsonProductos(){

        try {
            //Lee el fichero que le pasamos y lo carga en un reader
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/back/src/main/resources/template.json"));
            // Convierte el array JSON a un arraylist de Productos
            List<Persona> productos =
                    new Gson().fromJson(reader, new TypeToken<List<Persona>>()
                    {}.getType());
            reader.close();
            return productos;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }


    public void actualizarJson (List<Persona> listaProductosActualizada){

        try {
            //lee el fichero que le pasamos y lo carga en un reader

            BufferedWriter contenido = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/back/src/main/resources/template.json",false));
            // convierte el array JSON a un arraylist de Zonas basicas de salud
            List<Persona> listaActualizada = new ArrayList<Persona>(listaProductosActualizada);

            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(listaActualizada, contenido);

            contenido.close();// close writer

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex); //si no ha escrito nada,  array devuelve una excepcion
        }
    }




}
