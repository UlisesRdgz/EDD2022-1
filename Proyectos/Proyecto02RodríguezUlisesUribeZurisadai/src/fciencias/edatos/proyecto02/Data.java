package fciencias.edatos.proyecto02;

import java.io.Serializable;
import java.util.Arrays;

/**
* Define los métodos para imprimir el
* historial de preguntas al usuario.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 17 Diciembre 2021.
* @since Estructuras de Datos 2022-1. Proyecto02.
*/
public class Data implements Serializable{

    /**
     * Método para guardar el árbol en un arreglo.
     * @param arbol que será guardado en el arreglo.
     * @return el arreglo con el árbol guardado.
     */
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

    /**
     * Método para imprimir las preguntas del árbol en orden alfabético.
     * @param arbol obtener el tamaño de los nodos que no son hojas.
     * @param preguntas el arreglo con el árbol guardado.
     */
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

    /**
     * Método para imprimir las preguntas del árbol en el orden en 
     * que fueron agregadas.
     * @param arbol obtener el tamaño de los nodos que no son hojas.
     * @param preguntas el arreglo con el árbol guardado.
     */
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

    /**
     * Método para imprimir las respuetas del árbol en orden alfabético.
     * @param arbol obtener el tamaño de los nodos que no son hojas.
     * @param preguntas el arreglo con el árbol guardado.
     */
    public void respuestasAlf(BinarySearchTree<Integer, Question> arbol, Question[] preguntas) {
        String[] respuestasAlf = new String[arbol.sizeLeaf()];
        int posicion = 0;

        for (int i = 0; i < preguntas.length; i++) {
            if (preguntas[i].getResult()){ 
                respuestasAlf[posicion] = "Personaje: " + preguntas[i].getPregunta();
                posicion++;
            }
        }
        Arrays.sort(respuestasAlf);
        
        for (int i = 0; i < respuestasAlf.length; i++) {
            System.out.println(respuestasAlf[i]);
        }
    }

    /**
     * Método para imprimir las respuestas del árbol en el orden en 
     * que fueron agregadas.
     * @param arbol obtener el tamaño de los nodos que no son hojas.
     * @param preguntas el arreglo con el árbol guardado.
     */
    public void respuestasFecha(BinarySearchTree<Integer, Question> arbol, Question[] preguntas) {
        String[] respuestasFecha = new String[arbol.sizeLeaf()];
        int posicion = 0;

        for (int i = 0; i < preguntas.length; i++) {
            if (preguntas[i].getResult()){ 
                respuestasFecha[posicion] = "Fecha: " + preguntas[i].getDate() + " Hora: " + preguntas[i].getTime() + " Personaje: " + preguntas[i].getPregunta();
                posicion++;
            }
        }
        Arrays.sort(respuestasFecha);
        
        for (int i = 0; i < respuestasFecha.length; i++) {
            System.out.println(respuestasFecha[i]);
        }
    }
}