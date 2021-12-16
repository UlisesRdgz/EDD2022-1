package fciencias.edatos.proyecto02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Juego {
    
    public static void main(String[] args) {

        int contador = 0;
        String respuesta = "";
        Scanner sc = new Scanner(System.in);

        // Question zuri = new Question("¿Sabe nadar?", LocalDate.now(), LocalTime.now(), false);
        // Question zuri1 = new Question("¿Es un pez?", LocalDate.now(), LocalTime.now(), true);
        // Question zuri2 = new Question("¿Ladra?", LocalDate.now(), LocalTime.now(), false);
        // Question zuri3 = new Question("¿Es un perro?", LocalDate.now(), LocalTime.now(), true);
        // Question zuri4 = new Question("¿Es un gato?", LocalDate.now(), LocalTime.now(), true);

        try {

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
            BinarySearchTree<Integer, Question> piciosa = (BinarySearchTree<Integer, Question>) lee.readObject();
            lee.close();            

            System.out.println(piciosa.inicio().getPregunta() + " " + piciosa.actual().getDate() + " " + piciosa.actual().getTime());
            
            do {
                contador++;
                System.out.println(contador);
                System.out.println("S/N");
                respuesta = sc.nextLine();

                if (respuesta.equals("S")) {
                    System.out.println("");
                    System.out.println(piciosa.moveLeft().getPregunta() + " " + piciosa.actual().getDate() + " " + piciosa.actual().getTime());

                } else {
                    System.out.println("");
                    System.out.println(piciosa.moveRigth().getPregunta() + " " + piciosa.actual().getDate() + " " + piciosa.actual().getTime());
                }

            } while (contador < 20 && !piciosa.isLeaf());

            if (contador < 20) {
                
                System.out.println("S/N");
                respuesta = sc.nextLine();

                if (respuesta.equals("S")) 
                    System.out.println("\nHaz ganado");

                else {
                    System.out.println("Escribe una nueva pregunta para identificar lo que piensas");
                    String pregunta = sc.nextLine();
                    System.out.println("Escribe el **** en el que pensaste");
                    respuesta = sc.nextLine();

                    Question pregunta1 = new Question(pregunta, LocalDate.now(), LocalTime.now(), false);
                    Question pregunta2 = new Question(respuesta, LocalDate.now(), LocalTime.now(), true);
                    Question aux = piciosa.change(pregunta1);
                    piciosa.addLeft(pregunta2);
                    piciosa.addRigth(aux);

                    ObjectOutputStream escribe2 = new ObjectOutputStream(new FileOutputStream("Preguntas/bebe.txt"));
                    escribe2.writeObject(piciosa);
                    escribe2.flush();
                    escribe2.close();
                }
            } else
                System.out.println("Perdiste");
           
           
    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
