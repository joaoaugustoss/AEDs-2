#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAXTAM    100

typedef struct Serie{
    char name[100];
    char format[100];
    char duration[100];
    char country[100];
    char language[100];
    char broadcaster[100];
    char streaming[100];
    int seasons;
    int episodes;
}Serie;

//método para printar os atributos da série
void print(Serie *serie){
    printf("%s %s %s %s %s %s %s %d %d\n", serie->name, serie->format, serie->duration, serie->country, serie->language, serie->broadcaster,
    serie->streaming, serie->seasons, serie->episodes);
}
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
//método para testar se a entrada == FIM para finalizar as entradas
bool isFim(char s[]){
    return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}
//método para leitura do arquivo html e tratamento do mesmo
void read(char *fileName, Serie *serie){
    char *line = (char *)malloc(1500 * sizeof(char));
    char file[50];
    sprintf(file, "/tmp/series/%s", fileName);
    FILE *fp = fopen(file, "r"); //abertura do arquivo para leitura
    if(fp == NULL){ //teste para verificar se foi possível abrir o arquivo corretamente
        printf("Erro ao abrir o arquivo!!!\n");
    }
    
    //set nome da série
    strcpy(serie->name, getName(fileName));

    //set formato da série
    while(!strstr(fgets(line, 1500, fp), "Formato"));
    fgets(line, 1500, fp);
    strcpy(serie->format, removeTags(line));

    //set duração da série
    while(!strstr(fgets(line, 1500, fp), "Duração"));
    fgets(line, 1500, fp);
    strcpy(serie->duration, removeTags(line));

    //set país da série
    while(!strstr(fgets(line, 1500, fp), "País de origem"));
    fgets(line, 1500, fp);
    strcpy(serie->country, removeTags(line));

    //set idioma da série
    while(!strstr(fgets(line, 1500, fp), "Idioma original"));
    fgets(line, 1500, fp);
    strcpy(serie->language, removeTags(line));

    //set emissora da série
    while(!strstr(fgets(line, 1500, fp), "Emissora de televisão"));
    fgets(line, 1500, fp);
    strcpy(serie->broadcaster, removeTags(line));

    //set transmissão original da série
    while(!strstr(fgets(line, 1500, fp), "Transmissão original"));
    fgets(line, 1500, fp);
    strcpy(serie->streaming, removeTags(line));

    //set temporadas da série
    while(!strstr(fgets(line, 1500, fp), "N.º de temporadas"));
    fgets(line, 1500, fp);
    serie->seasons = justInt(removeTags(line));

    //set episódios da série
    while(!strstr(fgets(line, 1500, fp), "N.º de episódios"));
    fgets(line, 1500, fp);
    serie->episodes = justInt(removeTags(line));

    fclose(fp);
}
//método para clonar a classe
Serie clonar(Serie *serie) {
    return *serie;
}
Serie *array[MAXTAM];
int n = 0;
/**
 * Inicializacoes
 */
void start(){
   n = 0;
}
/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
*/
void inserirInicio(Serie *x) {
   int i;

   //validar insercao
   if(n >= MAXTAM){
      printf("Erro ao inserir!");
      exit(1);
   } 

   //levar elementos para o fim do array
   for(i = n; i > 0; i--){
      array[i] = array[i-1];
   }

   array[0] = x;
   n++;
}
/**
 * Insere um elemento na ultima posicao da 
 * @param x int elemento a ser inserido.
 */
void inserirFim(Serie *x) {
   //validar insercao
   if(n >= MAXTAM){
      printf("Erro ao inserir!");
      exit(1);
   }
   array[n] = x;
   n++;
}
/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(Serie *x, int pos) {
   int i;

   //validar insercao
   if(n >= MAXTAM || pos < 0 || pos > n){
      printf("Erro ao inserir!");
      exit(1);
   }

   //levar elementos para o fim do array
   for(i = n; i > pos; i--){
      array[i] = array[i-1];
   }

   array[pos] = x;
   n++;
}
/**
 * Remove um elemento da primeira posicao da lista e movimenta 
 * os demais elementos para o inicio da mesma.
 * @return resp int elemento a ser removido.
 */
Serie removerInicio() {
   int i;
   Serie *resp;

   //validar remocao
   if (n == 0) {
      printf("Erro ao remover!");
      exit(1);
   }

   resp = array[0];
   n--;

   for(i = 0; i < n; i++){
      array[i] = array[i+1];
   }

   return *resp;
}
/**
 * Remove um elemento da ultima posicao da 
 * @return resp int elemento a ser removido.
 */
Serie removerFim() {

   //validar remocao
   if (n == 0) {
      printf("Erro ao remover!");
      exit(1);
   }

   return *array[--n];
}
/**
 * Remove um elemento de uma posicao especifica da lista e 
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp int elemento a ser removido.
 */
Serie remover(int pos) {
   int i;
   Serie *resp;

   //validar remocao
   if (n == 0 || pos < 0 || pos >= n) {
      printf("Erro ao remover!");
      exit(1);
   }

   resp = array[pos];
   n--;

   for(i = pos; i < n; i++){
      array[i] = array[i+1];
   }

   return *resp;
}
/**
 * Mostra os array separados por espacos.
 */
void mostrar (){
    for(int i = 0; i < n; i++){
        print(array[i]);
        //printf("%s\n", serie[i].name);
    }
}

//método principal
int main(){
    char entrada[1000][100];
    char *file = (char *)malloc(100 * sizeof(char));
    char *batata = (char *)malloc(100 * sizeof(char));
    int numEntrada = 0, num, pos = 0;
    start();

    do{
        scanf(" %[^\n]s", entrada[numEntrada]);
    } while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;
    Serie* serie[numEntrada];

    for(int i = 0; i < numEntrada; i++){
        serie[i] = (Serie*)malloc(sizeof(Serie));
        read(entrada[i], serie[i]);
        inserirFim(serie[i]);
    }
    
    scanf("%d", &num);
    Serie *passa = NULL;
    for(int i = 0; i < num; i++){
        scanf(" %[^\n]s", batata);
        if(strstr(batata, "II")){
            file = strtok(batata, " ");
            file = strtok(NULL, " ");
        		passa = (Serie*)malloc(sizeof(Serie));
            read(file, passa);
            inserirInicio(passa);
        } else if(strstr(batata,"I*")){
            file = strtok(batata, " ");
            file = strtok(NULL, " ");
            pos = atoi(file);
            file = strtok(NULL, " ");
        		passa = (Serie*)malloc(sizeof(Serie));
            read(file, passa);
            inserir(passa, pos);
        } else if(strstr(batata, "IF")){
            file = strtok(batata, " ");
            file = strtok(NULL, " ");
        		passa = (Serie*)malloc(sizeof(Serie));
            read(file, passa);
            inserirFim(passa);
        } else if(strstr(batata, "RI")){
            printf("(R) %s\n", removerInicio().name);
        } else if(strstr(batata, "R*")){
            file = strtok(batata, " ");
            file = strtok(NULL, " ");
            pos = atoi(file);
            printf("(R) %s\n", remover(pos).name);
        } else if(strstr(batata, "RF")){
            printf("(R) %s\n", removerFim().name);
        }
    }
    mostrar();
    return 0;
}
