class Recursivo{
    public static boolean isMaiuscula (char c){
        return (c >= 'A' && c <= 'Z');
    }
  
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static int contarLetrasMaiusculas(String s, int i, int n, int resp){
        if(n - 1 >= i){    
            if(isMaiuscula(s.charAt(i)) == false){
                return contarLetrasMaiusculas(s, i = i + 1, n, resp);
            } else{
                return contarLetrasMaiusculas(s, i = i + 1, n, resp = resp + 1);
            }
        }
        return resp;
    }

    public static void main (String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

        //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
        for(int i = 0; i < numEntrada; i++){
            MyIO.println(contarLetrasMaiusculas(entrada[i], 0, entrada[i].length(), 0));
        }
    }
}