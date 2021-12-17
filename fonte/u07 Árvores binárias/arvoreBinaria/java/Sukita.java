import java.util.*;

class Sukita{
    public static void main(String[] args) throws Exception{
        ArvoreBinaria a = new ArvoreBinaria();
        for(int i = 1; i <= 30; i++){
            a.inserir(i);
            System.out.printf("Número de nós = %d --- log(i,2) = %.2f --- h = %d\n", i, (Math.log(i) / Math.log(2)), a.getAltura());
        }
    }
}
