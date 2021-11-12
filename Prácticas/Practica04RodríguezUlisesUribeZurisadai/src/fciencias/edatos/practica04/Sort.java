package fciencias.edatos.practica04;

import java.util.Arrays;

/**
 * Implementación de métodos
 * @author Rodríguez García Ulises.
 * @author Uribe García Zurisadai. 
 * @version 
 * @since Estructuras de datos 2022-1. Prática 4.
 */
public class Sort{

    /**
     * Intercambia dos elementos de posición en una misma colección
     * @param arreglo arreglo que cambia la posición de elementos dados.
     * @param i índice del primer elemento 
     * @param j índice del segundo elemento
     */
    private static void swap(int[] arreglo, int i, int j) {
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    public static void quickSort() {
        
    }

    public static void mergeSort() {
        
    }

    public int find(int[] arr, int e){
        return e;
    }
}