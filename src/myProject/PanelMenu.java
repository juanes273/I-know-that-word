package myProject;

import javax.swing.*;
import java.awt.*;

/**
 * Class that shows the first thing in the program (Menu)
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra
 */

public class PanelMenu extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;
    private String palabra, titulo;
    private boolean parpadeo;

    public PanelMenu() {
        palabra = "Presiona Enter para registrarte";
        titulo = "¡I know that word!";
        parpadeo = true;
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
    }

    /**
     * Functions that setup variables to a specific value
     */
    public void cambiarParpadeoTrue() {
        parpadeo = true;
    }

    public void cambiarParpadeoFalse() {
        parpadeo = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 128, 192));
        g.fillRect(0, 0, WIDTH, HEIGTH);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        g.setColor(new Color(255, 157, 60));
        g.drawString(titulo, WIDTH / 5, 40);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Presiona 'i' para ver instrucciones", 40, 200);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        g.setColor(new Color(255, 157, 60));

        if (parpadeo == true) {
            g.drawString(palabra, 20, HEIGTH - 50);
            repaint();
        } else {
            g.drawString("", 0, HEIGTH);
            repaint();
        }

        //g.setColor(new Color(255,157,60));
        //g.drawRect(3,3,393,393);
    }

}
