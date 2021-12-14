package fciencias.edatos.proyecto02;

import java.io.Serializable;
import java.util.Scanner;

/**
* Define las operaciones sobre un árbol
* binario de búsqueda.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 24 Noviembre 2021.
* @since Estructuras de Datos 2022-1. Práctica 05.
*/
public class BinarySearchTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T>, Serializable	{
	
	/**
	 * Nodo para un árbol binario de búsqueda.
	 */
	private class BinaryNode implements Serializable{

		/** Clave. */
		public K key;

		/** Elemento. */
		public T element;

		/** Padre del nodo. */
		public BinaryNode parent;

		/** Hijo izquierdo. */
		public BinaryNode left;

		/** Hijo derecho. */
		public BinaryNode rigth;

		/**
		 * Crea un nuevo nodo.
		 * @param key la clave.
		 * @param element el elemento a almacenar.
		 * @param parent el padre del nodo.
		 */
		public BinaryNode(K key, T element, BinaryNode parent){
			this.key = key;
			this.element = element;
			this.parent = parent;
		}
	}

	/** Root */
	private BinaryNode root;
	
	/**
	* Recupera el objeto con clave k.
	* @param k la clave a buscar.
	* @return el elemento con clave k o null si no existe.
	*/
	@Override
	public T retrieve(K k){
		BinaryNode node = retrieve(root, k);
		if(node == null)
			return null;
		return node.element;
	}

	/**
	 * Método auxiliar de retrieve.
	 * @param actual nodo actual.
	 * @param k la clave a buscar.
	 * @return el elemento.
	 */
	private BinaryNode retrieve(BinaryNode actual, K k){
		// No se encuentra el elemento
		if(actual == null)
			return null;

		// Si encontramos el elemento
		if(actual.key.compareTo(k) == 0)
			return actual;

		// Comparamos los elementos
		if(k.compareTo(actual.key) < 0){ // Verificamos en la izquierda
			return retrieve(actual.left, k);
		} else { // Verificar en la derecha
			return retrieve(actual.rigth, k);
		}
	}

	/**
	* Inserta un nuevo elemento al árbol.
	* @param e el elemento a ingresar.
	* @param k la clave del elemento a ingresar.
	*/
    @Override
	public void insert(T e, K k){
        if (isEmpty()){
        	root = new BinaryNode(k, e, null);
			return;
		}
        insert(root, e, k);
	}
	
	/**
	 * Método auxiliar de insert.
	 * @param actual nodo actual.
	 * @param e elemento a ingresar.
	 * @param k la clave del elemento a ingresar
	 */
	private void insert(BinaryNode actual, T e, K k){

        if(k.compareTo(actual.key) < 0){ // Verificamos en la izquierda
			if (actual.left == null) 
                actual.left = new BinaryNode(k, e, actual);
			else
                insert(actual.left, e, k);
		} else { // Verificar en la derecha
            if (actual.rigth == null)
			actual.rigth = new BinaryNode(k, e, actual);
					else
				insert(actual.rigth, e, k);
				
		}
	}
		
	/**
	* Elimina el nodo con clave k del árbol.
	* @param k la clave que pertene al nodo a eliminar.
	* @return el elemento almacenado en el nodo a eliminar,
	* null si el nodo con clave k no existe.
	*/
    @Override
    public T delete(K k) {
		/** Caso donde el árbol esté vacío. */
		if (isEmpty()) 
			return null;

		BinaryNode actual = retrieve(root, k);
		T aux = actual.element;

		/** Caso donde solo hay un elemento. */
		if (actual == root) {
			root = null;
			return aux;
		}

		/** Caso donde no tiene hijos. */
	    if(actual.left == null && actual.rigth == null) {
			if (actual.parent.left == actual) 
				actual.parent.left = null;
			else
				actual.parent.rigth = null;
	
			return aux;
		}

		/** Caso donde tiene dos hijos */
		if (actual.left != null && actual.rigth != null) {
			BinaryNode maxNode = actual;

			/** Buscamos el máximo de los mínimos */
			do {
				maxNode = maxNode.left;
			} while (maxNode.left != null && maxNode.rigth == null);

			while (maxNode.rigth != null) 
				maxNode = maxNode.rigth;

			/** Actualizamos las referencias */
			swap(actual, maxNode);

			if (maxNode.left != null){
				swap(maxNode, maxNode.left);
				maxNode.left = null;
			} else 
				maxNode.parent.rigth = null;

			return aux;			

		
		/** Caso donde tiene solo un hijo */
		} else {

			if (actual.left != null){
				swap(actual, actual.left);
				actual.left = null;
			}else {
				swap(actual, actual.rigth);
				actual.rigth = null;
			}

			return aux;
		}
    }

	/**
	 * Método auxiliar que cambia la posición del primer 
	 * elemento con el segundo.
	 * @param a nodo a cambiar.
	 * @param b nodo con el que se hace el cambio.
	 */
	private void swap(BinaryNode a, BinaryNode b){
		a.element = b.element;
		a.key = b.key;
	}


	/**
	* Encuentra la clave k con valor o peso mínimo del árbol.
	* @return el elemento con llave de peso mínimo.
	*/
    @Override
    public T findMin() {
		if (isEmpty()) 
			return null;
	
		BinaryNode actual = root;
		
		while (actual.left != null) 
			actual = actual.left;

		return actual.element;
	
    }
			
	/**
	* Encuentra la clave k con valor o peso máximo del árbol.
	* @return el elemento con llave de peso máximo.
	*/
    @Override
    public T findMax() {
        if (isEmpty())
			return null;
			
		BinaryNode actual = root;

        while (actual.rigth != null) 
			actual = actual.rigth;

		return actual.element;
    }

	/** 
	* Recorre el árbol en Preorden.
	*/
    @Override
    public void preorden() {
        preorden(root);
    }

	/**
	 * Método auxiliar de preorden.
	 * @param actual nodo que se irá recorriendo.
	 */
    public void preorden(BinaryNode actual){

		if (actual == null)
			return;

        System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);

        if (actual.left != null)
            preorden(actual.left);

		if (actual.rigth != null)
            preorden(actual.rigth);
    }

	/** 
	* Recorre el árbol en Inorden. 
	*/
    @Override
    public void inorden() {
        inorden(root);
    }

	/**
	 * Método auxiliar de inorden.
	 * @param actual nodo que se irá recorriendo.
	 */
	public void inorden(BinaryNode actual){

		if (actual == null)
			return;

        if (actual.left != null)
            inorden(actual.left);

		System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);

		if (actual.rigth != null)
            inorden(actual.rigth);
    }

	/** 
	* Recorre el árbol en Postorden.
	*/
    @Override
    public void postorden() {
        postorden(root);
    }

	/**
	 * Método auxiliar de postorden.
	 * @param actual nodo que se irá recorriendo.
	 */
	public void postorden(BinaryNode actual){

		if (actual == null)
			return;

        if (actual.left != null)
            postorden(actual.left);

		if (actual.rigth != null)
            postorden(actual.rigth);

		System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);
    }

	/**
	* Verifica si el árbol es vacío.
	* @return true si el árbol es vacío, false en otro caso.
	*/
    @Override
    public boolean isEmpty() {
        if (root == null)
            return true;
        return false;
    }

	/** */
	public boolean isLeaf(K k){
		BinaryNode actual = retrieve(root, k);

		if (actual.left == null && actual.rigth == null) 
			return true;
			
		return false;
	}

}