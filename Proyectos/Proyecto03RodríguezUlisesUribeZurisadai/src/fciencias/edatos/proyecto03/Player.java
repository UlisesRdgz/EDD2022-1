package fciencias.edatos.proyecto03;

import java.io.Serializable;

/**
* Clase jugador, donde se crean los jugadores.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 1.0 Enero 2022.
* @since Estructuras de Datos 2022-1. Proyecto03.
*/
public class Player implements Serializable{

    public class Palabras implements Serializable{

        /** Palabra. */
        private String palabra = "";

        /** Puntuación. */
        private int puntos = 0;

        /** Contructor de la palabra. */
        private Palabras(String palabra, int puntos) {
            this.palabra = palabra;
            this.puntos = puntos;
        }

        /** Obtener puntuación. */
        private int getPuntos(){
            return puntos;
        }

        /** Formato de la palabra. */
        public String toString(){
            String formato = "\u269C " + palabra + " - " + puntos + " puntos.";
            return formato;
        }
    }
    
    /** Nombre del jugador. */
    private String name = "";

    /** Puntaje del jugador. */
    private int score = 0;

    /** Lista de las mejores 3 palabras. */
    private DoubleLinkedList<Palabras> palabras = new DoubleLinkedList<>();

    /**
     * Contructor de un jugador.
     * @param name nombre del jugador.
     * @param score puntaje del jugador.
     */
    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    /**
     * Establece el nombre del jugador.
     * @param name nombre del jugador.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Establece el puntaje del jugador.
     * @param score puntaje del jugador.
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Regresa el nombre de jugador.
     * @return nombre del jugador.
     */
    public String getName(){
        return name;
    }

    /**
     * Regresa el puntaje del jugador.
     * @return puntaje del jugador.
     */
    public int getScore(){
        return score;
    }

    /**
     * Agrega las palabras a un jugador.
     * @param palabra palabra que agregaremos.
     * @param puntos puntaje de la palabra.
     */
    public void setWords(String palabra, int puntos){
        Palabras newWord = new Palabras(palabra, puntos);
        palabras.add(palabras.size(), newWord);
    }

    /**
     * Establece las 3 mejores palabras del jugador.
     * @param words palabras del jugador.
     */
    public void setTopWords(Palabras[] words){
        this.palabras.clear();
        for (Palabras palabras2 : words) {
            this.palabras.add(this.palabras.size(), palabras2);
        }
    }

    /**
     * Regresa e imprime las 3 palabras con mejor puntuación.
     * @return un arreglo con las 3 mejores palabras.
     */
    public Palabras[] topWords(){
        Palabras[] top;
        DoubleLinkedList<Palabras> aux = palabras;
        int cont = 0, i = 0;

        if(!palabras.isEmpty()){
            /** Solo hay un jugador. */
            if(palabras.size() == 1){
                top = new Palabras[1];
                top[0] = palabras.get(0);

            /** Solo hay dos palabras. */
            } else if(palabras.size() == 2) {
                top = new Palabras[2];
                if (aux.get(0).getPuntos() < aux.get(1).getPuntos()){
                    top[0] = aux.get(1);
                    top[1] = aux.get(0);
                } else{
                    top[1] = aux.get(1);
                    top[0] = aux.get(0); 
                } 

            /** Más de tres palabras. */
            } else {
                
                top = new Palabras[3];
                while(cont < 3){
                    i = 0;
                    for (int j = 1; j < aux.size(); j++) {
                        if (aux.get(i).getPuntos() < aux.get(j).getPuntos()) {
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
            return top;

        } else{
            System.out.println("Ninguna de las palabras fue válida :(");
            return null;
        }
    }

    /** Formato del jugador. */
    public String toString(){
        String formato = "Nombre: " + getName() + "\nPuntuación: " + getScore() + "\n" + palabras;
        return formato;
    }

}
