import java.util.Random;

class Alteracao{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static int gerador(){
        int n;
        Random gerador = new Random();
        gerador.setSeed(4);
        n = Math.abs(gerador.nextInt()) % 26;
        return n;
    }

    public static String converterLinha(String s){
        String resp = "";
        for(int i = 0; i < s.length(); i++){
            resp = resp +(char)(s.charAt(i) + gerador());
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
            MyIO.println(converterLinha(entrada[i]));
        }
    }
}
