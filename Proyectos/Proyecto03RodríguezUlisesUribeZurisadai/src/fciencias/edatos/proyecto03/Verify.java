package fciencias.edatos.proyecto03;

import java.text.Normalizer; 

public class Verify {

    /**
     * Elimina los acentos de la cadena.
     * @param s cadena a la cual se le eliminara el acento.
     * @return regresa la cadena sin el acento.
     */
    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
    
    /**
     * Verifica que la palabra dada contenga los carácteres
     * de la cadena inicial.
     * @param cadena secuencia inicial de carácteres.
     * @param palabra conjunto de carácteres con el cual 
     * se va a verificar. 
     * @return true si la palabra contiene los carácteres,
     * false en otro caso.
     */
    public boolean Check(char[] cadena, String palabra){

        palabra = stripAccents(palabra);
        char[] aux = new char[cadena.length];
        char[] caracteres = palabra.toCharArray();
        int contador = 0;

        for (int i = 0; i < caracteres.length; i++) {
            for (int j = 0; j < cadena.length; j++) {
                if (caracteres[i] == cadena[j] && aux[j] != 'x') {
                    aux[j] = 'x';
                    contador++;
                    break;
                }
            }
        }

        if (contador == caracteres.length) 
            return true;

        return false;
    }

    /**
     * Verifica que la palabra exista en el diccionario.
     * @param palabra término el cual se va a verificar.
     * @param map mapa donde verificaremos.
     * @return true si se encuentra en el diccionario,
     * false en otro caso.
     */
    public boolean CheckDiccionary(String palabra, TDAMap<String, DoubleLinkedList<String>> map){

        if(map.get(palabra) == null)
            return false;

        if(map.get(palabra).contains(palabra))
            return true;

        return false;
    }
}
