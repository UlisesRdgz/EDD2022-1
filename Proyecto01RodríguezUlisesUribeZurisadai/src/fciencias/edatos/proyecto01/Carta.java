package fciencias.edatos.proyecto01;

public class Carta {
    /** Valor de la carta */
    int numCarta;

    /** Tipo de la carta */
    String type;

    /**
     * Contructor de la clase carta.
     * @param numCarta
     * @param type
     */
    public Carta(int numCarta, String type){
        this.numCarta = numCarta;
        this.type = type;
    }

    /**
     * 
     * @return el número de la carta
     */
    public int getNum() {
        return numCarta;
    }

    /**
     * 
     * @return el tipo de carta
     */
    public String getType() {
        return type;
    }    

    /**
     * 
     */
    public String toString(){
        String carta = numCarta + "" + type;
        return carta;
    }
}