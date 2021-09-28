class Q1{
    public static void insertion(String[] entrada, int[] ouro, int[] prata, int[] bronze, String[] name){
        boolean aux = true;
        boolean aux2 = false;
        boolean aux3 = true;
        
        for (int i = 1; i < entrada.length; i++) {
            aux = true;
            aux2 = false;
            aux3 = true;
            String tmpS = entrada[i];
		    int tmpO = ouro[i];
            int tmpP = prata[i];
            int tmpB = bronze[i];
            String tmpN = name[i];
            int j = i - 1;
            
            while ((j >= 0) && aux && (ouro[j] >= tmpO)) {
                if(ouro[i] == ouro[j]){
                    aux = false;
                    aux2 = false;
                }
                else {
                    entrada[j + 1] = entrada[j];
                    j--;
                }
            }
        
            while ((j >= 0) && !aux && (prata[j] >= tmpP)) {
                if(prata[i] == prata[j]){ 
                    aux = true;
                    aux2 = true;
                }
                else {
                    entrada[j + 1] = entrada[j];
                    j--;
                }
            }

            while ((j >= 0)&& aux2 && (bronze[j] >= tmpB)) {
                if(bronze[i] == bronze[j]){
                    aux2 = false;
                    aux3 = false;
                }
                else {
                    entrada[j + 1] = entrada[j];
                    j--;
                }
            }

            while ((j >= 0) && !aux3 && (name[j].compareTo(tmpN) < 0)) {
                System.out.println("nomeJ: " + name[j] + " nomeI: " + tmpN);
                entrada[j + 1] = entrada[j];
                j--;
            }
            entrada[j + 1] = tmpS;
        }
    }

    public static void main(String[] args){
        int num = MyIO.readInt();
        String[] splited = new String[num];
        String[] name = new String[num];
        int[] ouro = new int[num];
        int[] prata = new int[num];
        int[] bronze = new int[num];
        String[] entrada = new String[num];
       

        for(int i = 0; i < num; i++){
            entrada[i] = MyIO.readLine();
        }
        for(int i = 0; i < num; i++){
            //System.out.println("Entrada: " + entrada[i]);
            splited = entrada[i].split(" ");
            name[i] = splited[0];
            ouro[i] = Integer.parseInt(splited[1]);
            prata[i] = Integer.parseInt(splited[2]);
            bronze[i] = Integer.parseInt(splited[3]);
        }

        insertion(entrada, ouro, prata, bronze, name);

        for(int i = num-1; i >= 0; i--){
            System.out.println(entrada[i]);
        }
    }
}