package fciencias.edatos.practica03;

public class Main {

    public static void main(String[] args) {

        String opcion = "A";

        Maze laberinto = new Maze(9, 0, 9, 20, opcion);
        Maze laberinto1 = new Maze(5, 11, 9, 20, opcion);
        Maze laberinto2 = new Maze(9, 0, 6, 5, opcion);
        Maze laberinto3 = new Maze(9, 16, 12, 11, opcion);
        Maze laberinto4 = new Maze(15, 4, 9, 20, opcion);
        Maze laberinto5 = new Maze(9, 0, 9, 20, opcion);
        Maze laberinto6 = new Maze(9, 1, 9, 20, opcion);

        

        System.out.println(laberinto);
        System.out.println("");
        System.out.println(laberinto1);
        System.out.println();
        System.out.println(laberinto2);
        System.out.println("");
        System.out.println(laberinto3);
        System.out.println("");
        System.out.println(laberinto4);
        System.out.println("");
        System.out.println(laberinto5);
        System.out.println();
        System.out.println(laberinto6);
        System.out.println("");
    }

}