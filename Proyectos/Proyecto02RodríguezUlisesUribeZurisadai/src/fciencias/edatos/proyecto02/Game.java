package fciencias.edatos.proyecto02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Data juego = new Data();
        String entrada = "", entrada1 = "", respuesta = "", respuesta1 = "";
        Scanner sc = new Scanner(System.in);
        BinarySearchTree<Integer, Question> arbol = null;
        Question[] preguntas = null;

        do {
            try {
                System.out.println("\n------------------------");
                System.out.println("           20Q           ");
                System.out.println("------------------------ ");
                System.out.println("1) Jugar                ");
                System.out.println("2) Historial            ");
                System.out.println("3) Salir                ");
                System.out.println("------------------------");
    
                respuesta = sc.nextLine();

                switch (respuesta) {
                    case "1": //Juego con la computadora
                        int contador = 0;

                        System.out.println("\n\u2B50 Personajes de Disney \u2B50");
        
                        /** Leemos el árbol del archivo */
                        ObjectInputStream lee = new ObjectInputStream(new FileInputStream("Preguntas/Personajes.txt"));
                        arbol = (BinarySearchTree<Integer, Question>) lee.readObject();
                        lee.close();   
        
                        preguntas = juego.toArray(arbol);

                        for (int i = 0; i < preguntas.length; i++) 
                            preguntas[i].unVisit();
        
                        arbol.inicio();
                        contador++;
        
                        while (contador < 20 && !arbol.isLeaf()) {
                            System.out.println("\n" + arbol.actual().getPregunta() + "\nPor favor escribe: S/N");
                            entrada = sc.nextLine();
        
                            if (entrada.equals("S") || entrada.equals("N")) {

                                if (entrada.equals("S")) 
                                    arbol.moveLeft();
                
                                else 
                                    arbol.moveRigth();

                                contador++;
                            }
                        } 
                          
                        do {
                            System.out.println("\n¿Tu personaje es " + arbol.actual().getPregunta() + "?\nPor favor escribe: S/N");
                            entrada1 = sc.nextLine();
                        } while (!entrada1.equals("S") && !entrada1.equals("N"));
                        
        
                        if (entrada1.equals("S")) 
                            System.out.println("\nPersonaje adivinado \uD83D\uDC4D");
        
                        else if (entrada1.equals("N") && contador < 20){
                            System.out.println("\nEscribe una nueva pregunta para identificar tu personaje");
                            String pregunta = sc.nextLine();
                            System.out.println("\nEscribe el personaje en el que pensaste");
                            String personaje = sc.nextLine();
        
                            Question pregunta1 = new Question(pregunta, LocalDate.now(), LocalTime.now(), false);
                            Question pregunta2 = new Question(personaje, LocalDate.now(), LocalTime.now(), true);
                            Question aux = arbol.change(pregunta1);
                            arbol.addLeft(pregunta2);
                            arbol.addRigth(aux);
        
                            ObjectOutputStream escribe2 = new ObjectOutputStream(new FileOutputStream("Preguntas/Personajes.txt"));
                            escribe2.writeObject(arbol);
                            escribe2.flush();
                            escribe2.close();

                        } else
                            System.out.println("Personaje no adivinado \uD83D\uDC4E");
                            
                    break;

                    case "2": //Listados de Datos

                        /** Leemos el árbol del archivo */
                        ObjectInputStream lee2 = new ObjectInputStream(new FileInputStream("Preguntas/Personajes.txt"));
                        arbol = (BinarySearchTree<Integer, Question>) lee2.readObject();
                        lee2.close();   

                        preguntas = juego.toArray(arbol);

                        for (int i = 0; i < preguntas.length; i++) 
                            preguntas[i].unVisit();

                        boolean flag = true;

                        while (flag){ // Se repite hasta que vayan al menú
                            System.out.println("\n-------------- Historial --------------\n ");
                            System.out.println("a) Listado de preguntas  - Alfabético ");
                            System.out.println("b) Listado de preguntas  - Fecha      ");
                            System.out.println("c) Listado de personajes - Alfabético ");
                            System.out.println("d) Listado de personajes - Fecha      ");
                            System.out.println("e) Volver al menú                    ");

                            respuesta1 = sc.nextLine();
        
                            switch (respuesta1) {
                                case "a":
                                    System.out.println("\n\u2728 Preguntas ordenadas Alfabéticamente \u2728\n");
                                    juego.preguntasAlf(arbol, preguntas);
                                    break;
                                        
                                case "b":
                                    System.out.println("\n\u2728 Preguntas ordenadas por Fecha \u2728\n"); 
                                    juego.preguntasFecha(arbol, preguntas);                                       
                                    break; 
                                    
                                case "c":
                                    System.out.println("\n\u2728 Personajes ordenados Alfabéticamente \u2728\n");
                                    juego.respuestasAlf(arbol, preguntas);                                        
                                    break;  
                                        
                                case "d":
                                    System.out.println("\n\u2728 Personajes ordenados por Fecha \u2728\n");
                                    juego.respuestasFecha(arbol, preguntas);                                        
                                    break;    
                                    
                                case "e":
                                    flag = false;
                                    break;
                                            
                                default:
                                    System.out.println("\nError: Inserte una letra válida");
                                    break;
                                }                    
                        }break;
                    
                    case "3": // Salir 
                        return;

                    default:
                        System.out.println("\nError: Inserte un número válido.");
                        break;
                }
           
            } catch (Exception e) {
                System.out.println("\nError: Opción inválida.");
                sc.nextLine();
            }    
        } while (respuesta != "3");        
    }
} 