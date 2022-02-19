package myProject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * Class used to do most of the functions in the game
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra
 */

public class ModelGame {
    private Usuarios user;
    private Diccionario frases;
    private int score, nivelActual, palabrasDeNivel, porcentajeAciertos;
    private boolean usuarioEsta;
    private ArrayList<String> palabrasVistas = new ArrayList<String>();
    private ArrayList<String> palabrasTotalNivel = new ArrayList<String>();
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> copiaPalabrasVistas = new ArrayList<String>();
    private ArrayList<String> diccionario1 = new ArrayList<String>();
    private ArrayList<String> usuario = new ArrayList<String>();


    public ModelGame() {
        frases = new Diccionario();
        user = new Usuarios();
        palabrasDeNivel = 10;
        palabrasVistas.add(frases.getFrase());
        palabrasTotalNivel.add(frases.getFrase());
        diccionario = frases.getDiccionario();
        nivelActual = 1;
        porcentajeAciertos = 14;
        usuario = user.getUsuarios();
    }

    /**
     * Function that verify if there`s a user in the users file and assign a level to the program if it's true
     */
    public void verificarUsuario(String nombre) {

        for (int i = 0; i < usuario.size(); i++) {

            int indice = usuario.indexOf(nombre);

            if (indice == -1) {
                usuarioEsta = false;
            } else {
                usuarioEsta = true;
                nivelActual = Integer.parseInt(usuario.get(indice + 1));
            }
        }
    }

    /**
     * Function that set up the number of words depending on the level of the user
     */
    public void verificarNivel() {
        switch (nivelActual) {
            case 1:
                palabrasDeNivel = 10;
                porcentajeAciertos = 14;
                break;
            case 2:
                palabrasDeNivel = 20;
                porcentajeAciertos = 28;
                break;
            case 3:
                palabrasDeNivel = 25;
                porcentajeAciertos = 38;
                break;
            case 4:
                palabrasDeNivel = 30;
                porcentajeAciertos = 48;
                break;
            case 5:
                palabrasDeNivel = 35;
                porcentajeAciertos = 56;
                break;
            case 6:
                palabrasDeNivel = 40;
                porcentajeAciertos = 68;
                break;
            case 7:
                palabrasDeNivel = 50;
                porcentajeAciertos = 90;
                break;
            case 8:
                palabrasDeNivel = 60;
                porcentajeAciertos = 108;
                break;
            case 9:
                palabrasDeNivel = 70;
                porcentajeAciertos = 133;
                break;
            case 10:
                palabrasDeNivel = 100;
                porcentajeAciertos = 200;
                break;
        }
    }

    /**
     * Function that setup which words are going to be showed to the user to be memorized
     */
    public ArrayList<String> memorizar() {
        for (int i = 0; i < palabrasDeNivel - 1; i++) {
            String palabraAqui = frases.getFrase();
            int indice = palabrasVistas.indexOf(palabraAqui);

            if (indice == -1) {
                palabrasVistas.add(i, palabraAqui);
            } else {
                palabraAqui = frases.getFrase();
                i = i - 1;
            }
        }
        return palabrasVistas;
    }


    /**
     * Function that setup every word that is going to be showed to the user (Correct and incorrect words)
     */
    public ArrayList<String> recordar() {
        palabrasTotalNivel.addAll(palabrasVistas);

        for (int i = 0; i < palabrasDeNivel - 1; i++) {

            String palabraAqui = frases.getFrase();

            int indice = palabrasTotalNivel.indexOf(palabraAqui);

            if (indice == -1) {
                palabrasTotalNivel.add(palabraAqui);
            } else {
                i = i - 1;
            }
        }

        Collections.shuffle(palabrasTotalNivel);

        return palabrasTotalNivel;
    }


    /**
     * Function that clean every array of words and initialize variables again
     */
    public void limpiarArrays() {
        palabrasVistas.clear();
        palabrasVistas.add(frases.getFrase());
        palabrasTotalNivel.clear();
        palabrasTotalNivel.add(frases.getFrase());
    }


    /**
     * Function that increase the score
     */
    public void contarAciertos() {
        score++;
    }

    /**
     * Function that increase de level
     */
    public void avanzarNivel() {
        nivelActual++;
    }

    /**
     * Getters
     */
    public ArrayList<String> getPalabrasVistas() {
        return palabrasVistas;
    }

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

    public boolean getUsuarioEsta() {
        return usuarioEsta;
    }

    public ArrayList<String> UsuariosgetUser() {
        return usuario;
    }
}