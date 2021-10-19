class Celula{
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula(){
        this(0);
    }

    public Celula(int elemento){
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz{
    private Celula inicio;
    private int linha, coluna;

    public Matriz(){
        this(3, 3);
    }

    public Matriz(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;

        //alocar matriz com this.linha linhas e this.coluna colunas
        //criando matriz "vazia" 
        Celula T, S;
        inicio = new Celula(0);
        for(int i = 0; i < (coluna - 1); i++){
            S.dir = new Celula(3);
            S.dir.esq = S;
            S = S.dir;
        }

        for(int j = 0; j < (linha - 1); j++){
            T.inf = new Celula(0);
            T.inf.sup = T;
            T = T.inf;
        }

        for(T = inicio; l < linha; T = T.inf, l++){
            T.inf = new Celula(1);
            T.inf.sup = T;

            for(S = T.inf, c = 1; c < coluna; S = S.dir, c++){
                S.dir = new Celula(2);
                S.dir.esq = S;
                S.dir.sup = S.sup.dir;
                S.sup.dir.inf = S.dir;
            }
        }
    }

    public void mostrarMatriz() {
      for (Celula p = inicio; p != null; p = p.inf) {
         for (Celula pp = p; pp != null; pp = pp.dir) {
            System.out.print(pp.elemento + " ");
         }
         System.out.println(" ");
      }

   }
}

public class TP03Q17{
    public static void main(String[] args) throws Exception{
        Matriz m = new Matriz(3, 3);

        m.mostrarMatriz();
    }
}