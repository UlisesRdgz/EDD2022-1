package fciencias.edatos.practica03;

/**
* Queue.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 2.0 Noviembre 2021.
* @since Estructuras de datos 2022-1. Prática 3.
*/

public class Queue<T> implements TDAQueue<T> {
    
    private DoubleLinkedList<T> lista = new DoubleLinkedList<>();

    /**
	 * Limpia la cola. Elimina todos los elementos.
	 */
	public void clear(){
        lista.clear();
    }

	/**
	 * Remueve y regresa el siguiente elemento en la cola.
	 * @return el siguiente en la cola o null si es vacía.
	 */
	public T dequeue(){
        if(lista.isEmpty())
            return null;

        return lista.remove(0);    
    }

	/**
	 * Agrega un nuevo al final de la cola.
	 * @param e el elemento a agregar.
	 */
	public void enqueue(T e){
        lista.add(lista.size(), e);        
    }

	/**
	 * Devuelve el elemento siguiente en la cola, sin eliminarlo.
	 * @return el siguiente elemento en la cola o null si es vacía.
	 */
	public T first(){
        if (lista.isEmpty()) 
            return null;
            
        return lista.get(0);
    }

	/**
	 * Verifica si la cola está vacía.
	 * @return true si la cola no contiene elementos, false en otro caso.
	 */
	public boolean isEmpty(){
        return lista.isEmpty();
    }
}