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
    private PanelMenu panelMenu;
    private FileWritter fileWritter;
    private Escucha escucha;
    private JButton jugar;
    private Boolean responder,pasarPalabra;

    /**
     * Constructor of GUI class
     */
    public GUI() {
        initGUI();

        //Default JFrame configuration
        this.setTitle("I know that word");
        this.setSize(400, 400);
        this.setBackground(Color.BLUE);
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

        modelGame = new ModelGame();
        escucha = new Escucha();
        //Set up JComponents
        headerProject = new Header("Oprime una letra del teclado  para jugar", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        panelFrase = new PanelFrase();
        panelFrase.setVisible(false);
        add(panelFrase,BorderLayout.CENTER);
        //this.addKeyListener(escucha);
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

        panelMenu = new PanelMenu();
        panelMenu.setVisible(true);
        add(panelMenu,BorderLayout.NORTH);
        this.addKeyListener(escucha);
        setFocusable(true);

        timer5 = new Timer(200,escucha);
        timer52 = new Timer(500, escucha);
        timer7 = new Timer(100,escucha);

        responder = false;
        pasarPalabra = true;
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
        String usuario;
        int score;
        String palabraPasada;

        @Override
        public void keyTyped(KeyEvent e) {
            palabraPasada = "";
            if(e.getKeyChar()=='r'){
                panelFrase.setVisible(true);
            }if(e.getKeyChar()=='o'){
                //super.keyTyped(e);
            }if(e.getKeyChar()=='z'){
                System.exit(0);
            }if(e.getKeyChar()=='t'){
            }if(e.getKeyChar()==KeyEvent.VK_ENTER){
                //FileWritter fileWritter = new FileWritter();
                usuario = JOptionPane.showInputDialog("Ingrese su usuario");
                modelGame.memorizar();
                ArrayList<String> palabrasm = modelGame.mostrarPalabras();
                System.out.println(palabrasm.size());
                panelConteo.addKeyListener(escucha);
                panelResolver.addKeyListener(escucha);
                timer5.start();
                panelMenu.setVisible(false);
                panelFrase.setVisible(true);
            }if(e.getKeyChar()=='b' && responder==true && pasarPalabra==true){
                ArrayList<String> palabrasm = modelGame.mostrarPalabras();
                ArrayList<String> palabrasD = modelGame.getPalabrasTotalNivel();


                if(palabrasm.indexOf(palabrasD.get(palabraActual))!=-1){
                    modelGame.contarAciertos();
                }
                pasarPalabra=false;
                System.out.println(palabrasD.get(palabraActual)+"/"+palabrasm.indexOf(palabrasD.get(palabraActual)));


            }if(e.getKeyChar()=='n' && responder==true){
                String palabra1;
                ArrayList<String> palabrasm = modelGame.mostrarPalabras();
                ArrayList<String> palabrasD = modelGame.getPalabrasTotalNivel();
                palabra1 = palabrasD.get(palabraActual);

                if(palabrasm.indexOf(palabra1)==-1){
                    modelGame.contarAciertos();
                }
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
            int palabrasDeNivel = modelGame.getPalabrasDeNivel();
            int nivel = modelGame.getNivelActual();
            int aciertos = modelGame.getPorcentajeAciertos();

            if(e.getSource()==jugar){
                //fileWritter.escribirTexto(usuario);
            }else if(e.getSource()==timer5){
                ArrayList<String> palabrasm = modelGame.mostrarPalabras();
                counter++;

                if(counter<palabrasDeNivel+1){
                    panelFrase.pintarPalabra(palabrasm.get(counter-1));
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
                    counter = 8;
                    timer7.start();
                    panelConteo.setVisible(false);
                    panelResolver.setVisible(true);
                    modelGame.recordar();
                    ArrayList<String> palabrasD = modelGame.getPalabrasTotalNivel();
                    ArrayList<String> palabrasF = modelGame.mostrarPalabras();
                    //JOptionPane.showMessageDialog(null,""+palabrasD.size()+palabrasF.size());
                }
            }else if(e.getSource()==timer7) {
                responder= true;
                counter--;
                //System.out.println("score: " + pasarPalabra + "\n" + "palabra actual: " + palabraActual);
                ArrayList<String> palabrasD = modelGame.getPalabrasTotalNivel();
                if (counter <= 8 && counter>0) {
                    panelResolver.pintarPalabra(palabrasD.get(palabraActual), "" + counter,nivel);
                    timer7.start();
                }if (counter<=0){
                    score = modelGame.getScore();
                    counter = 8;
                    palabraActual++;
                    pasarPalabra = true;
                }if(palabraActual>(palabrasDeNivel*2)-1){
                    score = modelGame.getScore();
                    timer7.stop();
                    counter = 8;
                    if(score>=aciertos){
                        int opcion = JOptionPane.showConfirmDialog(panelFrase,"¡Has completado el nivel!"
                                +"\n"+"¿Deseas continuar?","Ganaste",JOptionPane.YES_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        panelResolver.setVisible(false);
                        panelFrase.setVisible(true);
                        modelGame.avanzarNivel();
                        timer5.start();
                    }else{
                        if (opcion == JOptionPane.NO_OPTION){
                            FileWritter fileWritter = new FileWritter();
                            fileWritter.escribirTexto(usuario);
                            fileWritter.escribirTexto(String.valueOf(nivel));
                            System.exit(0);
                        }
                    }
                    }else{
                        int opcion2 = JOptionPane.showConfirmDialog(panelFrase,"Has perdido :c"
                                +"\n"+"¿Deseas intentarlo de nuevo?","Perdiste",JOptionPane.YES_OPTION);
                        if (opcion2 == JOptionPane.YES_OPTION){
                            panelResolver.setVisible(false);
                            panelFrase.setVisible(true);
                            timer5.start();
                        }else{
                            if (opcion2 == JOptionPane.NO_OPTION){
                                FileWritter fileWritter = new FileWritter();
                                fileWritter.escribirTexto(usuario);
                                fileWritter.escribirTexto(String.valueOf(nivel));
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
    }
}
