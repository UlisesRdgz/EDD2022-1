package fciencias.edatos.proyecto03;

import java.io.Serializable;

public class Player implements Serializable{
    
    /** Nombre del jugador */
    private String name = "";

    /** Puntaje del jugador */
    private int score = 0;

    /**
     * Contructor de un jugador.
     * @param name nombre del jugador.
     * @param score puntaje del jugador.
     */
    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score = score;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public String toString(){
        String formato = "Nombre: " + getName() + " Puntuación: " + getScore();
        return formato;
    }

}
