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

                        /** Crea el árbol original */
                        // Question p1 = new Question("¿Es de sexo femenino?", LocalDate.now(), LocalTime.now(), false);
                        // Question p2 = new Question("¿Es humana?", LocalDate.now(), LocalTime.now(), false);
                        // Question p3 = new Question("¿Es princesa?", LocalDate.now(), LocalTime.now(), false);
                        // Question p4 = new Question("Blancanieves", LocalDate.now(), LocalTime.now(), true);
                        // Question p5 = new Question("Maléfica", LocalDate.now(), LocalTime.now(), true);

                        // Question p6 = new Question("¿Es animal?", LocalDate.now(), LocalTime.now(), false);
                        // Question p7 = new Question("Nala", LocalDate.now(), LocalTime.now(), true);
                        // Question p8 = new Question("¿Es un juguete?", LocalDate.now(), LocalTime.now(), false);
                        // Question p9 = new Question("Betty", LocalDate.now(), LocalTime.now(), true);
                        // Question p10 = new Question("Señora Potts", LocalDate.now(), LocalTime.now(), true);

                        // Question p11 = new Question("¿Es humano?", LocalDate.now(), LocalTime.now(), false);
                        // Question p12 = new Question("¿Vive en la selva?", LocalDate.now(), LocalTime.now(), false);
                        // Question p13 = new Question("Tarzan", LocalDate.now(), LocalTime.now(), true);
                        // Question p14 = new Question("FLynn Rider", LocalDate.now(), LocalTime.now(), true);

                        // Question p15 = new Question("¿Es animal?", LocalDate.now(), LocalTime.now(), false);
                        // Question p16 = new Question("Sebastián", LocalDate.now(), LocalTime.now(), true);
                        // Question p17 = new Question("¿Es un carro?", LocalDate.now(), LocalTime.now(), false);
                        // Question p18 = new Question("Rayo McQueen", LocalDate.now(), LocalTime.now(), true);
                        // Question p19 = new Question("Olaf", LocalDate.now(), LocalTime.now(), true);
                        // BinarySearchTree<Integer, Question> q20 = new BinarySearchTree<>();

                        // q20.insert(p1, 20);
                        // q20.insert(p2, 10);
                        // q20.insert(p3, 5);
                        // q20.insert(p4, 2);
                        // q20.insert(p5, 7);
                        // q20.insert(p6, 15);
                        // q20.insert(p7, 12);
                        // q20.insert(p8, 18);
                        // q20.insert(p9, 16);
                        // q20.insert(p10, 19);
                        // q20.insert(p11, 30);
                        // q20.insert(p12, 25);
                        // q20.insert(p13, 22);
                        // q20.insert(p14, 28);
                        // q20.insert(p15, 35);
                        // q20.insert(p16, 32);
                        // q20.insert(p17, 38);
                        // q20.insert(p18, 37);
                        // q20.insert(p19, 39);
        
                        // FileOutputStream file = new FileOutputStream("Preguntas/Personajes.txt");
                        // ObjectOutputStream escribe = new ObjectOutputStream(file);
                        // escribe.writeObject(q20);
                        // escribe.flush();
                        // escribe.close();

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