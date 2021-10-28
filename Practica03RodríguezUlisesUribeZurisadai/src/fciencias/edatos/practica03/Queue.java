package fciencias.edatos.practica03;

public class Queue<T> implements TDAQueue<T> {
    
    private DoubleLinkedList<T> queue = new DoubleLinkedList<>();

    /**
	 * Limpia la cola. Elimina todos los elementos.
	 */
	public void clear(){
        queue.clear();
    }

	/**
	 * Remueve y regresa el siguiente elemento en la cola.
	 * @return el siguiente en la cola o null si es vacía.
	 */
	public T dequeue(){
        if(queue.isEmpty())
            return null;

        return queue.remove(0);    
    }

	/**
	 * Agrega un nuevo al final de la cola.
	 * @param e el elemento a agregar.
	 */
	public void enqueue(T e){
        queue.add(queue.size(), e);        
    }

	/**
	 * Devuelve el elemento siguiente en la cola, sin eliminarlo.
	 * @return el siguiente elemento en la cola o null si es vacía.
	 */
	public T first(){
        return queue.get(0);
    }

	/**
	 * Verifica si la cola está vacía.
	 * @return true si la cola no contiene elementos, false en otro caso.
	 */
	public boolean isEmpty(){
        return queue.isEmpty();
    }
}