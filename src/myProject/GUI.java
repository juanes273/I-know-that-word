package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private ModelGame modelGame;
    private Header headerProject;
    private JPanel squareColor;
    private JTextArea ola;
    private JButton initTimer;
    private Timer timer;
    private Escucha escucha;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Swing Timer use app");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {

        modelGame = new ModelGame();
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        //Set up JComponents
        headerProject = new Header("Swing Timer in use", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        squareColor = new JPanel();
        squareColor.setBackground(Color.CYAN);
        squareColor.setPreferredSize(new Dimension(100,100));

        add(squareColor,BorderLayout.CENTER);

        initTimer = new JButton("Timer");
        initTimer.addActionListener(escucha);
        //initTimer.setVisible(false);
        initTimer.setEnabled(true);
        add(initTimer,BorderLayout.SOUTH);

        timer = new Timer(1000,escucha);
        //timer.start();

        ola = new JTextArea(1, 7);
        ola.setText("0");
        add(ola,BorderLayout.EAST);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        private Random random;
        private int counter;

        public Escucha(){
            random = new Random();
            counter=0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==initTimer){
                modelGame.memorizar();
                ola.setText(modelGame.mostrarPalabras()[0]+"\n"+modelGame.mostrarPalabras()[1]+"\n"+modelGame.mostrarPalabras()[2]
                        +"\n"+modelGame.mostrarPalabras()[3]+"\n"+modelGame.mostrarPalabras()[4]+"\n"+modelGame.mostrarPalabras()[5]
                        +"\n"+modelGame.mostrarPalabras()[6]+"\n"+modelGame.mostrarPalabras()[7]+"\n"+modelGame.mostrarPalabras()[8]
                        +"\n"+modelGame.mostrarPalabras()[9]);
            }
        }
    }
}
