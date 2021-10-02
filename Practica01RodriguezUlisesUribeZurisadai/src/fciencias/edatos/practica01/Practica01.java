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

		for (int i = 0; i < length; i++) {
		int[] aux = new int[length];
		
			for (int j = 0; j < length; j++) {
				aux[board[i][j]] += 1;
				aux[board[j][i]] += 1;
			}

			for (int j = 0; j < aux.length; j++) {
				if(aux[j] > 2 || aux[j] < 2){
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

	/**
	 * Método optimizado
	 * Rota un arreglo hacia la izquierda dependiendo del número de 
	 * posiciones dado.  
	 * @param num el arreglo de enteros a rotar.
	 * @param position la cantidad de espacios a rotar.
	 */
	public static void rotateArrayOP(int[] num, int position){
		int longitud = num.length;
		int aux[] = new int[longitud];
		int p = 0; // p - lugar donde se guardará el nuevo valor
		int a = 0; // a - variable auxiliar

		for (int i = 0; i < longitud; i++) {
			// Si el arreglo se rota 0 veces rompemos el ciclo.
			if(position==0)
				break;

			// Se asignan los mismos valores de num al arreglo auxiliar.
			if(a < longitud){
				aux[i] = num[i];
				a++;
			}
			if(a == longitud){
				a++;
				i=0;
			}

			// Asignamos los valores en la posición rotada.
			if(a>longitud){
				p = (i + longitud) - position;
				if (p >= longitud || p < 0) 
					p = (p % longitud + longitud)% longitud;
				num[p] = aux[i];
			}
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
		System.out.println("\n\nEJEMPLOS DE ACTIVIDAD 2\n");

		// BoardA
		int[][] boardA = ArrayReader.readMatrix(directorio2 + "BoardA.txt");
		long inicio = System.currentTimeMillis();
		boolean boardResultA = isValidBoard(boardA);
		long fin = System.currentTimeMillis();

		System.out.println("El tablero A es válido: "+boardResultA);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardB
		int[][] boardB = ArrayReader.readMatrix(directorio2 + "BoardB.txt");
		inicio = System.currentTimeMillis();
		boolean boardResultB = isValidBoard(boardB);
		fin = System.currentTimeMillis();

		System.out.println("El tablero B  es válido: "+boardResultB);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");
		
		// BoardC
		int[][] boardC = ArrayReader.readMatrix(directorio2 + "BoardC.txt");
		inicio = System.currentTimeMillis();
		boolean boardResultC = isValidBoard(boardC);
		fin = System.currentTimeMillis();

		System.out.println("El tablero C es válido: "+boardResultC);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardD
		int[][] boardD = ArrayReader.readMatrix(directorio2 + "BoardD.txt");
		inicio = System.currentTimeMillis();
		boolean boardResultD = isValidBoard(boardD);
		fin = System.currentTimeMillis();

		System.out.println("El tablero D es válido: "+boardResultD);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardE 
		int[][] boardE = ArrayReader.readMatrix(directorio2 + "BoardE.txt");
		inicio = System.currentTimeMillis();
		boolean boardResultE = isValidBoard(boardE);
		fin = System.currentTimeMillis();
		System.out.println("El tablero E es válido: "+boardResultE);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardF
		 int[][] boardF = ArrayReader.readMatrix(directorio2 + "BoardF.txt");
		// inicio = System.currentTimeMillis();
		// boolean boardResultF = isValidBoard(boardF);
		// fin = System.currentTimeMillis();
		// System.out.println("El tablero F es válido: "+boardResultF);
		// System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.\n");

		System.out.println("\n ----------- Optimizado ----------- ");
		
		// BoardA optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultAOP = isValidBoardOP(boardA);
		fin = System.currentTimeMillis();
		System.out.println("El tablero A es válido: "+boardResultAOP);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardB optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultBOP = isValidBoardOP(boardB);
		fin = System.currentTimeMillis();
		System.out.println("El tablero B es válido: "+boardResultBOP);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardC optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultCOP = isValidBoardOP(boardC);
		fin = System.currentTimeMillis();
		System.out.println("El tablero C es válido: "+boardResultCOP);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardD optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultDOP = isValidBoardOP(boardD);
		fin = System.currentTimeMillis();
		System.out.println("El tablero D es válido: "+boardResultDOP);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardE optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultEOP = isValidBoardOP(boardE);
		fin = System.currentTimeMillis();
		System.out.println("El tablero E es válido: "+boardResultEOP);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// BoardF optimizado
		inicio = System.currentTimeMillis();
		boolean boardResultFOP = isValidBoardOP(boardF);
		fin = System.currentTimeMillis();
		System.out.println("El tablero F es válido: "+boardResultFOP);
		System.out.println("Tiempo de ejecución: " + (fin-inicio)+" milisegundos.");

		// EJEMPLOS DE ACTIVIDAD 3
<<<<<<< HEAD
		System.out.println("\n\nEJEMPLOS DE ACTIVIDAD 3\n")

		// Arreglo A1 rotado 500 veces.
		long inicio = System.currentTimeMillis();
		rotateArray(arrayA1, 500);
		long fin = System.currentTimeMillis();
		// System.out.println("Arreglo A1 rotado 500 veces: " + Arrays.toString(arrayA1));
		System.out.println("Tiempo de ejecución A1: " + (fin-inicio)+" milisegundos.");

		// Arreglo B1 rotado 1000 veces.
		inicio = System.currentTimeMillis();
		rotateArray(arrayB1, 1000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo B1 rotado 1000 veces: " + Arrays.toString(arrayB1));
		System.out.println("Tiempo de ejecución B1: " + (fin-inicio)+" milisegundos.");
		
		// Arreglo C1 rotado 2000 veces.
		inicio = System.currentTimeMillis();
		rotateArray(arrayC1, 2000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo C1 rotado 2000 veces: " + Arrays.toString(arrayC1));
		System.out.println("Tiempo de ejecución C1: " + (fin-inicio)+" milisegundos.");

		// Arreglo D1 rotado 3000 veces.
		inicio = System.currentTimeMillis();
		rotateArray(arrayD1, 3000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo D1 rotado 3000 veces: " + Arrays.toString(arrayD1));
		System.out.println("Tiempo de ejecución D1: " + (fin-inicio)+" milisegundos.");

		// Arreglo E1 rotado 10000 veces.
		inicio = System.currentTimeMillis();
		rotateArray(arrayE1, 10000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo E1 rotado 10000 veces: " + Arrays.toString(arrayE1));
		System.out.println("Tiempo de ejecución E1: " + (fin-inicio)+" milisegundos.");

		// Arreglo E1 rotado 20000 veces.
		inicio = System.currentTimeMillis();
		rotateArray(arrayF1, 20000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo F1 rotado 20000 veces: " + Arrays.toString(arrayF1));
		System.out.println("Tiempo de ejecución F1: " + (fin-inicio)+" milisegundos.\n");

		System.out.println(" ----------- Optimizados -----------  \n");

		// Arreglo A1 optimizado rotado 500 veces.
		inicio = System.currentTimeMillis();
		rotateArrayOP(arrayA1, 500);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo A1 rotado 500 veces: " + Arrays.toString(arrayA1));
		System.out.println("Tiempo de ejecución A1: " + (fin-inicio)+" milisegundos.");

		// Arreglo B1 optimizado rotado 1000 veces.
		inicio = System.currentTimeMillis();
		rotateArrayOP(arrayB1, 1000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo B1 rotado 1000 veces: " + Arrays.toString(arrayB1));
		System.out.println("Tiempo de ejecución B1: " + (fin-inicio)+" milisegundos.");

		// Arreglo C1 optimizado rotado 2000 veces.
		inicio = System.currentTimeMillis();
		rotateArrayOP(arrayC1, 2000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo C1 rotado 2000 veces: " + Arrays.toString(arrayC1));
		System.out.println("Tiempo de ejecución C1: " + (fin-inicio)+" milisegundos.");

		// Arreglo D1 optimizado rotado 3000 veces.
		inicio = System.currentTimeMillis();
		rotateArrayOP(arrayD1, 3000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo D1 rotado 3000 veces: " + Arrays.toString(arrayD1));
		System.out.println("Tiempo de ejecución D1: " + (fin-inicio)+" milisegundos.");
=======
		System.out.println("\n\nEJEMPLOS DE ACTIVIDAD 3\n");
>>>>>>> metodo2

		// Arreglo E1 optimizado rotado 10000 veces.
		inicio = System.currentTimeMillis();
		rotateArrayOP(arrayE1, 10000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo E1 rotado 10000 veces: " + Arrays.toString(arrayE1));
		System.out.println("Tiempo de ejecución E1: " + (fin-inicio)+" milisegundos.");

		// Arreglo F1 optimizado rotado 20000 veces.
		inicio = System.currentTimeMillis();
		rotateArrayOP(arrayF1, 20000);
		fin = System.currentTimeMillis();
		// System.out.println("Arreglo F1 rotado 20000 veces: " + Arrays.toString(arrayF1));
		System.out.println("Tiempo de ejecución F1: " + (fin-inicio)+" milisegundos.");

		System.out.println("\n\nFIN DE EJEMPLOS\n");
	}
}