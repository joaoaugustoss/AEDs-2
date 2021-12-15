private No pesquisarLista(char x){
    No resp = null;
    for(Celula i = primeiro.prox; i != null; i = i.prox){
        if(i.elemento == x){
            resp = i.no;
            i = ultimo;
        }
    }
    return resp;
}

public boolean pesquisar(String x) throws Exception{
    return pesquisar(String x, No no, int i);
}

private boolean pesquisar(String x, No no, int i) throws Exception{
    boolean resp;
    No filho = pesquisar(x.charAt(i));
    if(filho == null)
        resp = false;
    else if(i == x.length() - 1)
        resp = (filho.folha == true);
    else if(i < x.length() - 1)
        resp = pesquisar(x, filho, i++);
    else
        throw new Exception("Erro");
    return resp;
}

/*
 * COMPLEXIDADE
 * O(n log m), sendo n o tamanho da lista e m o tamanho da string a ser pesquisada
*/ 
