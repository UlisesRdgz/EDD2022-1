package fciencias.edatos.practica01;

import java.util.Arrays;
import java.util.HashSet;

/**
* Práctica 1 del curso de Estructuras de Datos.
* @author Emmanuel Cruz Hernández.
* @version 2.0 Septiembre 2021.
* @since Laboratorio de Estructuras de Datos 2022-1.
*/
public class Practica01{

	/** 
	* Hace la mezcla de dos arreglos ordenados desde la primera posición hasta
	* una posición límite
	* @param array1 el primer arreglo a mezlar
	* @param n el límite de mezcla del primer arreglo
	* @param array2 el segundo arreglo a mezclar
	* @param m el límite de mezcla del segundo arreglo.
	* @return un arreglo ordenado de longitud m+n con la mezcla definida.
	*/
	public static int[] mergeSortedArray(int[] array1, int n, int[] array2, int m){
		if(n > array1.length || m > array2.length)
			throw new RuntimeException("Límites no válidos");
		
		int[] result = new int[n + m];
		int pointer;
		for(pointer = 0; pointer < n; pointer++)
			result[pointer] = array1[pointer];
		for(int i = 0 ; i < m ; i++, pointer++)
			result[pointer] = array2[i];
		
		// Ordenamiento del arreglo result
		for(int j = 0; j < result.length - 1; j++){
			for(int k = j+1; k < result.length; k++){
				if(result[k] < result[j]){
					int aux = result[k];
					result[k] = result[j];
					result[j] = aux;
				}
			}
		}

		return result;
	}

    /**
    * Verifica si un tablero contiene los números desde 0 hasta n-1 en cada fila y cada columna.
    * @param board el tablero de nxn que contiene elementos dentro del rango [0, n-1].
    * @return true si el tablero contiene los números desde 0 hasta n-1 en cada fila y columna,
	* false en otro caso.
    */
    public static boolean isValidBoard(int[][] board){
    	int length = board.length;
		for (int i = 0; i < length ; i++) {
			for (int j = 0; j < length ; j++ ) {
				boolean verificador = false;
				// Verifica sobre las filas
				for(int k = 0 ; k < length; k++){
					if(board[i][k] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
				verificador = false;
				// Verifica sobre las columnas
				for(int k = 0 ; k < length; k++){
					if(board[k][i] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
			}
		}
		return true;
	}

    /**
     * Método optimizado
     * Verifica si un tablero nxn es válido considerando las filas y columnas, donde
     * cada elemento es único en cada fila y cada columna.
     * @param board arreglo bidimensional que representa al tablero nxn.
     * @return true si el tablero es válido, false en otro caso.
     */

    public static boolean isValidBoardOP(int[][] board){
		int length = board.length;

		for (int i=0; i < length; i++) {
			HashSet<Integer> set = new HashSet<Integer>();

			for (int j=0; j < length; j++) {
				if (set.contains(board[j][i])) return false;{
					set.add(board[j][i]);
				}
			}
		}
		return true;
	}

	/**
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void rotateArray(int[] num, int position){
		for(int i = 0; i < position ; i++){
			int aux = num[0];
			for(int j = 0; j < num.length -1 ; j++){
				num[j] = num[j+1];
			}
			num[num.length-1] = aux;
		}
	}

	public static void main(String[] args) {

		String directorio1 = "Examples/ArrayExamples/";
		String directorio2 = "Examples/BoardExamples/";

		// EJEMPLOS DE ACTIVIDAD 1
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");

		int[] arrayA1 = ArrayReader.readArray(directorio1 + "ArrayA1.txt");
		int[] arrayA2 = ArrayReader.readArray(directorio1 + "ArrayA2.txt");
		int[] resultA = mergeSortedArray(arrayA1, 3, arrayA2, 5);
		System.out.println("Resultado A: "+Arrays.toString(resultA));

		int[] arrayB1 = ArrayReader.readArray(directorio1 + "ArrayB1.txt");
		int[] arrayB2 = ArrayReader.readArray(directorio1 + "ArrayB2.txt");
		int[] resultB = mergeSortedArray(arrayB1, 5, arrayB2, 5);
		System.out.println("Resultado B: "+Arrays.toString(resultB));

		int[] arrayC1 = ArrayReader.readArray(directorio1 + "ArrayC1.txt");
		int[] arrayC2 = ArrayReader.readArray(directorio1 + "ArrayC2.txt");
		int[] resultC = mergeSortedArray(arrayC1, 4, arrayC2, 6);
		System.out.println("Resultado C: "+Arrays.toString(resultC));



		// EJEMPLOS DE ACTIVIDAD 2
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 2\n");

		// BoardA
		int[][] boardA = ArrayReader.readMatrix(directorio2 + "BoardA.txt");
		long inicio = System.currentTimeMillis();
		boolean boardResultA = isValidBoard(boardA);
		long fin = System.currentTimeMillis();

		System.out.println("El tablero A es válido: "+boardResultA);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardA optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultAOP = isValidBoardOP(boardA);
		fin = System.currentTimeMillis();
		System.out.println("El tablero A es válido: "+boardResultAOP);
		System.out.println("Tiempo de ejecución optimizado: " + (fin-inicio)+" milisegundos.\n");

		// BoardB
		int[][] boardB = ArrayReader.readMatrix(directorio2 + "BoardB.txt");
		inicio = System.currentTimeMillis();
		boolean boardResultB = isValidBoard(boardB);
		fin = System.currentTimeMillis();

		System.out.println("El tablero B es válido: "+boardResultB);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardB optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultBOP = isValidBoardOP(boardB);
		fin = System.currentTimeMillis();
		System.out.println("El tablero B es válido: "+boardResultBOP);
		System.out.println("Tiempo de ejecución optimizado: " + (fin-inicio)+" milisegundos.\n");

		// BoardC
		int[][] boardC = ArrayReader.readMatrix(directorio2 + "BoardC.txt");
		inicio = System.currentTimeMillis();
		boolean boardResultC = isValidBoard(boardC);
		fin = System.currentTimeMillis();

		System.out.println("El tablero C es válido: "+boardResultC);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardC optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultCOP = isValidBoardOP(boardC);
		fin = System.currentTimeMillis();
		System.out.println("El tablero C es válido: "+boardResultCOP);
		System.out.println("Tiempo de ejecución optimizado: " + (fin-inicio)+" milisegundos.\n");

		// BoardD
		int[][] boardD = ArrayReader.readMatrix(directorio2 + "BoardD.txt");
		inicio = System.currentTimeMillis();
		boolean boardResultD = isValidBoard(boardD);
		fin = System.currentTimeMillis();

		System.out.println("El tablero D es válido: "+boardResultD);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardD optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultDOP = isValidBoardOP(boardD);
		fin = System.currentTimeMillis();
		System.out.println("El tablero D es válido: "+boardResultDOP);
		System.out.println("Tiempo de ejecución optimizado: " + (fin-inicio)+" milisegundos.\n");

		// EJEMPLOS DE ACTIVIDAD 3
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 3\n");

		rotateArray(arrayA1, 5);
		rotateArray(arrayB1, 0);
		rotateArray(arrayC1, 6);

		System.out.println("Arreglo A1 rotado 5 veces: " + Arrays.toString(arrayA1));
		System.out.println("Arreglo B1 rotado 0 veces: " + Arrays.toString(arrayB1));
		System.out.println("Arreglo C1 rotado 6 veces: " + Arrays.toString(arrayC1));

		System.out.println("\n\nFIN DE EJEMPLOS\n");
	}
}