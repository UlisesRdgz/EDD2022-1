package fciencias.edatos.practica03;

public class Main {

    public static void main(String[] args) {

        String opcion = "A";

        Maze laberinto = new Maze(9, 0, 9, 20, opcion);
        Maze laberinto2 = new Maze(9, 1, 9, 20, opcion);

        System.out.println(laberinto);
        System.out.println(laberinto2.printEmpty());


    }

}