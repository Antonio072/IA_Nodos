
import java.util.Scanner;

public class Indice {

    public static void main(String[] args) {
        int llave;
        int pos;

        Scanner teclado =  new Scanner(System.in);
        Arbol arbol1 = new Arbol();

        for (int i = 0; i < 5; i++) {
            arbol1.insertar(i+100,1+1);
            do {
                System.out.println("Dame la llave (100...104 / 0 para termiinar");
                llave = teclado.nextInt();
                pos = arbol1.buscar(llave);
                if(pos!=0)
                    System.out.println("La posicion es" +pos);
            }while (llave!=0);
        }
    }
}
