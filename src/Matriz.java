import java.io.*;

public class Matriz {

    public void escribir(Filas arr[], int ren, int col) throws IOException {
        String Origen, Destino;
        double Distancia;
        StringBuffer buffer = null, buf = null;
        /*
        Escritura del archivo maestro
         */
        //System.out.println("archivo maestro");
        RandomAccessFile archivoMaestro = new RandomAccessFile("archivoMaestro", "rw");
        for (int i = 0; i < ren; i++) {
            Origen = arr[i].getOrigen();
            buffer = new StringBuffer(Origen);
            buffer.setLength(10);
            archivoMaestro.writeChars(buffer.toString());
            Destino = arr[i].getDestino();
            buf = new StringBuffer(Destino);
            buf.setLength(10);
            archivoMaestro.writeChars(buf.toString());
            Distancia = arr[i].getPeso();
            archivoMaestro.writeDouble(Distancia);
            buffer = new StringBuffer("\r");
            archivoMaestro.writeChars(buffer.toString());
        }
        archivoMaestro.close();
        /*
        Escritura del archivo indice
        */
        System.out.println("archivo indice");
        String id;
        int pos = 0;
        RandomAccessFile archivoIndice = new RandomAccessFile("archivoIndice", "rw");
        for (int i = 0; i < ren; i++) {
            Origen = arr[i].getOrigen();
            buffer = new StringBuffer(Origen);
            buffer.setLength(10);
            archivoIndice.writeChars(buffer.toString());
            pos++;
            archivoIndice.writeInt(pos);
            buffer = new StringBuffer("\r");
            archivoIndice.writeChars(buffer.toString());
        }
        archivoIndice.close();
    }

    public void leer() throws IOException {
        long actual, apfinal;
        System.out.println("---- Archivo indice -----");
        RandomAccessFile archivo = new RandomAccessFile("archivoIndice", "r");
        while ((actual = archivo.getFilePointer()) != (apfinal = archivo.length())) {
            System.out.print(archivo.readChar());
        }
        archivo.close();
    }
}