package fciencias.edatos.practica03;

import java.util.Scanner;

/**
* Implementación de la creación y solución del laberinto.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 2.0 Noviembre 2021.
* @since Estructuras de datos 2022-1. Prática 3.
*/
public class Maze {

    static Box[][] board; 
    
    Box actual, inicio, fin;

    int casilla;
    
    /**
     * Método contructor del laberinto Maze.
     * @param xInicial posición inicial en x.
     * @param yInicial posición inicial en y.
     * @param xFinal posición final en x.
     * @param yFinal posición final en y.
     * @param tablero laberinto sin resolver.
     */
    public Maze(int xInicial, int yInicial, int xFinal, int yFinal, String tablero){
        if (tablero.equals("a")) 
            Maze.board = ArrayReader.readMatrix("Laberintos/LaberintoA.txt");
        else
            Maze.board = ArrayReader.readMatrix("Laberintos/LaberintoB.txt");
        
        this.inicio = new Box(xInicial, yInicial, false);
        this.fin = new Box(xFinal, yFinal, false);
    }
    

    /**
     * Método para ver si el laberinto está resuelto.
     * @return true si el laberinto está resuelto, false en otro caso.
     */
    public boolean isSolution(){
        if (actual.getRow() == fin.getRow() && actual.getColumn() == fin.getColumn()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Método que verifica si el tablero se puede extender.
     * @return true si la casilla actual tiene vecinos sin visitar 
     *         false en otro caso.
     */
    public boolean isExtensible(){
        casilla = actual.peek();
        Box temp = actual;
        temp.visit();

        if (casilla == 4) 
            return false;

        /** Arriba */
        if (casilla == 0) 
            temp = board[actual.getRow()-1][actual.getColumn()];

        /** Derecha */ 
        else if(casilla == 1 && temp.getColumn() != 20) 
            temp = board[actual.getRow()][actual.getColumn()+1];
    
        /** Abajo */
        else if(casilla == 2)
            temp = board[actual.getRow()+1][actual.getColumn()];
    
        /** Izquierda */
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
     * pared y no haya sido visitada.
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
     * Encuentra la solución del laberinto.
     * @return una pila con la solución del laberinto.
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

    /**
     * Método para dibujar el tablero.
     * @param resuelto true para el tablero resuelto, false para el tablero vacio.
     * @return Una matriz con las posiciones del laberinto.
     */
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
     * Imprime la representación del laberinto vacio en consola.
     * @return el tablero vacío. 
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
     * Imprime la representación del laberinto resuelto en consola.
     * @return el tablero resuelto. 
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

    public static boolean isBlank(String str){
        return str.trim().isEmpty();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String respuesta1;
        String respuesta;

        while(true){
            try{
                System.out.println("      ~ LABERINTO ~      \n");
                System.out.println("1) Resolver un laberinto ");
                System.out.println("2) Cerrar");

                respuesta = sc.nextLine();
                
                switch (respuesta) {

                    case "1":
                        do {
                            System.out.println("\n--------------------------");
                            System.out.println("  Seleciona una opción    ");
                            System.out.println("--------------------------");
                            System.out.println("                          ");
                            System.out.println(" (a) Laberinto A          ");
                            System.out.println(" (b) Laberinto B          ");
                            System.out.println(" (c) Salir                ");
                            System.out.println("                          ");
                            System.out.println("--------------------------");
                            System.out.println("");
                            System.out.println("Si no ingresas una opción correcta, el programa no seguira.");
                            
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
                                    System.out.println("Coordenada en y para la fila: ");
                                    y1 = sc.nextInt(); 
                                    System.out.println("Coordenada en x para la columna: ");
                                    x1 = sc.nextInt();

                                    if (board[y][x].isWall()) {
                                        System.out.println("\nPARED: Ingresa una posición valida para Iniciar.\n");
                                        break;
                                    }
                                    if (board[y1][x1].isWall()) {
                                        System.out.println("\nPARED: Ingresa una posición valida para Finalizar.\n");
                                        break;
                                    }
                                    if (board[y][x] == board[y1][x1]) {
                                        System.out.println("\nError: Selecciona diferentes posiciones.\n");
                                        break;
                                    }
                                
                                    Maze laberinto1 = new Maze(y, x, y1, x1, respuesta1);
                                    
                                    //Imprime el laberinto con INICIO, FIN & SOLUCIÓN
                                    System.out.println(laberinto1); 
                                                            
                                    break;


                                case "b":
                                    System.out.println("\nEl laberinto B es:\n");
                                    Maze laberintoB = new Maze(9,0,9,20, respuesta1);
                                    System.out.println(laberintoB.printEmpty());
                                    
                                    System.out.println("Coloca las coordenadas donde desees INICIAR...");
                                    System.out.println("Coordenada en y para la fila: ");
                                    y = sc.nextInt();
                                    System.out.println("Coordenada en x para la columna: ");    
                                    x = sc.nextInt();
                                    System.out.println("---------------------------------------------");             
                                    System.out.println("Ahora elije el FINAL...");
                                    System.out.println("Coordenada en y para la fila: ");
                                    y1 = sc.nextInt(); 
                                    System.out.println("Coordenada en x para la columna: ");
                                    x1 = sc.nextInt();
                        
                                    if (board[y][x].isWall()) {
                                        System.out.println("\nPARED: Ingresa una posición valida para Iniciar\n");
                                        break;
                                    }
                                    if (board[y1][x1].isWall()) {
                                        System.out.println("\nPARED: Ingresa una posición valida para Finalizar\n");
                                        break;
                                    }
                                    if (board[y][x] == board[y1][x1]) {
                                        System.out.println("\nError: Selecciona diferentes posiciones.\n");
                                        break;
                                    }
                       
                                    Maze laberinto2 = new Maze(y, x, y1, x1, respuesta1);
                                    
                                    //Imprime el laberinto con INICIO, FIN & SOLUCIÓN
                                    System.out.println(laberinto2); 
                                                  
                                    break;

                                case "c":
                                    return ;

                                default:
                                    System.out.println("Opción inválida :c");
                                    break;
                            }
                        } while (respuesta1 != "c");

                    break;
                        
                    case "2":
                        System.out.println("\nHasta la próxima!");
                        return;

                    default:
                        System.out.println("Opción inválida :c\n"); 
                        break;
                }
            
            }catch (Exception e) {
                System.out.print("\nError: Vuelvelo a intentar.\n\n");
                sc.next();
            }
        }
    }
}
