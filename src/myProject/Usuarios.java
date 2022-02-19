package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that contains functions to the dictionary that we took from 'usuarios.txt'
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra jose.becerra@correounivalle.edu.co
 */

public class Usuarios {
    private ArrayList<String> usuarios = new ArrayList<String>();

    public Usuarios() {
        FileManagerUsuarios fileManagerUsuarios = new FileManagerUsuarios();
        usuarios = fileManagerUsuarios.lecturaFileUsuario();
    }

    /**
     * Function that get a word randomly from the users
     */
    public String getUser() {
        Random aleatorio = new Random();
        return usuarios.get(aleatorio.nextInt(usuarios.size()));
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }
}