package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Usuarios {
    private ArrayList<String> usuarios = new ArrayList<String>();

    public Usuarios(){
        FileManagerUsuarios fileManagerUsuarios = new FileManagerUsuarios();
        usuarios = fileManagerUsuarios.lecturaFileUsuario();
    }

    public String getFrase(){
        Random aleatorio = new Random();
        return usuarios.get(aleatorio.nextInt(usuarios.size()));
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }
}