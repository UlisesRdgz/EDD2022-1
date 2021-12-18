package fciencias.edatos.proyecto02;

import java.io.Serializable;
import java.util.Arrays;

public class Data implements Serializable{

    public Question[] toArray(BinarySearchTree<Integer, Question> arbol) {

        int i = 0;
        arbol.inicio();
        Question[] preguntas = new Question[arbol.sizeLeaf() + arbol.sizeNode()];

        while (i < preguntas.length) {
            
            if(!arbol.actual().isVisited()){
                preguntas[i] = arbol.actual();
                arbol.actual().visit();
                i++;                
            }

            if (arbol.getLeft() != null && !arbol.getLeft().isVisited()) {
                arbol.moveLeft();

            } else if (arbol.getRigth() != null && !arbol.getRigth().isVisited()){
                arbol.moveRigth();

            } else {
                arbol.moveParent();
            }
        }
        return preguntas;
    }

    public void preguntasAlf(BinarySearchTree<Integer, Question> arbol, Question[] preguntas) {
        String[] preguntasAlf = new String[arbol.sizeNode()];
        int posicion = 0;

        for (int i = 0; i < preguntas.length; i++) {
            if (!preguntas[i].getResult()){ 
                preguntasAlf[posicion] = "Pregunta: " + preguntas[i].getPregunta();
                posicion++;
            }
        }
        Arrays.sort(preguntasAlf);
        
        for (int i = 0; i < preguntasAlf.length; i++) {
            System.out.println(preguntasAlf[i]);
        }
    }

    public void preguntasFecha(BinarySearchTree<Integer, Question> arbol, Question[] preguntas) {
        String[] preguntasFecha = new String[arbol.sizeNode()];
        int posicion = 0;

        for (int i = 0; i < preguntas.length; i++) {
            if (!preguntas[i].getResult()){ 
                preguntasFecha[posicion] = "Fecha: " + preguntas[i].getDate() + " Hora: " + preguntas[i].getTime() + " Pregunta: " + preguntas[i].getPregunta();
                posicion++;
            }
        }
        Arrays.sort(preguntasFecha);
        
        for (int i = 0; i < preguntasFecha.length; i++) {
            System.out.println(preguntasFecha[i]);
        }
    }

    public void respuestasAlf(BinarySearchTree<Integer, Question> arbol, Question[] preguntas) {
        String[] respuestasAlf = new String[arbol.sizeLeaf()];
        int posicion = 0;

        for (int i = 0; i < preguntas.length; i++) {
            if (preguntas[i].getResult()){ 
                respuestasAlf[posicion] = "Pregunta: " + preguntas[i].getPregunta();
                posicion++;
            }
        }
        Arrays.sort(respuestasAlf);
        
        for (int i = 0; i < respuestasAlf.length; i++) {
            System.out.println(respuestasAlf[i]);
        }
    }

    public void respuestasFecha(BinarySearchTree<Integer, Question> arbol, Question[] preguntas) {
        String[] respuestasFecha = new String[arbol.sizeLeaf()];
        int posicion = 0;

        for (int i = 0; i < preguntas.length; i++) {
            if (preguntas[i].getResult()){ 
                respuestasFecha[posicion] = "Fecha: " + preguntas[i].getDate() + " Hora: " + preguntas[i].getTime() + " Pregunta: " + preguntas[i].getPregunta();
                posicion++;
            }
        }
        Arrays.sort(respuestasFecha);
        
        for (int i = 0; i < respuestasFecha.length; i++) {
            System.out.println(respuestasFecha[i]);
        }
    }
}