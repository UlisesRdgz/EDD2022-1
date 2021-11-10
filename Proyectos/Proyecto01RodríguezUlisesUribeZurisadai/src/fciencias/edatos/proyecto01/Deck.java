package fciencias.edatos.proyecto01;

import java.util.Random;

/**
* Clase para crear la baraja.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai. 
* @version 10 Noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 01.
*/
public class Deck{

    /** Arreglo para guardar la baraja */
    Carta deck[] = new Carta[52];

    /**
     * Contructor para la baraja, con números y tipos de carta.
     */
    public Deck(){
        for (int i = 0; i < 13; i++) {
             deck[i] = new Carta(i+1, "Corazon");
             deck[i+13] = new Carta(i+1, "Pica");
             deck[i+26] = new Carta(i+1, "Diamante");
             deck[i+39] = new Carta(i+1, "Trebol");
        }  
    }

    /**
     * Devuelve la carta en la posición i.
     * @param i posición de la carta a obtener.
     * @return la carta en la posición i.
     */
    public Carta getCarta(int i){
        return deck[i];
    }

    /**
     * Mezcla las cartas de la baraja
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
     * Imprime las cartas de la baraja 
     */
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