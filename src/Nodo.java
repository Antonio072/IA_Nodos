public class Nodo {

    int llav;
    int d1;

    Nodo izq;
    Nodo der;

    public Nodo(int ele, int dir, Nodo I, Nodo D){
        llav = ele;
        d1 = dir;
        izq = I;
        der = D;
    }

    public Nodo (int ele, int dir){
        this(ele,dir,null,null);
    }
}
