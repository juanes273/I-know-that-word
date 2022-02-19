package myProject;

import javax.swing.*;
import java.awt.*;

/**
 * Class that shows the part in where you have to select words
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra
 */

public class PanelResolver extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;

    private String palabra, contador, estado;
    private int nivel;
    private Image imagen;

    public PanelResolver() {
        palabra = "";
        contador = "0";
        nivel = 1;
        estado = "";
        setPreferredSize(new Dimension(WIDTH, HEIGTH));

        imagen = new ImageIcon("/recursos/messi.jpg").getImage();
    }

    /**
     * Function that setup variables to a specific value
     */
    public void pintarPalabra(String palabraIngresada, String contadorIngresado, int nivel) {
        this.palabra = palabraIngresada;
        this.contador = contadorIngresado;
        this.nivel = nivel;
        repaint();
    }

    /**
     * Functions that change the values of 'estado'
     */
    public void estadoTrue() {
        estado = "si";
    }

    public void estadoFalse() {
        estado = "no";
    }

    public void estadoNulo() {
        estado = "";
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 128, 192));
        g.fillRect(0, 0, WIDTH, HEIGTH);
        //g.drawImage(imagen,10,10,null);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString(palabra, WIDTH / 3, HEIGTH / 2);
        g.drawString("Tiempo restante: " + contador, 55, HEIGTH - 50);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Nivel: " + nivel, 50, 50);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
        g.setColor(Color.BLACK);

        if (estado == "si") {
            g.drawString("✔", (WIDTH / 3) - 30, (HEIGTH / 2));
            repaint();
        } else if (estado == "no") {
            g.drawString("X", (WIDTH / 3) - 30, (HEIGTH / 2));
            repaint();
        } else {
            g.drawString("", (WIDTH / 3) - 30, (HEIGTH / 2));
            repaint();
        }


        g.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString("Presiona 'B' para correcto", 180, 50);
        g.drawString("Presiona 'N' para incorrecto", 180, 80);
        g.setColor(Color.orange);
        g.drawRect(170, 30, 225, 65);
    }

}

