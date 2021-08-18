import java.io.File;
import java.io.RandomAccessFile;

class Arquivo{

    public static void main(String[] args){
        RandomAccessFile file = new RandomAccessFile(arquivo, "rw");
        if(!file.exists())
            file.createNewFile();
        int n = MyIO.readInt();
        double array[];
        for(int i = 0; i < n; i++){
            array[i] = MyIO.readDouble();
        }
        for(int i = 0; i < n; i++){
            System.out.println(array[i]);
        }
        file.close();
    }
}