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
    public Serie(String name, String format, String duration, String country, String language, String broadcaster, String streaming, int seasons, int episodes){
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
    public void setduration(String duration){
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
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   

            // Always close files.
            bufferedReader.close();         
        } catch(FileNotFoundException e) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        } catch(IOException e) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}

public class TP02Q01{
    public static void main(String[] args){
        String[] entrada = new String[1000];
        Serie[] serie = new Serie[1000];
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(entrada[numEntrada++].equals("FIM") == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            serie[i].readClass(entrada[i]);
        }
    }
}