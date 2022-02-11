package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Diccionario diccionario;
    private ModelGame modelGame;
    private Header headerProject;
    private JPanel squareColor;
    private JTextArea ola,mezclado;
    private JButton initTimer,acordate,reset;
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
        diccionario = new Diccionario();
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

        mezclado = new JTextArea(1, 7);
        mezclado.setText("0");
        add(mezclado,BorderLayout.WEST);

        acordate = new JButton("acordate");
        acordate.addActionListener(escucha);
        //initTimer.setVisible(false);
        acordate.setEnabled(true);
        add(acordate,BorderLayout.NORTH);

        reset = new JButton("Reset");
        reset.addActionListener(escucha);
        //initTimer.setVisible(false);
        reset.setEnabled(true);
        add(reset,BorderLayout.WEST);
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
                ArrayList<String> palabrasD = modelGame.getDiccionario();
                ArrayList<String> palabrasm = modelGame.mostrarPalabras();
                ola.setText(palabrasm.get(0)+"\n"+palabrasm.get(1)+"\n"+palabrasm.get(2)+"\n"+palabrasm.get(3)+"\n"+palabrasm.get(4)
                        +"\n"+palabrasm.get(5)+"\n"+palabrasm.get(6)+"\n"+palabrasm.get(7)+"\n"+palabrasm.get(8)+"\n"+palabrasm.get(9)
                        +"\n"+palabrasm.size() +"\n"+palabrasD.size());
            }else if(e.getSource()==acordate){
                modelGame.recordar();
                ArrayList<String> palabrase = modelGame.getPalabrasTotalNivel();
                Collections.shuffle(palabrase);
                ola.setText(palabrase.get(0)+"\n"+palabrase.get(1)+"\n"+palabrase.get(2)+"\n"+palabrase.get(3)+"\n"+palabrase.get(4)
                        +"\n"+palabrase.get(5)+"\n"+palabrase.get(6)+"\n"+palabrase.get(7)+"\n"+palabrase.get(8)
                        +"\n"+ palabrase.get(9)+"\n"+palabrase.get(10)+"\n"+palabrase.get(11)+"\n"+palabrase.get(12)+"\n"+palabrase.get(13)
                        +"\n"+palabrase.get(14) +"\n"+palabrase.get(15)+"\n"+palabrase.get(16)+"\n"+palabrase.get(17)+"\n"+palabrase.get(18)
                        +"\n"+palabrase.get(19)+"\n"+palabrase.size());
                modelGame.limpiarArrays();
            }else if(e.getSource()==reset){
            }
        }
    }
}
