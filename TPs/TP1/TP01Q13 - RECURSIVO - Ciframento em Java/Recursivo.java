class Recursivo{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String converterLinha(String s, int i, int n){
        String resp = "";
        if(n - 1 < i){
            MyIO.print("\n");
            return resp;
        }else{
            //MyIO.println(s);
            MyIO.print((char)(s.charAt(i) + 3));
            converterLinha(s, i = i + 1, n); 
        }
        return resp;
    }

    public static void main (String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

        //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
        for(int i = 0; i < numEntrada; i++){
            converterLinha(entrada[i], 0, entrada[i].length());
        }
    }
}
