package fciencias.edatos.proyecto01;

public class Jugador {

    /** Nombre del jugador */
    String nombre = "";

    /** Lista de cartas */
    TDAList<Carta> cartas = new DoubleLinkedList<>();

    /**
     * Constructor de la clase
     * @param nombre
     */
    public Jugador(String nombre){
        this.nombre = nombre;
    }

    /**
     * Obtener nombre
     * @return
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * 
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
     * Asignar cartas al jugador
     * @param cartas
     */
    public void setCartas(TDAList<Carta> cartas){
        this.cartas = cartas;
    }
}