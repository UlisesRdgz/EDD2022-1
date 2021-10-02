package fciencias.edatos.practica01;

import java.util.Arrays;

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
	* Método optimizado
	* Hace la mezcla de dos arreglos ordenados enteros desde la primera posición hasta
	* una posición límite n y m. Dando como resultado un arreglo ordenado de enteros de 
	* longitud n+m.
	* @param array1 el primer arreglo a mezlar
	* @param n el límite de mezcla del primer arreglo
	* @param array2 el segundo arreglo a mezclar
	* @param m el límite de mezcla del segundo arreglo.
	* @return un arreglo ordenado de longitud m+n con la mezcla definida.
	*/
	public static int[] mergeSortedArrayOP(int[] array1, int n, int[] array2, int m){
		int[] result = new int[n + m];
		int i= 0, j=0;

		for (int aux = 0; aux < result.length; aux++) {
			if (array1[i] <= array2[j] && i<n) {
				result[aux] = array1[i];
				i++;
			}else{
				result[aux] = array2[j];
				j++;
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

		// Declaramos los arreglos.
		int[] arrayA1 = ArrayReader.readArray(directorio1 + "ArrayA1.txt");
		int[] arrayA2 = ArrayReader.readArray(directorio1 + "ArrayA2.txt");

		// ArregloA
		long inicio = System.currentTimeMillis();
		int[] resultA = mergeSortedArray(arrayA1, 500, arrayA2, 700);
		long fin = System.currentTimeMillis();	
		// System.out.println("Resultado A: "+Arrays.toString(resultA));
		System.out.println("Tiempo de ejecución A: " + (fin-inicio)+" milisegundos.");

		// Declaramos los arreglos.
		int[] arrayB1 = ArrayReader.readArray(directorio1 + "ArrayB1.txt");
		int[] arrayB2 = ArrayReader.readArray(directorio1 + "ArrayB2.txt");
		
		// ArregloB
		inicio = System.currentTimeMillis();
		int[] resultB = mergeSortedArray(arrayB1, 2000, arrayB2, 3500);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado B: "+Arrays.toString(resultB));
		System.out.println("Tiempo de ejecución B: " + (fin-inicio)+" milisegundos.");

		// Declaramos los arreglos.
		int[] arrayC1 = ArrayReader.readArray(directorio1 + "ArrayC1.txt");
		int[] arrayC2 = ArrayReader.readArray(directorio1 + "ArrayC2.txt");

		// ArregloC
		inicio = System.currentTimeMillis();
		int[] resultC = mergeSortedArray(arrayC1, 4000, arrayC2, 4000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado C: "+Arrays.toString(resultC));
		System.out.println("Tiempo de ejecución C: " + (fin-inicio)+" milisegundos.");

		// Declaramos los arreglos.
		int[] arrayD1 = ArrayReader.readArray(directorio1 + "ArrayD1.txt");
		int[] arrayD2 = ArrayReader.readArray(directorio1 + "ArrayD2.txt");

		// ArregloD
		inicio = System.currentTimeMillis();
		int[] resultD = mergeSortedArray(arrayD1, 7000, arrayD2, 8000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado D: "+Arrays.toString(resultD));
		System.out.println("Tiempo de ejecución D: " + (fin-inicio)+" milisegundos.");

		// Declaramos los arreglos.
		int[] arrayE1 = ArrayReader.readArray(directorio1 + "ArrayE1.txt");
		int[] arrayE2 = ArrayReader.readArray(directorio1 + "ArrayE2.txt");

		// ArregloE
		inicio = System.currentTimeMillis();
		int[] resultE = mergeSortedArray(arrayE1, 15000, arrayE2, 19000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado E: "+Arrays.toString(resultE));
		System.out.println("Tiempo de ejecución E: " + (fin-inicio)+" milisegundos.");

		// Declaramos los arreglos.
		int[] arrayF1 = ArrayReader.readArray(directorio1 + "ArrayF1.txt");
		int[] arrayF2 = ArrayReader.readArray(directorio1 + "ArrayF2.txt");

		// ArregloF
		inicio = System.currentTimeMillis();
		int[] resultF = mergeSortedArray(arrayF1, 30000, arrayF2, 25000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado F: "+Arrays.toString(resultF));
		System.out.println("Tiempo de ejecución F: " + (fin-inicio)+" milisegundos.\n");
		
		System.out.println(" ----------- Optimizados ----------- \n");

		// Arreglo A
		inicio = System.currentTimeMillis();
		int[] resultAOP = mergeSortedArrayOP(arrayA1, 500, arrayA2, 700);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado A: "+Arrays.toString(resultAOP));
		System.out.println("Tiempo de ejecución A: " + (fin-inicio)+" milisegundos.");

		// Arreglo B
		inicio = System.currentTimeMillis();
		int[] resultBOP = mergeSortedArrayOP(arrayB1, 2000, arrayB2, 3500);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado B: "+Arrays.toString(resultBOP));
		System.out.println("Tiempo de ejecución B: " + (fin-inicio)+" milisegundos.");

		// Arreglo C
		inicio = System.currentTimeMillis();
		int[] resultCOP = mergeSortedArrayOP(arrayC1, 4000, arrayC2, 4000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado C: "+Arrays.toString(resultCOP));
		System.out.println("Tiempo de ejecución C: " + (fin-inicio)+" milisegundos.");

		// Arreglo D
		inicio = System.currentTimeMillis();
		int[] resultDOP = mergeSortedArrayOP(arrayD1, 7000, arrayD2, 8000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado D: "+Arrays.toString(resultDOP));
		System.out.println("Tiempo de ejecución D: " + (fin-inicio)+" milisegundos.");

		// Arreglo E
		inicio = System.currentTimeMillis();
		int[] resultEOP = mergeSortedArrayOP(arrayE1, 15000, arrayE2, 19000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado E: "+Arrays.toString(resultEOP));
		System.out.println("Tiempo de ejecución E: " + (fin-inicio)+" milisegundos.");

		// Arreglo F
		inicio = System.currentTimeMillis();
		int[] resultFOP = mergeSortedArrayOP(arrayF1, 30000, arrayF2, 25000);
		fin = System.currentTimeMillis();
		// System.out.println("Resultado F: "+Arrays.toString(resultFOP));
		System.out.println("Tiempo de ejecución F: " + (fin-inicio)+" milisegundos.");


		// EJEMPLOS DE ACTIVIDAD 2
		System.out.println("\n\nEJEMPLOS DE ACTIVIDAD 2\n");

		int[][] boardA = ArrayReader.readMatrix(directorio2 + "BoardA.txt");
		boolean boardResultA = isValidBoard(boardA);
		System.out.println("El tablero A es válido: "+boardResultA);

		int[][] boardB = ArrayReader.readMatrix(directorio2 + "BoardB.txt");
		boolean boardResultB = isValidBoard(boardB);
		System.out.println("El tablero B es válido: "+boardResultB);

		int[][] boardC = ArrayReader.readMatrix(directorio2 + "BoardC.txt");
		boolean boardResultC = isValidBoard(boardC);
		System.out.println("El tablero C es válido: "+boardResultC);

		int[][] boardD = ArrayReader.readMatrix(directorio2 + "BoardD.txt");
		boolean boardResultD = isValidBoard(boardD);
		System.out.println("El tablero D es válido: "+boardResultD);


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