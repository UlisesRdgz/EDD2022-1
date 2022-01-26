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
    public void readMap(TDAMap<String, String> map) {
        try(BufferedReader reader = new BufferedReader(new FileReader("Diccionario/diccionario-esp.txt"))){
            String line = null;
            int i = 0;

            while((line = reader.readLine()) != null){
                // System.out.println(i++);
                // System.out.println(line);
                map.put(line, line);
            }

        } catch(FileNotFoundException fnfe) {
            System.out.println("ARCHIVO NO ENCONTRADO");
        } catch(IOException ioe) {}
    }
    
}