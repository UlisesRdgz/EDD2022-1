package fciencias.edatos.proyecto02;

import java.io.Serializable;

public class Pregunta implements Serializable{
    
    String pregunta;
    int fecha;

    public Pregunta(String pregunta, int fecha) {
        this.pregunta = pregunta;
        this.fecha = fecha;
    }

    public String getPregunta() {
        return pregunta;
    }

    public int getFecha() {
        return fecha;
    }
}
