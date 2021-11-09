package fciencias.edatos.practica03;

import java.util.Random;

/**
* Implementación para crear cada casilla del laberinto.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 2.0 Noviembre 2021.
* @since Estructuras de datos 2022-1. Prática 3.
*/
public class Box {
    
    /** Representación de una pared */
    boolean wall;

    /** Casilla visitada */
    boolean visited = false;

    /** Elementos por visitar */
    Queue<Integer> neighbors = new Queue<>();
    
    int row, column;

    /**
     * Constructor de las casillas. 
     * @param row coordenada para la fila de la casilla.
     * @param column coordenada para la columna de la casilla.
     * @param wall true si es pared, false en otro caso
     */
    public Box(int row, int column, boolean wall){
        this.wall = wall;
        this.row = row;
        this.column = column;
        if (!wall) 
            fill();
    }

    /**
     * Método get para obtener la fila.
     * @return la posición de la fila.
     */
    public int getRow(){
        return row;
    }

    /**
     * Método get para obtener la columna.
     * @return la posición de la columna.
     */
    public int getColumn(){
        return column;
    }

    /**
     * Obtener el vecino próximo a visitar. 
     * @return un número random al cual se va a visitar.
     */
    public int peek(){
        if (neighbors.isEmpty()) 
            return 4;

        return neighbors.dequeue();
    }

    /**
     * Método para llenar la cola neightbors de números del 0 al 3
     * insertados aleatoreamente.
     */
    public void fill(){
        DoubleLinkedList<Integer> visited = new DoubleLinkedList<>(); 
        Random rn = new Random();
		int random = rn.nextInt(4);
        visited.add(0, random);

        for (int i = 0; i < 3; i++) {
            while (visited.contains(random)) {
                random = rn.nextInt(4);
            }

            visited.add(0, random);
        }

        for (int i = 0; i < 4; i++) {
            neighbors.enqueue(visited.get(i));
        }

    }
    

    /**
     * Permite saber si una casilla es pared o no.
     * @return true si es pared, false en otro caso.
     */
    public boolean isWall(){
        return wall;
    }

    /**
     * Permite saber si una casilla está visitada o no.
     * @return true si ya ha sido visitada, false en otro caso.
     */
    public boolean isVisited(){
        return visited;
    }

    /**
     * Método para visitar una casilla.
     */
    public void visit(){
        visited = true;
    }
}
