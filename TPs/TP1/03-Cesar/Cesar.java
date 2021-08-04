class Cesar{
    public static void Ciframento(String palavra){
        for(int i = 0; i < 1/*plavra.length();*/; i++){
            switch(palavra.charAt(i)){
                case 'a':
                   // palavra[i] = 'd';
                    break;
                case 'b':
                    //palavra[i] = 'e';
                    break;
            }
        }
    }

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    
    public static void main(String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            //entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
            numEntrada--;
    }       

}