package fciencias.edatos.proyecto02;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Question implements Serializable{
    
    private String question;
    private LocalDate date;
    private LocalTime time;
    private boolean result;
    private boolean visited = false;

    /**
     * Contructor de la clase Question.
     * @param question pregunta de true o false.
     * @param date fecha de la creación.
     * @param time hora de la creación.
     * @param result saber si es pregunta o respuesta.
     */
    public Question(String question, LocalDate date, LocalTime time, boolean result) {
        this.question = question;
        this.date = date;
        this.time = time;
        this.result = result;
    }

    /**
     * Accede a la pregunta.
     * @return la pregunta.
     */
    public String getPregunta() {
        return question;
    }

    /**
     * Accede a la fecha.
     * @return la fecha.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Accede a la hora.
     * @return la hora.
     */
    public LocalTime getTime(){
        return time;
    }

    /**
     * Accede al resultado.
     * @return el resultado.
     */
    public boolean getResult(){
        return result;
    }  

    /**
     * Permite saber si la pregunta fue visitada.
     * @return true si fue visitada, false en otro caso.
     */
    public boolean isVisited(){
        return visited;
    } 

    /**
     * Visita la pregunta.
     */
    public void visit(){
        this.visited = true;
    } 

    /**
     * Cambia el valor de la pregunta a no visitada.
     */
    public void unVisit(){
        this.visited = false;
    } 
}