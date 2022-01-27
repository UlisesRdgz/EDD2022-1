package fciencias.edatos.proyecto03;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.Random;

public class Game extends Thread implements Serializable{
    
    /**
     * Inicia el conteo.
     */
    public void run(){
        Timer();
    }

    /**
     * Temporizador.
     */
    public void Timer(){
        // int segundos = 0;
        for (int i = 0; i < 10; i++) {
            // System.out.println(segundos++);
            delay();
        }
    }

    /**
     * Método para esperar un segundo.
     */
    public void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }
    
    /**
     * Método donde la computadora crea una secuencia
     * de carácteres aleatorios.
     * @return una lista de carácteres aleatorios.
     */
    public char[] sequenceCPU() {
        String cadena = "aaaaabbccddeeeeeffgghhiiiiijjkkllmmnnñoooooppqqrrssttuuuuuvwxyzz";
        char[] cadenaRandom = new char[9];
        char[] caracteres = cadena.toCharArray();

        for (int i = 0; i < 9; i++) {
            Random random = new Random();
            int randomCharacter = random.nextInt(caracteres.length);
            cadenaRandom[i] = caracteres[randomCharacter];
            // System.out.println("Generated Random Character: " + randomCharacter);
        }

        return cadenaRandom;
    }

    public char[] sequenceUser(String cadena){
        cadena = cadena.replaceAll("\\p{Punct}", "");
        cadena = cadena.toLowerCase();

        if (cadena.toCharArray().length > 9) {
            return null;
        }
        char[] cadenaUsuario = cadena.toCharArray();
        return cadenaUsuario;
    }

    /**
     * Calcula la puntuación del jugador.
     * @param palabra término para obtener el puntaje.
     * @param list lista para ver si la palabra está
     * repetida.
     * @return el puntaje de la palabra.
     */
    public int Score(String palabra, DoubleLinkedList<String> list){
        int score = 0;

        if (!list.contains(palabra))
            score = palabra.length() * palabra.length();
            
        return score;
    }

    /**
     * Elimina los acentos de la cadena.
     * @param s cadena a la cual se le eliminara el acento.
     * @return regresa la cadena sin el acento.
     */
    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
    
    /**
     * Verifica que la palabra dada contenga los carácteres
     * de la cadena inicial.
     * @param cadena secuencia inicial de carácteres.
     * @param palabra conjunto de carácteres con el cual 
     * se va a verificar. 
     * @return true si la palabra contiene los carácteres,
     * false en otro caso.
     */
    public boolean check(char[] cadena, String palabra){

        palabra = stripAccents(palabra);
        char[] aux = new char[cadena.length];
        char[] caracteres = palabra.toCharArray();
        int contador = 0;

        for (int i = 0; i < caracteres.length; i++) {
            for (int j = 0; j < cadena.length; j++) {
                if (caracteres[i] == cadena[j] && aux[j] != 'x') {
                    aux[j] = 'x';
                    contador++;
                    break;
                }
            }
        }

        if (contador == caracteres.length) 
            return true;

        return false;
    }

    /**
     * Verifica que la palabra exista en el diccionario.
     * @param palabra término el cual se va a verificar.
     * @param map mapa donde verificaremos.
     * @return true si se encuentra en el diccionario,
     * false en otro caso.
     */
    public boolean checkDiccionary(String palabra, TDAMap<String, DoubleLinkedList<String>> map){

        if(map.get(palabra) == null)
            return false;

        if(map.get(palabra).contains(palabra))
            return true;

        return false;
    }

    public void topPlayers(DoubleLinkedList<Player> jugadores){
        Player[] top;
        DoubleLinkedList<Player> aux = jugadores;
        int cont = 0, i = 0;

        /** Solo hay un jugador */
        if(jugadores.size() == 1){
            top = new Player[1];
            top[0] = jugadores.get(0);

        /** Solo hay dos jugadores */
        } else if(jugadores.size() == 2) {
            top = new Player[2];
            if (aux.get(0).getScore() < aux.get(1).getScore()){
                top[0] = aux.get(1);
                top[1] = aux.get(0);
            } else{
                top[1] = aux.get(1);
                top[0] = aux.get(0); 
            } 

        /** Más de tres jugadores */
        } else {
            
            top = new Player[3];
            while(cont < 3){
                i = 0;
                for (int j = 1; j < aux.size(); j++) {
                    if (aux.get(i).getScore() < aux.get(j).getScore()) {
                        i = j;
                    } 
                }
                top[cont++] = aux.get(i);
                aux.remove(i);
            }
        }
        
        for (int j = 0; j < top.length; j++) {
            System.out.println(top[j]);
        }
    }
}
