package myProject;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that helps to read a 'txt', in this case used to read the users
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra jose.becerra@correounivalle.edu.co
 */

public class FileManagerUsuarios {
    public static final String PATH = "src/myProject/files/usuarios.txt";
    private FileReader fileReader;
    private BufferedReader input;

    /**
     * Function that read a 'txt' and put it in an arraylist (users)
     */
    public ArrayList<String> lecturaFileUsuario() {
        ArrayList<String> usuarios = new ArrayList<String>();

        try {
            fileReader = new FileReader(PATH);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                usuarios.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }
}