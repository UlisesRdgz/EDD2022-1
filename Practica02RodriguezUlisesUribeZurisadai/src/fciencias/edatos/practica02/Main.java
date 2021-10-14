package fciencias.edatos.practica02;

import java.util.Scanner;

public class Main {

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public static void sleep(){
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(1*1200);
         } catch (Exception e) {
            System.out.print(e);
         }
    }

    public static void sleep1(){
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(1*1800);
         } catch (Exception e) {
            System.out.print(e);
         }
    }

    public static void main(String[] args) {
        DoubleLinkedList<String> lista = new DoubleLinkedList<>();

    Scanner sc = new Scanner(System.in);

    do{
        System.out.println(" Menú \n" +
                           " 1)  Agregar una cadena \n" +
                           " 2)  Elimina una cadena \n" +
                           " 3)  Limpiar \n" +
                           " 4)  Verificar si hay algún elemento \n" +
                           " 5)  Obtener un elemento \n" +
                           " 6)  Verificar si está vacía\n" +
                           " 7)  Longitud \n" +
                           " 8)  Reversa \n" +
                           " 9)  Cortar \n" +
                           " 10) Mostrar \n" +
                           " 11) Salir \n");
                 
        int opcion = sc.nextInt();
        System.out.println("");
        clearScreen();
        
        switch (opcion) {
            case 1:
                if (lista.isEmpty()) {
                    System.out.println("Escribe el elemento que deseas agregar: ");
                    sc.nextLine();
                    String s = sc.nextLine();
                    lista.add(0, s);
                    System.out.println("Se agregó correctamente en la posición 0.");
                    sleep();
                    clearScreen();
                    break;
                }else{
                    System.out.println("Coloca la posición de la cadena que deseas agregar (0 - " + lista.size() + "):");
                    int i = sc.nextInt();
                    System.out.println("Escribe el elemento que deseas agregar: ");
                    sc.nextLine();
                    String s = sc.nextLine();
                    lista.add(i, s);
                    System.out.println("Se agregó correctamente en la posición " + i + ".");
                    sleep();
                    clearScreen();
                    break;
                }
            
            case 2:
                System.out.println("Coloca la posición de la cadena que deseas eliminar: ");
                int i= sc.nextInt();
                sc.nextLine();
                lista.remove(i);
                System.out.println("Se eliminó correctamente la cadena");
                break;

            case 3:
                System.out.println("La lista se limpiará en un momento");   
                lista.clear();
                System.out.println("Se limpió correctamente ");
                break;

            case 4:
                System.out.println("Coloca el elemento que deseas verificar: ");
                i= sc.nextInt();
                sc.nextLine();
                String s= sc.nextLine();                
                System.out.println(lista.contains(s));

            case 5:
                System.out.println("Coloca el elemento que deseas obtener: ");
                i= sc.nextInt();
                sc.nextLine();            
                System.out.println(lista.get(i));  
                
            case 6:
                if(lista.isEmpty())
                    System.out.println("La lista esta vacia");
                
                else
                    System.out.println("La lista no esta vacia");
                
                sleep1();
                clearScreen();
                break;
            
            case 7:
                System.out.println("La longitud de la lista es: " + lista.size());
                sleep1();
                clearScreen();
                break;

            case 8:
                System.out.println("La reversa de la lista es: \n");   
                lista.revert();
                System.out.println(lista);

                sleep1();
                clearScreen();
                break;

            case 9:
                System.out.println("¿Mitad izquierda o mitad derecha?");
                sc.nextLine();
                s = sc.nextLine();

                if(s=="true")
                    lista.cut(true);
        
                else
                    lista.cut(false);

                System.out.println("Lista cortada: ");
                System.out.println(lista);
                sleep1();
                clearScreen();
                break;

            case 10:
                System.out.println("Lista: ");          
                System.out.println(lista);
                sleep1();
                clearScreen(); 
                break;

            case 11:
                return;

            default:
                System.out.println(" Opción inválida :c ");
                break;
        }

    }while(true);

    }
}
