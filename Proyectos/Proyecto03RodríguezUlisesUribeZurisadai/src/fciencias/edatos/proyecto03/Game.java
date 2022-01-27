package fciencias.edatos.proyecto03;

import java.util.Random;

public class Game extends Thread{
    
    public void run(){
        Timer();
    }

    /**
     * Temporizador 
     * @return
     */
    public void Timer(){
        // int segundos = 0;
        for (int i = 0; i < 60; i++) {
            // System.out.println(segundos++);
            delay();
        }
    }

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
    public char[] Sequence() {
        char[] randomArray = new char[9];

        for (int i = 0; i < 9; i++) {
            Random random = new Random();
            char randomCharacter = (char) (random.nextInt(26) + 'a');
            randomArray[i] = randomCharacter;
            // System.out.println("Generated Random Character: " + randomCharacter);
        }

        return randomArray;
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

}
