package fciencias.edatos.practica03;

import java.util.Random;

public class Box {
    
    /** Atributos */
    boolean wall;

    boolean visited = false;

    Queue<Integer> neighbors = new Queue<>();
    
    int row, column;

    /**
     * 
     */
    public Box(int row, int column, boolean wall){
        this.wall = wall;
        this.row = row;
        this.column = column;
        if (!wall) 
            fill();
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column){
        this.column = column;
    } 

    public int peek(){
        if (neighbors.isEmpty()) 
            return 4;

        return neighbors.dequeue();
    }

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
     * @return
     */
    public boolean isWall(){
        return wall;
    }

    /**
     * Permite saber si una casilla está visitada o no.
     * @return
     */
    public boolean isVisited(){
        return visited;
    }

    /**
     * Visita la casilla.
     */
    public void visit(){
        visited = true;
    }
}
