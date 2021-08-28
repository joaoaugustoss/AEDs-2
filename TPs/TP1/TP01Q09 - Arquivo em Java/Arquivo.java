import java.io.File;
import java.io.RandomAccessFile;

public class Arquivo{
    public static int read(int n){ //Leitura do arquivo
        File f = new File("arquivo.bin"); //abertura do arquivo para leitura
        try{ //tratamento de exceções
            if(f == null){ //verifica se é possível abrir o arquivo
                MyIO.println("Não foi possível abrir o arquivo!!!");
                return -1;
            }
            RandomAccessFile file = new RandomAccessFile(f, "r"); //abertura RAF
            for(int i = 0; i < n; i++){
                file.seek((n - 1 - i) * 8); //busca números através do método seek
                double num = file.readDouble(); //lê os números do arquivo e salva na variável num
                if (num % 1 != 0)
                    MyIO.println(num); //printa a variável num na saída padrão
                else
                    MyIO.println((int) num); //converte a variável num para inteiro e printa na saída padrão
            }
            file.close(); //fecha o arquivo
        } catch(Exception e){ //pega as exceções e salva no endereço de memória de e

        }
        return 0;
    }

    public static int open(int n){ //abre arquivo para escrita
        double num = 0;
        File f = new File("arquivo.bin");
            try{ //tratamento de exceções
                if(f == null){ //verifica se é possível abrir o arquivo
                    MyIO.println("Não foi possível abrir o arquivo!!!");
                    return -1;
                }
                RandomAccessFile file = new RandomAccessFile(f, "rw"); //abertura RAF
                for(int i = 0; i < n; i++){
                    num = MyIO.readDouble(); //salva o número lido no arquivo na variável num
                    file.writeDouble(num); //escreve a variável num no arquivo
                }
                file.close(); //fecha o arquivo
        }catch(Exception e){ //pega as exceções e salva no endereço de memória de e

        }
        return 0;
    }

    public static void main(String[] args){ //função main
        int num = MyIO.readInt();
        open(num); //chamada da função de abertura de arquivo
        read(num); //chamada da função de leitura de arquivo
    }
}