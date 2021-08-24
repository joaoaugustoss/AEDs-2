import java.io.*;
import java.net.*;

class Html1 {
    public static boolean isFim(String s){ //Verifica condição de parada
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isBr(String s){ // Verifica <br>
        return(s.length() == 4 && s.charAt(0) == '<' && s.charAt(1) == 'b' && s.charAt(2) == 'r' && s.charAt(3) == '>');
    }

    public static boolean isTable(String s){ //Verifica <table>
        return(s.length() == 7 && s.charAt(0) == '<' && s.charAt(1) == 't' && s.charAt(2) == 'a' && s.charAt(3) == 'b' && s.charAt(4) == 'l' && 
        s.charAt(5) == 'e' && s.charAt(6) == '>');
    }

    public static boolean isSpecial(char c){ //Verifica <>
        boolean resp = true;
        if(c == '<' || c == '>')
            resp = false;
        return resp;
    }

    public static int[] getHtml(String endereco){ //Leitura da página retornando um array de inteiros
        int[] array = new int[25]; // declaração de um array para armazenar a resposta
        int x = 0, bre = 0, table = 0; //inicialização de variáveis
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
        char c;

        try {
            url = new URL(endereco);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) { //Início dos testes para o contador
                resp = line;
                if(isBr(resp))
                    bre++;
                else if(isTable(resp))
                    table++;
                else {
                    for(int i = 0; i < resp.length(); i++){ 
                        c = resp.charAt(i);
                        if(c == 97){
                            x = 1;
                            array[0] += x;
                        } else if(c == 101){
                            x = 1;
                            array[1] += x;
                        } else if(c == 105){
                            x = 1;
                            array[2] += x;
                        } else if(c == 111){
                            x = 1;
                            array[3] += x;
                        } else if(c == 117){
                            x = 1;
                            array[4] += x;
                        } else if(c == 225){
                            x = 1;
                            array[5] += x;
                        } else if(c == 233){
                            x = 1;
                            array[6] += x;
                        } else if(c == 237){
                            x = 1;
                            array[7] += x;
                        } else if(c == 243){
                            x = 1;
                            array[8] += x;
                        } else if(c == 250){
                            x = 1;
                            array[9] += x;
                        } else if(c == 224){
                            x = 1;
                            array[10] += x;
                        } else if(c == 232){
                            x = 1;
                            array[11] += x;
                        } else if(c == 236){
                            x = 1;
                            array[12] += x;
                        } else if(c == 242){
                            x = 1;
                            array[13] += x;
                        } else if(c == 249){
                            x = 1;
                            array[14] += x;
                        } else if(c == 227){
                            x = 1;
                            array[15] += x;
                        } else if(c == 245){
                            x = 1;
                            array[16] += x;
                        } else if(c == 226){
                            x = 1;
                            array[17] += x;
                        } else if(c == 234){
                            x = 1;
                            array[18] += x;
                        } else if(c == 238){
                            x = 1;
                            array[19] += x;
                        } else if(c == 244){
                            x = 1;
                            array[20] += x;
                        } else if(c == 251){
                            x = 1;
                            array[21] += x;
                        } else {
                            if(c >=  97 && c <= 122 && c != 97 && c != 101 && c != 105 && c != 111 && c != 117)
                                if(isSpecial(c)){
                                    x = 1;
                                    array[22] += x;
                                }
                        }
                    }
                }
            }
            array[23] = bre;
            array[24] = table;
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } 

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }
        return array; //Retorno do array com os contadores
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        String[] nome = new String[1000];
        int numEntrada = 0;

//Leitura da entrada padrao
        do {
            nome[numEntrada] = MyIO.readLine(); //Leitura do título da página
            if(!nome[numEntrada].equals("FIM")){
                entrada[numEntrada] = MyIO.readLine(); //Leitura do link da página
            }
        } while (isFim(nome[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

        String endereco, html;
        for(int i = 0; i < numEntrada; i++){
            endereco = entrada[i];
            int[] array = getHtml(entrada[i]); // array para printar a resposta da função getHtml
            MyIO.println("a(" + array[0] + ") e(" + array[1] + ") i(" + array[2] + ") o(" + array[3] + ") u(" + array[4] + ") á(" + array[5] + ") é(" +
             array[6] + ") í(" + array[7] + ") ó(" + array[8] + ") ú(" + array[9] + ") à(" + array[10] + ") è(" + array[11] + ") ì(" + array[12] + 
             ") ò(" + array[13] + ") ù(" + array[14] + ") ã(" + array[15] + ") õ(" + array[16] + ") â(" + array[17] + ") ê(" + array[18] + ") î(" + 
             array[19] + ") ô(" + array[20] + ") û(" + array[21] + ") consoante(" + array[22] + ") <br>(" + array[23] + ") <table>(" + array[24] + ") " +
              nome[i]);
        }
    }
}