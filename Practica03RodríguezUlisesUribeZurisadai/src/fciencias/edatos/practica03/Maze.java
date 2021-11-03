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
        temp.visit();
        System.out.println(casilla);

        if (casilla == 4) 
            return false;

        // Arriba
        if (casilla == 0) 
            temp = board[actual.getRow()-1][actual.getColumn()];

        // Derecha
        else if(casilla == 1 && temp.getColumn() != 21) 
            temp = board[actual.getRow()][actual.getColumn()+1];
    
        // Abajo
        else if(casilla == 2)
            temp = board[actual.getRow()+1][actual.getColumn()];
    
        // Izquierda
        else if(casilla == 3 && temp.getColumn() != 0) {
                temp = board[actual.getRow()][actual.getColumn()-1];
        }

        if (temp.getRow() == inicio.getRow() && temp.getColumn() == inicio.getColumn()) 
            return false;
        
        if(temp.isWall() || temp.isVisited())
            return false;
            
        return true;
    }


    /**
     * Método que mueve la casilla actual a una casilla vecina que no sea
     * pared y no haya sido visitada
     * 
     */
    public void extend(){
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
        int aux = 0;        

        while (!isSolution()) {

            if (isExtensible()) {
                System.out.println("Es Extensible :)");
                stack.push(actual);
                extend();
                
                System.out.println(actual.getRow());
                System.out.println(actual.getColumn() + "\n");
            }else{
                aux++;
                System.out.println("No es extensible");
                System.out.println(actual.getRow());
                System.out.println(actual.getColumn() + "\n");
            }

            if (casilla == 4) {
                System.out.println("Pop");
                actual = stack.pop();
                System.out.println(actual.getRow());
                System.out.println(actual.getColumn() + "\n");
            }

            if (stack.isEmpty() && aux == 4) {
                System.out.println("No tiene solución");
                break;
            }
        }

        return stack;
    }

    public String[][] drawMaze(boolean resuelto){
        TDAStack<Box> solucion = new Stack<>();
        String[][] aux = new String[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j].isWall()) 
                    aux[i][j] = "@@@@";

                else if (board[i][j] == board[inicio.getRow()][inicio.getColumn()]) 
                    aux[i][j] = ":)))";

                else if(board[i][j] == board[fin.getRow()][fin.getColumn()]) 
                    aux[i][j] = "FIN!";
                
                else
                    aux[i][j] = "    ";
                 
            }
        }

        if (resuelto) {
            solucion = solve();

            while (!solucion.isEmpty()) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[0].length; j++) {
                        if (board[i][j] == board[actual.getRow()][actual.getColumn()]) {
                            aux[i][j] = " *- ";
                            System.out.println("Actual " + actual.getRow() + " " + actual.getColumn());
                            if (!solucion.isEmpty()) 
                                actual = solucion.pop();
                        }

                        if (board[i][j] == board[inicio.getRow()][inicio.getColumn()]) 
                            aux[i][j] = ":)))";

                        if(board[i][j] == board[fin.getRow()][fin.getColumn()]) 
                            aux[i][j] = "FIN!";
                    }
                }
            }
        }
            
        return aux;
    }

    /**
     * Muestra la representación de un tablero
     */
    public String printEmpty(){
        String[][] empty = drawMaze(false);
        String mazeEmpty = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) 
                mazeEmpty += empty[i][j];
                
            mazeEmpty += "\n";
        }

        return mazeEmpty;
    }
    
    
    /**
     * Muestra la representación de un tablero
     */
    @Override
    public String toString(){
        String[][] solucion = drawMaze(true);
        String resultado = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) 
                resultado += solucion[i][j];
                
            resultado += "\n";
        }

        return resultado;
    }
}
