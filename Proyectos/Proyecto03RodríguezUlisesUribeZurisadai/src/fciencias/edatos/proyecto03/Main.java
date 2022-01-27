package fciencias.edatos.proyecto03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TDAMap<String, DoubleLinkedList<String>> map = new HashMap<>(700000);
        DicctionaryReader read = new DicctionaryReader();
        DoubleLinkedList<String> palabras = new DoubleLinkedList<>();
        Verify verifica = new Verify();
        Game juego = new Game();
        Scanner sc = new Scanner(System.in);
        
        read.readMap(map);

        //Opción computadora
        char[] generada = juego.Sequence();
        System.out.print("Caneda: ");
        for (char c : generada) {
            System.out.print(c);   
        }

        int count = 0, score = 0;
        String palabra;
        juego.start();

        while(juego.isAlive()) {

            System.out.println("\nIngresa la palabra");
            palabra = sc.nextLine();
            palabra = palabra.toLowerCase();

            // if(verifica.Check(generada, palabra)){
            //     System.out.println("Esta dentro");
                if(verifica.CheckDiccionary(palabra, map)){
                    System.out.println("Existe");
                    score += juego.Score(palabra, palabras);
                    if (juego.Score(palabra, palabras) != 0) {
                        palabras.add(0, palabra);
                    }
                }
            // }
            count++;
        }

        System.out.println(score);
    }
}
