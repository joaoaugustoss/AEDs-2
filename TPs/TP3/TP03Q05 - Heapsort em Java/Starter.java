// Imports
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class Lista{
	private Series[] array;
	private int n;

	public Lista() { // Construtor primário
		this(6); 
	}

	/*
	 * @method: Constructor
	 * @param: tamanho Int ( List size ) 
	*/
	public Lista(int tamanho){
		array = new Series[tamanho];
		n = 0;
	}
	
	
	/*
	 * @method: Inserts an elemnt on the last position of the List
	 * @param: x Series ( Element to be inserted ) 
	 * @throws: Exception if List is full
	*/
	public void inserirFim(Series x)throws Exception {
		
		// Validation
		if(n >= array.length){
			throw new Exception("Error on insertion:\nArray is full");
		}

		array[n] = x;
		n++;		
	} 
	
	/*
	 * @method: (SWAP) swaps the elements on array
	 * @param: i   Int (Position to be swaped)
	 * @param: h   Int (Position to be swaped)
	 */
	private void swap(int i, int h){
		Series temp = array[i];
		array[i] = array[h];
		array[h] = temp;
	}

	/*
	 * @method: Used to constuct the struct of the heap
	 * @param: tamHeap Int ( Size of the Heap )
	*/
	private void construir(int tamHeap){
		for(int i = tamHeap; i > 1 && array[i].getFormato().compareTo(array[i/2].getFormato()) > 0; i /= 2){
			swap(i, i/2);
		}
		for(int i = tamHeap; i > 1 && array[i].getFormato().compareTo(array[i/2].getFormato()) == 0 && array[i].getNome().compareTo(array[i/2].getNome()) > 0; i /= 2){
			swap(i, i/2);
		}
	}

	/*
	 * @method: Used to reconstruct the struct of the heap
	 * @param: tamHeap Int ( Size of the Heap )
	 */
	private void reconstruir(int tamHeap){
		int i = 1;
		while(i <= (tamHeap/2)){
			int filho = getMaiorFilho(i, tamHeap);
			int resp = array[i].getFormato().compareTo(array[filho].getFormato());
			if(resp < 0){
				swap(i, filho);
				i = filho;
			}else if( resp == 0 && array[i].getNome().compareTo(array[filho].getNome()) < 0){
				swap(i, filho);		
				i = filho;
			}
			else{
				i = tamHeap;
			}
		}
	}

	/*
	 * @method: Finds which is the "biggest" son on the array
	 * @param: i Int 
	 * @param: tamHeap Int
	 * @return: pos Int ( Position of the found son )
	 */ 
	public int getMaiorFilho(int i, int tamHeap){
		int pos;
		int resp = array[2*i].getFormato().compareTo(array[2*i+1].getFormato());
			
		if(2*i == tamHeap || resp > 0){
			pos = 2*i;
		}else if(2*i == tamHeap || resp == 0 && array[2*i].getNome().compareTo(array[2*i+1].getNome()) > 0){
			pos = 2*i;
		}else{
			pos = 2*i + 1;
		}

		return pos;
	}

	/*
	 * Sort elements in alphabetical order based on it's "formato"
	 * If equal "idioma" found, sort for "nome"
	 * @return: none (void)
	*/ 
	public void sort(){
		// Data declaration
		Series[] tmp = new Series[n + 1];

		for(int i = 0; i < n; i++){
			tmp[i + 1] = array[i];
		}

		array = tmp;

		for(int tamHeap = 2; tamHeap <= n; tamHeap++){
			construir(tamHeap);

		}

		// Sorting
		int tamHeap = n;
		while(tamHeap > 1){
			swap(1, tamHeap--);
			reconstruir(tamHeap);
		}

		tmp = array;
		array = new Series[n];
		for(int i = 0; i < n; i++){
			array[i] = tmp[i + 1];
		}
	}

	public void mostrar(){
		for(int i = 0; i < n; i++){
			array[i].imprimir();
		}
	}

}; 

class Series{
	// Data declaration
	private String nome;
	private String formato;
	private String duracao;
	private String paisOrigem;
	private String idioma;
	private String emissora;
	private String transmissao;
	private int temporadas;
	private int episodios;
	public static int contador;
	public static int movimentacao;
	static{
		movimentacao = 0;
		contador = 0;
	}

