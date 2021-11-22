package fciencias.edatos.practica05;

public class BinarySearchTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T>{
    
    public class BinaryNode{

        /** Clave */
        public K key;

        /** Elemento */
        public T element;

        /** Padre del nodo */
        public BinaryNode parent;

        /** Hijo izquierdo */
        public BinaryNode left;

        /** Hijo derecho */
        public BinaryNode right;

        public BinaryNode(K key, T element, BinaryNode parent) {
            this.key = key;
            this.element = element;
            this.parent = parent;
        }
    }
    /** Rama */
    private BinaryNode root;

    /**
     * Devuelve el elemento.
     * @param k clave k.
     * @return el elemento asociado a dicha clave, 
     * null si no existe.
     */
    @Override
	public T retrieve(K k){
		BinaryNode node = retrieve(root, k);
		if(node == null)
			return null;
		return node.element;
	}

	private BinaryNode retrieve(BinaryNode actual, K k){
		// Si el elemento no se encuentra.
		if(actual == null)
			return null;

		// Si se encuentra el elemento.
		if(actual.key.compareTo(k) == 0)
			return actual;

		// Comparamos.
		if(k.compareTo(actual.key) < 0){ 
			return retrieve(actual.left, k);
		} else { 
			return retrieve(actual.right, k);
		}
	}


    
    public static void main(String[] args) {
        
    }
}
