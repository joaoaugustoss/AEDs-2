import java.io.*;
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

class Celula {
	public Serie elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.

	/**
	 * Construtor da classe.
	*/
	public Celula() {
		this(null);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	*/
	public Celula(Serie elemento) {
        this.elemento = elemento;
        this.prox = null;
	}
}

//classe Lista
class Lista {
    private Serie[] series;
    private int n = 0;
	private Celula primeiro;
	private Celula ultimo;

	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	*/
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}

	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(Serie x) {
		Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {                 
			ultimo = tmp;
		}
        tmp = null;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Serie x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Serie removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Serie resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Serie removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

		// Caminhar ate a penultima celula:
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        Serie resp = ultimo.elemento; 
        ultimo = i; 
        i = ultimo.prox = null;
      
		return resp;
	}

	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
   public void inserir(Serie x, int pos) throws Exception {
        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0){
            inserirInicio(x);
        } else if (pos == tamanho){
            inserirFim(x);
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Serie remover(int pos) throws Exception {
        Serie resp;
        int tamanho = tamanho();

            if (primeiro == ultimo){
                throw new Exception("Erro ao remover (vazia)!");

        } else if(pos < 0 || pos >= tamanho){
                throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0){
            resp = removerInicio();
        } else if (pos == tamanho - 1){
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
		return resp;
	}

    public int tamanho() {
        int tamanho = 0; 
        for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return tamanho;
    }

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
            i.elemento.printClass();
		}
	}
}

//classe main
public class TP03Q11{
    public static void main(String[] args) throws Exception{
        String[] entrada = new String[1000];
        String[] file = new String[10];
        Serie[] passa = new Serie[30];
        Lista lista = new Lista();
        int numEntrada = 0, n = 0, pos = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(entrada[numEntrada++].equals("FIM") == false);
        numEntrada--;
        
        //vetor de séries
        Serie[] serie = new Serie[numEntrada];
        for(int i = 0; i < numEntrada; i++){
            serie[i] = new Serie();
            serie[i].readClass(entrada[i]);
            lista.inserirFim(serie[i]);
        }
        
        n = MyIO.readInt();
        String[] doido = new String[n];

        for(int i = 0; i < n; i++){
            doido[i] = MyIO.readLine();
        }

        for(int i = 0; i < n; i++){
            passa[i] = new Serie();
            if(doido[i].contains("II")){
                file = doido[i].split(" ");
                passa[i].readClass(file[1]);
                lista.inserirInicio(passa[i]);
            } else if(doido[i].contains("I*")){
                file = doido[i].split(" ");
                pos = Integer.parseInt(file[1]);
                passa[i].readClass(file[2]);
                lista.inserir(passa[i], pos);
            } else if(doido[i].contains("IF")){
                file = doido[i].split(" ");
                passa[i].readClass(file[1]);
                lista.inserirFim(passa[i]);
            } else if(doido[i].contains("RI")){
                System.out.println("(R) " + lista.removerInicio().getName());
            } else if(doido[i].contains("R*")){
                file = doido[i].split(" ");
                pos = Integer.parseInt(file[1]);
                System.out.println("(R) " + lista.remover(pos).getName());
            } else if(doido[i].contains("RF")){
                System.out.println("(R) " + lista.removerFim().getName());
            }
        }
        lista.mostrar();
    }
}