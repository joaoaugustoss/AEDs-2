#include <stdio.h>

void read(int n){ //Função de leitura do arquivo
    double num = 0;
    int numInt, aux;
    FILE *fp = fopen("arquivo.bin", "rb"); //abertura do arquivo para leitura binária
    if(fp == NULL){ //teste para verificar se o arquivo existe
        printf("Arquivo inválido!\n");
    }else{
        for(int i = 0; i < n; i++){
            fseek(fp, (n-1-i) * 8, SEEK_SET); //procura números de 8 bits
            fread(&num, sizeof(double), 1, fp); // salva o número na variável num
            aux = num;
            if(num - aux != 0) // caso o número for real printa o num
                printf("%g\n", num);
            else //caso xontrário printa aux
                printf("%d\n", aux);
        }
        fclose(fp); //fecha o arquivo
    }
}

void open(int n){ //Função de abertura do arquivo
    double num = 0;
    FILE *fp = fopen("arquivo.bin", "wb"); //abertura do arquivo para escrita binária
    if(fp == NULL){ //teste para verificar se o arquivo existe
        printf("Arquivo inválido!\n");
    }else{
        for(int i = 0; i < n; i++){
            scanf("%lf", &num); //leitura de um número da entrada padrão
            fwrite(&num, sizeof(double), 1, fp); //escreve o que foi lido da entrada padrão no arquivo
        }
        fclose(fp); //fecha o arquivo
    }
}

int main() {
    int num;
    scanf("%d", &num); //leitura do número
    open(num); //chamada da função de abertura do arquivo
    read(num); //chamada da função de leitura do arquivo
}
