package fciencias.edatos.proyecto01;

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

    /** */
    public void setJugador(boolean usuario){
        this.usuario = usuario;
    }

    /** */
    public boolean isUser(){
        return usuario;
    }

    /**
     * 
     * @return
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

    public void eliminarCartas(){
        for (int i = 0; i < cartas.size(); i++) {
            if (uno.equals(cartas.get(i)))
                cartas.remove(i);
            
            if (dos.equals(cartas.get(i)))
                cartas.remove(i);
        }
    }

    public void robar(int steel, Jugador stolen){
        Carta robada = stolen.getCartas().get(steel);
        stolen.getCartas().remove(steel);
        cartas.add(cartas.size(), robada);
    }

    public boolean verificarJugador(){
        if (cartas.isEmpty())
            return true;
        return false;
    }
}