class Celula {
	public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.

	public Celula() {
		this(0);
	}

	public Celula(int elemento) {
      this.elemento = elemento;
      this.prox = null;
	}
}

class Pilha {
	private Celula topo;

	public Pilha() {
		topo = null;
	}

	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = topo; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}

	public int getSoma(Pilha pilha1, Pilha pilha2) {
		return getSoma(topo, pilha1.topo, pilha2.topo);
	}

	private int getSoma(Celula i, Celula i1, Celula i2) {
		int resp = 0;
		if (i != null && i1 != null && i != null) {
			resp = i.elemento + i1.elemento + i2.elemento;
            getSoma(i.prox, i1.prox, i2.prox);
            System.out.print(resp + " ");
		}
		return resp;
	}
}

public class P02Q08{
    public static void main(String[] args) throws Exception{
        Pilha pilha1 = new Pilha();
        Pilha pilha2 = new Pilha();
        Pilha pilha3 = new Pilha();

        pilha1.inserir(1);
        pilha1.inserir(2);
        pilha1.inserir(3);
        pilha1.inserir(4);
        pilha1.inserir(5);

        pilha2.inserir(5);
        pilha2.inserir(4);
        pilha2.inserir(3);
        pilha2.inserir(2);
        pilha2.inserir(1);

        pilha3.inserir(2);
        pilha3.inserir(1);
        pilha3.inserir(5);
        pilha3.inserir(3);
        pilha3.inserir(4);

        System.out.print("Soma das 3 pilhas: [ ");
        pilha1.getSoma(pilha2, pilha3);
        System.out.print("]");

        System.out.print("\nFila 1: ");
        pilha1.mostrar();
        System.out.print("Fila 2: ");
        pilha2.mostrar();
        System.out.print("Fila 3: ");
        pilha3.mostrar();
    }
}