package fciencias.edatos.practica06;

/**
* Define las operaciones sobre un árbol AVL.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 19 Diciembre 2021.
* @since Estructuras de Datos 2022-1. Práctica 06.
*/
public class AVLTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T>{

    /**
	 * Nodo de un arbol AVL.
	 */
	public class AVLNode{

		/** Altura del nodo. */
		public int altura;

		/** Hijo izquierdo. */
		public AVLNode left;

		/** Hijo derecho. */
		public AVLNode rigth;

		/** Padre del nodo. */
		public AVLNode parent;

		/** Elemento almacenado en el nodo. */
		public T element;

		/** Clave del nodo. */
		public K key;

		/**
		 * Crea un nuevo nodo AVL
		 * @param element el elemento a almacenar.
		 * @param key la clave del nodo.
		 * @param padre el padre del nodo
		 */
		public AVLNode(K key, T element, AVLNode parent){
			this.element = element;
			this.key = key;
			this.parent = parent;
			this.altura = this.getAltura();
		}

		/**
		 * Calcula la altura del nodo.
		 */
		public int getAltura(){
			// Si este nodo es hoja
			if(left == null && rigth==null){
				return 0;
			} else if(left != null && rigth != null){ // Dos hijos
				int max = left.getAltura() > rigth.getAltura() ? left.getAltura() : rigth.getAltura();
				return 1 + max;
			} else{ // Tiene solo un hijo
				boolean hasLeft = left!=null;
				return 1 + (hasLeft ? left.getAltura() : rigth.getAltura());
			}
		}

		/**
		 * Actualiza la altura del nodo.
		 */
		public void actualizaAltura(){
			this.altura = this.getAltura();
		}
	}

    /** Raíz */
    private AVLNode root;

	/**
	* Recupera el objeto con clave k.
	* @param k la clave a buscar.
	* @return el elemento con clave k o null si no existe.
	*/
    @Override
    public T retrieve(K k) {
        AVLNode node = retrieve(k, root);
		if(node == null)
			return null;
		return node.element;
    }

    /**
	 * Obtenia el nodo con una clave específica.
	 * @param k la clave a buscar
	 * @param actual el nodo actual
	 * @return el nodo con clave k o null si no existe.
	 */
	private AVLNode retrieve(K k, AVLNode actual){
		// No se encuentra el elemento.
		if(actual == null)
			return null;

		/*********** Posible error */
		int compare = k.compareTo(actual.key);

		// Si encontramos el elemento
		if(compare == 0)
			return actual;

		// Comparamos los elementos
		if(k.compareTo(actual.key) < 0) // Verificamos en la izquierda
			return retrieve(k, actual.left);
		else // Verificar en la derecha
			return retrieve(k, actual.rigth);
	}

	/**
	* Inserta un nuevo elemento al árbol.
	* @param e el elemento a ingresar.
	* @param k la clave del elemento a ingresar.
	*/
    @Override
    public void insert(T e, K k) {
        if (isEmpty()){
        	root = new AVLNode(k, e, null);
			return;
		}

        AVLNode v = insert(k, e, root);

		// Rebalancear a partir de v hasta raiz
		//rebalancea(v);
    }

	/**
	 * Inserta un nodo de forma recursiva.
	 * @param e el elemento a insertar
	 * @param k es la clave del nodo a insertar
	 * @param actual el nodo actual
	 * @return 
	 */
	public AVLNode insert(K k, T e, AVLNode actual){
		if(k.compareTo(actual.key) < 0){ // Verificamos en la izquierda
			if (actual.left == null){
        		actual.left = new AVLNode(k, e, actual);
				return actual.left;

			} else
				return insert(k, e, actual.left);

		} else { // Verificar en la derecha
            if (actual.rigth == null){
				actual.rigth = new AVLNode(k, e, actual);
				return actual.rigth;

			} else
				return insert(k, e, actual.rigth);	
		}
	}

