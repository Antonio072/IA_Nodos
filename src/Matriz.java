import java.io.*;

public class Matriz {

    public void escribir(double matriz[][], int ren, int col) throws IOException {
        String m = "";
        StringBuffer buffer = null;
        /*
        Escritura del archivo maestro
         */
        System.out.println("archivo maestro");
        RandomAccessFile archivoMaestro = new RandomAccessFile("archivoMaestro", "rw");
        for (int i = 0; i < ren; i++) {
            for (int j = 0; j < col; j++) {
                m = m + " " + matriz[i][j];
            }
            m = m + "\n";
        }
        buffer = new StringBuffer(m);
        archivoMaestro.writeChars(buffer.toString());
        archivoMaestro.close();
        /*
        Escritura del archivo indice
         */
        System.out.println("archivo indice");
        RandomAccessFile archivoIndice = new RandomAccessFile("archivoIndice", "rw");
        for (int i = 0; i < ren; i++) {
            m+=  matriz[i][0];
            m+= " : " + matriz[i][1];
            m+= " , " + matriz[i][2];
            // for (int j = 0; j < col; j++) {
            //     m = m + " " + matriz[i][j];
            // }
            m = m + "\n";
        }
        buffer = new StringBuffer(m);
        archivoIndice.writeChars(buffer.toString());
        archivoIndice.close();
    }

    public void leer() throws IOException {
        long actual, apfinal;
        RandomAccessFile archivo = new RandomAccessFile("archivoIndice", "r");
        while ((actual = archivo.getFilePointer()) != (apfinal = archivo.length())) {
            System.out.print(archivo.readChar());
        }
        archivo.close();
    }
}