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

class Fila {
	private Celula primeiro;
	private Celula ultimo;

	public Fila() {
		primeiro = new Celula();
		ultimo = primeiro;
	}

	public void inserir(int x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}

	public int remover() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}

        Celula tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
		return resp;
	}

	public void mostrar() {
		System.out.print("[ ");
		
		for(Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		
		System.out.println("] ");
	}

    public boolean vazia(){
        boolean resp = false;
        if(primeiro == ultimo){
            resp = true;
        }
        return resp;
    }

    public void enfileirar(int i) {
		Celula tmp = new Celula(i);
		tmp.prox = primeiro;
		primeiro = tmp;
		tmp = null;
	}

    public int desenfileirar() throws Exception {
		if (primeiro == null) {
			throw new Exception("Erro ao remover!");
		}
		int resp = primeiro.elemento;
		Celula tmp = primeiro;
		primeiro = primeiro.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}
}

public class P02Q09{
    public static void main(String[] args) throws Exception{
        Fila f1 = new Fila();
        Fila tmp = new Fila();

        f1.enfileirar(1);
        f1.enfileirar(2);
        f1.enfileirar(3);
        f1.enfileirar(4);
        f1.enfileirar(5);

        tmp.inserir(1);
        tmp.inserir(2);
        tmp.inserir(3);
        tmp.inserir(4);
        tmp.inserir(5);

        System.out.print("f1: ");
        f1.mostrar();
        System.out.print("tmp: ");
        tmp.mostrar();

        System.out.println("f1 vazia? " + f1.vazia());
        System.out.println("tmp vazia? " + tmp.vazia());

        System.out.println("f1 desenfileirar? " + f1.desenfileirar());
        System.out.println("tmp remover? " + tmp.remover());
    }
}