package fciencias.edatos.proyecto03;

import java.util.Random;

/**
* Implementación básica de un HashMap.
* @author Emmanuel Cruz Hernández.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai.
* @version 2.0 Enero 2022. Anterior 1.0 Enero 2021.
* @since Estructuras de Datos 2022-1.
*/
public class HashMap<K,V> implements TDAMap<K,V>{

	/** Arreglo de elementos. */
	private V[] table;

	/** Capacidad de la tabla. */
	private int capacity;

	/** Factor primo para calcular longitudes. */
	private int prime;

	/** Cantidad del cambio y escala. */
	private long scale, shift;

	/** Número de elementos */
	private int size;

	/**
	* Crea un nuevo HashMap. 
	* @param cap la capacidad de la tabla.
	* @param p el factor primo.
	*/
	public HashMap(int cap, int p){
		table = (V[]) new Object[cap];
		prime = p;
		capacity = cap;
		Random rn = new Random();
		scale = rn.nextInt(prime-1) + 1;
		shift = rn.nextInt(prime);
	}

	/**
	* Crea un nuevo HashMap.
	* @param cap la capacidad de la tabla.
	*/
	public HashMap(int cap){
		this(cap, 109345121);
	}

	/**
	* Regresa la cantidad de elementos contenidos en el mapa.
	* @return la cantidad de elementos contenidos.
	*/
	@Override
	public int size(){
		return size;
	}

	/**
	* Obtiene el elemento con clave k en el mapa.
	* @param key la clave asignada a un elemento para obtener.
	* @return el elemento con clave key.
	*/
	@Override
	public V get(K key){
		int pos = hashFuction(key);
		return table[pos];
	}

	/**
	* Agrega un nuevo elemento al mapa.
	* @param key la clave del elemento a agregar.
	* @param value el elemento a agregar.
	* @return el elemento antiguo almacenado con clave key o null si no existe.
	*/
	@Override
	public V put(K key, V value){
		int pos = hashFuction(key);
		V oldValue = table[pos];
		// if (oldValue != null)
		// 	System.out.println("\nEsta repetido\n");
		table[pos] = value;
		size++;
		return oldValue;
	}

	/**
	* Elimina el elemento con clave key.
	* @param key la clave del elemento a remover.
	* @return el elemento con clave key eliminado o null si no existe.
	*/
	@Override
	public V remove(K key){
		int pos = hashFuction(key);
		V oldValue = table[pos];
		table[pos] = null;
		size--;
		return oldValue;
	}

	/**
	* Verifica si el mapa es vacio.
	* @return true si es vacio, false en otro caso.
	*/
	@Override
	public boolean isEmpty(){
		if (size == 0) 
			return true;
		return false;
	}

	/**
	 * Funcion hash
	 * @param k la clave
	 * @return un entero asociado a la clave dentro de un rango válido
	 */
	private int hashFuction(K k){
		int hashCode = (int) (Math.abs(k.hashCode() * scale + shift) % prime);
		return hashCode % capacity;
	}

}
