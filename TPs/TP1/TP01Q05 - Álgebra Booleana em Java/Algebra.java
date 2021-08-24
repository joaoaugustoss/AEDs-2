class Algebra{
    public static boolean isFim(String s){
        return (s.length() == 1 && s.charAt(0) == '0');
    }

    public static boolean isTrue(char c){
        return (c == '1');
    }

    public static boolean algebrar(String s){
        char q = s.charAt(0);
        boolean A = isTrue(s.charAt(2)), B = isTrue(s.charAt(4));
        //MyIO.println(A + " " + B);
        char x;
        if(q == '2'){
            for(int i = 0; i < s.length(); i++){
                x = s.charAt(i);
                if(x == 'n'){
                    x = '!';
                    MyIO.print(x);
                } else if(x == 'a'){
                    x = '&&';
                    MyIO.print(x);
                } else if(x == 'o'){
                    x = '||';
                    MyIO.print(x);
                }
            }
            //MyIO.println(A + " " + B);

        } else if(q == '3'){

            boolean C = isTrue(s.charAt(6));
            //MyIO.println(A + " " + B + " " + C);
            for(int i = 0; i < s.length(); i++){
                x = s.charAt(i);
                if(x == 'n'){
                    x = '!';
                    MyIO.print(x);
                } else if(x == 'a'){
                    x = '&&';
                    MyIO.print(x);
                } else if(x == 'o'){
                    x = '||';
                    MyIO.print(x);
                }
            }
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