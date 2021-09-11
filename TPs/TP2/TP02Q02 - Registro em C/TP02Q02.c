#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

typedef struct Serie{
    char name[30];
    char format[30];
    char duration[40];
    char country[30];
    char language[10];
    char broadcaster[30];
    char streaming[30];
    int seasons;
    int episodes;
}Serie;
//método para tratamento dos atributos que recebem número inteiro, convertendo de char para int
int justInt(char line[]){
    char *resp = (char *)malloc(sizeof(strlen(line)));
    for(int i = 0; i < strlen(line); i++){
        if(line[i] >= '0' && line[i] <= '9'){
            resp[i] = line[i];
        } else
            i = strlen(line);
    }
    return atoi(resp);
}
//método para a remoção das tags lidas nas linhas
char* removeTags(char s[]){
    //printf("ENTRADA\t%s\n", s);
    char *resp = (char *)malloc(sizeof(strlen(s)));
    int i = 0, j = 0;
    while(i < strlen(s)){
        if(s[i] == '<'){
            i++;
            while(s[i] != '>') i++;
        } else if(s[i] == '&'){
            i++;
            while(s[i] != ';') i++;
        } else {
            resp[j] = s[i];
            j++;
        }
        i++;
    }
    resp[j-1] = '\0';
    //printf("SAIDA\t%s\n%lu\n", resp, strlen(resp));
    return resp;
}
//método para tratar o nome do arquivo lido e retorná-lo sem caracteres especiais
char* getName(char fileName[]){
    char *teste;
    char *resp = (char *)malloc(sizeof(strlen(fileName)));
    for(int i = 0; i < strlen(fileName); i++){
        if(fileName[i]  == '_'){
            resp[i] = ' ';
        } else {
            resp[i] = fileName[i];
        }
    }
    teste = strtok(resp, ".");
    teste = strtok(NULL, ".");
    //printf("%s\n", resp);
    return resp;
}
//método para printar os atributos da série
void print(Serie *serie){
    printf("%s %s %s %s %s %s %s %d %d\n", serie->name, serie->format, serie->duration, serie->country, serie->language, serie->broadcaster,
    serie->streaming, serie->seasons, serie->episodes);
}
//método para testar se a entrada == FIM para finalizar as entradas
bool isFim(char s[]){
    return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}
//método para leitura do arquivo html e tratamento do mesmo
void read(char *fileName, Serie *serie){
    char *line = (char *)malloc(1600 * sizeof(char));
    char file[50], *teste1, *teste2;
    sprintf(file, "/tmp/series/%s", fileName);
    FILE *fp = fopen(file, "r"); //abertura do arquivo para leitura
    if(fp == NULL){ //teste para verificar se foi possível abrir o arquivo corretamente
        printf("Erro ao abrir o arquivo!!!\n");
    }
    //set nome da série
    printf("%s", getName(fileName));

    //set formato da série
    while(!strstr(fgets(line, 1000, fp), "Formato"));
    fgets(line, 1000, fp);
    printf(" %s", removeTags(line));
    //strcpy(serie->format, removeTags(line));

    //set duração da série
    while(!strstr(fgets(line, 1000, fp), "Duração"));
    fgets(line, 1000, fp);
    printf(" %s", removeTags(line));
    //strcpy(removeTags(line), serie->format);

    //set país da série
    while(!strstr(fgets(line, 1600, fp), "País de origem"));
    fgets(line, 1600, fp);
    printf(" %s", removeTags(line));
    //strcpy(removeTags(line), serie->format);

    //set idioma da série
    while(!strstr(fgets(line, 1000, fp), "Idioma original"));
    fgets(line, 1000, fp);
    printf(" %s", removeTags(line));
    //strcpy(removeTags(line), serie->format);

    //set emissora da série
    while(!strstr(fgets(line, 1000, fp), "Emissora de televisão"));
    fgets(line, 1000, fp);
    printf(" %s", removeTags(line));
    //strcpy(removeTags(line), serie->format);

    //set transmissão original da série
    while(!strstr(fgets(line, 1600, fp), "Transmissão original"));
    fgets(line, 1600, fp);
    printf(" %s", removeTags(line));
    //strcpy(removeTags(line), serie->format);

    //set temporadas da série
    while(!strstr(fgets(line, 1000, fp), "N.º de temporadas"));
    fgets(line, 1000, fp);
    printf(" %d", justInt(removeTags(line)));
    //strcpy(removeTags(line), serie->format);

    //set episódios da série
    while(!strstr(fgets(line, 1000, fp), "N.º de episódios"));
    fgets(line, 1000, fp);
    printf(" %d\n", justInt(removeTags(line)));
    //strcpy(removeTags(line), serie->format);

    //print(serie);
    fclose(fp);
}

int main(){
    Serie *serie;
    char entrada[1000][100];
    int numEntrada = 0;

    do{
        scanf(" %[^\n]s", entrada[numEntrada]);
    } while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    for(int i = 0; i < numEntrada; i++){
        read(entrada[i], serie);
        //printf("%s\n", serie->format);
    }
    
    return 0;
}