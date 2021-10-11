package fciencias.edatos.practica02;

import java.util.Iterator;

/**
* Double Linked Lists.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 8.0 Octubre 2021.
* @since Estructuras de datos 2022-1. Prática 2.
*/

public class DoubleLinkedList<T> implements TDAList<T>{

	private class Node{

		private T element;

		private Node next; //siguiente

		private Node prev; //anterior

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
	public void add(int i, T e) throws IndexOutOfBoundsException{
		if(i<0 || i>size()){
			throw new IndexOutOfBoundsException("Índice fuera del rango");
		}

		Node nuevo = new Node(e);

		// Si la lista esta vacia.
		if (isEmpty()) {
			head = nuevo;
			tail = nuevo;
			size++;
			return;
		}

		// Si se agrega al inicio
		if (i == 0) {
			nuevo.setNext(head);
			head.setPrev(nuevo);
			head = nuevo;
			size++;
			return;
		}

		// Si se agrega al final
		if (i == size()) {
			nuevo.setPrev(tail);
			tail.setNext(nuevo);
			tail = nuevo;
			size++;
			return;
		}

		if (i <= size()/2) {
			Node iterador = head;
			for (int j = 0; j < i-1; j++) 
				iterador = iterador.getNext();

			nuevo.setNext(iterador.getNext());
			nuevo.setPrev(iterador);
			iterador.getNext().setPrev(nuevo);
			iterador.setNext(nuevo);
			size++;
			return;

		}else{
			Node iterador = tail;
			for(int j = size(); j>i+1; j--)
				iterador = iterador.getPrev();

			nuevo.setNext(iterador);
			nuevo.setPrev(iterador.getPrev());
			iterador.getPrev().setNext(nuevo);
			iterador.setPrev(nuevo);
			size++;
			return;
		}
	}


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
	 * @return true si el elemento está contenido, false en otro caso.
	 */
    @Override 
	public boolean contains(T e){
		
		Node iterador1 = head;
		Node iterador2 = tail;

		for (int j = 1, k = size(); j <= size()/2 || k-1>size()/2; j++, k--){
			if (iterador1.getElement() == e || iterador2.getElement() == e)
				return true;
			
			iterador1 = iterador1.getNext();
			iterador2 = iterador2.getPrev();
		}

		return false;
	}

	/**
	 * Obtiene el elemento en la posición <i>i</i>.
	 * @param i el índice a obtener elemento.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
    @Override 
	public T get(int i) throws IndexOutOfBoundsException{
		return null;
	}

	/**
	 * Verifica si la lista está vacía.
	 * @return true si la lista no contiene elementos, false en otro caso.
	 */
    @Override 
	public boolean isEmpty(){
		if (size() == 0) 
			return true;
		return false;
	}


	/**
	 * Elimina el elemento en la posición <i>i</i>.
	 * @param i el índice del elemento a eliminar.
	 * @return el elemento eliminado.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
    @Override 
	public T remove(int i) throws IndexOutOfBoundsException{
		if(i<0 || i>=size()){
			throw new IndexOutOfBoundsException("Índice fuera del rango");
		}
	
		T element;
		if (size() == 1) {
			element = head.getElement();
			clear();
			return element;
		}
		if (i == 0) {
			element = head.getElement();
			head = head.getNext();
			head.setPrev(null);
			size--;
			return element;
		} 
		if (i== size()-1) {
			element = tail.getElement();
			tail = tail.getPrev();
			tail.setNext(null);
			size--;
			return element;
		}
		if (i < size()/2) {
			Node iterador = head;
			for (int j = 0; j < i; j++) 
				iterador = iterador.getNext();

			element = iterador.getElement();
			iterador.getNext().setPrev(iterador.getPrev());
			iterador.getPrev().setNext(iterador.getNext());
			size--;
			return element;

		}else{
			Node iterador = tail;
			for(int j = size(); j>i+1; j--)
				iterador = iterador.getPrev();

			element = iterador.getElement();
			iterador.getPrev().setNext(iterador.getNext());
			iterador.getNext().setPrev(iterador.getPrev());
			size--;			
			return element;
		}
	} 

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
	public void revert(){

	}

	/**
	 * Da la mitad derecha o izquierda de una lista.
	 * @param side la mitad que recortar de la lista original.
	 * true - mitad derecha.
	 * false - mitad izquierda.
	 * @return una nueva lista con la mitad de los elementos.
	 */
    @Override 
	public TDAList cut(boolean side){
		return null;
	}

	/**
	 * Da una cadena con los elementos contenidos en la lista.
	 * @return una representación de la lista.
	 */
    @Override 
	public String toString(){
		String formato = "";

        Node iterador = head;
        while(iterador != null){
            formato += iterador.getElement() + "\n";
            iterador = iterador.getNext();
        }

        return formato;
	}

	/**
	 * Da un iterador para la lista.
	 * @return un iterador para la estructura.
	 */
    @Override 
	public Iterator listIterador(){
		return null;
	}
}
