package fciencias.edatos.proyecto03;

import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable{
    public static void main(String[] args) {

        TDAMap<String, DoubleLinkedList<String>> map = new HashMap<>(700000);
        DicctionaryReader read = new DicctionaryReader();
        DoubleLinkedList<String> palabras = new DoubleLinkedList<>();
        DoubleLinkedList<Player> usuarios = new DoubleLinkedList<>();
        Player nuevo = new Player("", 0);
        Game juego = new Game();
        Scanner sc = new Scanner(System.in);
        
        read.readMap(map);

        //Opción computadora
        char[] generada = juego.Sequence();
        System.out.print("Caneda: ");
        for (char c : generada) {
            System.out.print(Character.toUpperCase(c));   
        }

        int score = 0;
        String palabra;
        juego.start();

        // while (juego.isAlive()) {
        //     System.out.println("\nIngresa la palabra");
        //     palabra = sc.next();
        //     palabra = palabra.toLowerCase();

        //     if(juego.check(generada, palabra)){
        //         System.out.println("Esta dentro");
        //         if(juego.checkDiccionary(palabra, map)){
        //             System.out.println("Existe");
        //             score += juego.Score(palabra, palabras);
        //             if (juego.Score(palabra, palabras) != 0) {
        //                 palabras.add(0, palabra);
        //             }
        //         }
        //     }
        // } 

        System.out.println("\nTu score es de: " + score);
        System.out.println(palabras);

        nuevo.setScore(180);
        nuevo.setName("Zuri");

        if (read.readData() != null) {
            usuarios = read.readData();
        }

        read.saveData(usuarios, nuevo);
        System.out.println(read.readData());

        juego.topPlayers(usuarios);
        
    }
}
