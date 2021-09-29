class Fila {
    private int[] array;
    private int primeiro; // Remove do indice "primeiro".
    private int ultimo; // Insere no indice "ultimo".


    /**
        * Construtor da classe.
    */
    public Fila () {
        this(5);
    }

    /**
        * Construtor da classe.
        * @param tamanho Tamanho da fila.
    */
    public Fila (int tamanho){
        array = new int[tamanho+1];
        primeiro = ultimo = 0;
    }

    /**
        * Método para retornar o elemento na posição
        * @param i Posição do elemento desejado 
    */
    public int getElemento(int i){
        return array[i];
    }

    /**
        * Método para retornar o tamanho da fila
    */
    public int tamanho(){
        int tam = 0;
        for(int i = primeiro; i != ultimo; i = ((i + 1) % array.length)){
            tam += 1;
        }
        return tam;
    }

    /**
        * Insere um elemento na ultima posicao da fila.
        * @param x int elemento a ser inserido.
        * @throws Exception Se a fila estiver cheia.
    */
    public void inserir(int x) throws Exception {

        //validar insercao
        //System.out.println("Último para o if: " + (ultimo + 1) % array.length + " TAM: " + tamanho());
        if (tamanho() == array.length - 1) {
            remover();
        }

        array[ultimo] = x;
        //System.out.println("Primeiro: " + primeiro + "\tarray[ultimo]: " + array[ultimo].getName() + "\tÚltimo: " + ultimo);
        ultimo = (ultimo + 1) % array.length;
    }

    /**
        * Remove um elemento da primeira posicao da fila e movimenta 
        * os demais elementos para o primeiro da mesma.
        * @return resp int elemento a ser removido.
        * @throws Exception Se a fila estiver vazia.
    */
    public int remover() throws Exception {

        //validar remocao

        //System.out.println("Primeiro: " + primeiro + "\tÚltimo: " + ultimo + "\tTamanho: " + tamanho());
        if (tamanho() < 0) {
            throw new Exception("Erro ao remover!");
        }

        int resp = array[primeiro];
        //System.out.println("Primeiro: " + primeiro + "\tarray[primeiro]: " + array[primeiro].getName() + "\tÚltimo: " + ultimo);
        primeiro = (primeiro + 1) % array.length;
        return resp;
    }

    /**
        * Mostra os array separados por espacos.
        */
    public void mostrar (){
        System.out.print("[ ");
        for(int i = primeiro; i != ultimo; i = ((i + 1) % array.length)) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
   }
}

class P01Q13{

    public static void soma(Fila fila1, Fila fila2, Fila fila3, int elemento) throws Exception{
        if(elemento >= 5){
            System.out.println("Soma finalizada!");
            
        } else {
            System.out.println("Soma dos termos na posição " + elemento + ": " + (fila1.getElemento(elemento) + fila2.getElemento(elemento) + fila3.getElemento(elemento)));
            soma(fila1, fila2, fila3, elemento = elemento + 1);
            
        }
    }



    public static void main(String[] args) throws Exception{
        Fila fila1 = new Fila();
        Fila fila2 = new Fila();
        Fila fila3 = new Fila();

        fila1.inserir(1);
        fila1.inserir(2);
        fila1.inserir(3);
        fila1.inserir(4);
        fila1.inserir(5);

        fila2.inserir(5);
        fila2.inserir(4);
        fila2.inserir(3);
        fila2.inserir(2);
        fila2.inserir(1);

        fila3.inserir(9);
        fila3.inserir(8);
        fila3.inserir(7);
        fila3.inserir(6);
        fila3.inserir(5);

        soma(fila1, fila2, fila3, 0);

        System.out.print("Fila 1: ");
        fila1.mostrar();
        System.out.print("Fila 2: ");
        fila2.mostrar();
        System.out.print("Fila 3: ");
        fila3.mostrar();
    }
}