package myProject;

import java.io.*;
import java.util.ArrayList;

public class FileManagerUsuarios {
    public static final String PATH = "src/myProject/files/usuarios.txt";
    private FileReader fileReader;
    private BufferedReader input;

    public ArrayList<String> lecturaFileUsuario(){
        ArrayList<String> usuarios = new ArrayList<String>();

        try {
            fileReader = new FileReader(PATH);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null){
                usuarios.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }
}