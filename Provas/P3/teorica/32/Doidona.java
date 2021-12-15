class Doidona{
    int TAMT1 = 8;
    int TAMT2 = 8;
    int TAMT3 = 8;
    int TAMT4 = 8;
    final int NULO = -1;

    int[] t1;
    int[] t2;
    int[] t3;
    Celula[] t4;
    AVL avl;

    public Doidona(){
        t1 = new int[TAMT1];
        t2 = new int[TAMT2];
        t3 = new int[TAMT3];
        t4 = new Celula[TAMT4];
        
        //inicializar a T1
        for(int i = 0; i < TAMT1; i++){
            t1[i] = NULO;
        }

        //inicializar a T2
        for(int i = 0; i < TAMT2; i++){
            t2[i] = NULO;
        }

        //inicializar a T3
        for(int i = 0; i < TAMT3; i++){
            t3[i] = NULO;
        }

        //inicializar a T4
        for(int i = 0; i < TAMT4; i++){
            t4[i].elemento = NULO;
            Alvinegra alvinegra = new Alvinegra();
            t4[i].prox = alvinegra;
        }
        
        //inicializar a árvore AVL
        avl = new AVL();
    }

    public mostrar(){
        //print da tabela T1
        for(int i = 0; i < TAMT1; i++){
            if(t1[i] != NULO)
                System.out.print(t1[i] + " ");
        }

        //print da tabela T2
        for(int i = 0; i < TAMT2; i++){
            if(t2[i] != NULO)
                System.out.print(t2[i] + " ");
        }
        
        //print da AVL presente na área de reserva da tabela T2
        avl.mostrar();

        //print da tabela T3
        for(int i = 0; i < TAMT3; i++){
            if(t3[i] != NULO){
                System.out.print(t3[i] + " ");
            }
        }

        //print da tabela T4 com a árvore Alvinegra
        for(int i = 0 ; i < TAMT4; i++){
            if(t4[i].elemento != NULO){
                System.out.print(t4[i].elemento + " ");
                //mostrar a árvore Alvinegra
                alvinegra.mostrar(t4[i].prox);
            }
        }
    }
}
