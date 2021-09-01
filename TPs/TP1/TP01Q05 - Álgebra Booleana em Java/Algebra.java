class Algebra{
    public static String[] expressao = {"!(false)", "!(true)", "||(false,false)", "||(false,true)", "||(true,false)", "||(true,true)", "&&(false,false)",
    "&&(false,true)", "&&(true,false)", "&&(true,true)", "||(false,false,false)", "||(false,false,true)", "||(false,true,false)", "||(false,true,true)",
    "||(true,false,false)", "||(true,false,true)", "||(true,true,false)", "||(true,true,true)", "&&(false,false,false)", "&&(false,false,true)", 
    "&&(false,true,false)", "&&(false,true,true)", "&&(true,false,false)", "&&(true,false,true)", "&&(true,true,false)", "&&(true,true,true)", 
    "||(false,false,false,false)", "||(false,false,false,true)", "||(false,false,true,false)", "||(false,false,true,true)", "||(false,true,false,false)",
    "||(false,true,false,true)", "||(false,true,true,false)", "||(false,true,true,true)", "||(true,false,false,false)", "||(true,false,false,true)", 
    "||(true,false,true,false)", "||(true,false,true,true)", "||(true,true,false,false)", "||(true,true,false,true)", "||(true,true,true,false)", 
    "||(true,true,true,true)", "&&(false,false,false,false)", "&&(false,false,false,true)", "&&(false,false,true,false)", "&&(false,false,true,true)", 
    "&&(false,true,false,false)", "&&(false,true,false,true)", "&&(false,true,true,false)", "&&(false,true,true,true)", "&&(true,false,false,false)", 
    "&&(true,false,false,true)", "&&(true,false,true,false)", "&&(true,false,true,true)", "&&(true,true,false,false)", "&&(true,true,false,true)", 
    "&&(true,true,true,false)", "&&(true,true,true,true)"};

    public static String[] result = {"1", "0", "0", "1", "1", "1", "0", "0", "0", "1", "0", "1", "1", "1", "1", "1", "1", "1", "0", "0", "0", "0", "0",
    "0", "0", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
    "0", "0", "0", "0", "1"};

    public static boolean isFim(String s){
        return (s.length() == 1 && s.charAt(0) == '0');
    }

    public static boolean isTrue(char c){
        return (c == '1');
    }

    public static String trim(String s){
        String resp = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ' ')
                resp += s.charAt(i);
        }
        return resp;
    }

    public static String replace(String s){
        for(int i = 0; i < expressao.length - 1; i++){
            if(s == expressao[i])
                s = result[i];
            else
                
        }
        return s;
    }

    public static String verifica(char x, boolean A, boolean B, boolean C){
        String resp = ""; 
        String operator;
        if(x == 't'){
            operator = "!";
            resp += operator;
        } else if(x == 'a'){
            operator = "&&";
            resp += operator;
        } else if(x == 'r'){
            operator = "||";
            resp += operator;
        } else if(x == 'o'){
            resp += "";
        } else if(x == 'n'){
            resp += "";
        } else if(x == 'd'){
            resp += "";
        } else if(x >= 48 && x <= 57){
            resp = resp;
        } else if(x == 'A'){
            resp += A;
        } else if(x == 'B'){
            resp += B;
        } else if(x == 'C'){
            resp += C;
        } else
            resp += x;
        return resp;
    }

    public static boolean algebrar(String s){
        String resp = "";
        char q = s.charAt(0), x;
        boolean A = isTrue(s.charAt(2)), B = isTrue(s.charAt(4)), doido;
        if(q == '2'){
            for(int i = 0; i < s.length(); i++){
                x = s.charAt(i);
                resp += verifica(x, A, B, false);
                resp = trim(resp);
                resp = replace(resp);
                //doido = Boolean.getBoolean(resp);
            }
            MyIO.println(resp);

        } else if(q == '3'){

            boolean C = isTrue(s.charAt(6));
            
            for(int i = 0; i < s.length(); i++){
                x = s.charAt(i);
                resp += verifica(x, A, B, C);
                resp = trim(resp);
                resp = replace(resp);
                //doido = Boolean.getBoolean(resp);
            }
            MyIO.println(resp);
        }
        return A;
    }

    public static void main(String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;

        //Leitura da entrada padrão
        do{
            entrada[numEntrada] = MyIO.readLine();
        } while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            algebrar(entrada[i]);
        }
    }
}