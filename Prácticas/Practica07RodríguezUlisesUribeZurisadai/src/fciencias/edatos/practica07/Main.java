package fciencias.edatos.practica07;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
* Clase para leer el archivo y calcular el peso total.
* @author Rodríguez García Ulises.
* @author Uribe García Zurisadai.
* @version 1.0 Enero 2022.
* @since Estructuras de Datos 2022-1.
*/
public class Main {
    
    /**
     * Lee los valores de un .txt y los almacena en un mapa.
     * @param map mapa donde serán almacenados los valores.
     */
    public static void readMap(TDAMap<String, Double> map) {
        try(BufferedReader reader = new BufferedReader(new FileReader("TablaPeriodica/tabla-periodica.txt"))){
            String line = null;

            while((line = reader.readLine()) != null){
                String[] data = line.split(",");

                // Guardamos el nombre y valor de los elementos.
                String element = data[1]; 
                Double value = Double.valueOf(data[2]);
                map.put(element, value);
            }

        } catch(FileNotFoundException fnfe) {
            System.out.println("ARCHIVO NO ENCONTRADO");
        } catch(IOException ioe) {}
    }

    public static void main(String[] args) {
		TDAMap<String, Double> map = new HashMap<>(68041);
        readMap(map);

        Scanner sc = new Scanner(System.in);
		String respuesta = "";
        String line = "------------------------------------";
                      
                System.out.println("\n        TABLAS DE DISPERSIÓN  \n");
                System.out.println(line);

		do {
			try {	
               
				System.out.println("               Menú                ");
                System.out.println(line);
				System.out.println("1)  Ingresar fórmula química   \n" +
								   "2)  Salir                        ");
				
				respuesta = sc.nextLine();
                
                switch (respuesta) {
					case "1":
                        do {
                            try {
                                Double resultado = 0.0;                                
                                sc = new Scanner(System.in);

				                System.out.println("\n------------------------------------");
                                System.out.println("Ingresa la secuencia de cadena:");
                                String cadena = sc.nextLine();
                                // Separa la cadena cada que encuentra una mayúscula.
                                String[] elements = cadena.split("(?=\\p{Upper})");
                                System.out.println(" ");

                                for (int i = 0; i < elements.length; i++) {
				                
                                    System.out.println("---------------------");

                                    System.out.println("Símbolo: " + elements[i]);

                                    // Separa la cadena en letras y números.
                                    String[] values = elements[i].split("(?<=\\D)(?=\\d)");
                                    String nombre = values[0];
                                    Double masa;
                                    if (values.length < 2)
                                        masa = 1.0;
                                    else
                                        masa = Double.valueOf(values[1]);

				                    
                                    resultado += masa * map.get(nombre);
                                    System.out.println("Masa: " + map.get(nombre) + "\nCantidad: " + masa);
                                }
                                System.out.println("---------------------\n");
                                System.out.println("Peso total de la sustancia:\n> " + resultado);
                                System.out.println(line);
                                break;

                            } catch (Exception e) {
                                System.out.println("\nIngresa una fórmula válida.");
                            } 
                        } while (true);

                    break;

                    case "2":
                        return;

                    default:
						System.out.println("\nError, Opción inválida.\nIngresa una de las opciones.");
					break;    
                }

            } catch (Exception e) {
				System.out.println("Error. Vuélvelo a intentar.");
			}

		} while (respuesta != "3");   
	}
}


