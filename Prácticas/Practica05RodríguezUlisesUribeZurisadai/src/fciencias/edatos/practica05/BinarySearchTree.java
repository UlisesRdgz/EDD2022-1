package fciencias.edatos.practica05;

public class BinarySearchTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T>	{
	
	/**
	 * Nodo para un árbol binario de búsqueda.
	 */
	public class BinaryNode{

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

	@Override
	public T retrieve(K k){
		BinaryNode node = retrieve(root, k);
		if(node == null)
			return null;
		return node.element;
	}

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

    @Override
	public void insert(T e, K k){
        if (isEmpty()){
        	root = new BinaryNode(k, e, null);
			return;
		}
        insert(root, e, k);
	}
	
	private void insert(BinaryNode actual, T e, K k){

        if(k.compareTo(actual.key) < 0){ // Verificamos en la izquierda
			if (actual.left == null) 
                actual.left = new BinaryNode(k, e, actual);
			else
                insert(actual.left, e, k);
		} else { // Verificar en la derecha
            if (actual.rigth != null)
                insert(actual.rigth, e, k);
			else
				actual.rigth = new BinaryNode(k, e, actual);
		}
	}

    @Override
    public T delete(K k) {
		return null;
    }


	/**
	* Encuentra la clave k con valor o peso mínimo del árbol.
	* @return el elemento con llave de peso mínimo.
	*/
    @Override
    public T findMin() {
		if (isEmpty()) {
			return null;
		}
		BinaryNode actual = root;
		
		while (actual.left != null) {
			actual = actual.left;
		}
		return actual.element;
	
    }
			

    @Override
    public T findMax() {
        return null;
    }

	
    @Override
    public void preorden() {
        preorden(root);
    }

    public void preorden(BinaryNode actual){

		if (actual == null)
			return;

        System.out.println(actual.element);

        if (actual.left != null)
            preorden(actual.left);

		if (actual.rigth != null)
            preorden(actual.rigth);
    }

    @Override
    public void inorden() {
        inorden(root);
    }

	public void inorden(BinaryNode actual){

		if (actual == null)
			return;

        if (actual.left != null)
            inorden(actual.left);

		System.out.println(actual.element);

		if (actual.rigth != null)
            inorden(actual.rigth);
    }

    @Override
    public void postorden() {
        postorden(root);
    }

	public void postorden(BinaryNode actual){

		if (actual == null)
			return;

        if (actual.left != null)
            postorden(actual.left);

		if (actual.rigth != null)
            postorden(actual.rigth);

		System.out.println(actual.element);
    }

    @Override
    public boolean isEmpty() {
        if (root == null)
            return true;
        return false;
    }


}

