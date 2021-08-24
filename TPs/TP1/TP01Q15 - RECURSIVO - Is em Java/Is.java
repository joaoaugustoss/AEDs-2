class Is{
    public static boolean isFim(String s){ //Verifica se é FIM
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isVogal(String s){ //Verifica se é vogal
        char x;
        boolean resp = true;
        for(int i = 0; i < s.length(); i++){
            x = s.charAt(i);
            if(!(x == 'a' || x == 'A' || x == 'e' || x == 'E' || x == 'i' || x == 'I' || x == 'o' || x == 'O' || x == 'u' || x == 'U'))
                resp = false;
        }
        return resp;
    }

    public static boolean isConsoante(String s){ //Verifica se é consoante
        char x;
        boolean resp = true;
        for(int i = 0; i < s.length(); i++){
            x = s.charAt(i);
            if((x == 'a' || x == 'A' || x == 'e' || x == 'E' || x == 'i' || x == 'I' || x == 'o' || x == 'O' || x == 'u' || x == 'U') || !(x >= 'A' && x <= 'Z' || x >= 'a' && x <= 'z'))
                resp = false;
        }
        return resp;
    }

    public static boolean isInt(String s){ //Verifica se é número inteiro
        char x;
        boolean resp = true;
        for(int i = 0; i < s.length(); i++){
            x = s.charAt(i);
            if(x >= 'A' && x <= 'Z' || x >= 'a' && x <= 'z' || x == '.' || x == ',' || x == ';')
                resp = false;
        }
        return resp;
    }

    public static boolean isFloat(String s){ //Verifica se é número real
        char x;
        int count = 0;
        boolean resp = false;
        for(int i = 0; i < s.length(); i++){
            x = s.charAt(i);
            if(x == '.' || x == ',' || x == ';')
                count++;
            if(x >= 48 && x <= 57){
                resp = true;
            }
        }
        if(count > 1)
            resp = false;
        return resp;
    }

    public static boolean Is(String string[], int i, int count){
        if(i > count)
            return false;
        else{
            if(isVogal(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isConsoante(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isInt(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isFloat(entrada[i]) == true)
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
            Is(string, i++, count);
        return true;
    }

    public static void main (String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;
        String x1, x2, x3, x4;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

        //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
        for(int i = 0; i < numEntrada; i++){
            if(isVogal(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isConsoante(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isInt(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");
            if(isFloat(entrada[i]) == true)
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
        }
    }
}