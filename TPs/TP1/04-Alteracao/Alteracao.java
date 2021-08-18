import java.util.Random;

class Alteracao{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static char gerador(Random gerador){
        char n;
        n = (char)( 'a' + (Math.abs(gerador.nextInt()) % 26));
        return n;
    }

    public static String converterLinha(String s, char a, char b){
        String resp = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == a){
                resp += b;
            }
            else
                resp += s.charAt(i);
        }
        return resp;
    }

    public static void main (String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Random gerador = new Random();
        gerador.setSeed(4);
        char a, b;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

        //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
        for(int i = 0; i < numEntrada; i++){
            a = gerador(gerador);
            b = gerador(gerador);
            MyIO.println(converterLinha(entrada[i], a, b));
        }
    }
}
