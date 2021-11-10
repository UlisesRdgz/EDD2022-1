package fciencias.edatos.proyecto01;

/**
* Clase donde se crea la carta.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 0.6 Noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 01.
*/
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
     * Obtener el número de la carta.
     * @return el número de la carta.
     */
    public int getNum() {
        return numCarta;
    }

    /**
     * Obtener el tipo de carta.
     * @return el tipo de carta
     */
    public String getType() {
        return type;
    }  
    
    /**
     * Da el número de la carta.
     * @return el número de la carta
     */
    public void setNum(int numCarta) {
        this.numCarta = numCarta;
    }

    /**
     * Da el tipo de carta.
     * @return el tipo de carta
     */
    public void setType(String type) {
        this.type = type;
    }   

    /**
     * Imprime las cartas dependiendo de su número y tipo.
     * @return la carta correspondiente.
     */
    public String toString(){
        String carta = "";

        switch (type) {
            case "Pica":
                if (numCarta == 1) 
                    carta = "\uD83C\uDCA1";
                else if (numCarta == 2)
                    carta = "\uD83C\uDCA2";
                else if (numCarta == 3)
                    carta = "\uD83C\uDCA3";
                else if (numCarta == 4)
                    carta = "\uD83C\uDCA4";
                else if (numCarta == 5)
                    carta = "\uD83C\uDCA5";
                else if (numCarta == 6)
                    carta = "\uD83C\uDCA6";
                else if (numCarta == 7)
                    carta = "\uD83C\uDCA7";
                else if (numCarta == 8)
                    carta = "\uD83C\uDCA8";
                else if (numCarta == 9)
                    carta = "\uD83C\uDCA9";
                else if (numCarta == 10)
                    carta = "\uD83C\uDCAA";
                else if (numCarta == 11)
                    carta = "\uD83C\uDCAB";
                else if (numCarta == 12)
                    carta = "\uD83C\uDCAD";
                else if (numCarta == 13)
                    carta = "\uD83C\uDCAE";

                break;

            case "Corazon":
                if (numCarta == 1) 
                    carta = "\uD83C\uDCB1";
                else if (numCarta == 2)
                    carta = "\uD83C\uDCB2";
                else if (numCarta == 3)
                    carta = "\uD83C\uDCB3";
                else if (numCarta == 4)
                    carta = "\uD83C\uDCB4";
                else if (numCarta == 5)
                    carta = "\uD83C\uDCB5";
                else if (numCarta == 6)
                    carta = "\uD83C\uDCB6";
                else if (numCarta == 7)
                    carta = "\uD83C\uDCB7";
                else if (numCarta == 8)
                    carta = "\uD83C\uDCB8";
                else if (numCarta == 9)
                    carta = "\uD83C\uDCB9";
                else if (numCarta == 10)
                    carta = "\uD83C\uDCBA";
                else if (numCarta == 11)
                    carta = "\uD83C\uDCBB";
                else if (numCarta == 12)
                    carta = "\uD83C\uDCBD";
                else if (numCarta == 13)
                    carta = "\uD83C\uDCBE";
                break;

            case "Diamante":
                if (numCarta == 1) 
                    carta = "\uD83C\uDCC1";
                else if (numCarta == 2)
                    carta = "\uD83C\uDCC2";
                else if (numCarta == 3)
                    carta = "\uD83C\uDCC3";
                else if (numCarta == 4)
                    carta = "\uD83C\uDCC4";
                else if (numCarta == 5)
                    carta = "\uD83C\uDCC5";
                else if (numCarta == 6)
                    carta = "\uD83C\uDCC6";
                else if (numCarta == 7)
                    carta = "\uD83C\uDCC7";
                else if (numCarta == 8)
                    carta = "\uD83C\uDCC8";
                else if (numCarta == 9)
                    carta = "\uD83C\uDCC9";
                else if (numCarta == 10)
                    carta = "\uD83C\uDCCA";
                else if (numCarta == 11)
                    carta = "\uD83C\uDCCB";
                else if (numCarta == 12)
                    carta = "\uD83C\uDCCD";
                else if (numCarta == 13)
                    carta = "\uD83C\uDCCE";
              
                break;

            case "Trebol":
                if (numCarta == 1) 
                    carta = "\uD83C\uDCD1";
                else if (numCarta == 2)
                    carta = "\uD83C\uDCD2";
                else if (numCarta == 3)
                    carta = "\uD83C\uDCD3";
                else if (numCarta == 4)
                    carta = "\uD83C\uDCD4";
                else if (numCarta == 5)
                    carta = "\uD83C\uDCD5";
                else if (numCarta == 6)
                    carta = "\uD83C\uDCD6";
                else if (numCarta == 7)
                    carta = "\uD83C\uDCD7";
                else if (numCarta == 8)
                    carta = "\uD83C\uDCD8";
                else if (numCarta == 9)
                    carta = "\uD83C\uDCD9";
                else if (numCarta == 10)
                    carta = "\uD83C\uDCDA";
                else if (numCarta == 11)
                    carta = "\uD83C\uDCDB";
                else if (numCarta == 12)
                    carta = "\uD83C\uDCDD";
                else if (numCarta == 13)
                    carta = "\uD83C\uDCDE";
                break;
                
            default:
                break;
        }
        
        return carta;
    }
}
