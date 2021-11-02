package fciencias.edatos.practica03;

public class Maze {
    
    Box[][] board; 
    
    Box actual, inicio, fin;

    int casilla;
    
    /**
     * 
     */
    public Maze(int xInicial, int yInicial, int xFinal, int yFinal, String tablero){
        if (tablero == "A") 
            this.board = ArrayReader.readMatrix("Laberintos/LaberintoA.txt");
        else
            this.board = ArrayReader.readMatrix("Laberintos/LaberintoB.txt");
        
        this.inicio = new Box(xInicial, yInicial, false);
        this.fin = new Box(xFinal, yFinal, false);
        
    }

    /**
     * Método para ver si el laberinto está resuelto
     * @return true si el laberinto está resuelto
     */
    public boolean isSolution(){
        if (actual.getRow() == fin.getRow() && actual.getColumn() == fin.getColumn()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 
     * @return true si la casilla actual tiene vecinos
     */
    public boolean isExtensible(){
        casilla = actual.peek();
        Box temp = actual;

        if (casilla == 4) 
            return false;

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
        else if(casilla == 3 && !actual.equals(inicio)) {
                temp = board[actual.getRow()][actual.getColumn()-1];
        }
        
        if(temp.isWall() == true || temp.isVisited() == true)
            return false;
            
        return true;
    }


    /**
     * Método que mueve la casilla actual a una casilla vecina que no sea
     * pared y no haya sido visitada
     * 
     */
    public void extend(){
        actual.visit();
        // Arriba
        if (casilla == 0) 
            actual = board[actual.getRow()-1][actual.getColumn()];

        // Derecha
        else if(casilla == 1){
            actual = board[actual.getRow()][actual.getColumn()+1];
    
        // Abajo
        }else if(casilla == 2)
            actual = board[actual.getRow()+1][actual.getColumn()];
    
        // Izquierda
        else if(casilla == 3)
            actual = board[actual.getRow()][actual.getColumn()-1];   
    }


    /**
     * Encuentra la solución del laberinto
     */
    public TDAStack<Box> solve(){
        TDAStack<Box> stack = new Stack<>();
        actual = inicio;
        stack.push(actual);

        while (!isSolution()) {
            if (isExtensible()) {
                extend();
                stack.push(actual);
            }

            if (casilla == 4) 
                actual = stack.pop();
        }

        return stack;
    }

    public String[][] drawMaze(){
        TDAStack<Box> solucion = new Stack<>();
        String[][] aux = new String[board.length][board[0].length];
        solucion = solve();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j].isWall()) {
                    aux[i][j] = "@@@@";
                }else if (board[i][j] == board[inicio.getRow()][inicio.getColumn()]) {
                    aux[i][j] = ":)))";
                }else if(board[i][j] == board[fin.getRow()][fin.getColumn()]) {
                    aux[i][j] = "FIN!";
                }else{
                    aux[i][j] = "    ";
                } 
            }
        }

        while (!solucion.isEmpty()) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == board[actual.getRow()][actual.getColumn()]) {
                        aux[i][j] = " *- ";
                        actual = solucion.pop();
                    }
                }
            }
        }
        return aux;
    }
    
    
    /**
     * Muestra la representación de un tablero
     */
    @Override
    public String toString(){
        // TDAStack<Box> solucion = new Stack<>();
        // String laberinto = "";
        // solucion = solve();
        String[][] solucion = drawMaze();
        String resultado = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                resultado += solucion[i][j];
                // if (board[i][j].isWall()) {
                //     laberinto += "@@@@";
                // }else if (board[i][j] == board[inicio.getRow()][inicio.getColumn()]) {
                //     laberinto += ":)))";
                // }else if (board[i][j] == board[actual.getRow()][actual.getColumn()]) {
                //     laberinto += "----";
                // }else if(board[i][j] == board[fin.getRow()][fin.getColumn()]) {
                //     laberinto += "FIN!";
                // }else{
                //     laberinto += "    ";
                // } 

                // if (board[i][j] == board[inicio.getRow()][inicio.getColumn()]) {
                //     actual = solucion.pop();
                }
                resultado += "\n";
                // laberinto += "\n";
            }
        // return laberinto;
        return resultado;
    }
}
