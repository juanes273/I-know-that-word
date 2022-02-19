package myProject;

import java.io.*;

/**
 * Class used to write a 'txt', in this case is used to save the users
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra
 */

public class FileWritter {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;


    /**
     * Function that write a 'txt' (users)
     */
    public void escribirTexto(String linea) {
        try {
            fileWriter = new FileWriter("src/myProject/files/usuarios.txt", false);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
