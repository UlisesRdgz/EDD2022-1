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
        TDAList<Carta> cartasJugador6 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador7 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador8 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador9 = new DoubleLinkedList<>();
        TDAList<Carta> cartasJugador10 = new DoubleLinkedList<>();

        int numero = 0, numero2 = 0, numero3 = 0, numero4 = 0, numero5 = 0,
            numero6 = 0, numero7 = 0, numero8 = 0, numero9 = 0, numero10 = 0;
        
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
        }else if (jugadores == 6) {
            numero = 9;
            numero2 = 18;
            numero3 = 27;
            numero4 = 35;
            numero5 = 43;
            numero6 = 51;
        }else if (jugadores == 7) {
            numero = 8;
            numero2 = 16;
            numero3 = 23;
            numero4 = 30;
            numero5 = 37;
            numero6 = 44;
            numero7 = 51;
        }else if (jugadores == 8) {
            numero = 7;
            numero2 = 14;
            numero3 = 21;
            numero4 = 27;
            numero5 = 33;
            numero6 = 39;
            numero7 = 45;
            numero8 = 51;
        }else if (jugadores == 9) {
            numero = 6;
            numero2 = 12;
            numero3 = 18;
            numero4 = 24;
            numero5 = 30;
            numero6 = 36;
            numero7 = 41;
            numero8 = 46;
            numero9 = 51;
        }else if (jugadores == 10) {
            numero = 6;
            numero2 = 11;
            numero3 = 16;
            numero4 = 21;
            numero5 = 26;
            numero6 = 31;
            numero7 = 36;
            numero8 = 41;
            numero9 = 46;
            numero10 = 51;
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
                    /** Quinto jugador */
                    for (int i = numero4, j = 0; i < numero5; i++, j++) {
                        cartasJugador5.add(j, baraja.getCarta(i));
                    }
                    listaJugadores.get(4).setCartas(cartasJugador5); 

                    if (jugadores >= 6) {
                        /** Sexto jugador */
                        for (int i = numero5, j = 0; i < numero6; i++, j++) {
                            cartasJugador6.add(j, baraja.getCarta(i));
                        }
                        listaJugadores.get(5).setCartas(cartasJugador6);

                        if (jugadores >= 7) {
                            /** Séptimo jugador */
                            for (int i = numero6, j = 0; i < numero7; i++, j++) {
                                cartasJugador7.add(j, baraja.getCarta(i));
                            }
                            listaJugadores.get(6).setCartas(cartasJugador7);

                            if (jugadores >= 8) {
                                /** Octavo jugador */
                                for (int i = numero7, j = 0; i < numero8; i++, j++) {
                                    cartasJugador8.add(j, baraja.getCarta(i));
                                }
                                listaJugadores.get(7).setCartas(cartasJugador8);

                                if (jugadores >= 9) {
                                    /** nueve jugadores */
                                    for (int i = numero8, j = 0; i < numero9; i++, j++) {
                                        cartasJugador9.add(j, baraja.getCarta(i));
                                    }
                                    listaJugadores.get(8).setCartas(cartasJugador9);

                                    if (jugadores == 10) {
                                        /** Diez jugadores */
                                        for (int i = numero9, j = 0; i < numero10; i++, j++) {
                                            cartasJugador8.add(j, baraja.getCarta(i));
                                        }
                                        listaJugadores.get(9).setCartas(cartasJugador10);
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
        agregaJugador("Ulises", 0);
        agregaJugador("Zuri", 1);
        agregaJugador("Cuau", 2);
        agregaJugador("Emi", 3);
        agregaJugador("name", 4);
        agregaJugador("Ulises", 5);
        agregaJugador("Z", 6);
        agregaJugador("C", 7);
        agregaJugador("E", 8);
        agregaJugador("zz", 9);

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
        
        OldMaid juego = new OldMaid(9);
        
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