package myProject;

import java.io.*;

public class FileWritter {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;


    public void escribirTexto(String linea){
        try {
            fileWriter = new FileWriter("src/myProject/files/usuarios.txt",false);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
