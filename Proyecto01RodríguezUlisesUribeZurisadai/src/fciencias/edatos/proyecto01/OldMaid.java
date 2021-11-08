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
        agregaJugador("Ulises", 0);
        agregaJugador("Zuri", 1);
        agregaJugador("Cuau", 2);
        agregaJugador("Emi", 3);

        assign();
        System.out.println(listaJugadores.get(0).getNombre());
        System.out.println(listaJugadores.get(0).getCartas());
        System.out.println("\n");

        System.out.println(listaJugadores.get(1).getNombre());
        System.out.println(listaJugadores.get(1).getCartas());
        System.out.println("\n");

        System.out.println(listaJugadores.get(2).getNombre());
        System.out.println(listaJugadores.get(2).getCartas());
        System.out.println("\n");

        System.out.println(listaJugadores.get(3).getNombre());
        System.out.println(listaJugadores.get(3).getCartas());
        System.out.println("\n");

        baraja.printDeck();
    }
    
    public static void main(String[] args) {
        
        OldMaid juego = new OldMaid(4);
        
        juego.juego();

        // Scanner respuesta = new Scanner(System.in);

        // do {
        //     try {
                
        //         System.out.println();
        //     } catch (Exception e) {
        //         //TODO: handle exception
        //     }
        // } while (condition);
    }

}