import java.io.*;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Arbol {

    Nodo raiz=null;

    public void insertar(int llave, int p){
        Nodo nuevo;
        Nodo ant = null;
        Nodo rec;
            if(raiz==null)
            {
                raiz =  new Nodo(llave,p);
                System.out.println("Valor insertado " + llave);
            }
                else
                {
                    nuevo = new Nodo(llave,p);
                    System.out.println("Valor insertado " + p);
                    rec = raiz;
                    while (rec != null)
                    {
                        ant=rec;
                        if(rec.llav > nuevo.llav)
                            rec = rec.der;
                        else
                            rec = rec.der;
                    }
                    if(ant.llav > nuevo.llav)
                        ant.izq = nuevo;
                    else
                        ant.der = nuevo;

                }
    }


    public int buscar (int llave)
    {
        int p=0;
        Nodo rec; Nodo ant;
        if(raiz == null)
            System.out.println("En este momento el arbol se encuentra vacio");
        else
        {
            rec = raiz;
            ant = raiz;
            while((ant.llav != llave) && (rec != null))
                {
                    ant = rec;
                    if (rec.llav > llave)
                        rec = rec.izq;
                    else    
                        rec = rec.der;
                }
            if( ant.llav == llave)
            {
                System.out.println("El valor se encuenta en el arbol: " + ant.llav);
                p = ant.d1;
            }
            else
                System.out.println("El valor " + llave + " no se encuenta en el arbol");
        }
        return(p);
    }
}
