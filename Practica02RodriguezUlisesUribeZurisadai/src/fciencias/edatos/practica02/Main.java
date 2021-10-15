package fciencias.edatos.practica02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DoubleLinkedList<String> lista = new DoubleLinkedList<>();

    Scanner sc = new Scanner(System.in);

    do{
        System.out.println("Menú \n" +
                           "1)  Agregar una cadena \n" +
                           "2)  Elimina una cadena \n" +
                           "3)  Limpiar \n" +
                           "4)  Verificar si hay algún elemento \n" +
                           "5)  Obtener un elemento \n" +
                           "6)  Verificar si está vacía\n" +
                           "7)  Longitud \n" +
                           "8)  Reversa \n" +
                           "9)  Cortar \n" +
                           "10) Mostrar \n" +
                           "11) Salir \n");
                 
        int opcion = sc.nextInt();
        
        switch (opcion) {
            case 1:
            int i;
            String s;
            
            if (lista.isEmpty()) {
                System.out.println("\nEscribe el elemento que deseas agregar: ");
                sc.nextLine();
                s = sc.nextLine();
                lista.add(0, s);
                System.out.println("\nSe agregó correctamente en la posición 0.");
                break;
            }else{
                do{
                    System.out.println("\nColoca la posición de la cadena que deseas agregar (0 - " + lista.size() + "):");
                    i = sc.nextInt();
                }while(i > lista.size());
                System.out.println("\nEscribe el elemento que deseas agregar: ");
                sc.nextLine();
                s = sc.nextLine();
                lista.add(i, s);
                System.out.println("\nSe agregó correctamente en la posición " + i + ".");
                break;
            }
        
            case 2:
                int size = lista.size()-1;
                if (lista.isEmpty()) {
                    System.out.println("\nLa lista esta vacia");
                    break;
                }else if(size == 0){
                    System.out.println("\nSe elimino el único elemento en la lista: " + lista.remove(0) + ".");
                    break;
                }else{
                    do{
                        System.out.println("\nColoca la posición de la cadena que deseas eliminar (0 - " + size + "):");
                        i = sc.nextInt();
                    }while(i > size);
                    System.out.println("\nSe elimino correctamente la posición " + i + " con el elemento : " + lista.remove(i) + ".");
                    break;
                }

            case 3:
                if (lista.isEmpty()){ 
                    System.out.println("\nLa lista ya esta vacia.");
                }else{
                    lista.clear();
                    System.out.println("\nLa lista se limpió correctamente.");
                }
                break;

                case 4:
                if (lista.isEmpty()) {
                    System.out.println("\nNo hay elementos en la lista.");
                }else{
                    System.out.println("\nColoca el elemento que deseas verificar.");
                    s = sc.next();
                    if(lista.contains(s))
                        System.out.println("\nEl elemento se encuentra dentro de la lista.");
                    else
                        System.out.println("\nEl elemento no se encuentra.");
                    
                }
                break;

            case 5:
                size = lista.size()-1;
                if (lista.isEmpty()) {
                    System.out.println("\nNo hay ningun elemento, la lista esta vacia");
                }else if(lista.size() == 1){
                    System.out.println("\nEl único elemento en la lista es: " + lista.get(0) + ".");
                }else{
                    System.out.println("\nColoca la posición del elemento que deseas obtener: (0 - " + size + ").");
                    i= sc.nextInt(); 
                    System.out.println("\nEl elemento en la posición " + i +" es: " + lista.get(i) + ".");
                }
                
                break;  

            default:
                System.out.println("Opción inválida :c ");
                break;
        }

    }while(true);

    }
}
