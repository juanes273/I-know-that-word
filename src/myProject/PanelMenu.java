package myProject;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;
    private String palabra,titulo;
    private boolean parpadeo;

    public PanelMenu(){
        palabra = "Presiona Enter para registrarte";
        titulo = "¡I know that word!";
        parpadeo = true;
        setPreferredSize(new Dimension(WIDTH,HEIGTH));
    }

    public void cambiarParpadeoTrue(){
        parpadeo= true;
    }

    public void cambiarParpadeoFalse(){
        parpadeo = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(52,158,126));
        g.fillRect(0,0,WIDTH,HEIGTH);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        g.setColor(Color.BLACK);
        g.drawString(titulo, WIDTH/5,40);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        g.setColor(Color.BLACK);
        g.drawString("Presiona 'i' para ver instrucciones", 40,200);

        g.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        g.setColor(Color.BLACK);

        if(parpadeo==true){
            g.drawString(palabra, 20,HEIGTH-50);
            repaint();
        }else{
            g.drawString("", 0,HEIGTH);
            repaint();
        }
    }

}
