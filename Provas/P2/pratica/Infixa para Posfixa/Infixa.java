class Pilha {
    private char[] str;
    private int n = 0;

    public Pilha () {
        this(200);
    }

    public Pilha (int tamanho){
        str = new char[tamanho];
        n = 0;
    }

    public void inserir(char s) throws Exception {
        if(n >= str.length){
            throw new Exception("Erro ao inserir!");
        }
        str[n] = s;
        n++;
    }

    public char remover() throws Exception {
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }
        return str[--n];
    }

    public void desempilhar() throws Exception {
        while(n != 0){
            remover();
        }
    }

    public void mostrar (){
        for(int i = 0; i < n; i++){
            System.out.print(str[i]);
        }
    }
}

public class Infixa{
    public static void main(String[] args) throws Exception{
        int n = MyIO.readInt();
        Pilha pilha = new Pilha(300);
        boolean b = false, a = false; 
        String s;
        char aux = ' ', teste = ' ';
        for(int i = 0; i < n; i++){
            s = MyIO.readLine();
            pilha.desempilhar();
            for(int j = 0; j < s.length(); j++){
                if(s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/' || s.charAt(j) == '^'){
                    if(s.charAt(j) == '/'){
                        teste = s.charAt(j);
                        a = true;
                    } else {
                        aux = s.charAt(j);
                        b = true;
                        pilha.inserir(s.charAt(j));
                    }
                } else if(s.charAt(j) != '(' && s.charAt(j) != ')'){
                    if(b == true){
                        b = false;
                        aux = pilha.remover();
                        pilha.inserir(s.charAt(j));
                        pilha.inserir(aux);
                        aux = ' ';
                    }else 
                        pilha.inserir(s.charAt(j));
                } else if((a == true && s.charAt(j) == ')') || j == s.length()){
                    a = false;
                    pilha.inserir(teste);
                    teste = ' ';
                }
            }
            if(aux != ' '){
                pilha.inserir(aux);
            } else if(teste != ' '){
                pilha.inserir(teste);
            }
            pilha.mostrar();
            System.out.println("");
        }
    }
}