import java.io.*;
import java.util.*;
import java.io.FileReader;

class Serie{
    /** 
     * declaração dos atributos
     */
    private String name;
    private String format;
    private String duration;
    private String country;
    private String language;
    private String broadcaster;
    private String streaming;
    private int seasons;
    private int episodes;
    /**
     * construtor primário
     */
    public Serie(){
        name = "";
        format = "";
        duration = "";
        country = "";
        language = "";
        broadcaster = "";
        streaming = "";
        seasons = 0;
        episodes = 0;
    }
    /**
     * construtor secundário
     * @param allSerie
     */
    public Serie(String name, String format, String duration, String country, String language, String broadcaster, String streaming, int seasons, 
    int episodes){
        this.name = name;
        this.format = format;
        this.duration = duration;
        this.country = country;
        this.language = language;
        this.broadcaster = broadcaster;
        this.streaming = streaming;
        this.seasons = seasons;
        this.episodes = episodes;
    }
    /**
     * método para setar o atributo name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * método para setar o atributo formato
     * @param format
     */
    public void setFormat(String format){
        this.format = format;
    }
    /**
     * método para setar o atributo duration
     * @param duration
     */
    public void setDuration(String duration){
        this.duration = duration;
    }
    /**
     * método para setar o atributo country
     * @param country
     */
    public void setCountry(String country){
        this.country = country;
    }
    /**
     * método para setar o atributo language
     * @param language
     */
    public void setLanguage(String language){
        this.language = language;
    }
    /**
     * método para setar o atributo broadcaster
     * @param broadcaster
     */
    public void setBroadcaster(String broadcaster){
        this.broadcaster = broadcaster;
    }
    /**
     * método para setar o atributo streaming
     * @param streaming
     */
    public void setStreaming(String streaming){
        this.streaming = streaming;
    }
    /**
     * método para setar o atributo seasons
     * @param seasons
     */
    public void setSeasons(int seasons){
        this.seasons = seasons;
    }
    /**
     * método para setar o atributo episodes
     * @param episodes
     */
    public void setEpisodes(int episodes){
        this.episodes = episodes;
    }
    /**
     * método para retornar o atributo name
     */
    public String getName(){ 
        return this.name; 
    }
    /**
     * método para retornar o atributo format
     */
    public String getFormat(){ 
        return this.format; 
    }
    /**
     * método para retornar o atributo duration
     */
    public String getDuration(){ 
        return this.duration; 
    }
    /**
     * método para retornar o atributo country
     */
    public String getCountry(){ 
        return this.country; 
    }
    /**
     * método para retornar o atributo language
     */
    public String getLanguage(){ 
        return this.language; 
    }
    /**
     * método para retornar o atributo broadcaster
     */
    public String getBroadcaster(){ 
        return this.broadcaster; 
    }
    /**
     * método para retornar o atributo streaming
     */
    public String getStreaming(){ 
        return this.streaming; 
    }
    /**
     * método para retornar o atributo seasons
     */
    public int getSeasons(){ 
        return this.seasons; 
    }
    /**
     * método para retornar o atributo episodes
     */
    public int getEpisodes(){ 
        return this.episodes; 
    }
    /**
     * método para clonar a classe
     */
    public Serie clone(){
        Serie resp = new Serie();
        resp.name = this.name;
        resp.format = this.format;
        resp.duration = this.duration;
        resp.country = this.country;
        resp.language = this.language;
        resp.broadcaster = this.broadcaster;
        resp.streaming = this.streaming;
        resp.seasons = this.seasons;
        resp.episodes = this.episodes;
        return resp;
    }
    /**
     * método para printar a classe
     */
    public void printClass(){
        System.out.println(this.name + " " + this.format + " " + this.duration + " " + this.country + " " + this.language + " " + this.broadcaster + " " +
        this.streaming + " " + this.seasons + " " + this.episodes);
    }
    /**
     * método para tratar a linha, deixar apenas números e converter o retorno de String para Integer
     * @param line
     */
    public int justInt(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um número ele é concatenado a variável resp
                resp += line.charAt(i);
            } else { //caso seja outro caracter, o i recebe o valor da condição de parada e o método de repetição é encerrado
                i = line.length();
            }
        }
        return Integer.parseInt(resp); //conversão da string resp para número inteiro a ser retornado
    }
    /**
     * método para a remoção das tags da linha lida do arquivo para retornar apenas o que é desejado
     * @param line
     */
    public String removeTags(String line){
        String resp = "";
        int i = 0;
        while(i < line.length()){ //enquanto i for menor que o tamanho da String linha
            if(line.charAt(i) == '<'){ // é testado para verificar se o contador i ainda está dentro das tags
                i++;
                while(line.charAt(i) != '>') i++; //ao encontrar o sinal de fechamento das tags o laço de repetição é encerrado
            } else if(line.charAt(i) == '&'){ //mesmo tratamento de cima mas para outras exceções presentes em alguns outros arquivos
                i++;
                while(line.charAt(i) != ';') i++;
            } else { //o que estiver fora das tags é concatenado a String resp a ser retornada
                resp += line.charAt(i);
            }
            i++;
        }
        //System.out.println(resp);
        return resp;
    }
    /**
     * método para tratar o nome do arquivo e retornar o nome da série
     * @param fileName
     */
    public String searchName(String fileName){
        String resp = "";
        for(int i = 0; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ //caso o caracter na posição i seja igual ao '_' a variável resp recebe um espaço em branco
                resp += ' ';
            } else { //caso não tenha espaço em branco o caracter é concatenado à string resp
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 últimos caracteres relacionados à extensão do arquivo
    }
    /**
     * método para leitura do arquivo .html e tratamento das linhas
     * @param fileName
     */
    public void readClass(String fileName){
        String line;
        String resp = "";
        String file = "/tmp/series/" + fileName;
        try {
            FileReader fileReader = new FileReader(file); //declaração da variável fileReader que será recebida pelo bufferedReader

            BufferedReader br = new BufferedReader(fileReader); //declaração do bufferedReader para leitura do arquivo
            
            //set nome da série
            this.name = searchName(fileName);
            
            //set Formato da série
            while(!br.readLine().contains("Formato"));
            this.format = removeTags(br.readLine()).trim();

            //set duração da série
            while(!br.readLine().contains("Duração"));
            this.duration = removeTags(br.readLine()).trim();

            //set país da série
            while(!br.readLine().contains("País de origem"));
            this.country = removeTags(br.readLine()).trim();

            //set idioma da série
            while(!br.readLine().contains("Idioma original"));
            this.language = removeTags(br.readLine()).trim();

            //set emissora da série
            while(!br.readLine().contains("Emissora de televisão"));
            this.broadcaster = removeTags(br.readLine()).trim();

            //set transmissão original da série
            while(!br.readLine().contains("Transmissão original"));
            this.streaming = removeTags(br.readLine()).trim();

            //set temporadas da série
            while(!br.readLine().contains("N.º de temporadas"));
            this.seasons = justInt(removeTags(br.readLine()));

            //set episódios da série
            while(!br.readLine().contains("N.º de episódios"));
            this.episodes = justInt(removeTags(br.readLine()));
            //fechamento do bufferedReader
            br.close();         
        //Tratamento de exceções
        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}

class Lista {
    public static int comp = 0;
    public static int mov = 0;
    private Serie[] series;
    private static int n = 0;

    /**
     * Construtor da classe.
     */
    public Lista () {
        this(200);
    }

    /**
     * Construtor da classe.
     * @param tamanho Tamanho da lista.
    */
    public Lista (int tamanho){
        series = new Serie[tamanho];
        n = 0;
    }

    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Serie s) throws Exception {

        //validar insercao
        if(n >= series.length){
            throw new Exception("Erro ao inserir!");
        } 

        //levar elementos para o fim do series
        for(int i = n; i > 0; i--){
            series[i] = series[i-1];
        }

        series[0] = s;
        n++;
    }

    public void sort() {
        //Alterar o vetor ignorando a posicao zero
        Serie[] tmp = new Serie[n+1];
        for(int i = 0; i < n; i++){
            mov++;
            tmp[i+1] = series[i];
        }
        series = tmp;

        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++){
            construir(tamHeap);
        }

        //Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
            swap(1, tamHeap--);
            reconstruir(tamHeap);
        }

        //Alterar o vetor para voltar a posicao zero
        tmp = series;
        series = new Serie[n];
        for(int i = 0; i < n; i++){
            series[i] = tmp[i+1];
            mov++;
        }
    }


    private void construir(int tamHeap){
        int i = tamHeap;
        comp += 3;
        while(i > 1 && (series[i].getFormat().compareTo(series[i/2].getFormat()) > 0 || (series[i].getFormat().compareTo(series[i/2].getFormat())) == 0 && (series[i].getName().compareTo(series[i/2].getName())) > 0)){
            swap(i, i/2);
            i /= 2;
            comp += 3;
        }
        
        /*for(int i = tamHeap; i > 1 && series[i].getFormat().compareTo(series[i/2].getFormat()) > 0; i /= 2){
            swap(i, i/2);
        }
        for(int i = tamHeap; i > 1 && series[i].getFormat().compareTo(series[i/2].getFormat()) == 0 && series[i].getName().compareTo(series[i/2].getName()) > 0; i /= 2){
            swap(i, i/2);
        }*/
    }

    private void reconstruir(int tamHeap){
        int i = 1;
        while(i <= (tamHeap/2)){
            comp++;
            int filho = getMaiorFilho(i, tamHeap);
            if(series[i].getFormat().compareTo(series[filho].getFormat()) < 0){
                swap(i, filho);
                i = filho;
            }else if(series[i].getFormat().compareTo(series[filho].getFormat()) == 0 && series[i].getName().compareTo(series[filho].getName()) < 0){
                comp += 2;
                swap(i, filho);
                i = filho;
            }else{
                i = tamHeap;
            }
        }
    }

    public int getMaiorFilho(int i, int tamHeap){
        int filho;
        comp += 3;
        if (2*i == tamHeap || series[2*i].getFormat().compareTo(series[2*i+1].getFormat()) > 0){
            filho = 2*i;
        } else if(series[2*i].getFormat().compareTo(series[2*i+1].getFormat()) == 0 && series[2*i].getName().compareTo(series[2*i+1].getName()) > 0){
            filho = 2*i;
        }else{
            filho = 2*i + 1;
        }
        return filho;
    }

    public void swap(int i, int j) {
        mov += 3;
        Serie temp = series[i];
        series[i] = series[j];
        series[j] = temp;
    }

    public void mostrar(){
        for(int i = 0; i < n; i++){
            series[i].printClass();
        }
    }
}

class TP03Q05{
    static int count = 0;
    public static void main(String[] args) throws Exception{
        long inicio = now();
        String[] entrada = new String[1000];
        Lista lista = new Lista();
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(entrada[numEntrada++].equals("FIM") == false);
        numEntrada--;


        Serie[] serie = new Serie[numEntrada];

        for(int i = 0; i < numEntrada; i++){
            serie[i] = new Serie();
            serie[i].readClass(entrada[i]);
            lista.inserirInicio(serie[i]);
        }

        lista.sort();
        lista.mostrar();
        long fim = now();
        saveFile((fim - inicio)/1000.0, lista.comp, lista.mov);
    }

    //método para salvar o tempo de execução e o número de repetições no arquivo .txt
    public static void saveFile(double time, int comp, int mov){
        try{
            FileWriter fileWriter = new FileWriter("724667_heapsort.txt");
            BufferedWriter bw = new BufferedWriter(fileWriter); 
            bw.write(comp + "\t" + mov * 3 + "\t" + time + "s");
            bw.close();
        //Tratamento de exceções
        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + "724667_heapsort.txt" + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + "724667_heapsort.txt" + "'");
        }
    }    
    //método para calcular o tempo de execução
    public static long now(){
        return new Date().getTime();
    }
}