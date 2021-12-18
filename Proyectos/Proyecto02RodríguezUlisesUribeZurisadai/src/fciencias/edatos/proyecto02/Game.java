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
        int contador = 0;
        String entrada = "", entrada1 = "", respuesta = "", respuesta1 = "";
        Scanner sc = new Scanner(System.in);
        BinarySearchTree<Integer, Question> arbol = null;
        Question[] preguntas = null;

        Question zuri = new Question("¿Sabe nadar?", LocalDate.now(), LocalTime.now(), false);
        Question zuri1 = new Question("¿Es un pez?", LocalDate.now(), LocalTime.now(), true);
        Question zuri2 = new Question("¿Ladra?", LocalDate.now(), LocalTime.now(), false);
        Question zuri3 = new Question("¿Es un perro?", LocalDate.now(), LocalTime.now(), true);
        Question zuri4 = new Question("¿Es un gato?", LocalDate.now(), LocalTime.now(), true);

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

                        // BinarySearchTree<Integer, Question> q20 = new BinarySearchTree<>();

                        // q20.insert(zuri, 10);
                        // q20.insert(zuri1, 5);
                        // q20.insert(zuri2, 15);
                        // q20.insert(zuri3, 12);
                        // q20.insert(zuri4, 17);
        
                        // FileOutputStream file = new FileOutputStream("Preguntas/bebe.txt");
                        // ObjectOutputStream escribe = new ObjectOutputStream(file);
                        // escribe.writeObject(q20);
                        // escribe.flush();
                        // escribe.close();
        
                        /** Leemos el árbol del archivo */
                        ObjectInputStream lee = new ObjectInputStream(new FileInputStream("Preguntas/bebe.txt"));
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
                            System.out.println("\n" + arbol.actual().getPregunta() + "\nPor favor escribe: S/N");
                            entrada1 = sc.nextLine();
                        } while (!entrada1.equals("S") && !entrada1.equals("N"));
                        
        
                        if (entrada1.equals("S")) 
                            System.out.println("\nHaz ganado");
        
                        else if (entrada1.equals("N") && contador < 20){
                            System.out.println("\nEscribe una nueva pregunta para identificar lo que piensas");
                            String pregunta = sc.nextLine();
                            System.out.println("\nEscribe el **** en el que pensaste");
                            String personaje = sc.nextLine();
        
                            Question pregunta1 = new Question(pregunta, LocalDate.now(), LocalTime.now(), false);
                            Question pregunta2 = new Question(personaje, LocalDate.now(), LocalTime.now(), true);
                            Question aux = arbol.change(pregunta1);
                            arbol.addLeft(pregunta2);
                            arbol.addRigth(aux);
        
                            ObjectOutputStream escribe2 = new ObjectOutputStream(new FileOutputStream("Preguntas/bebe.txt"));
                            escribe2.writeObject(arbol);
                            escribe2.flush();
                            escribe2.close();

                        } else
                            System.out.println("Perdiste");
                            
                    break;

                    case "2": //Listados de Datos

                        /** Leemos el árbol del archivo */
                        ObjectInputStream lee2 = new ObjectInputStream(new FileInputStream("Preguntas/bebe.txt"));
                        arbol = (BinarySearchTree<Integer, Question>) lee2.readObject();
                        lee2.close();   

                        preguntas = juego.toArray(arbol);

                        for (int i = 0; i < preguntas.length; i++) 
                            preguntas[i].unVisit();

                        boolean flag = true;

                        while (flag){ // Se repite hasta que vayan al menú
                            System.out.println("\n          * Historial *            ");
                            System.out.println("a) Listado de preguntas              ");
                            System.out.println("b) Listado de preguntas agregadas    ");
                            System.out.println("c) Listado de entes                  ");
                            System.out.println("d) Listado de entes agregados        ");
                            System.out.println("e) Volver al menú                    ");

                            respuesta1 = sc.nextLine();
        
                            switch (respuesta1) {
                                case "a":
                                    System.out.println("\nListado de preguntas ordenadas Alfabéticamente\n");
                                    juego.preguntasAlf(arbol, preguntas);
                                    break;
                                        
                                case "b":
                                    System.out.println("\nListado de preguntas ordenadas por Fecha\n"); 
                                    juego.preguntasFecha(arbol, preguntas);                                       
                                    break; 
                                    
                                case "c":
                                    System.out.println("\nListado de entes ordenadas Alfabéticamente\n");
                                    juego.respuestasAlf(arbol, preguntas);                                        
                                    break;  
                                        
                                case "d":
                                    System.out.println("\nListado de entes ordenadas por Fecha\n");
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