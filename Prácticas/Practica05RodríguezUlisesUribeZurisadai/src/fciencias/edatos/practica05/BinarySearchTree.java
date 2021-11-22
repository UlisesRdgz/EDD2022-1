package fciencias.edatos.practica05;

public class BinarySearchTree{
    
    public class BinaryNode<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T>{

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

        public BinaryNode(k key, T element, BinaryNode, parent) {
            this.key = key;
            
        }
    }
    
    public static void main(String[] args) {
        
    }
}
