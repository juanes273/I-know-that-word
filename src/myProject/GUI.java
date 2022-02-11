package myProject;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Timer timer5,timer52,timer7;
    private ModelGame modelGame;
    private Header headerProject;
    private PanelFrase panelFrase;
    private PanelResolver panelResolver;
    private PanelConteo panelConteo;
    private Escucha escucha;
    private JButton reset;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Hangman app");
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
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object

        //GridBagConstraints constraints = new GridBagConstraints();
        modelGame = new ModelGame();
        escucha = new Escucha();
        //Set up JComponents
        headerProject = new Header("Oprime una letra del teclado  para jugar", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        panelFrase = new PanelFrase();
        panelFrase.setVisible(true);
        add(panelFrase,BorderLayout.NORTH);
        this.addKeyListener(escucha);
        setFocusable(true);

        panelConteo = new PanelConteo();
        panelConteo.setVisible(false);
        add(panelConteo,BorderLayout.EAST);
        //this.addKeyListener(escucha);
        setFocusable(true);

        panelResolver = new PanelResolver();
        panelResolver.setVisible(false);
        add(panelResolver,BorderLayout.WEST);
        //this.addKeyListener(escucha);
        setFocusable(true);

        reset = new JButton("Reset");
        reset.addActionListener(escucha);
        this.add(reset, BorderLayout.SOUTH);

        timer5 = new Timer(200,escucha);
        timer52 = new Timer(20, escucha);
        timer7 = new Timer(20,escucha);

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
    private class Escucha implements ActionListener, KeyListener {

        int palabraActual;
        private int counter;

        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyChar()=='r'){
                panelFrase.setVisible(true);
            }if(e.getKeyChar()=='o'){
                //super.keyTyped(e);
                modelGame.memorizar();
                ArrayList<String> palabrasm = modelGame.mostrarPalabras();
                System.out.println(palabrasm.size());
                panelConteo.addKeyListener(escucha);
                panelResolver.addKeyListener(escucha);
            }if(e.getKeyChar()=='z'){
                System.exit(0);
            }if(e.getKeyChar()=='t'){
                timer5.start();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==reset){
                JOptionPane.showMessageDialog(null, "popo");
            }else if(e.getSource()==timer5){
                ArrayList<String> palabrasm = modelGame.mostrarPalabras();
                counter++;

                if(counter<11){
                    panelFrase.pintarPalabra(palabrasm.get(counter-1)+(counter-1));
                }else{
                    timer5.stop();
                    counter = 6;
                    panelFrase.setVisible(false);
                    panelConteo.setVisible(true);
                    timer52.start();
                }
            }else if(e.getSource()==timer52){
                counter--;
                if(counter>=0 && counter<=5){
                    panelConteo.pintarPalabra(""+counter);
                }else{
                    timer52.stop();
                    counter = 7;
                    timer7.start();
                    panelConteo.setVisible(false);
                    panelResolver.setVisible(true);
                    modelGame.recordar();
                    ArrayList<String> palabrasD = modelGame.getPalabrasTotalNivel();
                    ArrayList<String> palabrasF = modelGame.mostrarPalabras();
                    JOptionPane.showMessageDialog(null,""+palabrasD.size()+palabrasF.size());
                }
            }else if(e.getSource()==timer7) {
                counter--;
                ArrayList<String> palabrasD = modelGame.getPalabrasTotalNivel();
                System.out.println(palabrasD.size());
                if (counter <= 7) {
                    panelResolver.pintarPalabra(palabrasD.get(palabraActual), "" + counter);
                    System.out.println("counter: " + counter + "\n" + "palabra actual: " + palabraActual);
                }if (counter==0){
                    counter = 7;
                    palabraActual++;
                    System.out.println("counter: " + counter + "\n" + "palabra actual: " + palabraActual);
                }if(palabraActual>19){
                    timer7.stop();
                    counter = 7;
                }
            }
        }
    }
}
