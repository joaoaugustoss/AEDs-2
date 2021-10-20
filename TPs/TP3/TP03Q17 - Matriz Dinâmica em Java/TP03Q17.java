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
        Celula T;
        Celula S;
        int c = 1, l = 1;
        inicio = new Celula(0);
        for(S = inicio; c < coluna; S = S.dir){
            S.dir = new Celula(0);
            S.dir.esq = S;
            c++;
        }

        for(T = inicio; l < linha; T = T.inf, l++){
            T.inf = new Celula(0);
            T.inf.sup = T;

            for(S = T.inf, c = 1; c < coluna; S = S.dir, c++){
                S.dir = new Celula(0);
                S.dir.esq = S;
                S.dir.sup = S.sup.dir;
                S.sup.dir.inf = S.dir;
            }
        }
    }

    public void mostrar() {
        for (Celula p = inicio; p != null; p = p.inf) {
            for (Celula pp = p; pp != null; pp = pp.dir) {
                System.out.print(pp.elemento + " ");
            }
            System.out.println(" ");
        }
    }

    public Celula inserir(int l, int c){
        for(Celula i = inicio; i != null; i = i.inf){
            for(Celula j = i; j != null; j = j.dir){
                j.elemento = MyIO.readInt();
            }
        }
        return this.inicio;
    }

    public void setElemento(int c, int l, int elemento){
        Celula i, j;
        int aux1 = 0, aux2 = 0;
        for(i = inicio, aux1 = 1; i != null; i = i.inf, aux1++){
            for(j = i, aux2 = 1; j != null; j = j.dir, aux2++){
                if(aux1 == c && aux2 == l)
                    aux2 = elemento;
            }
        }
    }

    public int getElemento(int c, int l){
        Celula i, j;
        int aux1 = 0, aux2 = 0, resp = 0;
        for(i = inicio, aux1 = 1; i != null; i = i.inf, aux1++){
            for(j = i, aux2 = 1; j != null; j = j.dir, aux2++){
                if(aux1 == c && aux2 == l)
                    resp = j.elemento;
            }
        }
        return resp;
    }

    public void mostrarDiagonalPrincipal(){
        Celula i, j;
        int aux1 = 0;
        int aux2 = 0;
        int resp = 0;
        for(i = inicio, aux1 = 1; i != null; i = i.inf, aux1++){
            for(j = i, aux2 = 1; j != null; j = j.dir, aux2++){
                if(aux1 == aux2){
                    resp = j.elemento;
                    System.out.print(resp + " ");
                }
            }
        }
    }

    public void mostrarDiagonalSecundaria(int l, int c){
        Celula i, j;
        int aux1 = 0, aux2 = 0, resp = 0;
        int linha = (c - l) + 1;
        int coluna = l;
        for(i = inicio, aux1 = 1; i != null; i = i.inf, aux1++){
            for(j = i, aux2 = 1; j != null; j = j.dir, aux2++){
                if(aux1 == linha && aux2 == coluna){
                    resp = j.elemento;
                    System.out.print(resp + " ");
                    linha++;
                    coluna--;
                }
            }
        }
    }

    public Matriz soma(Matriz m1, Matriz m2){
        Matriz resp = new Matriz(this.linha, this.coluna);
        int aux1 = 0, aux2 = 0;
        Celula i, j;
        for(i = resp.inicio, aux1 = 1; i != null; i = i.inf, aux1++){
            for(j = i, aux2 = 1; j != null; j = j.dir, aux2++){
                j.elemento = m1.getElemento(aux1, aux2) + m2.getElemento(aux1, aux2);
            }
        }
        return resp;
    }

    public void multiplica(Matriz m1, Matriz m2){
        int teste[][] = new int[m1.linha][m2.coluna];
        Matriz resp = null;
        if(m1.coluna == m2.linha){    
            resp = new Matriz(m1.linha, m2.coluna);
            int aux1 = 0, aux2 = 0, mult = 0;
            Celula i, j;
            for(i = m1.inicio, aux1 = 1; i != null; i = i.inf, aux1++){
                for(j = i, aux2 = 1; j != null; j = j.dir, aux2++){
                    mult = 0;
                    for(int z = 1; z <= m1.coluna; z++){
                        mult = mult + m1.getElemento(aux1, z) * m2.getElemento(z, aux2);
                    }
                    System.out.print(mult + " ");
                }
                System.out.println();
            }
        }
    }
}

public class TP03Q17{
    public static void main(String[] args) throws Exception{
        int caso = MyIO.readInt();

        for(int i = 0; i < caso; i++){
            int l1 = MyIO.readInt();
            int c1 = MyIO.readInt();
            Matriz m1 = new Matriz(l1, c1);
            m1.inserir(l1, c1);
            //m1.mostrar();
            //System.out.println("elemento 0, 0 m1: " + m1.getElemento(0, 0));

            int l2 = MyIO.readInt();
            int c2 = MyIO.readInt();
            Matriz m2 = new Matriz(l2, c2);
            m2.inserir(l2, c2);
            //m2.mostrar();

            Matriz m3 = new Matriz(l2, c2);

            //System.out.println("elemento 0, 0 m2: " + m2.getElemento(1, 1));

            m1.mostrarDiagonalPrincipal();
            System.out.println();
            m1.mostrarDiagonalSecundaria(l1, c1);
            System.out.println();
            m3.soma(m1, m2).mostrar();
            m3.multiplica(m1, m2);
        }
    }
}