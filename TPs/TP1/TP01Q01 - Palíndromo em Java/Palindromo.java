class Palindromo{ 
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isPalindromo(String s){
        boolean resp = true;
        int j = 0; 
        for(int i = s.length() - 1; i >= 0 && resp; i--, j++){
            if(s.charAt(i) != s.charAt(j))
                resp = false;
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
        
        for(int i = 0; i < numEntrada; i++){
            if(isPalindromo(entrada[i]) == true)
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
        }
    }
}