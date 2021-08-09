class Recursivo{
    public static boolean isMaiuscula (char c){
        return (c >= 'A' && c <= 'Z');
    }
  
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static int contarLetrasMaiusculas (String s, int count){
        int resp = 0;
        MyIO.println("Início recursivo");
        if(s.charAt(count) == '\n')
            resp = 0;
        else if(isMaiuscula(s.charAt(count)) == true)
            resp++;
        //contarLetrasMaiusculas(s, count++);
        MyIO.println("Fim recursivo"); 
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
            MyIO.println(contarLetrasMaiusculas(entrada[i], 0));
        }
    }
}