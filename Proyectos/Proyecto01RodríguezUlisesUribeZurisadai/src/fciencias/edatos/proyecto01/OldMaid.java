package fciencias.edatos.proyecto01;

import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

import java.util.List;
import java.util.Random;

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

    public int turnoUsuario(int i, int jugadorRobar){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + listaJugadores.get(i).getNombre() + ", tus cartas:\n");
        partida.add(partida.size(), "\nCartas del jugador " + listaJugadores.get(i).getNombre() + " ");
        System.out.println(listaJugadores.get(i).getCartas());
        int stolen = -1;

        do{
            try {
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
            } catch (Exception e) {
                System.out.println("\nIngresa numeros");
                sc.next();
            } if(stolen < 0 || stolen >= listaJugadores.get(jugadorRobar).getCartas().size()){
                System.out.println("\nEsa no es una opción válida");
            }
            
        }while(stolen < 0 || stolen >= listaJugadores.get(jugadorRobar).getCartas().size());

        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("\nRobaste la carta " + listaJugadores.get(jugadorRobar).getCartas().get(stolen));
        return stolen;
    }

    public int turnoMaquina(int jugadorRobar){
        Random rn = new Random();
        int stolen = rn.nextInt(listaJugadores.get(jugadorRobar).getCartas().size());  
        return stolen;
    }

    public void juego(){
        baraja.shuffle();
        // listaJugadores.get(0).setJugador(true);

        System.out.println("\n ----------------- Ronda 1 -----------------\n");
        partida.add(0, " ----------------- Ronda 1 -----------------\n");
        assign();
        partida.add(partida.size(), "\nCartas iniciales\n");
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (i != listaJugadores.size()-1) {
                partida.add(partida.size(), "\n" + listaJugadores.get(i).getNombre() + "  " +
                listaJugadores.get(i).getCartas()); 
                System.out.println("\n" + listaJugadores.get(i).getNombre() + "  " +
                listaJugadores.get(i).getCartas()); 
            } else if(i != 9 && i == listaJugadores.size()-1){
                partida.add(partida.size(), "\n" + listaJugadores.get(i).getNombre() + "  " +
                listaJugadores.get(i).getCartas() + "\n");
                System.out.println("\n" + listaJugadores.get(i).getNombre() + "  " +
                listaJugadores.get(i).getCartas() + "\n");  
            } else{
                partida.add(partida.size(), "\n" + listaJugadores.get(i).getNombre() + " " +
                listaJugadores.get(i).getCartas() + "\n");
                System.out.println("\n" + listaJugadores.get(i).getNombre() + " " +
                listaJugadores.get(i).getCartas() + "\n");
            }
        }

        for (int i = 0; i < listaJugadores.size(); i++) {
            // Comparar y eliminar cartas
            while(listaJugadores.get(i).compare()){
                partida.add(partida.size(), "\n" + listaJugadores.get(i).eliminarCartas());
            }

            // Eliminar jugadores sin cartas
            if (listaJugadores.get(i).verificarJugador()) {
                System.out.println("Se borró el jugador " + listaJugadores.get(i).getNombre());
                listaJugadores.remove(i);
            }
        }

        /** Siguientes rondas */
        int ronda = 2;
        while(listaJugadores.size() > 1){
            int stolen = 0;
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

                    System.out.print("\nCarta robada del " + listaJugadores.get(i).getNombre() + " al " +
                    listaJugadores.get(jugadorRobar).getNombre() + ": " + 
                    listaJugadores.get(jugadorRobar).getCartas().get(stolen) + "\n");
                }
                partida.add(partida.size(), "\n\nCarta robada del " + listaJugadores.get(i).getNombre() + " al " +
                                            listaJugadores.get(jugadorRobar).getNombre() + ": " + 
                                            listaJugadores.get(jugadorRobar).getCartas().get(stolen));

                // Robamos la carta
                listaJugadores.get(i).robar(stolen, listaJugadores.get(jugadorRobar));
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
                        System.out.println("Se borró el jugador " + listaJugadores.get(j).getNombre());
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
        System.out.println("\nEl jugador " + listaJugadores.get(0).getNombre() + " perdió.");
        partida.add(partida.size(), "\n\n'' " + listaJugadores.get(0).getNombre() + " se quedo con la SOLTERONA " + 
                    listaJugadores.get(0).getCartas() + " ''");
    } 

    public String toString(){
        String registro = "";
        for (int i = 0; i < partida.size(); i++) {
            registro += partida.get(i);
        }


        return registro;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String respuesta = " ";
        String partida = "No has iniciado ninguna partida\n";
        int jugadores;

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
                    while(true){
                        try {                      
                        System.out.println("\n                A JUGAR!                ");
                        System.out.println("¿Cuántos jugadores deseas en la partida?");
                        jugadores = Integer.parseInt(sc.nextLine());

                        if (jugadores == 1 || jugadores > 10) {
                            System.out.println("\n D: Deben ser más de dos jugadores ");
                            break;
                        }

                        OldMaid juego = new OldMaid(jugadores);
                        juego.juego();
                        System.out.println("\n");
                        partida = juego.toString();
                        break;
                        
                                               
                        } catch (Exception e) {
                            System.out.println("\nError: Debes ingresar un número válido.");
                        }                     
                    } 
                    break;

                    case "2":
                        System.out.println("\n *********** Historial del juego ***********\n");
                        System.out.println(partida);
                        break;
                        
                      
                    case "3":
                        System.out.println("\n  *********** Reglas del juego ***********\n");
                        System.out.println("- Cada jugador le debe robar una carta al \n" +
                                           "  jugador que tenga a la derecha.");
                        System.out.println("- Si logra formar un par, automáticamente \n"+
                                           "  se descartan esas cartas.");
                        System.out.println("- Si el jugador se queda sin cartas, tendrá\n"+
                                           "  que abandonar la partida.");   
                        System.out.println("- Pierde el jugador que quede al final.");                                                 
                        break;
                            
                    
                    case "4":
                        System.out.println("\n Hasta la próxima!");
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