	// Constructors
	public Series() { // primary
		this.nome = "";
		this.formato = "";
		this.duracao = "";
		this.paisOrigem = "";
		this.idioma = "";
		this.emissora = "";
		this.transmissao = "";
		this.temporadas = 0;
		this.episodios = 0;
	}	

	public Series(String n, String f, String d, String p, String i, String e, String t, int temporadas, int episodios){ // Secondary
		this.nome = n;
		this.formato = f;
		this.duracao = d;
		this.paisOrigem = p;
		this.idioma = i;
		this.emissora = e;
		this.transmissao = t;
		this.temporadas = temporadas;
		this.episodios = episodios;
	}
	
	// Setters
	public void setNome(String n) { this.nome = n; }
	public void setFormato(String f) { this.formato = f; }
	public void setDuracao(String d) { this.duracao = d; } 
	public void setPaisOrigem(String p) { this.paisOrigem = p; }
	public void setIdioma(String i) { this.idioma = i; }
	public void setEmissora(String e) { this.emissora = e; } 
	public void setTransmissora(String t) { this.transmissao = t; }
	public void setTemporadas(int tempo) { this.temporadas = tempo; }
	public void setEpisodios(int epis) { this.episodios = epis; } 

	// Getters
	public String getNome() { return this.nome; }
	public String getFormato() { return this.formato; }
	public String getDuracao() { return this.duracao; }
	public String getPaisOrigem() { return this.paisOrigem; }
	public String getIdioma() { return this.idioma; }
	public String getEmissora() { return this.emissora; }
	public String getTransmissora() { return this.transmissao; }
	public int getTemporadas() { return this.temporadas; }
	public int getEpisodios() { return this.episodios; } 	

	// Methods
	
	/*
	 * @method: clones the atributes of a object and returns the cloned Object
	 * @param: none
	 * @return: cloned Series
	*/ 
	public Series clone(){
		Series cloned = new Series();

		cloned.nome = this.nome;
		cloned.formato = this.formato;
		cloned.duracao = this.duracao;
		cloned.paisOrigem = this.paisOrigem;
		cloned.idioma = this.idioma;
		cloned.emissora = this.emissora;
		cloned.transmissao = this.transmissao;
		cloned.temporadas = this.temporadas;
		cloned.episodios = this.episodios;

		return cloned;
	}

	/*
	 * @method: transforms a String-based number into a Integer, (ex: "176" into 176)
	 * @param: str String
	 * @return int
	*/ 
	private int stringToInt(String str){
		// Data declaration
		int i = 0, num = 0;

		// Loops through the string
		while (i < str.length()){	
			if(str.charAt(i) != ' '){
				num *= 10;
				num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
			}else{
				i = str.length(); // When it reaches an empty space it means that has transformed the number
			}
		}
		return num;
	} // ending stringToInt

	/*
	 * @method: receives a String and trims it (removes initial and ending " " spaces)
	 * @param: line String
	 * @return: String
	*/
	private String trimString(String str){
		int size = str.length();
		String newString = "";
		
		/*
		 * The main idea is to check if there's an empty space before or after
		 * the String, if there's, ignore it
		*/
		if(str.charAt(0) == ' ' && str.charAt(size - 1) == ' '){
			for(int i = 1; i < str.length() - 1; i++){
				newString += str.charAt(i);
			}
		}else if(str.charAt(0) == ' '){
			for(int i = 1; i < str.length(); i++){
				newString += str.charAt(i);
			}
		}else if(str.charAt(size - 1) == ' '){
			for(int i = 0; i < str.length() - 1; i++){
				newString += str.charAt(i);
			}
		}else{
			for(int i = 0; i < str.length(); i++){
				newString += str.charAt(i);
			}
		}
		return newString;
	}
	/*
	 * @method: Receives two strings, and check if they're equal
	 * @param: line String
	 * @param: toBeChecked String
	 * @return: boolean
	*/
	public boolean equals(String line, String toBeChecked){
		// Data declaration
		int n = 0;
		boolean resp = true;
		n = toBeChecked.length();

		// if the size of the read line is different than the one we are comparing, than it's not the right line
		if(line.length() < n) { resp = false; } // ending IF
		
		// Checking if letters are different
		for(int i = 0; resp && i < n; i++){
			if(line.charAt(i) != toBeChecked.charAt(i)) { resp = false; }
		} // ending FOR
		
		return resp;
	}
	
