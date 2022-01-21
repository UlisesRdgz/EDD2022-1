package fciencias.edatos.practica07;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class ArrayReader {
    
    /**
     * Lee los valores de un txt y los almacena en un mapa.
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
		TDAMap<String, Double> map = new HashMap<>(127000);
        Double resultado = 0.0;
        readMap(map);

        Scanner sc = new Scanner(System.in);
		String respuesta = "";

		do {
			try {		
				System.out.println("\n-----------------------------");
				System.out.println("           Menu          ");
				System.out.println( " 1)  Ingresar fórmula química \n" +
									" 2)  Salir");
				
				respuesta = sc.nextLine();
                
                switch (respuesta) {
					case "1":
                        do {
                            try {
                                sc = new Scanner(System.in);
                                System.out.println("\nIngresa la secuencia de cadena (H2O)");
                                String cadena = sc.nextLine();
                                // Separa la cadena cada que encuentra una mayúscula.
                                String[] elements = cadena.split("(?=\\p{Upper})");

                                for (int i = 0; i < elements.length; i++) {
                                    System.out.println(elements[i]);

                                    // Separa la cadena en letras y números.
                                    String[] values = elements[i].split("(?<=\\D)(?=\\d)");
                                    String nombre = values[0];
                                    Double masa;
                                    if (values.length < 2)
                                        masa = 1.0;
                                    else
                                        masa = Double.valueOf(values[1]);

                                    resultado += masa * map.get(nombre);
                                    System.out.println("El valor es: " + map.get(nombre) + "\nMasa: " + masa + "\n");
                                }
                                System.out.println("El peso total de la sustancia es: " + resultado);
                                break;

                            } catch (Exception e) {
                                System.out.println("\nIngresa una fórmula valida.");
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
				System.out.println("Error. Vuelvelo a intentar.");
			}
		} while (respuesta != "3");

        
	}
}


