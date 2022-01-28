package fciencias.edatos.proyecto03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
* Clase para leer y guardar los datos.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 1.0 Enero 2022.
* @since Estructuras de Datos 2022-1. Proyecto03.
*/
public class Data implements Serializable{

    /**
     * Lee los valores de un .txt y los almacena en un mapa.
     * @param map mapa donde serán almacenados los valores.
     */
    public void readMap(TDAMap<String, DoubleLinkedList<String>> map) {
        try(BufferedReader reader = new BufferedReader(new FileReader("Datos/diccionario-esp.txt"))){
            DoubleLinkedList<String> list = new DoubleLinkedList<>();
            String line = null;
            int i = 0;

            while((line = reader.readLine()) != null){
                if (map.get(line) == null) {
                    list = new DoubleLinkedList<>(); 
                    map.put(line, list);
                    list.add(0, line);

                }else {
                    list = map.get(line);
                    list.add(list.size(), line);
                }
            }

        } catch(FileNotFoundException fnfe) {
            System.out.println("ARCHIVO NO ENCONTRADO");
        } catch(IOException ioe) {}
    }

    /**
     * Lee el archivo donde se encuentran los jugadores.
     * @return lista con los jugadores.
     */
    public DoubleLinkedList<Player> readData() {
        try {
            ObjectInputStream lee = new ObjectInputStream(new FileInputStream("Datos/usuarios.txt"));
            DoubleLinkedList<Player> lista = new DoubleLinkedList<>();
            lista = (DoubleLinkedList<Player>) lee.readObject();
            return lista;

        } catch (Exception e) {
            System.out.println("");
        }

        return null;
    }

    /**
     * Guarda los jugadores en el archivo .txt
     * @param lista lista de jugadores.
     * @param usuario nuevo usuario.
     */
    public void saveData(DoubleLinkedList<Player> lista, Player usuario) {
        try {
            lista.add(lista.size(), usuario);
            ObjectOutputStream escribe = new ObjectOutputStream(new FileOutputStream("Datos/usuarios.txt"));
            escribe.writeObject(lista);

        } catch (Exception e) {
            System.out.println("No se encontro el archivo.");
        }
    }
    
}