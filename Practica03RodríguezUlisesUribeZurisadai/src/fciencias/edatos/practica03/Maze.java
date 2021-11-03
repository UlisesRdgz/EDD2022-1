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
        if (tablero == "LaberintoA") 
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
                stack.push(actual);
                extend();
            }else
                aux++;
            
            if (casilla == 4) 
                actual = stack.pop();
            
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
                    aux[i][j] = " :D ";

                else if(board[i][j] == board[fin.getRow()][fin.getColumn()]) 
                    aux[i][j] = "EXIT";
                
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
        
        DoubleLinkedList<String> lista = new DoubleLinkedList<>();
        Scanner sc = new Scanner(System.in);
        String respuesta1;
        int respuesta;

        do{
            try{
                System.out.println("        LABERINTO       \n");
                System.out.println("1) Resolver un laberinto ");
                System.out.println("2) Salir");

                respuesta = sc.nextInt();
                
                switch (respuesta) {

                    case 1:
                        do {
                            System.out.println(" LaberintoA");
                            System.out.println(" LaberintoB");
                            System.out.println(" Menú ");
                            
                            respuesta1 = sc.next();

                    switch (respuesta1) {
                        case "LaberintoA":
                            //Imprime el Tablero
                            int x,y,x1,y1;
                            System.out.println("El laberinto A es:\n");
                            Maze laberintoA = new Maze(9,0,9,20, respuesta1);
                            System.out.println(laberintoA.printEmpty());
                             

                            System.out.println("Coloca las coordenadas donde desees iniciar...");
                            System.out.println("Dame la coordenada en x para la fila: ");
                            x= sc.nextInt();
                            System.out.println("Dame la coordenada en y para la columna: ");    
                            y= sc.nextInt();           
                            System.out.println("        Ahora elije el final...");
                            System.out.println("Dame la coordenada en x para la fila: ");
                            x1= sc.nextInt();
                            System.out.println("Dame la coordenada en y para la columna: ");    
                            y1= sc.nextInt();                  
                            
                           
                            Maze laberinto1 = new Maze(x, y, x1, y1, respuesta1);
                            
                            //Imprime el laberinto con INICIO, FIN & SOLUCIÓN 
                            System.out.println(laberinto1); 
                                                      
                            break;


                        case "LaberintoB":
                            //Imprime el Tablero
                            System.out.println("El laberinto B es:\n");
                            Maze laberintoB = new Maze(9,11,9,20, respuesta1);
                            System.out.println(laberintoB.printEmpty());
                           

                        case "Menu":
                            return;

                        default:
                            break;
                        }
                    } while (respuesta1 != "Menu");

                    break;
                        
                    case 2:
                        System.out.println("Hasta la próxima!");
                        return;

                    default:
                        System.out.println("\nOpción inválida :c\n"); 
                        break;
                }
            
            }catch (Exception e) {
                System.out.println("\nError: Solo puedes ingresar números enteros\n");
                sc.next();
            }
            
        }while(true);
    }
}
