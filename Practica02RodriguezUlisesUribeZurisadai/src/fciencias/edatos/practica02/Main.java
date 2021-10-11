package fciencias.edatos.practica02;

public class Main {
    public static void main(String[] args) {
        DoubleLinkedList<String> lista = new DoubleLinkedList<>();

        lista.add(0, "Es");
        lista.add(1, "Un");
        lista.add(2, "Ejemplo");
        lista.add(3, "El");
        lista.add(0, "Hola");
        lista.add(1, "Este");
        lista.add(5, "Para");
        lista.add(5, ":)");
        lista.add(8, "Método");

        System.out.print("Longitud de la lista: " + lista.size() + "\n");
        System.out.println(lista);

        System.out.println(lista.contains("Este"));
        System.out.println(lista.contains("Es"));
        System.out.println(lista.contains("Un"));
        System.out.println(lista.contains("uwuw"));
        System.out.println(lista.contains("Ejemplo"));
        System.out.println(lista.contains(":)"));
        System.out.println(lista.contains("xd"));
        System.out.println(lista.contains("Para"));
        System.out.println(lista.contains("El"));
        System.out.println(lista.contains("Método"));
    }
}
