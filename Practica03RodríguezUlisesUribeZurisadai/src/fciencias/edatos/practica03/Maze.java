package fciencias.edatos.practica03;

import java.util.Scanner;

public class Maze {
    
    Box[][] board; 
    
    Box actual, inicio, fin;

    int casilla;
    
    /**
     * 
     */
    public Maze(int xInicial, int yInicial, int xFinal, int yFinal, String tablero){
        if (tablero.equals("a")) 
            this.board = ArrayReader.readMatrix("Laberintos/LaberintoA.txt");
        else
            this.board = ArrayReader.readMatrix("Laberintos/LaberintoB.txt");
        
        if (!this.board[xInicial][yInicial].isWall())
            this.inicio = new Box(xInicial, yInicial, false);
        else    
            System.out.println("\nPARED: Debes colocar una posición correcta donde desees inciar.");

        if (!this.board[xFinal][yFinal].isWall())
            this.fin = new Box(xFinal, yFinal, false);
        else    
            System.out.println("\nPARED: Debes colocar una posición correcta donde desees finalizar.");
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

        if (casilla == 4) 
            return false;

        // Arriba
        if (casilla == 0) 
            temp = board[actual.getRow()-1][actual.getColumn()];

        // Derecha
        else if(casilla == 1 && temp.getColumn() != 20) 
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

        while (!isSolution()) {

            if (isExtensible()) {
                stack.push(actual);
                extend();
            }
            
            if (casilla == 4){
                if(stack.isEmpty())
                    break;
                actual = stack.pop();
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
                    aux[i][j] = " :D ";

                else if(board[i][j] == board[fin.getRow()][fin.getColumn()]) 
                    aux[i][j] = "EXIT";
                
                else
                    aux[i][j] = "    ";
                 
            }
        }

        if (resuelto) {

            solucion = solve();

            if (solucion.isEmpty()) {
                System.out.println("\nNo hay solución:");
                return aux;
            }else{
                System.out.println("\nSolución encontrada:"); 
            }
            
            while (!solucion.isEmpty()) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[0].length; j++) {
                        if (board[i][j] == board[actual.getRow()][actual.getColumn()]) {
                            aux[i][j] = " *- ";
                            if (!solucion.isEmpty()) 
                                actual = solucion.pop();
                        }

                        if (board[i][j] == board[inicio.getRow()][inicio.getColumn()]) 
                            aux[i][j] = " :D ";

                        if(board[i][j] == board[fin.getRow()][fin.getColumn()]) 
                            aux[i][j] = "EXIT";
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

        mazeEmpty = "  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20 \n";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++){ 
                if (i == inicio.getRow() && j == inicio.getColumn()) 
                    empty[i][j] = "    ";

                if (i == fin.getRow() && j == fin.getColumn()) 
                    empty[i][j] = "    ";

                mazeEmpty += empty[i][j];
            }

            mazeEmpty += " " + i;
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

        resultado = "  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20 \n";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) 
                resultado += solucion[i][j];
            
            resultado += " " + i;
            resultado += "\n";
        }

        return resultado;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String respuesta1;
        int respuesta;

        do{
            try{
                System.out.println("      ~ LABERINTO ~      \n");
                System.out.println("1) Resolver un laberinto ");
                System.out.println("2) Cerrar");

                respuesta = sc.nextInt();
                
                switch (respuesta) {

                    case 1:
                        do {
                            System.out.println("--------------------------");
                            System.out.println(" (a) Laberinto A        ");
                            System.out.println(" (b) Laberinto B        ");
                            System.out.println(" (c) Salir               ");
                            System.out.println("--------------------------");
                            
                            respuesta1 = sc.next();

                    switch (respuesta1) {
                        case "a":
                            //Imprime el Tablero
                            int x,y,x1,y1;
                            System.out.println("\nEl laberinto A es:\n");
                            Maze laberintoA = new Maze(9,0,9,20, respuesta1);
                            System.out.println(laberintoA.printEmpty());
                             

                            System.out.println("Coloca las coordenadas donde desees INICIAR...");
                            System.out.println("Coordenada en y para la fila: ");
                            y = sc.nextInt();
                            System.out.println("Coordenada en x para la columna: ");    
                            x = sc.nextInt();
                            System.out.println("---------------------------------------------");             
                            System.out.println("Ahora elije el FINAL...");
                            System.out.print("Coordenada en y para la fila: ");
                            y1 = sc.nextInt(); 
                            System.out.println("Coordenada en x para la columna: ");
                            x1 = sc.nextInt();
                           
                            Maze laberinto1 = new Maze(y, x, y1, x1, respuesta1);
                            
                            //Imprime el laberinto con INICIO, FIN & SOLUCIÓN
                            System.out.println(laberinto1); 
                                                      
                            break;


                        case "b":
                        System.out.println("\nEl laberinto B es:\n");
                        Maze laberintoB = new Maze(9,0,9,20, respuesta1);
                        System.out.println(laberintoB.printEmpty());
                         
                        System.out.println("Coloca las coordenadas donde desees INICIAR...");
                        System.out.println("Coordenada en x para la fila: ");
                        x = sc.nextInt();
                        System.out.println("Coordenada en y para la columna: ");    
                        y = sc.nextInt();
                        System.out.println("---------------------------------------------");             
                        System.out.println("Ahora elije el FINAL...");
                        System.out.println("Coordenada en x para la fila: ");
                        x1 = sc.nextInt();
                        System.out.println("Coordenada en y para la columna: ");    
                        y1 = sc.nextInt();   
                        
                       
                        Maze laberinto2 = new Maze(x, y, x1, y1, respuesta1);
                        
                        //Imprime el laberinto con INICIO, FIN & SOLUCIÓN
                        System.out.println("\nSolución encontrada:\n"); 
                        System.out.println(laberinto2); 
                                                  
                        break;
                           

                        case "c":
                            return ;

                        default:
                            break;
                        }
                    } while (respuesta1 != "c");

                    break;
                        
                    case 2:
                        System.out.println("Hasta la próxima!");
                        return;

                    default:
                        System.out.println("\nOpción inválida :c\n"); 
                        break;
                }
            
            }catch (Exception e) {
                System.out.println("\nError: Ingresa un número entero\n");
                sc.next();
            }
            
        }while(true);
    }
}
