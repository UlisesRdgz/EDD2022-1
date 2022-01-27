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

public class DicctionaryReader implements Serializable{

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
                    // System.out.println(list.size());
                    list.add(0, line);

                    // System.out.println(list.get(0));
                }else {
                    // System.out.println(list.size());
                    list = map.get(line);
                    list.add(list.size(), line);
                    // System.out.println(list.get(list.size()-1));
                }
            }

        } catch(FileNotFoundException fnfe) {
            System.out.println("ARCHIVO NO ENCONTRADO");
        } catch(IOException ioe) {}
    }

    public DoubleLinkedList<Player> readData() {

        try {
            ObjectInputStream lee = new ObjectInputStream(new FileInputStream("Datos/usuarios.txt"));
            DoubleLinkedList<Player> lista = new DoubleLinkedList<>();
            lista = (DoubleLinkedList<Player>) lee.readObject();
            return lista;

        } catch (Exception e) {
            System.out.println("Sin información");
        }

        return null;
    }

    public void saveData(DoubleLinkedList<Player> lista, Player usuario) {
        try {
            System.out.println(lista.size());
            lista.add(lista.size(), usuario);
            System.out.println(lista.size());
            ObjectOutputStream escribe = new ObjectOutputStream(new FileOutputStream("Datos/usuarios.txt"));
            escribe.writeObject(lista);

        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
    
}