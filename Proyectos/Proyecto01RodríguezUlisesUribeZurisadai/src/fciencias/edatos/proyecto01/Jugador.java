package fciencias.edatos.proyecto01;

/**
* Clase que guarda datos del Jugador.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 10 Noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 01.
*/
public class Jugador {

    /** Nombre del jugador */
    String nombre = "";

    /** Lista de cartas */
    TDAList<Carta> cartas = new DoubleLinkedList<>();

    /** Cartas comparadas */
    Carta uno, dos;

    /** Jugador usuario */
    boolean usuario = false;

    /**
     * Constructor del jugador.
     * @param nombre del jugador.
     */
    public Jugador(String nombre){
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre del jugador.
     * @return el nombre del jugador.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Devuelve las cartas del jugador.
     * @return las cartas del jugador.
     */
    public TDAList<Carta> getCartas(){
        return cartas;
    }

    /** 
     * Indica si el jugador es usuario o máquina.
     */
    public void setJugador(boolean usuario){
        this.usuario = usuario;
    }

    /** 
     * Verifica si es usuario o máquina.
     * @return true si es usuario, false en otro caso.
     */
    public boolean isUser(){
        return usuario;
    }

    /**
     * Compara las cartas de los jugadores.
     * @return true si hay cartas con el mismo número,
     *         false en el caso contrario.
     */
    public boolean compare(){
        int aux = 1;
        for (int i = 0; i < cartas.size()-1; i++) {
            for (int j = aux; j < cartas.size(); j++) {
                if (cartas.get(i).getNum() == cartas.get(j).getNum()){
                    uno = cartas.get(i);
                    dos = cartas.get(j);
                    return true;
                }
            }
            aux++;
        }
        return false;
    }

    /**
     * Método que elimina las cartas iguales de cada jugador.
     * @return las cartas par.
     */
    public String eliminarCartas(){
        String cartasIguales = "";
        for (int i = 0; i < cartas.size(); i++) {
            if (uno.equals(cartas.get(i))){
                
                if (!nombre.equals("Jugador 10")){
                    cartasIguales += "El " + nombre + " obtuvo el siguiente par de cartas:  " + uno + " " ;
                    System.out.print("El " + nombre + " obtuvo el siguiente par de cartas:  " + uno + " ");
                }else{ 
                    cartasIguales += "El " + nombre + " obtuvo el siguiente par de cartas: " + uno + " " ;
                    System.out.print("El " + nombre + " obtuvo el siguiente par de cartas: " + uno + " ");
                }
                
                cartas.remove(i);
            }
            
            if (dos.equals(cartas.get(i))){
                if (isUser()){
                    System.out.println(dos);
                    cartasIguales += dos;
                }else{
                    System.out.println(dos);
                    cartasIguales += dos;
                }
                cartas.remove(i);
            }
        }
        return cartasIguales;
    }

    /**
     * Método para robar las cartas al jugador de la izquierda.
     * @param steal carta que se desea robar.
     * @param stolen Jugador al que robaremos la carta.
     */
    public void robar(int steal, Jugador stolen){
        Carta robada = stolen.getCartas().get(steal);
        stolen.getCartas().remove(steal);
        cartas.add(cartas.size(), robada);
    }

    /**
     * Verificar si el jugador sigue teniendo cartas.
     * @return true si ya no tiene, false en caso de que aún tenga.
     */
    public boolean verificarJugador(){
        if (cartas.isEmpty())
            return true;
        return false;
    }

    /**
     * Intercambia las cartas del jugador.
     * @param carta1 primera carta,
     * @param carta2 segunda carta.
     */
    public void rearrange(int carta1, int carta2){
        Carta aux = new Carta(cartas.get(carta1).getNum(), cartas.get(carta1).getType());
        cartas.get(carta1).setNum(cartas.get(carta2).getNum());
        cartas.get(carta1).setType(cartas.get(carta2).getType());
        cartas.get(carta2).setNum(aux.getNum());
        cartas.get(carta2).setType(aux.getType());
    }
}