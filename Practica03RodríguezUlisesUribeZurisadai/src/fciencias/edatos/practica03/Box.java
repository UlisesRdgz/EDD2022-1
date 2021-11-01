package fciencias.edatos.practica03;

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

        if(!isWall()){
            neighbors.enqueue(0);
            neighbors.enqueue(1);
            neighbors.enqueue(2);
            neighbors.enqueue(3);
        }
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
        return neighbors.dequeue();
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