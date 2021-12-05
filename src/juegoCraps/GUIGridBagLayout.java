package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
    private static final String MENSAJE_INICIO="Bienvenido a Craps\n" +
            "Oprime el botón lanzar para iniciar el juego" +
            "\nSi tu tiro de salida es 7 u 11 ganas con Natural" +
            "\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps" +
            "\nSi sacas cualquier otro valor estableceras el punto" +
            "\nEstado en punto podrás seguir lanzando los dados" +
            "\npero ahora ganaras si sacas nuevamente el valor del punto " +
            "\nsin  que previamente hayas sacado 7";


    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar, ayuda, salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea mensajesSalida, resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;

    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("Juego Craps");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void initGUI(){
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton(" ? "); // puedo poner icono o imgen
        ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir = new JButton("Salir");
        ayuda.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imageDado);;
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imageDado);
            modelCraps.determinarJuego();

            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajesSalida.setRows(4);
            mensajesSalida.setText(modelCraps.getEstadoToString()[1]);
        }
    }
}
