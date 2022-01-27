package fciencias.edatos.proyecto03;

import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String entrada = "", respuesta1= "";
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
                      
                        boolean flag = true;

                        while (flag){ // Se repite hasta que vayan al menú
                            TDAMap<String, DoubleLinkedList<String>> map = new HashMap<>(700000);
                            DicctionaryReader read = new DicctionaryReader();
                            DoubleLinkedList<String> palabras = new DoubleLinkedList<>();
                            DoubleLinkedList<Player> usuarios = new DoubleLinkedList<>();
                            Player nuevo = new Player("", 0);
                            Game juego = new Game();
                    
                            System.out.println("\n---------------- Jugar ----------------\n ");
                            System.out.println("a) Secuencia creada por la computadora  ");
                            System.out.println("b) Secuencia creada por el usuario      ");
                            System.out.println("c) Menú principal                       ");

                            respuesta1 = sc.nextLine();
        
                            switch (respuesta1) {
                                case "a":                           

                                    System.out.println("\n\u269C Secuencia creada por la computadora  ");
                                    
                                    read.readMap(map);

                                    //Opción computadora
                                    char[] generada = juego.sequenceCPU();
                                    System.out.print("Cadena: ");
                                    for (char c : generada) {
                                        System.out.print(c);   
                                    }
                                    
                                    int score = 0;
                                    String palabra;
                                    juego.start();
                                    
                                   
                                    while (juego.isAlive()) {                                                                                   
                                        System.out.println("\nIngresa la palabra: ");
                                        palabra = sc.nextLine();
                                        palabra = palabra.toLowerCase();

                                        if(juego.check(generada, palabra)){
                                            //System.out.println("\nEstá dentro \u2713");
                                            if(juego.checkDiccionary(palabra, map)){
                                                System.out.println("Existe \u2713");
                                                score += juego.Score(palabra, palabras);
                                                if (juego.Score(palabra, palabras) != 0) {
                                                    palabras.add(0, palabra);
                                                }
                                            }
                                        }
                                    }                                 
                                   
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

                                    break;
                                        
                                case "b":
                                    
                                    System.out.println("\n\u269C Secuencia creada por el usuario    ");                                     
                                   
                                                                         
                                    break; 
                                    
                                case "c":
                                    flag = false;
                                    break;
                                            
                                default:
                                    System.out.println("\nError: Inserte una opción válida");
                                    break;
                                }                    
                        }break;

                    case "2":
                        System.out.println("\n-------------- Reglas ----------------");
                        System.out.println(" Para que sea válida la palabra, sólo se tomarán\n en cuenta los siguientes requisitos: ");
                        System.out.println(" \u2743 Los signos de puntuación si cuentan.");
                        System.out.println(" \u2743 Se deben usar letras de la secuencia dada.");
                        System.out.println(" \u2743 Deben pertencer al diccionario.");
                        System.out.println(" \u2743 Las palabras se puntuaran si: ");
                        System.out.println(" \u2743 No están repetidas las palabras. ");
                        System.out.println(" \u2743 Es una palabra válida. ");

                        

                    case "3":
                        System.out.println("\n------------ Estadísticas ------------");

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
