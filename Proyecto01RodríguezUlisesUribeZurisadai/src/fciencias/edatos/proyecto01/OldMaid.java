package fciencias.edatos.proyecto01;

import java.util.Scanner;

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
    }

    /**
     * 
     */
    public void agregaJugador(String name, int position){
        listaJugadores.add(position, new Jugador(name));
    }

    public void assign(){

        /** Lista de cartas del jugador */
        TDAList<Carta> cartasJugador1 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador2 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador3 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador4 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador5 = new DoubleLinkedList<>();

        int numero = 0, numero2 = 0, numero3 = 0, numero4 = 0, numero5 = 0;
        
        if (jugadores == 2) {
            numero = 26;
            numero2 = 51;
        }else if (jugadores == 3){
            numero = 17;
            numero2 = 34;
            numero3 = 51;
        }else if (jugadores == 4) {
            numero = 13;
            numero2 = 26;
            numero3 = 39;
            numero4 = 51;
        }else if (jugadores == 5) {
            numero = 11;
            numero2 = 21;
            numero3 = 31;
            numero4 = 41;
            numero5 = 51;
        }

        System.out.println(numero + " " + numero2);
        
        /** Primer jugador */
        for (int i = 0; i < numero; i++) {
            cartasJugador1.add(i, baraja.getCarta(i));
        }
        listaJugadores.get(0).setCartas(cartasJugador1);

        /** Segundo jugador */
        for (int i = numero, j = 0; i < numero2; i++, j++) {
            cartasJugador2.add(j, baraja.getCarta(i));
        }
        listaJugadores.get(1).setCartas(cartasJugador2);
        
        if (jugadores >= 3) {
            /** Tecer jugardor */
            for (int i = numero2, j = 0; i < numero3; i++, j++) {
                cartasJugador3.add(j, baraja.getCarta(i));
            }
            listaJugadores.get(2).setCartas(cartasJugador3);

            if (jugadores >= 4) {
                /** Cuarto jugador */
                for (int i = numero3, j = 0; i < numero4; i++, j++) {
                    cartasJugador4.add(j, baraja.getCarta(i));
                }
                listaJugadores.get(3).setCartas(cartasJugador4);

                if (jugadores >= 5) {
                    /** Cuarto jugador */
                    for (int i = numero4, j = 0; i < numero5; i++, j++) {
                        cartasJugador5.add(j, baraja.getCarta(i));
                    }
                    listaJugadores.get(4).setCartas(cartasJugador5);
                }
            }
        }
    }

    public void juego(){
        baraja.shuffle();
        agregaJugador("Jugador1", 0);
        agregaJugador("Jugador2", 1);
        agregaJugador("Jugador3", 2);
        agregaJugador("Jugador4", 3);

        System.out.println("Primera ronda");
        assign();

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

                System.out.println(i);
                System.out.println(listaJugadores.size());
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
                    if (listaJugadores.get(j).verificarJugador()) {
                        System.out.println("Se borró el jugador " + listaJugadores.get(j).getNombre());
                        listaJugadores.remove(j);
                        i = (i-1% listaJugadores.size() + listaJugadores.size())% listaJugadores.size();
                    }
                }
            }
        }
        System.out.println("El jugador " + listaJugadores.get(0).getNombre() + " perdio.");
    } 
    
    public static void main(String[] args) {
    
        // OldMaid juego = new OldMaid(4);
        
        // juego.juego();

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