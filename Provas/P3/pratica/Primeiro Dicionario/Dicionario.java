import java.io.*;

//classe Lista
class Lista {
    private String[] elemento;
    private int n = 0;

    public Lista () {
        this(200);
    }

    public Lista (int tamanho){
        elemento = new String[tamanho];
        n = 0;
    }

    public void inserir(String s) throws Exception {
        //validar insercao
        boolean aux = false;
        if(n >= elemento.length){
            throw new Exception("Erro ao inserir!");
        }
        for(int i = 0; i < n; i++){
            if(elemento[i].equals(s))
                aux = true;
        }
        if(!aux){
            elemento[n] = s;
            n++; 
        }
    }

    public void mostrar (){
        for(int i = 0; i < n; i++){
            System.out.println(elemento[i]);
        }
    }

    public void sort() {
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++){
                if (elemento[menor].compareTo(elemento[j]) > 0){
                    menor = j;
                } else {
                    if(elemento[menor].compareTo(elemento[j]) == 0){
                        if(elemento[menor].compareTo(elemento[j]) > 0)
                            menor = j;
                    }
                }
            }
            swap(menor, i);
        }
    }

    public void swap(int i, int j){
        String temp = elemento[i];
        elemento[i] = elemento[j];
        elemento[j] = temp;
    }
}



public class Dicionario{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        Lista lista = new Lista(10000);
        while((entrada = br.readLine()) != null){
            if(entrada.contains(" ") || entrada.contains(".")){
                entrada = entrada.replace(".", " ").replace("(", " ").replace("*$", "").replace("#", "").replace(":", "").replace("\"", "");
                entrada = entrada.replace("  ", " ");
                String[] separa = entrada.split(" ");

                for(int i = 0; i < separa.length; i++){
                    lista.inserir(separa[i].toLowerCase());
                }
            }
        }
        lista.sort();
        lista.mostrar();
    }
}
