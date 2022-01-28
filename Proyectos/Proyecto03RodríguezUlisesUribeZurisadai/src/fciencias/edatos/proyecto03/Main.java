package fciencias.edatos.proyecto03;

import java.io.Serializable;
import java.util.Scanner;

/**
* Clase principal donde se ejecutara el juego.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 1.0 Enero 2022.
* @since Estructuras de Datos 2022-1. Proyecto03.
*/
public class Main implements Serializable{
    public static void main(String[] args) {

        TDAMap<String, DoubleLinkedList<String>> map = new HashMap<>(700000);
        Data read = new Data();
        DoubleLinkedList<Player> usuarios = new DoubleLinkedList<>();
        Game juego = new Game();
        Scanner sc = new Scanner(System.in);
        String entrada = "";

        read.readMap(map);

        do {
            try {                
                System.out.println("\n-------------------------------");
                System.out.println("⑨        Nueve Letras         ⑨");
                System.out.println("-------------------------------");
                System.out.println("1) Jugar                       ");
                System.out.println("2) Reglas                      ");
                System.out.println("3) Estadísticas                ");
                System.out.println("4) Salir                       ");
                System.out.println("-------------------------------");

                entrada = sc.nextLine();

                switch (entrada) {
                    case "1":
                        String nombre = "";
                        boolean flag = true, juega = true;

                        while (flag){ // Se repite hasta que vayan al menú
                            DoubleLinkedList<String> palabras = new DoubleLinkedList<>();
                            Player nuevo = new Player("", 0);
                            char[] generada = new char[9];
                            String respuesta1 = "";

                            while (flag) {
                                System.out.println("\n---------------- Jugar ----------------\n ");
                                System.out.println("a) Secuencia creada por la computadora  ");
                                System.out.println("b) Secuencia creada por el usuario      ");
                                System.out.println("c) Volver al menú                       ");
                                
                                respuesta1 = sc.nextLine();
            
                                switch (respuesta1) {
                                    case "a":                           
                                        System.out.println("\nIngresa tu nombre:");  
                                        nombre = sc.nextLine();
                                        System.out.println("\n\u269C Secuencia creada por la computadora  ");
                                        //Opción computadora
                                        generada = juego.sequenceCPU();
                                        flag = false;
                                        break;
                                            
                                    case "b":
                                        System.out.println("\nIngresa tu nombre:");  
                                        nombre = sc.nextLine();
                                        System.out.println("\n\u269C Secuencia creada por el usuario    ");  
                                        boolean correcta = false;

                                        while(!correcta){
                                            System.out.println("\nIngresa una cadena de 9 letras:");
                                            String cadena = sc.nextLine(); 
                                            generada = juego.sequenceUser(cadena);

                                            if(generada != null)
                                                correcta = true;
                                        }                                  
                                        
                                        flag = false;
                                        break; 

                                    case "c":
                                        juega = false;
                                        flag = false;
                                        break;
                                                
                                    default:
                                        System.out.println("\nError: Inserte una opción válida");
                                        break;
                                }   
                            }
                            
                            if (juega) {
                                int score = 0, scoreFinal = 0;
                                String palabra;
                                long inicio = System.currentTimeMillis();
                                
                                do {             
                                    System.out.print("\nCadena: ");

                                    for (char c : generada) 
                                        System.out.print(Character.toUpperCase(c));  

                                    System.out.println("\nIngresa la palabra: ");
                                    palabra = sc.nextLine();
                                    palabra = palabra.toLowerCase();

                                    if(juego.check(generada, palabra)){

                                        if(juego.checkDiccionary(palabra, map)){
                                            System.out.println("\nLa palabra está dentro del diccionario. \u2713");
                                            score = juego.Score(palabra, palabras);
                                            scoreFinal += juego.Score(palabra, palabras);

                                            if (juego.Score(palabra, palabras) != 0) 
                                                palabras.add(0, "Palabra: " + palabra + " Puntaje: " + score);
                                                nuevo.setWords(palabra, score);
                                        }else
                                            System.out.println("\nPalabra incorrecta. \u2718");
                            
                                    }else
                                        System.out.println("\nPalabra incorrecta. \u2718");

                                } while(System.currentTimeMillis() - inicio < 60000);                              
                                    
                                System.out.println("\nTu puntuación es de: " + scoreFinal);

                                if (palabras.isEmpty()) 
                                    nuevo.setWords(":(", 0);
                                
                                System.out.println("\nLas mejores tres palabras fueron: ");
                                nuevo.setTopWords(nuevo.topWords());

                                nuevo.setName(nombre);
                                nuevo.setScore(scoreFinal);

                                if (read.readData() != null) 
                                    usuarios = read.readData();

                                read.saveData(usuarios, nuevo);          
                            }
                        }

                    break;

                    case "2":
                        System.out.println("\n-------------- Reglas ----------------");
                        System.out.println(" Para que sea válida la palabra, sólo se tomarán\n en cuenta los siguientes requisitos: ");
                        System.out.println(" \u2743 Los signos de puntuación si cuentan.");
                        System.out.println(" \u2743 Se deben usar letras de la secuencia dada.");
                        System.out.println(" \u2743 Deben pertencer al diccionario.");
                        System.out.println(" \u2743 Las palabras se puntuaran si: ");
                        System.out.println(" \u2743 No están repetidas las palabras. ");
                        System.out.println(" \u2743 Es una palabra válida. ");
                        break;

                    case "3":
                        System.out.println("\n------------ Estadísticas ------------\n");

                        if (read.readData() != null) {
                            usuarios = read.readData();
                            juego.topPlayers(usuarios);
                        } else 
                            System.out.println("No hay partidas registradas.");

                        break;    

                    case "4":
                        System.out.println("\nHasta la próximaa! :)");
                        return;            
                
                    default:
                        System.out.println("\nError: Inserte una opción válida");
                        break;
                }


            } catch (Exception e) {
                System.out.println("\nError: Opción inválida.");
                sc.nextLine();
            }

        } while (entrada != "4");        
    }
}
