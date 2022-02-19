package myProject;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Main process of the java program
 *
 * @version v.1.0.0 date:18/02/2022
 * @autor Juan Esteban Brand Tovar  brand.juan@correounivalle.edu.co / Jose Miguel Becerra Casierra jose.becerra@correounivalle.edu.co
 */
public class GUI extends JFrame {

    private Timer timerMostrar, timerPreparacion, timerResolver, timerMenu, timerNivel;
    private ModelGame modelGame;
    private Header headerProject;
    private PanelFrase panelFrase;
    private PanelResolver panelResolver;
    private PanelConteo panelConteo;
    private PanelMenu panelMenu;
    private Escucha escucha;
    private Boolean responder, pasarPalabra;
    private String usuario;

    public static final String instrucciones = "-En la primera sección se mostrará cada 5 segundos una palabra que deberás \n"
            + " memorizar, luego tendrás un tiempo de preparación de 5 segundos \n"
            + " Finalmente se mostrará el doble de palabras y tendrás que decidir cuales estaban o no.\n"
            + "-Deberás presionar 'B' para las correctas \n"
            + " Y 'N' para las incorrectas \n"
            + "-Presiona 'X' en cualquier momento para cerrar el juego";

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
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
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
        this.add(headerProject, BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        panelFrase = new PanelFrase();
        panelFrase.setVisible(false);
        add(panelFrase, BorderLayout.CENTER);
        //this.addKeyListener(escucha);
        setFocusable(true);

        panelConteo = new PanelConteo();
        panelConteo.setVisible(false);
        add(panelConteo, BorderLayout.EAST);
        //this.addKeyListener(escucha);
        setFocusable(true);

        panelResolver = new PanelResolver();
        panelResolver.setVisible(false);
        add(panelResolver, BorderLayout.WEST);
        //this.addKeyListener(escucha);
        setFocusable(true);

        panelMenu = new PanelMenu();
        panelMenu.setVisible(true);
        add(panelMenu, BorderLayout.NORTH);
        this.addKeyListener(escucha);
        setFocusable(true);

        timerMostrar = new Timer(5000, escucha);
        timerPreparacion = new Timer(1000, escucha);
        timerResolver = new Timer(1000, escucha);
        timerMenu = new Timer(500, escucha);
        timerNivel = new Timer(1000, escucha);
        timerMenu.start();

        responder = false;
        pasarPalabra = true;
    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
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
        int score;
        String palabraPasada;

        @Override
        public void keyTyped(KeyEvent e) {
            palabraPasada = "";
            if (e.getKeyChar() == 'x') {
                ArrayList<String> user = modelGame.UsuariosgetUser();
                FileWritter fileWritter = new FileWritter();
                int nivel = modelGame.getNivelActual();
                if (usuario == null || usuario == "") {
                    System.exit(0);
                } else {
                    boolean usuarioEsta = modelGame.getUsuarioEsta();
                    if (usuarioEsta == true) {
                        int indice = (user.indexOf(usuario)) + 1;
                        if (nivel >= 8) {
                            nivel = 8;
                        }
                        user.set(indice, String.valueOf(nivel));
                        String usuariosFinal = null;
                        for (int i = 0; i < user.size(); i++) {
                            if (usuariosFinal == null) {
                                usuariosFinal = user.get(i);
                            } else if (user.get(i) != null) {
                                usuariosFinal = usuariosFinal + "\n" + user.get(i);
                            }
                        }
                        fileWritter.escribirTexto(usuariosFinal.trim());
                        System.exit(0);
                    } else {
                        if (nivel >= 8) {
                            nivel = 8;
                        }
                        String usuariosFinal = null;
                        for (int i = 0; i < user.size(); i++) {
                            if (usuariosFinal == null) {
                                usuariosFinal = user.get(i);
                            } else if (user.get(i) != null) {
                                usuariosFinal = usuariosFinal + "\n" + user.get(i);
                            }
                        }
                        usuariosFinal = usuariosFinal + "\n" + usuario + "\n" + nivel;
                        fileWritter.escribirTexto(usuariosFinal.trim());
                        System.exit(0);
                    }
                }
            }
            if (e.getKeyChar() == 'i') {
                JOptionPane.showMessageDialog(null, instrucciones);
            }
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                timerMenu.stop();
                counter = 0;
                usuario = JOptionPane.showInputDialog("Ingrese su usuario");
                modelGame.verificarUsuario(usuario);
                modelGame.verificarNivel();
                modelGame.memorizar();
                panelConteo.addKeyListener(escucha);
                panelResolver.addKeyListener(escucha);
                timerNivel.start();
                panelMenu.setVisible(false);
                panelFrase.setVisible(true);
            }
            if (e.getKeyChar() == 'b' && responder == true && pasarPalabra == true) {
                ArrayList<String> palabrasVistas = modelGame.getPalabrasVistas();
                ArrayList<String> palabrasTotal = modelGame.getPalabrasTotalNivel();


                if (palabrasVistas.indexOf(palabrasTotal.get(palabraActual)) != -1) {
                    modelGame.contarAciertos();
                    panelResolver.estadoTrue();
                } else {
                    panelResolver.estadoFalse();
                }
                pasarPalabra = false;


            }
            if (e.getKeyChar() == 'n' && responder == true) {
                String palabra1;
                ArrayList<String> palabrasVistas = modelGame.getPalabrasVistas();
                ArrayList<String> palabrasTotal = modelGame.getPalabrasTotalNivel();
                palabra1 = palabrasTotal.get(palabraActual);

                if (palabrasVistas.indexOf(palabra1) == -1) {
                    modelGame.contarAciertos();
                    panelResolver.estadoTrue();
                } else {
                    panelResolver.estadoFalse();
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
            ArrayList<String> user = modelGame.UsuariosgetUser();
            boolean usuarioEsta = modelGame.getUsuarioEsta();
            int palabrasDeNivel = modelGame.getPalabrasDeNivel();
            int nivel = modelGame.getNivelActual();
            int aciertos = modelGame.getPorcentajeAciertos();

            if (e.getSource() == timerNivel) {
                timerMenu.stop();
                counter++;
                if (counter < 2) {
                    panelFrase.pintarPalabra("Nivel: " + nivel);
                } else {
                    timerMostrar.start();
                    timerNivel.stop();
                    counter = 0;
                }
            }
            if (e.getSource() == timerMostrar) {
                ArrayList<String> palabrasVistas = modelGame.getPalabrasVistas();
                counter++;
                if (counter < palabrasDeNivel + 1) {
                    panelFrase.pintarPalabra(palabrasVistas.get(counter - 1));
                } else {
                    timerMostrar.stop();
                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?",
                            "Continuar", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        counter = 6;
                        panelFrase.setVisible(false);
                        panelConteo.setVisible(true);
                        timerPreparacion.start();
                    } else if (confirmacion == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }

                }
            } else if (e.getSource() == timerPreparacion) {
                counter--;
                if (counter >= 0 && counter <= 5) {
                    panelConteo.pintarPalabra("" + counter);
                } else {
                    timerPreparacion.stop();
                    counter = 8;
                    timerResolver.start();
                    panelConteo.setVisible(false);
                    panelResolver.setVisible(true);
                    modelGame.recordar();
                }
            } else if (e.getSource() == timerResolver) {
                responder = true;
                counter--;
                ArrayList<String> palabrasTotal = modelGame.getPalabrasTotalNivel();
                if (counter <= 8 && counter >= 0) {
                    panelResolver.pintarPalabra(palabrasTotal.get(palabraActual), "" + counter, nivel);
                    //timerResolver.start();
                }
                if (counter <= 0) {
                    panelResolver.estadoNulo();
                    counter = 8;
                    palabraActual++;
                    pasarPalabra = true;
                }
                if (palabraActual > (palabrasDeNivel * 2) - 1) {
                    score = modelGame.getScore();
                    timerResolver.stop();
                    counter = 8;
                    if (score >= aciertos) {
                        int opcion = JOptionPane.showConfirmDialog(panelFrase, "¡Has completado el nivel!"
                                + "\n" + "¿Deseas continuar?", "Ganaste", JOptionPane.YES_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "si");
                            panelResolver.setVisible(false);
                            panelFrase.setVisible(true);
                            modelGame.avanzarNivel();
                            timerMostrar.start();
                        } else if (opcion == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "no");
                            FileWritter fileWritter = new FileWritter();
                            if (usuarioEsta == true) {
                                int indice = (user.indexOf(usuario)) + 1;
                                user.set(indice, String.valueOf(nivel + 1));
                                String usuariosFinal = "";
                                for (int i = 0; i < user.size(); i++) {
                                    if (user.get(i) != "") {
                                        usuariosFinal = usuariosFinal + "\n" + user.get(i);
                                    }
                                }
                                fileWritter.escribirTexto(usuariosFinal.trim());
                            } else {
                                String usuariosFinal = "";
                                for (int i = 0; i < user.size(); i++) {
                                    if (user.get(i) != "") {
                                        usuariosFinal = usuariosFinal + "\n" + user.get(i);
                                    }
                                }

                                usuariosFinal = usuariosFinal + "\n" + usuario + "\n" + (nivel + 1);
                                fileWritter.escribirTexto(usuariosFinal.trim());
                            }
                            System.exit(0);
                        }
                    } else {
                        int opcion2 = JOptionPane.showConfirmDialog(panelFrase, "Has perdido :c"
                                + "\n" + "¿Deseas intentarlo de nuevo?", "Perdiste", JOptionPane.YES_OPTION);
                        if (opcion2 == JOptionPane.YES_OPTION) {
                            panelResolver.setVisible(false);
                            modelGame.limpiarArrays();
                            modelGame.verificarNivel();
                            modelGame.memorizar();
                            panelConteo.addKeyListener(escucha);
                            panelResolver.addKeyListener(escucha);
                            panelMenu.setVisible(false);
                            panelFrase.setVisible(true);
                            palabraActual = 0;
                            counter = 0;
                            timerMostrar.start();
                        } else if (opcion2 == JOptionPane.NO_OPTION) {
                            if (usuarioEsta == true) {
                                FileWritter fileWritter = new FileWritter();
                                int indice = (user.indexOf(usuario)) + 1;
                                user.set(indice, String.valueOf(nivel));
                                String usuariosFinal = "";
                                for (int i = 0; i < user.size(); i++) {
                                    if (user.get(i) != "") {
                                        usuariosFinal = usuariosFinal + "\n" + user.get(i);
                                    }
                                }
                                fileWritter.escribirTexto(usuariosFinal.trim());
                            } else {
                                FileWritter fileWritter = new FileWritter();
                                String usuariosFinal = "";
                                for (int i = 0; i < user.size(); i++) {
                                    if (user.get(i) != "") {
                                        usuariosFinal = usuariosFinal + "\n" + user.get(i);
                                    }
                                }

                                usuariosFinal = usuariosFinal + "\n" + usuario + "\n" + nivel;
                                fileWritter.escribirTexto(usuariosFinal.trim());
                            }
                            System.exit(0);
                        }
                    }
                }
            } else if (e.getSource() == timerMenu) {
                counter++;
                if (counter % 2 == 0) {
                    panelMenu.cambiarParpadeoTrue();
                } else {
                    panelMenu.cambiarParpadeoFalse();
                }
            }
        }
    }
}