class Is{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isVogal(String s){
        boolean resp;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'a' || s.charAt(i) == 'A' || s.charAt(i) == 'e' || s.charAt(i) == 'E' || s.charAt(i) == 'i' || s.charAt(i) == 'I' || s.charAt(i) == 'o' || s.charAt(i) == 'O' || s.charAt(i) == 'u' || s.charAt(i) == 'U')
                resp = true;
        }
    }

    public static boolean isConsoante(String s){
        boolean resp;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != 'a' || s.charAt(i) != 'A' || s.charAt(i) != 'e' || s.charAt(i) != 'E' || s.charAt(i) != 'i' || s.charAt(i) != 'I' || s.charAt(i) != 'o' || s.charAt(i) != 'O' || s.charAt(i) != 'u' || s.charAt(i) != 'U')
                resp = true;
        }
        return resp;
    }

    public static boolean isInt(String s){
        Integer.parseInt(s)
        int y = x;
        
    }

    public static boolean isFloat(String s){

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
            MyIO.println(contarLetrasMaiusculas(entrada[i]));
        }
    }
}
