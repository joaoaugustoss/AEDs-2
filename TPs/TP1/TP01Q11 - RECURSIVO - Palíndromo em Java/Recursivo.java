class Recursivo{ 
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isPalindromo(String s, int i, int j, boolean resp){
        if(resp == false && i < 0) //condição de parada recursividade
            return resp;
        else if(s.charAt(i) != s.charAt(j)){
            resp = false;
            isPalindromo(s, i = i - 1, j = j + 1, resp);
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
            if(isPalindromo(entrada[i], entrada[i].length() - 1, 0, true) == true)
               MyIO.println("SIM");
            else
               MyIO.println("NAO");
        }
    }
}