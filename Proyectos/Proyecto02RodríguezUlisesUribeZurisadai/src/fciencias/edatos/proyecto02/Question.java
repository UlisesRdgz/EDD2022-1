package fciencias.edatos.proyecto02;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Question implements Serializable{
    
    private String question;
    private LocalDate date;
    private LocalTime time;
    private boolean result;

    public Question(String question, LocalDate date, LocalTime time, boolean result) {
        this.question = question;
        this.date = date;
        this.time = time;
        this.result = result;
    }

    public String getPregunta() {
        return question;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public boolean getResult(){
        return result;
    }  
}
