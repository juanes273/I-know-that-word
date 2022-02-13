package myProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class ModelGame {
    private Diccionario frases;
    private String alias;
    private int score,nivelActual,palabrasDeNivel,porcentajeAciertos;
    private ArrayList<String> palabrasVistas = new ArrayList<String>();
    private ArrayList<String> palabrasTotalNivel = new ArrayList<String>();
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> copiaPalabrasVistas = new ArrayList<String>();
    private ArrayList<String> diccionario1 = new ArrayList<String>();


    public ModelGame(){
        frases = new Diccionario();
        palabrasDeNivel = 10;
        palabrasVistas.add(frases.getFrase());
        palabrasTotalNivel.add(frases.getFrase());
        diccionario = frases.getDiccionario();
        nivelActual = 1;
        porcentajeAciertos = 14;
    }

    public void verificarNivel(){
        switch (nivelActual){
            case 1: palabrasDeNivel = 10;
                porcentajeAciertos = 14;
            break;
            case 2: palabrasDeNivel = 20;
                porcentajeAciertos = 28;
                break;
            case 3: palabrasDeNivel = 25;
                porcentajeAciertos = 38;
                break;
            case 4: palabrasDeNivel = 30;
                porcentajeAciertos = 48;
                break;
            case 5: palabrasDeNivel = 35;
                porcentajeAciertos = 56;
                break;
            case 6: palabrasDeNivel = 40;
                porcentajeAciertos = 68;
                break;
            case 7: palabrasDeNivel = 50;
                porcentajeAciertos = 90;
                break;
            case 8: palabrasDeNivel = 60;
                porcentajeAciertos = 108;
                break;
            case 9: palabrasDeNivel = 70;
                porcentajeAciertos = 133;
                break;
            case 10: palabrasDeNivel = 100;
                porcentajeAciertos = 200;
                break;
        }
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

            if (indice == -1) {
                palabrasTotalNivel.add(palabraAqui);
            } else {
                palabraAqui = frases.getFrase();
                i = i - 1;
            }
        }

        Collections.shuffle(palabrasTotalNivel);

        return palabrasTotalNivel;
    }


    public void limpiarArrays(){
        palabrasVistas.clear();
        palabrasVistas.add(frases.getFrase());
        palabrasTotalNivel.clear();
        palabrasTotalNivel.add(frases.getFrase());
    }


    public void contarAciertos(){
        score++;
    }

    public void avanzarNivel(){
        nivelActual++;
    }



    public ArrayList<String> getDiccionario() {
        return diccionario;
    }

    public ArrayList<String> mostrarPalabras(){return palabrasVistas;}

    public ArrayList<String> getPalabrasTotalNivel() {
        return palabrasTotalNivel;
    }

    public int getScore() {
        return score;
    }

    public int getPalabrasDeNivel() {
        return palabrasDeNivel;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public int getPorcentajeAciertos() {
        return porcentajeAciertos;
    }
}