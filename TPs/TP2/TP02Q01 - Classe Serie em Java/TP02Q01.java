import java.io.*;
import java.io.FileReader;

class Serie{
    //declaração dos atributos
    private String name;
    private String format;
    private String duration;
    private String country;
    private String language;
    private String broadcaster;
    private String streaming;
    private int seasons;
    private int episodes;
    
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
    public void setName(String name){
        this.name = name;
    }
    public void setFormat(String format){
        this.format = format;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setLanguage(String language){
        this.language = language;
    }
    public void setBroadcaster(String broadcaster){
        this.broadcaster = broadcaster;
    }
    public void setStreaming(String streaming){
        this.streaming = streaming;
    }
    public void setSeasons(int seasons){
        this.seasons = seasons;
    }
    public void setEpisodes(int episodes){
        this.episodes = episodes;
    }
    public String getName(){ 
        return this.name; 
    }
    public String getFormat(){ 
        return this.format; 
    }
    public String getDuration(){ 
        return this.duration; 
    }
    public String getCountry(){ 
        return this.country; 
    }
    public String getLanguage(){ 
        return this.language; 
    }
    public String getBroadcaster(){ 
        return this.broadcaster; 
    }
    public String getStreaming(){ 
        return this.streaming; 
    }
    public int getSeasons(){ 
        return this.seasons; 
    }
    public int getEpisodes(){ 
        return this.episodes; 
    }
    public void cloneClass(){
    }
    public void printClass(){
        MyIO.println(this.name + " " + this.format + " " + this.duration + " " + this.country + " " + this.language + " " + this.broadcaster + " " +
        this.streaming + " " + this.seasons + " " + this.episodes);
    }
    public void readClass(String fileName){
        String line;
        String resp = "";
        String file = "/tmp/series/" + fileName;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null /*&& line.equals("</table>") == false*/) {
                resp += line;
            }
            String linha[] = resp.split("<table class=");
            searchName(resp);
            searchFormat(linha[1]);
            searchDuration(linha[1]);
            searchCountry(linha[1]);
            searchLanguage(linha[1]);
            searchBroadcaster(linha[1]);
            searchStreaming(linha[1]);
            searchSeasons(linha[1]);
            searchEpisodes(linha[1]);
            //printClass();
            //MyIO.println(linha[1]);
            // Always close files.
            bufferedReader.close();         
        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    private void searchName(String linha){
        String resp[] = linha.split("</i>");
        String name[] = resp[0].split("<i>");
        //MyIO.println(name[1]);
        setName(name[1]);
    }
    private void searchFormat(String linha){
        String resp[] = linha.split("Formato");
        String subFormat1[] = resp[1].split("</a>");
        String subFormat2[] = subFormat1[0].split("<a");
        String format[] = subFormat2[1].split(">");
        setFormat(format[1]);
    }
    private void searchDuration(String linha){
        String resp[] = linha.split("Duração");
        String subDuration1[] = resp[1].split("\">");
        String subDuration2[] = subDuration1[1].split("</td>");
        if(subDuration2[0].contains("<small>")){
            String subDuration3[] = subDuration2[0].split("</small>");
            String duration[] = subDuration3[0].split("<small>");
            setDuration(duration[0] + duration[1]);
            //MyIO.println(duration[0] + duration[1] + " if");
        } else{
            setDuration(subDuration2[0]); 
            //MyIO.println(subDuration2[0] + " else");
        }
    }
    private void searchCountry(String linha){
        String resp[] = linha.split("País de origem");
        String subCountry1[] = resp[1].split("</a>");
        String subCountry2[] = subCountry1[0].split("title=\"");
        String country[] = subCountry2[1].split("\">");
        //MyIO.println(country[0]);
        setCountry(country[0]);
    }
    private void searchLanguage(String linha){
        String resp[] = linha.split("Idioma original");
        String subLanguage1[] = resp[1].split("</td>");
        String subLanguage2[] = subLanguage1[1].split("title=\"");
        if(!subLanguage2[0].contains("<a ")){
            String language[] = subLanguage2[0].split(";\">");
            setLanguage(language[1]);
            //MyIO.println(language[1]);
        } else if(subLanguage2[1].contains("<img ")){
            String language[] = subLanguage2[1].split("</a> ");
            setLanguage(language[1]);
            //MyIO.println(language[1]);
        } else {
            String subLanguage3[] = subLanguage2[1].split("</a>");
            String language[] = subLanguage3[0].split(">");
            setLanguage(language[1]);
            //MyIO.println(language[1]);
        }
    }
    private void searchBroadcaster(String linha){
        String resp[] = linha.split("Emissora de televisão original");
        String subBroadcaster1[] = resp[1].split("title=\"");
        if(subBroadcaster1[1].contains("<img")){
            String broadcaster[] = subBroadcaster1[2].split("\">");
            //MyIO.println(broadcaster[0]);
            setBroadcaster(broadcaster[0]);
        } else {

        String subBroadcaster2[] = subBroadcaster1[1].split("\">");
            String broadcaster[] = subBroadcaster2[1].split("</a>");
            //MyIO.println(broadcaster[0]);
            setBroadcaster(broadcaster[0]);
        }

    }
    private void searchStreaming(String linha){
        String data = "";
        String resp[] = linha.split("Transmissão original");
        String subStreaming1[] = resp[1].split("<td ");
        if(subStreaming1[1].contains("<span ") && !subStreaming1[1].contains("><a ")){
            String subStreaming2[] = subStreaming1[1].split(";\">");
            String subStreaming3[] = subStreaming2[1].split("<span ");
            data += subStreaming3[0];
            String subStreaming4[] = subStreaming3[2].split("\">");
            MyIO.println(subStreaming3[2] + " (<span) \n");
            
        } else if(subStreaming1[1].contains("><a ")){
            String subStreaming2[] = subStreaming1[1].split(">");
            MyIO.println(subStreaming2[2] + " (><a) \n");
        } else{
            String subStreaming2[] = subStreaming1[1].split(";\">");
            String streaming[] = subStreaming2[1].split("</td>");
            MyIO.println(streaming[0] + " FINALIZADA\n"); 
        }
        
        //setStreaming(streaming[0]);
    }
    private void searchSeasons(String linha){
        String resp[] = linha.split("N.º de temporadas");
        String subSeasons1[] = resp[1].split(";\">");
        String subSeasons2[] = subSeasons1[1].split("</td>");
        String seasons[] = subSeasons2[0].split(" ");
        int num = Integer.parseInt(seasons[0]);
        setSeasons(num);
    }
    private void searchEpisodes(String linha){
        int num = 0;
        String ep = "";
        String resp[] = linha.split("N.º de episódios");
        String subEpisodes1[] = resp[1].split(";\">");
        String subEpisodes2[] = subEpisodes1[1].split(" ");
        String episodes[] = subEpisodes2[0].split("</td>");
        int n = Integer.parseInt(episodes[0]);
        setEpisodes(n);

        //MyIO.println(episodes[0]);
        /*if(subEpisodes[1].contains(" <span style=\"")){
            String episodes[] = subEpisodes[1].split(" ");
            //MyIO.println(episodes[0]);
            num = Integer.parseInt(episodes[0]);
            setEpisodes(num);
        } else if(subEpisodes[1].contains("</td>") && !subEpisodes[1].contains(" (<a")){
            String episodes[] = subEpisodes[1].split("</td>");
            //MyIO.println(episodes[0] + "Tchau");
            num = Integer.parseInt(episodes[0]);
            setEpisodes(num);
        } else {
            for(int i = 0; i < 1; i++){
                if(subEpisodes[1].charAt(i) >= '0' && subEpisodes[1].charAt(i) <= '9')
                    ep += subEpisodes[1].charAt(i);
            }
            int n = Integer.parseInt(ep);
            setEpisodes(n);
        }*/
    }
}         

public class TP02Q01{
    public static void main(String[] args){
        String[] entrada = new String[1000];
        Serie serie = new Serie();
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(entrada[numEntrada++].equals("FIM") == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            serie.readClass(entrada[i]);
        }
        //MyIO.println(serie.getName() + " " + serie.getFormat() + " " + serie.getDuration() + " " + serie.getCountry() + " " + serie.getLanguage() 
        //+ " " + serie.getBroadcaster() + " " + serie.getStreaming() + " " + serie.getSeasons() + " " + serie.getEpisodes());
    }
}