package fciencias.edatos.proyecto02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Juego {
    
    public static void main(String[] args) {

        int contador = 0, posicion = 10;
        String respuesta = "";
        Scanner sc = new Scanner(System.in);

        // Pregunta zuri = new Pregunta("¿Piciosa?", 10);
        // Pregunta zuri1 = new Pregunta("Eres tu?", 5);
        // Pregunta zuri2 = new Pregunta("Oww mi bebé", 2);
        // Pregunta zuri3 = new Pregunta("Solo hablo con mi piciosa", 15);
        // Pregunta zuri4 = new Pregunta("Impostora get out of here", 7);

        try {

            // BinarySearchTree<Integer, String> q20 = new BinarySearchTree<>();

            // q20.insert("¿Piciosa?", 10);
            // q20.insert("¿Eres tu?", 5);
            // q20.insert("¿Eres mi bebe?", 2);
            // q20.insert("¿Quien eres?", 15);
            // q20.insert("¿Eres Impostora?", 7);

            // FileOutputStream file = new FileOutputStream("Preguntas/bebe.txt");
            // ObjectOutputStream escribe = new ObjectOutputStream(file);
            // escribe.writeObject(q20);
            // escribe.flush();
            // escribe.close();

            /** Leemos el árbol del archivo */
            ObjectInputStream lee = new ObjectInputStream(new FileInputStream("Preguntas/bebe.txt"));
            BinarySearchTree<Integer, String> piciosa = (BinarySearchTree<Integer, String>) lee.readObject();
            lee.close();            

            System.out.println(piciosa.retrieve(posicion));
            
            while (contador < 20 && !piciosa.isLeaf(posicion)) {
                System.out.println("S/N");
                
                respuesta = sc.nextLine();

                if (respuesta.equals("S")) {
                    System.out.println("");
                    posicion = posicion/2;
                    System.out.println(piciosa.retrieve(posicion));
                } else {
                    System.out.println("");
                    posicion += posicion/2;
                    System.out.println(piciosa.retrieve(posicion));
                }
            }

            if (contador < 20 && respuesta.equals("S")) 
                System.out.println("Owww mi bebe");

            else {
                System.out.println("Escribe una nueva pregunta para identificar lo que piensas");
                String pregunta = sc.nextLine();
                System.out.println("Escribe el **** en el que pensaste");
                respuesta = sc.nextLine();

                String aux = piciosa.delete(posicion);
                piciosa.insert(pregunta, posicion);

                int posAux = posicion/2;
                piciosa.insert(respuesta, posAux);

                posicion += posicion/2;
                piciosa.insert(aux, posicion);

               ObjectOutputStream escribe2 = new ObjectOutputStream(new FileOutputStream("Preguntas/bebe.txt"));
               escribe2.writeObject(piciosa);
               escribe2.flush();
               escribe2.close();
            }
    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
