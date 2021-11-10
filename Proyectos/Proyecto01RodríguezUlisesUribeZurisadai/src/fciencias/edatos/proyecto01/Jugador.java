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

    /** Jugador real */
    boolean usuario = false;

    /**
     * Constructor de la clase
     * @param nombre
     */
    public Jugador(String nombre){
        this.nombre = nombre;
    }

    /**
     * Obtener nombre.
     * @return el nombre dado.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Da las cartas
     * @param cartas
     */
    public TDAList<Carta> getCartas(){
        return cartas;
    }

    /**
     * Establecer el nombre del jugador
     * @param nombre
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Le asigna cartas al jugador
     * @param cartas
     */
    public void setCartas(TDAList<Carta> cartas){
        this.cartas = cartas;
    }

    /** 
     * Para indicar que es el usuario quien jugará. 
     */
    public void setJugador(boolean usuario){
        this.usuario = usuario;
    }

    /** 
     * @return al usuario que jugará
     */
    public boolean isUser(){
        return usuario;
    }

    /**
     * Compara las cartas de los jugadores.
     * @return false si no hay cartas con el mismo número,
     * true en el caso contrario.
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
     * Método que elimina las cartas par de cada jugador.
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
     * @param steel
     * @param stolen
     */
    public void robar(int steel, Jugador stolen){
        Carta robada = stolen.getCartas().get(steel);
        stolen.getCartas().remove(steel);
        cartas.add(cartas.size(), robada);
    }

    /**
     * Método para verificar que el jugador sigue teniendo cartas.
     * @return falso si tiene cartas, true si no tiene.
     */
    public boolean verificarJugador(){
        if (cartas.isEmpty())
            return true;
        return false;
    }

    /**
     * Reorganiza las cartas del jugador.
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