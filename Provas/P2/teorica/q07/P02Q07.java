class No {
	public char elemento; 
	public No esq, dir;  

	public No(char elemento) {
		this(elemento, null, null);
	}

	
	public No(char elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

class ArvoreBinaria {
	private No raiz;

	public ArvoreBinaria() {
		raiz = null;
	}
	
	public boolean verifica() {
        boolean resp = false;
        resp = verifica(raiz, resp);
		return resp;
	}

	private boolean verifica(No i, boolean resp) {
		if (i != null) {
            if(i.elemento == 'a' || i.elemento == 'e' || i.elemento == 'i' || i.elemento == 'o' || i.elemento == 'u' && 
              ((i.dir.elemento == 'a' || i.dir.elemento == 'e' || i.dir.elemento == 'i' || i.dir.elemento == 'o' || i.dir.elemento == 'u') ||
               (i.esq.elemento == 'a' || i.esq.elemento == 'e' || i.esq.elemento == 'i' || i.esq.elemento == 'o' || i.esq.elemento == 'u'))){
                resp = true;
            }
			verifica(i.esq, resp); 
			verifica(i.dir, resp); 
		}
        return resp;
	}

	public void inserir(char x) throws Exception {
		raiz = inserir(x, raiz);
	}

	private No inserir(char x, No i) throws Exception {
		if (i == null) {
            i = new No(x);

        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

		return i;
	}
}

public class P02Q07{
    public static void main(String[] args) throws Exception{
        ArvoreBinaria tree1 = new ArvoreBinaria();
        ArvoreBinaria tree2 = new ArvoreBinaria();

        tree1.inserir('i');
        tree1.inserir('e');
        tree1.inserir('o');
        tree1.inserir('a');
        System.out.println("Teste 1: " + tree1.verifica());

        tree2.inserir('z');
        tree2.inserir('f');
        tree2.inserir('o');
        tree2.inserir('c');
        System.out.println("Teste 2: " + tree2.verifica());

        //Complexidade theta(n)
    }
}