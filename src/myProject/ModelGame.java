package myProject;

import java.util.ArrayList;

public class ModelGame {
    private Diccionario frases;
    private String alias;
    private int score,nivelActual,palabrasDeNivel;
    private String[] palabrasVistas, palabrasNivel;

    public ModelGame(){
        frases = new Diccionario();
        palabrasDeNivel = 10;
        ArrayList<String> palabrasVistas= new String[10];
    }

    public void memorizar(){
        String palabraAqui = "";
        for (int i = 0; i < palabrasDeNivel; i++) {
            palabraAqui = frases.getFrase();
            if (palabraAqui != palabrasVistas[i]) {
                palabrasVistas[i] = palabraAqui;
            }
        }
    }

public String[] mostrarPalabras(){return palabrasVistas;}
}
