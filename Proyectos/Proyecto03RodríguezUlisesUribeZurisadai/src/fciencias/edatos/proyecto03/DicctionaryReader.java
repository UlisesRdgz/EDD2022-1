package fciencias.edatos.proyecto03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DicctionaryReader {

    /**
     * Lee los valores de un .txt y los almacena en un mapa.
     * @param map mapa donde serán almacenados los valores.
     */
    public void readMap(TDAMap<String, DoubleLinkedList<String>> map) {
        try(BufferedReader reader = new BufferedReader(new FileReader("Diccionario/diccionario-esp.txt"))){
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
    
}