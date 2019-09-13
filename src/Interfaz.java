import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author Luis Angel Arroyo Morales
 **/
public class Interfaz extends JFrame implements ActionListener {

    JTextField tfIngresar;
    JLabel lblSize;
    JButton btnGene, btnGuardar;
    JPanel areaArriba, areaCentro, areaAbajo;
    Container container;
    ArrayList arr;
    ArrayList<String> texts;
    double[][] matriz;
    int aux = 0;
    Matriz m;

    Interfaz() {
        iniciarComponentesGraficos();
    }

    private void iniciarComponentesGraficos() {
        container = getContentPane();
        setTitle("Matriz");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tfIngresar = new JTextField();
        tfIngresar.setPreferredSize(new Dimension(250, 50));
        lblSize = new JLabel("Numero de ciudades");
        lblSize.setPreferredSize(new Dimension(150, 100));
        btnGene = new JButton("Generar");
        btnGene.setPreferredSize(new Dimension(100, 50));
        btnGene.addActionListener(this);
        areaArriba = new JPanel(new FlowLayout());
        areaAbajo = new JPanel(new FlowLayout());
        areaArriba.add(lblSize);
        areaArriba.add(tfIngresar);
        areaArriba.add(btnGene);
        areaCentro = new JPanel();
        container.add(areaArriba, BorderLayout.NORTH);
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        areaAbajo.add(btnGuardar);
        container.add(areaAbajo, BorderLayout.SOUTH);
        arr = new ArrayList();
        texts = new ArrayList();
        m = new Matriz();
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        areaCentro.removeAll();
        if (ae.getSource() == btnGene) {
            try {
                generarInterfazMatriz();
            } catch (NumberFormatException excepcion) {
                JOptionPane.showMessageDialog(this, "solo puedes ingresar valores numericos");
            }
            añadirInterfazMatriz();
        }
        if (ae.getSource() == btnGuardar) {
            recuperarDatosInterfazMatriz();
            llenarMatriz();
            escribirMatrizArchivo();
            //limpiar arreglos auxiliares
            arr.clear();
            texts.clear();
        }
    }

    private void generarInterfazMatriz() {
        aux = Integer.parseInt(tfIngresar.getText());
        if (aux > 0) {
            areaCentro.setLayout(new GridLayout(aux, aux, 0, 4));
            for (int i = 0; i < aux; i++) {
//                for (int j = 0; j < aux; j++) {
                /*
                Origen
                 */
                    JLabel lblOrigen = new JLabel("Origen: ");
                    lblOrigen.setHorizontalAlignment(SwingConstants.CENTER);
                    JTextField tfPeso = new JTextField(5);
                    tfPeso.setHorizontalAlignment(SwingConstants.LEFT);
                    areaCentro.add(lblOrigen);
                    areaCentro.add(tfPeso);
                    arr.add(tfPeso);
                /*
                Destino
                 */
                    JLabel lblDestino = new JLabel("Destino: ");
                    lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
                    tfPeso = new JTextField(5);
                    tfPeso.setHorizontalAlignment(SwingConstants.LEFT);
                    areaCentro.add(lblDestino);
                    areaCentro.add(tfPeso);
                    arr.add(tfPeso);
                /*
                Distancia
                 */
                    JLabel lblDistancia = new JLabel("Distancia: ");
                    lblDistancia.setHorizontalAlignment(SwingConstants.CENTER);
                    tfPeso = new JTextField(5);
                    tfPeso.setHorizontalAlignment(SwingConstants.LEFT);
                    areaCentro.add(lblDistancia);
                    areaCentro.add(tfPeso);
                    arr.add(tfPeso);
//                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "solo puedes ingresar numeros positivos");
        }
    }

    private void añadirInterfazMatriz() {
        container.add(areaArriba, BorderLayout.NORTH);
        container.add(areaCentro, BorderLayout.CENTER);
        tfIngresar.setText("");
        pack();
    }

    private void recuperarDatosInterfazMatriz() {

        matriz = new double[aux][aux];
        for (Object textField : arr) {
            texts.add(((JTextField) textField).getText());
        }
    }

    private void llenarMatriz() {
        int o = 0;
        for (int p = 0; p < aux; p++) {
            for (int q = 0; q < aux; q++) {
                try {

                    System.out.println(matriz[p][2]);
                    matriz[p][2] = Double.valueOf(texts.get(o));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "solo puedes ingresar valores numericos");
                }
                o++;
            }
        }
    }

    private void escribirMatrizArchivo() {
        try {
            m.escribir(matriz, aux, aux);
            m.leer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}