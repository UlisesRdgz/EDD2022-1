package fciencias.edatos.proyecto01;

import java.util.Scanner;
import java.util.Random;

/**
* Clase en donde se desarrolla el juego OldMaid.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 10 Noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 01.
*/
public class OldMaid {

    /** Baraja del juego */
    Deck baraja = new Deck();

    /** Número de jugadores */
    int jugadores = 0;

    /** Lista de jugadores*/
    TDAList<Jugador> listaJugadores = new DoubleLinkedList<>();

    /** Partida guardada */
    TDAList<String> partida = new DoubleLinkedList<>();

    /**
     * Contructor del juego OldMaid.
     * @param players número de jugadores.
     */
    public OldMaid(int jugadores){
        this.jugadores = jugadores;

        for (int i = 1; i <= jugadores; i++) 
            listaJugadores.add(i-1, new Jugador("Jugador " + i));
    }

    /**
     * Asigna las cartas correspondientes a cada jugador.
     */
    public void assign(){
        TDAList<Integer> numeroCartas = new DoubleLinkedList<>();
        int num = 51 / jugadores, aux = num; 
        
        for (int i = 0; i < jugadores; i++) {

            if (i == jugadores-1) {
                numeroCartas.add(i, aux);
                break;
            }
            if (jugadores == 2 || jugadores == 4 || (jugadores == 5 && i == 0) || (jugadores == 6 && i < 3) ||
               (jugadores == 7 && i < 2) || (jugadores == 8 && i < 3) || (jugadores == 9 && i < 6) || (jugadores == 10 && i == 0)){
                aux += 1;
                numeroCartas.add(i, aux);
            }else
                numeroCartas.add(i, aux);
                
            aux += num;
        }
        
        /** Primer jugador */
        for (int i = 0; i < numeroCartas.get(0); i++) 
            listaJugadores.get(0).getCartas().add(i, baraja.getCarta(i));

        /** Segundo jugador */
        for (int i = numeroCartas.get(0), j = 0; i < numeroCartas.get(1); i++, j++) 
            listaJugadores.get(1).getCartas().add(j, baraja.getCarta(i));
        
        if (jugadores >= 3) {
            /** Tecer jugardor */
            for (int i = numeroCartas.get(1), j = 0; i < numeroCartas.get(2); i++, j++) 
                listaJugadores.get(2).getCartas().add(j, baraja.getCarta(i));

            if (jugadores >= 4) {
                /** Cuarto jugador */
                for (int i = numeroCartas.get(2), j = 0; i < numeroCartas.get(3); i++, j++) 
                    listaJugadores.get(3).getCartas().add(j, baraja.getCarta(i));

                if (jugadores >= 5) {
                    /** Quinto jugador */
                    for (int i = numeroCartas.get(3), j = 0; i < numeroCartas.get(4); i++, j++) 
                        listaJugadores.get(4).getCartas().add(j, baraja.getCarta(i));

                    if (jugadores >= 6) {
                        /** Sexto jugador */
                        for (int i = numeroCartas.get(4), j = 0; i < numeroCartas.get(5); i++, j++) 
                            listaJugadores.get(5).getCartas().add(j, baraja.getCarta(i));

                        if (jugadores >= 7) {
                            /** Séptimo jugador */
                            for (int i = numeroCartas.get(5), j = 0; i < numeroCartas.get(6); i++, j++) 
                                listaJugadores.get(6).getCartas().add(j, baraja.getCarta(i));

                            if (jugadores >= 8) {
                                /** Octavo jugador */
                                for (int i = numeroCartas.get(6), j = 0; i < numeroCartas.get(7); i++, j++) 
                                    listaJugadores.get(7).getCartas().add(j, baraja.getCarta(i));

                                if (jugadores >= 9) {
                                    /** Noveno jugador */
                                    for (int i = numeroCartas.get(7), j = 0; i < numeroCartas.get(8); i++, j++) 
                                        listaJugadores.get(8).getCartas().add(j, baraja.getCarta(i));

                                    if (jugadores == 10) {
                                        /** Décimo jugador */
                                        for (int i = numeroCartas.get(8), j = 0; i < numeroCartas.get(9); i++, j++) 
                                            listaJugadores.get(9).getCartas().add(j, baraja.getCarta(i));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Método para que el jugador realize su turno.
     * @param i Posición del jugador.
     * @param jugadorRobar Posición del jugador a robar.
     * @return la carta robada.
     */
    public int turnoUsuario(int i, int jugadorRobar){
        Scanner sc = new Scanner(System.in);
        String respuesta = "";
        int carta1, carta2;
        do{
            try {
                System.out.println("\n" + listaJugadores.get(i).getNombre() + ", tus cartas:");
                System.out.println(listaJugadores.get(i).getCartas());
                System.out.println("\n¿Deseas reacomodar tus cartas? s/n");
                respuesta = sc.nextLine();
                if (respuesta.equals("s")) {
                    String opciones = "";
                    System.out.println("\n" + listaJugadores.get(i).getCartas());
                    for (int index = 1; index <= listaJugadores.get(i).getCartas().size(); index++) {
                        opciones += index + " ";
                    }
                    System.out.println(opciones + "\n");
                    System.out.println("Escribe la primera carta");
                    carta1 = Integer.parseInt(sc.nextLine())-1;
                    System.out.println("Escribe la segunda carta");
                    carta2 = Integer.parseInt(sc.nextLine())-1;
                    listaJugadores.get(i).rearrange(carta1, carta2);
                }
                
            } catch (Exception e) {
                System.out.println("\nError: Debes escoger números validos,\n" +
                                   "presiona enter para intentar de nuevo");
                sc.nextLine();
            }
        }while(!respuesta.equals("n"));

        int stolen = -1;
        do{
            try {
                System.out.println("");
                System.out.println("Carta que deseas robar del " + listaJugadores.get(jugadorRobar).getNombre());
                String lista = "";
                for (int index = 1; index <= listaJugadores.get(jugadorRobar).getCartas().size(); index++) {
                    lista += "\uD83C\uDCA0" + " ";
                }
                lista += "\n";
                for (int index = 1; index <= listaJugadores.get(jugadorRobar).getCartas().size(); index++) {
                    lista += index + " ";
                }
                System.out.println(lista);
                stolen = Integer.parseInt(sc.nextLine()) - 1;
            } catch (Exception e) {
                System.out.println("\nError: Presiona enter para continuar.");
                sc.nextLine();
            } if(stolen < 0 || stolen >= listaJugadores.get(jugadorRobar).getCartas().size()){
                System.out.println("\nIngresa un número del 1 al " + (listaJugadores.get(jugadorRobar).getCartas().size()));
            }
            
        }while(stolen < 0 || stolen >= listaJugadores.get(jugadorRobar).getCartas().size());

        clearScreen();
        System.out.println("Robaste la carta " + listaJugadores.get(jugadorRobar).getCartas().get(stolen));
        return stolen;
    }

    /**
     * Método que máquina la maquina realize su turno.
     * @param jugadorRobar jugador al que robaremos la carta..
     * @return la carta robada.
     */
    public int turnoMaquina(int jugadorRobar){
        Random rn = new Random();
        int stolen = rn.nextInt(listaJugadores.get(jugadorRobar).getCartas().size());  
        return stolen;
    }

    /**
     * Método que clasifica las rondas y los turnos de cada jugador,
     * sus tiradas y cartas robadas.
     */
    public void juego(){
        baraja.shuffle();
        listaJugadores.get(0).setJugador(true);
        clearScreen();

        System.out.println(" ----------------- Ronda 1 -----------------\n");
        partida.add(0, " ----------------- Ronda 1 -----------------\n");
        assign();
        partida.add(partida.size(), "\nCartas iniciales\n");
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (i != listaJugadores.size()-1) {
                partida.add(partida.size(), "\n" + listaJugadores.get(i).getNombre() + "  " +
                listaJugadores.get(i).getCartas());
                if (i != 0) {
                    String lista = listaJugadores.get(i).getNombre() + "  ";
                    for (int index = 0; index < listaJugadores.get(i).getCartas().size(); index++) {
                        lista += "\uD83C\uDCA0" + " ";
                    }
                    System.out.println(lista);
                } 
            } else if(i != 9 && i == listaJugadores.size()-1){
                partida.add(partida.size(), "\n" + listaJugadores.get(i).getNombre() + "  " +
                listaJugadores.get(i).getCartas() + "\n");
                String lista = listaJugadores.get(i).getNombre() + "  ";
                for (int index = 0; index < listaJugadores.get(i).getCartas().size(); index++) {
                    lista += "\uD83C\uDCA0" + " ";
                }
                System.out.println(lista + "\n");
            } else{
                partida.add(partida.size(), "\n" + listaJugadores.get(i).getNombre() + " " +
                listaJugadores.get(i).getCartas() + "\n");
                String lista = listaJugadores.get(i).getNombre() + " ";
                for (int index = 0; index < listaJugadores.get(i).getCartas().size(); index++) {
                    lista += "\uD83C\uDCA0" + " ";
                }
                System.out.println(lista + "\n");
            }

            if (i == 0) {
                System.out.println(listaJugadores.get(0).getNombre() + "  " +
                listaJugadores.get(0).getCartas()); 
            }
        }

        for (int i = 0; i < listaJugadores.size(); i++) {
            // Comparar y eliminar cartas
            while(listaJugadores.get(i).compare()){
                partida.add(partida.size(), "\n" + listaJugadores.get(i).eliminarCartas());
            }

            // Eliminar jugadores sin cartas
            if (listaJugadores.get(i).verificarJugador()) {
                System.out.println(listaJugadores.get(i).getNombre() + " se quedó sin cartas.");
                listaJugadores.remove(i);
            }
        }

        /** Siguientes rondas */
        int ronda = 2;
        while(listaJugadores.size() > 1){
            int stolen = 0;
            sleep(3000);
            clearScreen();
            partida.add(partida.size(), "\n\n ----------------- Ronda " + ronda + " -----------------");
            System.out.println("\n ----------------- Ronda " + ronda++ + " -----------------");
            
            for (int i = 0; i < listaJugadores.size(); i++) {
                
                if (listaJugadores.size() == 1) 
                    break;

                int jugadorRobar = (i-1% listaJugadores.size() + listaJugadores.size())% listaJugadores.size();

                // Turno del usuario
                if (listaJugadores.get(i).isUser()) {
                    stolen = turnoUsuario(i, jugadorRobar);
                }else{
                    // Turno de la maquina
                    stolen = turnoMaquina(jugadorRobar);
                }
                partida.add(partida.size(), "\n\nCarta robada del " + listaJugadores.get(i).getNombre() + " al " +
                                            listaJugadores.get(jugadorRobar).getNombre() + ": " + 
                                            listaJugadores.get(jugadorRobar).getCartas().get(stolen));

                // Robamos la carta
                listaJugadores.get(i).robar(stolen, listaJugadores.get(jugadorRobar));
                sleep(500);
                partida.add(partida.size(), "\nCartas del " + listaJugadores.get(i).getNombre() + ": " + listaJugadores.get(i).getCartas());
                String lista = "\nCartas del " + listaJugadores.get(i).getNombre() + ": ";
                for (int index = 0; index < listaJugadores.get(i).getCartas().size(); index++) {
                    lista += "\uD83C\uDCA0" + " ";
                }
                System.out.println(lista);
                lista += "\n";

                for (int j = 0; j < listaJugadores.size(); j++) {
                    // Comparar y eliminar cartas
                    while(!listaJugadores.get(j).verificarJugador() && listaJugadores.get(j).compare()){
                        partida.add(partida.size(), "\n" + listaJugadores.get(j).eliminarCartas());
                    }
                    // Eliminar jugadores sin cartas
                    if (listaJugadores.get(j).verificarJugador()) {
                        System.out.println(listaJugadores.get(j).getNombre() + " se quedó sin cartas.");
                        partida.add(partida.size(), "\n" + listaJugadores.get(j).getNombre() + " se quedó sin cartas.");
                        listaJugadores.remove(j);
                        if (i == j) 
                            i = (i-1% listaJugadores.size() + listaJugadores.size())% listaJugadores.size();
                        if (i != 0 && j == (i-1% listaJugadores.size() + listaJugadores.size())% listaJugadores.size()) 
                            i = i-1;
                    }
                }
            }   
        }
        while(!listaJugadores.get(0).verificarJugador() && listaJugadores.get(0).compare()){
            partida.add(partida.size(), "\n" + listaJugadores.get(0).eliminarCartas());
        }
        System.out.println("\n'' " + listaJugadores.get(0).getNombre() + " se quedo con la SOLTERONA " + 
                          listaJugadores.get(0).getCartas() + " ''");
        partida.add(partida.size(), "\n\n'' " + listaJugadores.get(0).getNombre() + " se quedo con la SOLTERONA " + 
                    listaJugadores.get(0).getCartas() + " ''");
    } 

    /**
     * Imprime lel registro que se ha llevado de la partida.
     * @return el registro del juego 
     */
    public String toString(){
        String registro = "";
        for (int i = 0; i < partida.size(); i++) {
            registro += partida.get(i);
        }
        return registro;
    }

    public static void sleep(int i){
        try {
            Thread.sleep(1*i);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String respuesta = " ";
        String partida = "No has iniciado ninguna partida\n";
        int jugadores = 0;

        do {
            try {
                System.out.println("\n------------------------.");
                System.out.println("     ~ OLD MAID ~       |");
                System.out.println("------------------------|");
                System.out.println("1) Jugar                |");
                System.out.println("2) Historial            |");
                System.out.println("3) Reglas               |");
                System.out.println("4) Salir                |");
                System.out.println("------------------------");

                
                respuesta = sc.nextLine();

                switch (respuesta) {
                    
                    case "1":
                        
                    do{
                        try {                      
                            System.out.println("\n                A JUGAR!                ");
                            System.out.println("¿Cuántos jugadores deseas en la partida?");
                            jugadores = Integer.parseInt(sc.nextLine());

                            if (jugadores < 2 || jugadores > 10) {
                                System.out.println("\n D: Deben ser de dos a 10 jugadores ");
                            }else{
                                OldMaid juego = new OldMaid(jugadores);
                                System.out.println("\nBaraja inicial.\n");
                                juego.baraja.printDeck();
                                sleep(1000);  
                                System.out.println("\n\nBarajeamos\n");
                                sleep(2000);
                                System.out.println("Descartamos una carta: \uD83C\uDCA0 \n");
                                sleep(2000);        
                                juego.juego();
                                System.out.println("\n");
                                partida = juego.toString();
                                break;
                            }
                                               
                        } catch (Exception e) {
                            System.out.println("\nError: Debes ingresar un número válido.");
                        }                     
                    } while(jugadores < 2 || jugadores > 10);
                    break;

                    case "2":
                        System.out.println("\n************* Historial del juego *************\n");
                        System.out.println(partida);
                        break;
                        
                      
                    case "3":
                        System.out.println("\n************* Reglas del juego *************\n");
                        System.out.println("> Cada jugador le debe robar una carta al \n" +
                                           "  jugador que tenga a la derecha.");
                        System.out.println("> Si logra formar un par, automáticamente \n"+
                                           "  se descartan esas cartas.");
                        System.out.println("> Si el jugador se queda sin cartas, tendrá\n"+
                                           "  que abandonar la partida.");   
                        System.out.println("> Pierde el jugador que quede al final con\n"+
                                           "  la carta SOLTERONA.");                                                 
                        break;
                            
                    
                    case "4":
                        System.out.println("\nHasta la próximaa! :)");
                        return;
                                    
                    default:
                        System.out.println("\nError: Opción inválida.\nIngresa una de las opciones");
                        break;
                }

            } catch (Exception e) {
                System.out.print("\nError: Vuelvelo a intentarlo.\n\n");
                sc.next();
            }
        } while (respuesta != "4");                          
    }
} 