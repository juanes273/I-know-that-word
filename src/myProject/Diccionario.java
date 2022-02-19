package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that contains functions to the dictionary that we took from 'diccionario.txt'
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra jose.becerra@correounivalle.edu.co
 */

public class Diccionario {
    private ArrayList<String> diccionario = new ArrayList<String>();

    public Diccionario() {
        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile();
    }

    /**
     * Function that get a word randomly from the dictionary
     */
    public String getFrase() {
        Random aleatorio = new Random();
        return diccionario.get(aleatorio.nextInt(diccionario.size()));
    }

    public ArrayList<String> getDiccionario() {
        return diccionario;
    }
}
