class Celula2 {
	public Pilha elemento; // Elemento inserido na celula.
	public Celula2 prox; // Aponta a celula prox.

	/**
	 * Construtor da classe.
	*/
	public Celula2() {
		this(null);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	*/
	public Celula2(Pilha elemento) {
        this.elemento = elemento;
        this.prox = null;
	}
}

//classe Fila
class Fila {
	private Celula2 primeiro;
	private Celula2 ultimo;
    public int count;

	public Fila() {
		primeiro = new Celula2();
		ultimo = primeiro;
        count = 0;
	}

	public void inserir(Pilha x) throws Exception{
		ultimo.prox = new Celula2(x);
		ultimo = ultimo.prox;
	}

	public int remover() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}

        Celula2 tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento.topo.elemento;
        tmp.prox = null;
        tmp = null;
		return resp;
	}

	public void mostrar() {
		for(Celula2 i = primeiro.prox; i != null; i = i.prox) {
			i.elemento.mostrar();
		}
	}

    public int pesquisa(){
		for(Celula2 i = primeiro.prox; i != null; i = i.prox) {
            count = 0;
            for(Celula j = i.elemento.topo; j != null; j = j.prox) {
                if(j.elemento != 1){
                    count += 1;
                } else if(j.elemento == 1 && count == 0){
                    i.prox = null;
                    j.prox = null;
                } else {
                    count += 1;
                    i.prox = null;
                    j.prox = null;
                }        
            }
        }
        return count;
    }
}

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

//classe Pilha
class Pilha {
	public Celula topo;

	public Pilha() {
		topo = null;
	}

	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

	public int remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}
		int resp = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}

	public void mostrar() {
		for (Celula i = topo; i != null; i = i.prox) {
			System.out.println(i.elemento);
		}
	}

	public void mostraPilha() {
		mostraPilha(topo);
	}

	private void mostraPilha(Celula i) {
		if (i != null) {
			mostraPilha(i.prox);
			System.out.println("" + i.elemento);
		}
	}
}

public class Caixas{
    public static void main(String[] args) throws Exception{
        int n = MyIO.readInt();
        int p = MyIO.readInt();
        int doido = 0, aux = 0;
        Pilha pilha;
        Fila fila;
        while(p != 0 && n != 0){
            fila = new Fila();
            for(int i = 0; i < p; i++){
                pilha = new Pilha();
                doido = MyIO.readInt();
                for(int j = 0; j < doido; j++){
                    aux = MyIO.readInt();
                    pilha.inserir(aux);
                }
                fila.inserir(pilha);
            }
            //fila.mostrar();
            System.out.println(fila.pesquisa());
            n = MyIO.readInt();
            p = MyIO.readInt();
        }
    }
}
