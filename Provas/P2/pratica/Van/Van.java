
class Lista {
    private String[] nome;
    private int n = 0;

    public Lista () {
        this(200);
    }

    public Lista (int tamanho){
        nome = new String[tamanho];
        n = 0;
    }

    public void inserirFim(String s) throws Exception {

        //validar insercao
        if(n >= nome.length){
            throw new Exception("Erro ao inserir!");
        }

        nome[n] = s;
        n++;
    }

    public void mostrar (){
        String[] aux = new String[n];
        for(int i = 0; i < n; i++){
            aux = nome[i].split(" ");
            System.out.println(aux[0]);
        }
    }

    public void sort() {
        String[] aux1 = new String[n];
        String[] aux2 = new String[n];
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++){
                aux1 = nome[i].split(" ");
                aux2 = nome[j].split(" ");
                if (Integer.parseInt(aux1[2]) > Integer.parseInt(aux2[2])){
                    menor = j;
                } else {
                    if(aux1[2].compareTo(aux2[2]) == 0){
                        if(aux1[1].compareTo(aux2[1]) > 0)
                            menor = j;
                        else if(aux1[1].compareTo(aux2[1]) == 0){
                            if(aux1[0].compareTo(aux2[0]) > 0)
                                menor = j;
                        }
                    } 
                } 
            }
            swap(menor, i);
        }
    }

    public void swap(int i, int j){
        String temp = nome[i];
        nome[i] = nome[j];
        nome[j] = temp;
    }
}

public class Van{
    public static void main(String[] args) throws Exception{
        int q = MyIO.readInt();
        Lista lista = new Lista(q);
        String a;
        String aux[] = new String[10];
        int c;
        for(int i = 0; i < q; i++){
            a = MyIO.readLine();
            lista.inserirFim(a);
        }
        lista.sort();
        lista.mostrar();
    }
}