	/*
	 * @method: receives a line and cleans it, removing HTML's tags, and returns a new String without tags
	 * @param: line String
	 * @return: String
	*/
	private String cleaningTags(String line){
		// data declaration
		String aux = "";
		boolean flag = false;
		String cleanedString = "";

		// Looping through the read line, and cleaning every html tag that this line contains
		/*
		 * The main idea here is to check if the char is equals to '<', if it's, it means that a HTML tag has opened
		 * So, CANT read anything until the tag is closed, '>' found, (in this case, when '>' found, FLAG == true, allowing the reading).
		 *
		 * If a tag ends, and another one starts right before, it'll read '<', which will make FLAG == false, disabling the reading
		 * And also, will check if the tag is <br> tag, in this case, it should stop the looping.
		 */
		for(int i = 0; i < line.length(); i++){
			aux = ""; // Cleaning aux at the beginning of every iteration of the looping

			if(line.charAt(i) == '<' ) {
				
				for(int j = 0; j < line.length() && j < 3; j++){
					if(j != line.length() && (j + i) < line.length()) { // Ensuring that won't access wrong memory places
						aux += line.charAt(j + i);
					}else { j = line.length(); } // STOPPING THE INNER FOR
				} // Ending inner FOR

				if(equals(aux, "<br")) { i = line.length() -1; }  // If the read tag is equals to "<br"
				else { flag = false; }  

			}else if(flag) { 
				if(line.charAt(i) == '&') { // Cleaning few leftovers
				       	i+= 5; 
				} 
				else{
					cleanedString += line.charAt(i); // Copying the content
				}
			} 

			if(line.charAt(i) == '>' ) { flag = true; }
		} // Ending outter FOR

		return cleanedString;
	}// ending cleaningTags 

	/*
	 * @method: receives a String and checks if it has  ( <table class="infobox_V2 ... > )
	 * @param: line String
	 * @return: boolean
	*/
	private boolean rightPlace(String line){
		// data declaration
		int count = 0;
		boolean resp = false;
		String check = "<table class=\"infobox_v2\"", splitted = "";
		
		// If the size of the read line is lesser than the one we are comparing, than it's not the right line
		if(line.length() < check.length()) return false;

		// Loops through the read string and checks if it has the "check" content in it
		for(int i = 0;i < line.length(); i++){

			if(line.charAt(i) == check.charAt(0)){
				// Checking if that part is == "checked" content
				for(int j = 0; j < line.length(); j++){

					if(j != check.length() && (j + i) < line.length()) { // Ensuring that won't access wrong memory places
						if(line.charAt(j + i) == check.charAt(j)) { 
							count++; // Count increasing when char of "line" == char of "check"
						}
						else{ count = 0; } // If different, 0s count
					}else{  
						j = line.length();
					}

				} // END INTERNAL FOR 
				
				// If, somewhere, it finds the right string, it means that the read line contains "check"
				if(count == check.length()){
					resp = true;
					i = line.length();
				}
				
			}
		} // END EXTERNAL FOR
		
		return resp;
	} // ending method

	/*
	 * @method: receives the path of a file, and splits it's content to anything between "<table ....> ... </table>"
	 * @param: path String
	 * @return: void
	*/
	private void splittingMainString(String path){		
		String aux = "", foo = "";
		boolean resp = false, nonStop = true, read = false;
		
		// Handling possible errors
		try{
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			
			while(nonStop && (line = reader.readLine()) != null){
				// If the read line contains the right element ("<table class infobox_v2..."
				if(rightPlace(line)) { resp = true; }

				// if found right place
				if(resp){
					// check if it's "td"
					if(equals(line, "<td")){
						// remove any tags
						aux = cleaningTags(line);
						
						if(!read && equals(aux, "Formato")){
						       	foo = reader.readLine(); 
							foo = cleaningTags(foo);
							this.formato = trimString(foo); 
							read = true;
						}else if(equals(aux, "Duração")){
							foo = reader.readLine();
							foo = cleaningTags(foo);
							this.duracao = trimString(foo);
						}else if(equals(aux, "País de origem")){
							foo = reader.readLine();
							foo = cleaningTags(foo);
							this.paisOrigem = trimString(foo);
						}else if(equals(aux, "Idioma original")){
							foo = reader.readLine();
							foo = cleaningTags(foo);
							this.idioma = trimString(foo);
						}else if(equals(aux, "Emissora de televisão original")){
							foo = reader.readLine();
							foo = cleaningTags(foo);
							this.emissora = trimString(foo);
						}else if(equals(aux, "Transmissão original")){
							foo = reader.readLine();
							foo = cleaningTags(foo);
							this.transmissao = trimString(foo);
						}else if(equals(aux, "N.º de temporadas")){
							foo = reader.readLine();
							foo = cleaningTags(foo);
							this.temporadas = stringToInt(foo);
						}else if(equals(aux, "N.º de episódios")){
							foo = reader.readLine();
							foo = cleaningTags(foo);
							this.episodios = stringToInt(foo);
						}
					}
					
				}
				if(resp && equals(line, "</table>")) {nonStop = false; } 		
			}	
			reader.close();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}catch(IOException e){
			System.out.println("File cannot be read.");
		}catch(Exception e){
			e.printStackTrace();
		}
	} // ending method splittingMainString

