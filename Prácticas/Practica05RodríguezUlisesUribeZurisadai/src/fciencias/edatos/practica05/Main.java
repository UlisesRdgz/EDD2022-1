package fciencias.edatos.practica05;

public class Main {
    public static void main(String[] args) {
        TDABinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

        tree.insert("A", 12);
        tree.insert("B", 2);
        tree.insert("C", 6);
        tree.insert("D", 16);
        tree.insert("E", 1);
        tree.insert("F", 3);
        tree.insert("G", 0);

        tree.preorden();
        System.out.println("");
        tree.inorden();
        System.out.println("");
        tree.postorden();

     }
}
