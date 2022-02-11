package myProject;

import javax.swing.*;
import java.awt.*;

public class PanelResolver extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;

    private String palabra,contador;

    public PanelResolver(){
        palabra = "";
        contador ="0";
        setPreferredSize(new Dimension(WIDTH,HEIGTH));
    }

    public void pintarPalabra(String palabraIngresada, String contadorIngresado){
        this.palabra = palabraIngresada;
        this.contador = contadorIngresado;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,WIDTH,HEIGTH);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        g.setColor(Color.MAGENTA);
        g.drawString(palabra, WIDTH/3,HEIGTH/2);
        g.drawString(contador, WIDTH/3,HEIGTH);

    }

}