	/*
	 * @method: receives the fileName, takes off the .html and every "_" in order to get the name of the Serie
	 * @param: fileName String
	 * @return: none (void)
	*/
	private void gettingNameSerie(String fileName){
		// Data declaration
		String classAtributeName = "";

		// Looping through "fileName"'s content, if '_' is found, replace it to an empty space (' ')
		// When finds a '.', it means that reached the end of the file name, (ex. Breaking_Bad.html = Breaking Bad)
		for(int i = 0; i < fileName.length(); i++){
			if(fileName.charAt(i) == '.'){
			       	i = fileName.length();
			}else if(fileName.charAt(i) == '_'){
				classAtributeName += ' ';
			}else{
				classAtributeName += fileName.charAt(i);
			}
		}
		this.nome = classAtributeName;
	}

	/*
	 * @method: receives the name of certain file.hmtl and checks for few patterns on it
	 * @param: fileName String
	 * @return: none (void)
	*/
	public void ler(String fileName){
		// Getting the right path for each read file
		String path = "/tmp/series/" + fileName;
		gettingNameSerie(fileName); // Method that will get the name of the serie based on the name of the file
		splittingMainString(path);
	}// ending ler
	
	/*
	 * @method: prints the actual content of the atributes
	 * @param: none
	 * @return: none
	*/ 
	public void imprimir (){
		System.out.println(this.nome + " " + this.formato + " " + this.duracao + " " + this.paisOrigem + " " + this.idioma + " " + this.emissora + " " + this.transmissao + " " + this.temporadas + " " + this.episodios);
	}// ending imprimir

} // ending classv


// Class that contains the main method
public class Starter{

	/*
	 * @method: receives a string and checks if it's content is equals to "FIM"
	 * @param: str String
	 * @return: boolean
	*/
	public static boolean isFim(String str){
		Series s1 = new Series();
		return (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
	}// ending isFim


	/*
	 * @method: (MAIN) receives entries from keyboard and starts the program
	 * @param: args String[]
	 * @return: none (void)
	*/
	public static void main(String[] args){
		// data declaration
		int count = 0, stopper = 0, position = 0;	
		String[] name = new String[1000];
		Series[] s1 = new Series[100];
		Lista l1;
		long tempoInicio = System.currentTimeMillis();
		// Handling possible errors with the reader
		try{
			BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
			// Reading entries until "FIM" is read
			do{
				name[count] = leitor.readLine();
				if(!isFim(name[count])) {
					s1[count] = new Series();
					s1[count].ler(name[count]);

				}
			}while(!isFim(name[count++]));
			count--; // When read the last FIM

			// Creating a new List with the ammount of readLines
			l1 = new Lista(count);
			
			// Adding every Series object in the List
			for(int i = 0; i < count; i++){
				l1.inserirFim(s1[i]);	
			}
			l1.sort();
			l1.mostrar();
			
			BufferedWriter escritor = new BufferedWriter(new FileWriter(new File("725997_heapsort.txt")));
			escritor.write("725597\t" + (System.currentTimeMillis() - tempoInicio) + "ms\t" + s1[0].contador + "\t" + s1[0].movimentacao);

			leitor.close();
			escritor.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}// ending main

} // ending start
