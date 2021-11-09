package fciencias.edatos.proyecto01;

import java.util.Scanner;
import java.util.Random;

public class OldMaid {

    /** Baraja del juego */
    Deck baraja = new Deck();

    /** Número de jugadores */
    int jugadores = 0;

    /** Lista de jugadores*/
    TDAList<Jugador> listaJugadores = new DoubleLinkedList<>();

    /**
     * Contructor
     * @param jugadores
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
                                    /** Noveno jugadores */
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

    public void juego(){
        baraja.shuffle();
        listaJugadores.get(0).setJugador(true);

        System.out.println("Primera ronda");
        assign();
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println("\n" + listaJugadores.get(i).getNombre() + ", tus cartas:\n");
            System.out.println(listaJugadores.get(i).getCartas());
            System.out.println("");
        }
        

        // Primera ronda
        for (int i = 0; i < listaJugadores.size(); i++) {
            // Comparar y eliminar cartas
            while(listaJugadores.get(i).compare()){
                listaJugadores.get(i).eliminarCartas();
            }

            // Eliminar jugadores sin cartas
            if (listaJugadores.get(i).verificarJugador()) {
                System.out.println("Se borró el jugador " + listaJugadores.get(i).getNombre());
                listaJugadores.remove(i);
            }
        }

        /** Siguientes rondas */
        Scanner sc = new Scanner(System.in);
        while(listaJugadores.size() > 1){
            int stolen = 0;

            for (int i = 0; i < listaJugadores.size(); i++) {
                
                if (listaJugadores.size() == 1) 
                    break;

                int jugadorRobar = (i-1% listaJugadores.size() + listaJugadores.size())% listaJugadores.size();

                // Turno del usuario
                if (listaJugadores.get(i).isUser()) {
                    System.out.println("\n" + listaJugadores.get(i).getNombre() + ", tus cartas:\n");
                    System.out.println(listaJugadores.get(i).getCartas());
                    System.out.println("");
                    System.out.println("Carta que deseas robar del " + listaJugadores.get(jugadorRobar).getNombre() + "\n");
                    String lista = "";
                    for (int index = 1; index <= listaJugadores.get(jugadorRobar).getCartas().size(); index++) {
                        lista += "\uD83C\uDCA0" + " ";
                    }
                    lista += "\n";
                    for (int index = 1; index <= listaJugadores.get(jugadorRobar).getCartas().size(); index++) {
                        lista += index + " ";
                    }
                    System.out.println(lista);
                    stolen = sc.nextInt() - 1;
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    System.out.println("\nRobaste la carta " + listaJugadores.get(jugadorRobar).getCartas().get(stolen));
                }else{
                    // Turno de la maquina
                    Random rn = new Random();
                    stolen = rn.nextInt(listaJugadores.get(jugadorRobar).getCartas().size());  
                    String lista = "";
                    
                    // for (int index = 1; index <= listaJugadores.get(jugadorRobar).getCartas().size(); index++) {
                    //     lista += "\uD83C\uDCA0" + " ";
                    // }
                    // System.out.println(lista);
                    // lista += "\n";

                    System.out.print("\nCarta robada: ");
                    System.out.println(listaJugadores.get(jugadorRobar).getCartas().get(stolen));
                }
                // Robamos la carta
                listaJugadores.get(i).robar(stolen, listaJugadores.get(jugadorRobar));
                System.out.println("\nCartas del " + listaJugadores.get(i).getNombre() + ":");
                System.out.println(listaJugadores.get(i).getCartas());

                for (int j = 0; j < listaJugadores.size(); j++) {
                    // Comparar y eliminar cartas
                    while(!listaJugadores.get(j).verificarJugador() && listaJugadores.get(j).compare()){
                        listaJugadores.get(j).eliminarCartas();
                    }
                    // Eliminar jugadores sin cartas
                    int posicion = (i-1% listaJugadores.size() + listaJugadores.size())% listaJugadores.size();
                    if (listaJugadores.get(j).verificarJugador()) {
                        System.out.println("Se borró el jugador " + listaJugadores.get(j).getNombre());
                        if (j != posicion) 
                            i = posicion;
                        listaJugadores.remove(j);
                    }
                }
            }   
        }
        System.out.println("\nEl jugador " + listaJugadores.get(0).getNombre() + " perdio.");
    } 
    
    public static void main(String[] args) {
        
        

        Scanner sc = new Scanner(System.in);
        String respuesta = " ";
        int jugadores;

        do {
            try {
                System.out.println("\n   Bienvenido  ");
                System.out.println(" ~~ OLD MAID ~~ \n");
                System.out.println("1) Reglas");
                System.out.println("2) Jugar");
                System.out.println("3) Historial");
                System.out.println("4) Salir\n");

                respuesta = sc.nextLine();

                switch (respuesta) {
                    case "1":
                        System.out.println("\n  *********** Reglas del juego ***********\n");
                        System.out.println("- Cada jugador le debe robar una carta al \n" +
                                           "  jugador que tenga a la derecha.");
                        System.out.println("- Si logra formar un par, automáticamente \n"+
                                           "  se descartan esas cartas.");
                        System.out.println("- Si el jugador se queda sin cartas, tendrá\n"+
                                           "  que abandonar la partida.");   
                        System.out.println("- Pierde el jugador que quede al final.");                                                 
                        break;

                    case "2":
                        
                        System.out.println("¿Cuántos jugadores deseas en la partida?");
                        jugadores = Integer.parseInt(sc.nextLine());

                        
                        OldMaid juego = new OldMaid(jugadores);
                        juego.juego();
                        System.out.println("\n");
                        break;
                      
                    case "3":
                        System.out.println("\n********* Historial de la partida *********\n");
                        
                    
                    case "4":
                        System.out.println("Esperamos que te hayas divertido");
                        System.out.println("Vuelve pronto!");
                        return;
                                    
                    default:
                        break;
                }

            } catch (Exception e) {
                System.out.print("\nError: Vuelvelo a intentarlo.\n\n");
                sc.next();
            }
        } while (respuesta != "3");                          
    }
} 