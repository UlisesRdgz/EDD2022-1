package fciencias.edatos.proyecto01;

import java.util.Random;

public class Deck{

    /** Arreglo para guardar la baraja */
    Carta deck[] = new Carta[52];

    /**
     * Contructor
     */
    public Deck(){
        for (int i = 0; i < 13; i++) {
             deck[i] = new Carta(i+1, "Corazon");
             deck[i+13] = new Carta(i+1, "Pica");
             deck[i+26] = new Carta(i+1, "Diamante");
             deck[i+39] = new Carta(i+1, "Trebol");
        }  
    }

    public Carta getCarta(int i){
        return deck[i];
    }

    /**
     * Mezclar
     */
    public void shuffle(){
        Carta aux;
        Random rn = new Random();

        for (int i = 0; i < 52; i++) {
            int random = rn.nextInt(52);
            aux = deck[0];
            deck[0] = deck[random];
            deck[random] = aux; 
        }
    }
    
    /**
     * Compara dos cartas
     * @param uno
     * @param dos
     * @return
     */
    public boolean compare(Carta uno, Carta dos){
        if (uno.getNum() == dos.getNum()) 
            return true;
        return false;
    }

    public void discard(){

        
    }

    public void printDeck(){
        for (int i = 0; i < 13; i++) {
            System.out.print(deck[i] + " " );  
        }
        System.out.println();
        for (int i = 0; i < 13; i++) {
            System.out.print(deck[i+13] + " " );
        }
        System.out.println();
        for (int i = 0; i < 13; i++) {
            System.out.print(deck[i+26] + " " );
        }
        System.out.println();
        for (int i = 0; i < 13; i++) {
            System.out.print(deck[i+39] + " " );
        }
    }    
}