	/**
	* Elimina el nodo con clave k del árbol.
	* @param k la clave perteneciente al nodo a eliminar.
	* @return el elemento almacenado en el nodo a eliminar.
	* null si el nodo con clave k no existe.
	*/
    @Override
    public T delete(K k) {
        AVLNode v = retrieve(k, root);

		// El elemento que queremos eliminar no está en el árbol
		if(v == null)
			return null;

		T eliminado = v.element;

		// Eliminar con auxiliar
		AVLNode w = delete(v);

		// Rebalancear
		//rebalancea(w);

		return eliminado;
    }

	private AVLNode delete(AVLNode actual){

		/** Caso donde tiene dos hijos. */
		if (actual.left != null && actual.rigth != null) {
			AVLNode mayor = findMax(actual.left);
			swap(mayor, actual);
			return delete(mayor);

		/** Caso donde no tiene hijos. */
		} else if (actual.left == null && actual.rigth == null) {
			if (actual.parent.left == actual) 
				actual.parent.left = null;
			else
				actual.parent.rigth = null;

			return actual.parent;

		/** Caso donde tiene solo un hijo. */
		} else {
			if(actual.left != null){
				swap(actual, actual.left);
				return delete(actual.left);
			} else{
				swap(actual, actual.rigth);
				return delete(actual.rigth);
			}
		}

	}

	/**
	 * Método auxiliar que intercambia la posición del 
	 * primer nodo con el segundo.
	 * @param v primer nodo a cambiar.
	 * @param w segundo nodo a cambiar.
	 */
	private void swap(AVLNode v, AVLNode w){
		T value = v.element;
		K llave = v.key;
		v.element = w.element;
		v.key = w.key;
		w.element = value;
		w.key = llave;
	}

	/**
	* Encuentra la clave k con valor o peso mínimo del árbol.
	* @return el elemento con llave de peso mínimo.
	*/
    @Override
    public T findMin() {
        if (isEmpty()) 
			return null;
	
		AVLNode actual = root;
		
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
			
		AVLNode actual = root;

        while (actual.rigth != null) 
			actual = actual.rigth;

		return actual.element;
    }

	/**
	 * Encuentra la clave k con valor o peso máximo del árbol
	 * empezando desde el nodo dado.
	 * @return el nodo con llave de peso máximo.
	 */
	private AVLNode findMax(AVLNode actual){
		if (isEmpty())
			return null;

        while (actual.rigth != null) 
			actual = actual.rigth;

		return actual;
	}

	/**
	 * Recorre el árbol en preorden.
	 */
    @Override
    public void preorden() {
		preorden(root);
    }

	/**
	 * Método auxiliar de preorden.
	 * @param actual nodo que se irá recorriendo.
	 */
    public void preorden(AVLNode actual){

		if (actual == null)
			return;

        System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);

		/***** Posible error */
        preorden(actual.left);
        preorden(actual.rigth);
    }

	/**
	 * Recorre el árbol en inorden.
	 */
    @Override
    public void inorden() {
		inorden(root);
    }

	/**
	 * Método auxiliar de inorden.
	 * @param actual nodo que se irá recorriendo.
	 */
	public void inorden(AVLNode actual){

		if (actual == null)
			return;

        inorden(actual.left);

		System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);

        inorden(actual.rigth);
    }

	/**
	 * Recorre el árbol en postorden.
	 */
    @Override
    public void postorden() {
        postorden(root);
    }

	/**
	 * Método auxiliar de postorden.
	 * @param actual nodo que se irá recorriendo.
	 */
	public void postorden(AVLNode actual){

		if (actual == null)
			return;

        postorden(actual.left);
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

	public static void main(String[] args) {
		AVLTree<Integer, Integer> arbol = new AVLTree<>();

		arbol.insert(9, 9);
		arbol.insert(12, 12);
		arbol.insert(3, 3);
		arbol.insert(4, 4);
		arbol.insert(2, 2);
		arbol.insert(5, 5);
		arbol.insert(1, 1);
		arbol.insert(11, 11);
		arbol.insert(14, 14);
		arbol.insert(15, 15);

		arbol.delete(9);
		arbol.delete(12);
		arbol.delete(5);

		arbol.postorden();
	}
}
