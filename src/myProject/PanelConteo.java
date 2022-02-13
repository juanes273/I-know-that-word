package myProject;

import javax.swing.*;
import java.awt.*;

public class PanelConteo extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;

    private String palabra;

    public PanelConteo(){
        palabra = "";
        setPreferredSize(new Dimension(WIDTH,HEIGTH));
    }

    public void pintarPalabra(String palabraIngresada){
        this.palabra = palabraIngresada;

        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(52,158,126));
        g.fillRect(0,0,WIDTH,HEIGTH);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        g.setColor(Color.BLACK);
        g.drawString("Tienes", WIDTH-250,HEIGTH-300);
        g.drawString(palabra, WIDTH/2,HEIGTH-200);
        g.drawString("Segundos para resolver", WIDTH/10,HEIGTH-100);

    }

}