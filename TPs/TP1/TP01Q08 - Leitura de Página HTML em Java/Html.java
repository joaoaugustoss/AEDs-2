import java.io.*;
import java.net.*;

class Html {
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

    public static String getHtml(String endereco){
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
                resp += line + '\n';
            }

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
        return resp;
    }

    public static String countHtml(String s){
        String resp = getHtml(s);
        String retorno = "";
        //int[] array = new int[25]; // declaração de um array para armazenar a resposta
        int x0 = 0, x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0, 
        x13 = 0, x14 = 0, x15 = 0, x16 = 0, x17 = 0, x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0, bre = 0, table = 0;
        char c;
        for(int i = 0; i < resp.length(); i++){ 
            c = resp.charAt(i);
            if(resp.charAt(i) == '<' && resp.charAt(i+1) == 't' && resp.charAt(i+2) == 'a' && resp.charAt(i+3) == 'b' && resp.charAt(i+4) == 'l' && resp.charAt(i+5) == 'e' && resp.charAt(i+6) == '>')
                table+=1;
            else if(resp.charAt(i) == '<' && resp.charAt(i+1) == 'b' && resp.charAt(i+2) == 'r' && resp.charAt(i+3) == '>')
                bre+=1;
            else{ 
                if(c == 97){
                    x0+=1;
                } else if(c == 101){
                    x1+=1;
                } else if(c == 105){
                    x2+=1;
                } else if(c == 111){
                    x3+=1;
                } else if(c == 117){
                    x4+=1;
                } else if(c == 225){
                    x5+=1;
                } else if(c == 233){
                    x6+=1;
                } else if(c == 237){
                    x7+=1;
                } else if(c == 243){
                    x8+=1;
                } else if(c == 250){
                    x9+=1;
                } else if(c == 224){
                    x10+=1;
                } else if(c == 232){
                    x11+=1;
                } else if(c == 236){
                    x12+=1;
                } else if(c == 242){
                    x13+=1;
                } else if(c == 249){
                    x14+=1;
                } else if(c == 227){
                    x15+=1;
                } else if(c == 245){
                    x16+=1;
                } else if(c == 226){
                    x17+=1;
                } else if(c == 234){
                    x18+=1;
                } else if(c == 238){
                    x19+=1;
                } else if(c == 244){
                    x20+=1;
                } else if(c == 251){
                    x21+=1;
                } else {
                    if(c >=  97 && c <= 122 && c != 97 && c != 101 && c != 105 && c != 111 && c != 117)
                        if(isSpecial(c)){
                            x22+=1;
                        }
                }
            }
            
        }
        return retorno += "a(" + x0 + ") e(" + x1 + ") i(" + x2 + ") o(" + x3 + ") u(" + x4 + ") á(" + x5 + ") é(" + x6 + ") í(" + x7 + ") ó(" + x8 + ") ú(" 
        + x9 + ") à(" + x10 + ") è(" + x11 + ") ì(" + x12 + ") ò(" + x13 + ") ù(" + x14 + ") ã(" + x15 + ") õ(" + x16 + ") â(" + x17 + ") ê(" + x18 + 
        ") î(" + x19 + ") ô(" + x20 + ") û(" + x21 + ") consoante(" + x22 + ") <br>(" + bre + ") <table>(" + table + ") ";
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        String[] nome = new String[1000];
        String resp;
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
            resp = countHtml(entrada[i]);
            MyIO.println(resp + nome[i]);
        }
    }
}