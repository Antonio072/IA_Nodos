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
    Filas[] matriz;
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
            areaCentro.setLayout(new GridLayout(aux, 1, 0, 4));
            for (int i = 0; i < aux; i++) {
//               for (int j = 0; j < aux; j++) {
                /*
                Origen
                 */
                    JLabel lblOrigen = new JLabel("Origen: ");
                    lblOrigen.setHorizontalAlignment(SwingConstants.CENTER);
                    JTextField tfOrigen = new JTextField(5);
                    tfOrigen.setHorizontalAlignment(SwingConstants.LEFT);
                    areaCentro.add(lblOrigen);
                    areaCentro.add(tfOrigen);
                    arr.add(tfOrigen);
                /*
                Destino
                 */
                    JLabel lblDestino = new JLabel("Destino: ");
                    lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
                    JTextField tfDestino = new JTextField(5);
                    tfDestino.setHorizontalAlignment(SwingConstants.LEFT);
                    areaCentro.add(lblDestino);
                    areaCentro.add(tfDestino);
                    arr.add(tfDestino);
                /*
                Distancia
                 */
                    JLabel lblDistancia = new JLabel("Distancia: ");
                    lblDistancia.setHorizontalAlignment(SwingConstants.CENTER);
                    JTextField tfPeso = new JTextField(5);
                    tfPeso.setHorizontalAlignment(SwingConstants.LEFT);
                    areaCentro.add(lblDistancia);
                    areaCentro.add(tfPeso);
                    arr.add(tfPeso);
                }
//            }
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

        for (Object textField : arr) {
            texts.add(((JTextField) textField).getText());
        }
    }

    private void llenarMatriz() {
        matriz = new Filas[aux];
        int o = 0;
        for (int p = 0; p < aux; p++) {
            Filas f = new Filas();
            f.setOrigen(texts.get(o));
            o++;
            f.setDestino(texts.get(o));
            o++;
            try{
                f.setPeso(Double.valueOf(texts.get(o)));
            }catch (Exception e){
                f.setPeso(0.0);
                JOptionPane.showMessageDialog(this,"La distancia debe ser un numero real");
            }
            o++;
            matriz[p] = f;
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