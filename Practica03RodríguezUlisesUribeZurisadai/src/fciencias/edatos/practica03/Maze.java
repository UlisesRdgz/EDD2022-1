package fciencias.edatos.practica03;

public class Maze {
    
    Box[][] board; 
    
    Box actual, temp, inicio, fin;
    
    /**
     * 
     */
    public Maze(int xini, int yini, int xfin, int yfin, String tablero){
        if (tablero == "A") 
            this.board = ArrayReader.readMatrix("Laberintos/LaberintoA.txt");
        else
            this.board = ArrayReader.readMatrix("Laberintos/LaberintoB.txt");
        
        this.inicio = new Box(xini, yini, false);
        this.fin = new Box(xfin, yfin, false);
        
    }

    /**
     * Método para ver si el laberinto está resuelto
     * @return true si el laberinto está resuelto
     */
    public boolean isSolution(){
        if (actual != fin)
            return false;

        return isExtensible();
    }
    
    /**
     * 
     * @return true si la casilla actual tiene vecinos
     */
    public boolean isExtensible(){
        int casilla = actual.peek();
        temp = actual;

        for (int i = 0; i < 4; i++) {
            // Arriba
            if (casilla == 0) 
                temp = board[actual.getRow()-1][actual.getColumn()];

            // Derecha
            else if(casilla == 1)
                temp = board[actual.getRow()][actual.getColumn()+1];
    
            // Abajo
            else if(casilla == 2)
                temp = board[actual.getRow()+1][actual.getColumn()];
    
            // Izquierda
            else if(casilla == 3)
                temp = board[actual.getRow()][actual.getColumn()-1];

            if(!temp.isWall() || !temp.isVisited() || temp != null)
                return true;
                
        }
        return false;
    }


    /**
     * Método que mueve la casilla actual a una casilla vecina que no sea
     * pared y no haya sido visitada
     * 
     */
    public void extend(){
        actual.visit();
        actual = temp;    
    }


    /**
     * Encuentra la solución del laberinto
     */
    public TDAStack<Box> solve(){
        actual = inicio;
        Stack<Box> stack = new Stack<>();
        stack.push(actual);
        
        while(!isSolution()) {
            if (isExtensible()) {
                extend();
                stack.push(actual);
            }
            else{
                stack.pop();
            }
        }
        return stack;
    }
    
    
    /**
     * Muestra la representación de un tablero
     */
    @Override
    public String toString(){
        String laberinto = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].isWall()) {
                    laberinto += "@@@@";
                }else if (board[i][j] == board[inicio.row][inicio.column]) {
                    laberinto += ":)))";
                }else if (board[i][j] == board[fin.row][fin.column]) {
                    laberinto += "FIN!";
                }else{
                    laberinto += "    ";
                }
                
            }
            laberinto += "\n";
        }

        return laberinto;
    }
}