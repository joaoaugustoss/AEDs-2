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
            this.name = searchName(fileName).trim();
            
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

class NoAN{
    public boolean cor;
    public Serie elemento;
    public NoAN esq, dir;
    public NoAN (){
        this(null);
    }
    public NoAN (Serie elemento){
        this(elemento, false, null, null);
    }
    public NoAN (Serie elemento, boolean cor){
        this(elemento, cor, null, null);
    }
    public NoAN (Serie elemento, boolean cor, NoAN esq, NoAN dir){
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Alvinegra {
        private NoAN raiz; // Raiz da arvore.
        public int comp;

        public Alvinegra() {
            raiz = null;
            comp = 0;
        }

        public boolean pesquisar(String elemento) {
            System.out.print("raiz");
            return pesquisar(elemento, raiz);
        }

        private boolean pesquisar(String elemento, NoAN i) {
            boolean resp;
            if (i == null) {
                comp++
                System.out.println(" NAO");
                resp = false;
            } else if (elemento.compareTo(i.elemento.getName()) == 0) {
                comp++
                System.out.println(" SIM");
                resp = true;
            } else if (elemento.compareTo(i.elemento.getName()) < 0) {
                comp++
                System.out.print(" esq");
                resp = pesquisar(elemento, i.esq);
            } else {
                comp++
                System.out.print(" dir");
                resp = pesquisar(elemento, i.dir);
            }
            return resp;
        }

        public void caminharCentral() {
            System.out.print("[ ");
            caminharCentral(raiz);
            System.out.println("]");
        }

        private void caminharCentral(NoAN i) {
            if (i != null) {
                comp++
                caminharCentral(i.esq); // Elementos da esquerda.
                System.out.print(i.elemento.getName() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
                caminharCentral(i.dir); // Elementos da direita.
            }
        }

        public void inserir(Serie elemento) throws Exception {
            //Se a arvore estiver vazia
            if(raiz == null){
                comp++
                raiz = new NoAN(elemento);
            //Senao, se a arvore tiver um elemento 
            } else if (raiz.esq == null && raiz.dir == null){
                comp++
                if (elemento.getName().compareTo(raiz.elemento.getName()) < 0){
                    comp++
                    raiz.esq = new NoAN(elemento);
                } else {
                    comp++
                    raiz.dir = new NoAN(elemento);
                }
            //Senao, se a arvore tiver dois elementos (raiz e dir)
            } else if (raiz.esq == null){
                comp++
                if(elemento.getName().compareTo(raiz.elemento.getName()) < 0){
                    comp++
                    raiz.esq = new NoAN(elemento);
                } else if (elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0){
                    comp++
                    raiz.esq = new NoAN(raiz.elemento);
                    raiz.elemento = elemento;
                } else {
                    comp++
                    raiz.esq = new NoAN(raiz.elemento);
                    raiz.elemento = raiz.dir.elemento;
                    raiz.dir.elemento = elemento;
                }
                raiz.esq.cor = raiz.dir.cor = false;
            //Senao, se a arvore tiver dois elementos (raiz e esq)
            } else if (raiz.dir == null){
                comp++
                if(elemento.getName().compareTo(raiz.elemento.getName()) > 0){
                    comp++
                    raiz.dir = new NoAN(elemento);
                } else if (elemento.getName().compareTo(raiz.esq.elemento.getName()) > 0){
                    comp++
                    raiz.dir = new NoAN(raiz.elemento);
                    raiz.elemento = elemento;
                } else {
                    comp++
                    raiz.dir = new NoAN(raiz.elemento);
                    raiz.elemento = raiz.esq.elemento;
                    raiz.esq.elemento = elemento;
                }
                raiz.esq.cor = raiz.dir.cor = false;
            //Senao, a arvore tem tres ou mais elementos
            } else {
                    comp++
                inserir(elemento, null, null, null, raiz);
            }
            raiz.cor = false;
        }

        private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i){
            //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
            if(pai.cor == true){
                comp++
                //4 tipos de reequilibrios e acoplamento
                if(pai.elemento.getName().compareTo(avo.elemento.getName()) > 0){ // rotacao a esquerda ou direita-esquerda
                    comp++
                    if(i.elemento.getName().compareTo(pai.elemento.getName()) > 0){
                        comp++
                        avo = rotacaoEsq(avo);
                    } else {
                        comp++
                        avo = rotacaoDirEsq(avo);
                    }
                } else { // rotacao a direita ou esquerda-direita
                    comp++
                    if(i.elemento.getName().compareTo(pai.elemento.getName()) < 0){
                        comp++
                        avo = rotacaoDir(avo);
                    } else {
                        comp++
                        avo = rotacaoEsqDir(avo);
                    }
                }
                if (bisavo == null){
                    comp++
                    raiz = avo;
                } else if(avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0){
                    comp++
                    bisavo.esq = avo;
                } else {
                    comp++
                    bisavo.dir = avo;
                }
                //reestabelecer as cores apos a rotacao
                avo.cor = false;
                avo.esq.cor = avo.dir.cor = true;
            } //if(pai.cor == true)
        }

    private void inserir(Serie elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            comp++
            if(elemento.getName().compareTo(pai.elemento.getName()) < 0){
                comp++
                i = pai.esq = new NoAN(elemento, true);
            } else {
                comp++
                i = pai.dir = new NoAN(elemento, true);
            }
            if(pai.cor == true){
                comp++
                balancear(bisavo, avo, pai, i);
            }
        } else {
            comp++
            //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
                comp++
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz){
                    comp++
                    i.cor = false;
                }else if(pai.cor == true){
                    comp++
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
                comp++
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
                comp++
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                comp++
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;
        noEsq.dir = no;
        no.esq = noEsqDir;
        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;
        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

//classe main
public class TP04Q04{
    public static void main(String[] args) throws Exception{
        long inicio = now();
        String[] entrada = new String[1000];
        String[] pesquisa = new String[50];
        Alvinegra arvore = new Alvinegra();
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(entrada[numEntrada++].equals("FIM") == false);
        numEntrada--;
        
        //vetor de séries
        Serie[] serie = new Serie[numEntrada];
        for(int i = 0; i < numEntrada; i++){
            serie[i] = new Serie();
            serie[i].readClass(entrada[i]);
            arvore.inserir(serie[i]);
        }
        numEntrada = 0;
        //arvore.caminharCentral();
        //leitura das séries a serem pesquisadas
        do{
            pesquisa[numEntrada] = MyIO.readLine();
        } while(pesquisa[numEntrada++].equals("FIM") == false);
        numEntrada--;
        for(int i = 0; i < numEntrada; i++){
            arvore.pesquisar(pesquisa[i].trim());
        }
        long fim = now();
        saveFile((fim-inicio)/1000.0, arvore.comp);
    }
    //método para salvar o tempo de execução e o número de repetições no arquivo .txt
    public static void saveFile(double time, int comp){
        try{
            FileWriter fileWriter = new FileWriter("724667_alvinegra.txt");
            BufferedWriter bw = new BufferedWriter(fileWriter); 
            bw.write(comp + "\t" + time + "s");
            bw.close();
        //Tratamento de exceções
        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + "724667_alvinegra.txt" + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + "724667_alvinegra.txt" + "'");
        }
    }    
    //método para calcular o tempo de execução
    public static long now(){
        return new Date().getTime();
    }
}
