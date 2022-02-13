package myProject;

import javax.swing.*;
import java.awt.*;

public class PanelResolver extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;

    private String palabra,contador;
    private int nivel;
    private Image imagen;

    public PanelResolver(){
        palabra = "";
        contador ="0";
        nivel = 1 ;
        setPreferredSize(new Dimension(WIDTH,HEIGTH));

        imagen = new ImageIcon("/recursos/messi.jpg").getImage();
    }

    public void pintarPalabra(String palabraIngresada, String contadorIngresado, int nivel){
        this.palabra = palabraIngresada;
        this.contador = contadorIngresado;
        this.nivel = nivel;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(52,158,126));
        g.fillRect(0,0,WIDTH,HEIGTH);
        //g.drawImage(imagen,10,10,null);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        g.setColor(Color.BLACK);
        g.drawString(palabra, WIDTH/3,HEIGTH/2);
        g.drawString("Tiempo restante: "+contador, 55,HEIGTH-50);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        g.setColor(Color.BLACK);
        g.drawString("Nivel: " + nivel, 50,50);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        g.setColor(Color.BLACK);
        g.drawString("Presiona 'B' para correcto", 180,50);
        g.drawString("Presiona 'N' para incorrecto", 180,80);
        g.setColor(Color.orange);
        g.drawRect(170,30,225,65);
    }

}

