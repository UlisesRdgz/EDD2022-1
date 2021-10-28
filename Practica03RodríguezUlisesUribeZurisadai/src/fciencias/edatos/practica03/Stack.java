package fciencias.edatos.practica03;

import java.util.EmptyStackException;

public class Stack<T> implements TDAStack<T> {
	
    private DoubleLinkedList<T> lista = new DoubleLinkedList<>();
    
    /**
	 * Limpia la pila. Elimina todos los elementos.
	 */
	@Override
	public void clear(){
		lista.clear();
	}

	/**
	 * Verifica si la pila está vacía.
	 * @return true si la pila no contiene elementos, false en otro caso.
	 */
	@Override
	public boolean isEmpty(){
		return lista.isEmpty();
	}

	/**
	 * Remueve y regresa el tope de la pila.
	 * @return el tope de la pila.
	 * @throws EmptyStackExpception si la pila está vacía.
	 */
	@Override
	public T pop() throws EmptyStackException{
		if(lista.isEmpty())
			throw new EmptyStackException();

		return lista.remove(0);
	}

	/**
	 * Agrega un nuevo elemento a la pila.
	 * @param e el elemento a agregar.
	 */
	@Override
	public void push(T e){
		lista.add(0, e);
	}

	/**
	 * Regresa el tope de la pila.
	 * @throws EmptyStackExpception si la pila está vacía.
	 */
	@Override
	public T top() throws EmptyStackException{
		if (lista.isEmpty())
			throw new EmptyStackException();

		return lista.get(0);
	}
}
