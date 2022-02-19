package myProject;

import javax.swing.*;
import java.awt.*;

/**
 * Class that shows the words to be memorized
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra
 */

public class PanelFrase extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;

    private String palabra;

    public PanelFrase() {
        palabra = "";
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
    }

    /**
     * Function that setup variables to a specific value
     */
    public void pintarPalabra(String palabraIngresada) {
        this.palabra = palabraIngresada;

        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 128, 192));
        g.fillRect(0, 0, WIDTH, HEIGTH);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString(palabra, WIDTH / 3, HEIGTH / 2);

    }

}
