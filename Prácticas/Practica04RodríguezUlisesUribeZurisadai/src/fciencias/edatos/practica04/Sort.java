package fciencias.edatos.practica04;

import java.util.Arrays;
import java.util.Random;

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
     * @param arr arreglo que cambia la posición de elementos dados.
     * @param i índice del primer elemento 
     * @param j índice del segundo elemento
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

	/**
	 * Subdivide el arreglo en subarreglos más pequeños y los ordena. 
	 * @param arr el arrelgo a ordenar.
	 */
    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }
        
	/**
	 * Auxiliar de quickSort para dividir y ordenar.
	 * @param arr el arreglo con los elementos a dividir y ordenar.
	 * @param lo el índice de inicio a modificación.
	 * @param hi el índice del último elemento.
	 */
    public static void quickSort(int[] arr, int lo, int hi) {
        if(hi <= lo)
            return;
        int j = partition(arr, lo, hi);
        quickSort(arr, lo, j-1);
        quickSort(arr, j+1, hi);
    }

	/**
	 * Método para dividir el arreglo. 
	 * @param arr el arreglo con los elementos a modificar.
	 * @param lo el inicio de la primera mitad.
	 * @param hi el índice del último elemento.
	 * @return el número ordenado.
	 */
    public static int partition (int arr[], int lo, int hi){
        int i = lo;
        int j = hi+1;
        int piv = arr[lo];

        while (true) {
            while (arr[++i] < piv){
                if (i == hi)
                    break;
            }
            while(piv < arr[--j]){
                if (j == lo)
                    break;
            }
            if(i >= j)
                break;

            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

	/**
	 * Ordena un arreglo de forma ascendente con merge sort.
	 * @param arr el arreglo a ordenar.
	 */
	public static void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length-1);
	}

	/**
	 * Auxiliar de mergeSort para dividir y mezclar.
	 * @param arr el arreglo con los elementos a dividir y mezclar.
	 * @param lo el índice de inicio a modificación.
	 * @param hi el índice del último elemento a modificación.
	 */
	private static void mergeSort(int[] arr, int lo, int hi){
		// Cuando ya esta ordenado el fragmento de lo hasta hi
		if(hi <= lo)
			return;

		// La mitad del corte del arreglo
		int mid = lo + (hi-lo) / 2;

		mergeSort(arr, lo, mid);
		mergeSort(arr, mid+1, hi);

		merge(arr, lo, mid, hi);
	}

	/**
	 * Mezcla dos arreglos, ordenando de menor a mayor.
	 * @param arr el arreglo con los elementos a modificar.
	 * @param lo el inicio de la primera mitad.
	 * @param mid el índice de la mitad del subarreglo.
	 * @param hi el índice del último elemento.
	 */
	private static void merge(int[] arr, int lo, int mid, int hi){
		int i = 0, j = 0;
        //Creamos dos arreglos auxiliares
        int aux1[] = new int[mid - lo + 1];
        int aux2[] = new int[hi - mid];
		
        for (int k = 0; k < aux1.length; k++)
            aux1[k] = arr[lo + k];
        for (int k = 0; k < aux2.length; k++)
            aux2[k] = arr[mid + k + 1];

		for(int k = lo ; k <= hi; k++){
			if (i < aux1.length && j < aux2.length) {
				if (aux1[i] < aux2[j])
					arr[k] = aux1[i++];
				else 
					arr[k] = aux2[j++];
			}
			else if(i < aux1.length)
				arr[k] = aux1[i++];
			else 
				arr[k] = aux2[j++];
		}
	}

    /**
	 * Verifica si un elemento está contenido en un arreglo, y si 
	 * no encontramos el elemento entonces -1.
	 * @param arr el arreglo donde buscar.
	 * @param e el elemento a buscar.
	 */
	public static int find(int[] arr, int e){
		return find(arr, e, 0, arr.length-1);
	}
	
	/**
	 * Regresa la posición de un elemento en un arreglo.
	 * @param arr el arreglo donde buscar.
	 * @param elem el elemento a buscar.
	 * @param lo el inicio de la primera mitad.
	 * @param mid el índice de la mitad del subarreglo.
	 * @param hi el índice del último elemento.
	 * @return la posición del el elemento
	*/
	private static int find(int[] arr, int elem, int lo, int hi){
		if(lo>hi)
			return -1;
	
		int mid = lo + ((hi-lo)/2);
	
		if(arr[mid] == elem)
			return mid;
	
		if(arr[mid] > elem)
			return find(arr, elem, lo, mid-1);
		else
			return find(arr, elem, mid+1, hi);
	}
	
	/**
	 * Crea un nuevo arreglo con números pseudoaleatorios.
	 * @param n el tamaño del arreglo a crear.
	 * @param max el mayor elemento a generar en el arreglo.
	 * @return un arreglo de tamaño n con números pseudoaleatorios de 0 a 19.
	 */
	public static int[] generate(int n, int max){
		int[] res = new int[n];
		Random rn = new Random();
		for(int i = 0 ; i < n; i++)
			res[i] = rn.nextInt(max);
		return res;
	}

    public static void main(String[] args) {
		int[] arr1 = generate(10000, 10);
		int[] arr2 = Arrays.copyOf(arr1, arr1.length);


		// System.out.println("No ordenado: " + Arrays.toString(arr1));
		double inicio = System.currentTimeMillis();
		quickSort(arr1);
		find(arr1, 2);
		double fin = System.currentTimeMillis();
		// System.out.println("Ordenado: " + Arrays.toString(arr1) + "\n");
		System.out.println("Ordenado con quicksort tardó: " + (fin - inicio) + " milisegundos");

		// System.out.println("No ordenado: " + Arrays.toString(arr2));
		inicio = System.currentTimeMillis();
		mergeSort(arr2);
		fin = System.currentTimeMillis();
		// System.out.println("Ordenado: " + Arrays.toString(arr2) + "\n");
		System.out.println("Ordenado con mergeSort tardó: " + (fin - inicio) + " milisegundos");

		// System.out.println("Find: " + find(arr1, 2));
	}
}