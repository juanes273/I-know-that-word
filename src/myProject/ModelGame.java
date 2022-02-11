package myProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class ModelGame {
    private Diccionario frases;
    private String alias;
    private int score,nivelActual,palabrasDeNivel,palabrasDeNivelTotal;
    private ArrayList<String> palabrasVistas = new ArrayList<String>();
    private ArrayList<String> palabrasTotalNivel = new ArrayList<String>();
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> copiaPalabrasVistas = new ArrayList<String>();
    private ArrayList<String> diccionario1 = new ArrayList<String>();


    public ModelGame(){
        frases = new Diccionario();
        palabrasDeNivel = 10;
        palabrasDeNivelTotal = 20;
        palabrasVistas.add(frases.getFrase());
        palabrasTotalNivel.add(frases.getFrase());
        diccionario = frases.getDiccionario();
    }

    public ArrayList<String> memorizar() {
        Random aleatorio = new Random();

        for (int i = 0; i < palabrasDeNivel-1; i++) {
            String palabraAqui = frases.getFrase();
            int indice = palabrasVistas.indexOf(palabraAqui);

            if (indice == -1) {
                palabrasVistas.add(i, palabraAqui);
            }else{
                palabraAqui = frases.getFrase();
                i = i-1;
            }
        }
        return palabrasVistas;
    }


    public ArrayList<String> recordar(){
        Random aleatorio = new Random();

        palabrasTotalNivel.addAll(palabrasVistas);

        for (int i = 0; i < palabrasDeNivel-1; i++) {
            String palabraAqui = frases.getFrase();
            int indice = palabrasTotalNivel.indexOf(palabraAqui);

            if(indice == -1){
                palabrasTotalNivel.add(palabraAqui);
            }else{
                palabraAqui = frases.getFrase();
                i = i-1;
            }
        }

        return palabrasTotalNivel;
    }



    public void limpiarArrays(){
        palabrasVistas.clear();
        palabrasVistas.add(frases.getFrase());
        palabrasTotalNivel.clear();
        palabrasTotalNivel.add(frases.getFrase());
    }

    public ArrayList<String> getDiccionario() {
        return diccionario;
    }

    public ArrayList<String> mostrarPalabras(){return palabrasVistas;}

    public ArrayList<String> getPalabrasTotalNivel() {
        return palabrasTotalNivel;
    }

    public ArrayList<String> mezclar(){
        Collections.shuffle(palabrasVistas);
        return palabrasVistas;
    }
}