package fciencias.edatos.practica02;

import fciencias.edatos.practica01.TDAList;

/**
* Double Linked Lists.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 8.0 Octubre 2021.
* @since Estructuras de datos 2022-1. Prática 2.
*/

public class DoubleLinkedList<T> implements TDAList<T>{

	private class Node{

		public T element;

		public Node next; //siguiente

		public Node prev; //anterior

		/**
		 * Crea un nuevo nodo
		 * @param element elemento que almacena el nodo 
		 */
		public Node(T element){
			this.element = element;
		} 

		/**
		 * Permite cambiar al siguiente nodo
		 * @param newNode
		 */
		public void setNext(Node newNode){
			this.next = newNode;
		}

		/**
		 * Permite cambiar al nodo anterior
		 * @param newNode
		 */
		public void setPrev(Node newNode){
			this.prev = newNode;
		}

		/**
		 * Accede a la información del nodo
		 * @return element 
		 */
		public T getElement(){
			return element;
		}

		/**
		 * Accede al nodo siguiente
		 * @return next
		 */
		public Node getNext(){
			return next;
		}

		/**
		 * Accede al nodo anterior
		 * @return prev
		 */
		public Node getPrev(){
			return prev;
		}

		
	}
	/** Cabeza de la lista */
	private Node head;

	/** Cola de la lista */
	private Node tail;

	/** Longitud de la lista */
	private int size;
	
	/**
	 * Inserta un nuevo elemento <i>e</i> en la posición <i>i</i>.
	 * @param i la posición donde agregar el elemento.
	 * @param e el elemento a insertar.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
    @Override 
	public void add(int i, T e) throws IndexOutOfBoundsException;


	/**
	 * Limpia la lista. Elimina todos los elementos.
	 */
    @Override 
	public void clear(){
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Verifica si un elemento está contenido en la lista.
	 * @param e el elemento a verificar si está contenido.
	 * @return true si el elemento está contenid, false en otro caso.
	 */
    @Override 
	public boolean contains(T e);

	/**
	 * Obtiene el elemento en la posición <i>i</i>.
	 * @param i el índice a obtener elemento.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
    @Override 
	public T get(int i) throws IndexOutOfBoundsException;

	/**
	 * Verifica si la lista está vacía.
	 * @return true si la lista no contiene elementos, false en otro caso.
	 */
    @Override 
	public boolean isEmpty(){
		if (size != 0) 
			return false;
		return true;
	}


	/**
	 * Elimina el elemento en la posición <i>i</i>.
	 * @param i el índice del elemento a eliminar.
	 * @return el elemento eliminado.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
    @Override 
	public T remove(int i) throws IndexOutOfBoundsException;

	/**
	 * Regresa la cantidad de elementos contenidos en la lista.
	 * @return la cantidad de elementos contenidos.
	 */
    @Override 
	public int size(){
		return size;
	}

	/**
	 * Revierte los elementos de la lista. Esto es, da la reversa de la lista.
	 */
    @Override 
	public void revert();

	/**
	 * Da la mitad derecha o izquierda de una lista.
	 * @param side la mitad que recortar de la lista original.
	 * true - mitad derecha.
	 * false - mitad izquierda.
	 * @return una nueva lista con la mitad de los elementos.
	 */
    @Override 
	public TDAList cut(boolean side);

	/**
	 * Da una cadena con los elementos contenidos en la lista.
	 * @return una representación de la lista.
	 */
    @Override 
	public String toString();

	/**
	 * Da un iterador para la lista.
	 * @return un iterador para la estructura.
	 */
    @Override 
	public Iterator listIterador();

}
