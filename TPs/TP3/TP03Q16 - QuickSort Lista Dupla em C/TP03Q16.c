#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>

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
    char *resp = (char *)malloc(sizeof(strlen(line) + 1));
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
    char *resp = (char *)malloc(sizeof(char) * strlen(s));
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
    char *resp = (char *)malloc(sizeof(char) * strlen(fileName));
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

char* blank(char* s){
    char* resp = (char*) malloc(strlen(s) * sizeof(char));
    if(s[0] == ' ' && s[strlen(s) - 1] == ' '){
        for(int i = 1; i < strlen(s) - 1; i++){
            resp[i-1] = s[i];
            resp[i] = '\0';
        }
    } else if(s[0] == ' '){
        for(int i = 1; i < strlen(s); i++){
            resp[i-1] = s[i];
            resp[i] = '\0';
        }
    } else if(s[strlen(s) - 1] == ' '){
        for(int i = 0; i < strlen(s) - 1; i++){
            resp[i] = s[i];
            resp[i + 1] = '\0';
        }
    } else {
        for(int i = 0; i < strlen(s); i++){
            resp[i] = s[i];
            resp[i + 1] = '\0';
        }
    }
    return resp;
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

typedef struct CelulaDupla {
	Serie* elemento;        // Elemento inserido na celula.
	struct CelulaDupla* prox; // Aponta a celula prox.
    struct CelulaDupla* ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Serie* elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = nova->prox = NULL;
   return nova;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla* primeiro;
CelulaDupla* ultimo;
int mov = 0;
int comp = 0;

/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start () {
   primeiro = novaCelulaDupla(NULL);
   ultimo = primeiro;
}

/**
 * Insere elemento na pilha (politica FILO).
 * @param x int elemento a inserir.
 */
void inserir(Serie* x) {
   ultimo->prox = novaCelulaDupla(x);
   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar() {
    CelulaDupla* i;
    for (i = primeiro->prox; i != NULL; i = i->prox) {
        print(i->elemento);
    }
}

int tamanho(){
    int tam = 0;
    for(CelulaDupla* i = primeiro->prox; i != NULL; i = i->prox){
        tam += 1;
    }
    return tam;
}

CelulaDupla* getElemento(int pos){
    CelulaDupla* resp;
    int tam = tamanho();

    if(pos >= 0 || pos < tam){   
        CelulaDupla* i = primeiro->prox;
        for(int j = 0; j < pos; j++, i = i->prox);
        resp = i;
    }  
    return resp;
}

void swap(Serie *i, Serie *j) {
    mov += 3;
    Serie temp = *i;
    *i = *j;
    *j = temp;
}

void quicksortRec(int esq, int dir) {
    int i = esq, j = dir;
    CelulaDupla* pivo = primeiro->prox;

    for(int k = 0; k < ((dir+esq)/2) && pivo != NULL; pivo = pivo->prox, k++);

    while (i <= j) {
        
        while (strcmp(blank(getElemento(i)->elemento->country), blank(pivo->elemento->country)) < 0 || (strcmp(blank(getElemento(i)->elemento->country), blank(pivo->elemento->country)) == 0 && (strcmp(blank(getElemento(i)->elemento->name), blank(pivo->elemento->name)) < 0))) i++;
        
        while (strcmp(blank(getElemento(j)->elemento->country), blank(pivo->elemento->country)) > 0 || (strcmp(blank(getElemento(j)->elemento->country), blank(pivo->elemento->country)) == 0 && (strcmp(blank(getElemento(j)->elemento->name), blank(pivo->elemento->name)) > 0))) j--;
        
        if (i <= j) {
            swap(getElemento(i)->elemento, getElemento(j)->elemento);
            i++;
            j--;
        }
    }
    if (esq < j)  quicksortRec(esq, j);
    if (i < dir)  quicksortRec(i, dir);
}

void quicksort() {
    quicksortRec(0, tamanho()-1);
}

void saveFile(float time){
    FILE *fp = fopen("724667_selecaoRecursiva.txt", "w");
    if(fp == NULL){
        printf("Arquivo inválido!!!\n");
    }
    fprintf(fp, "724667\t%d\t%d\t%gs\n", mov, comp, (time/(double)CLOCKS_PER_SEC));

    fclose(fp);
}

//método principal
int main(){
    clock_t t;
    char entrada[1000][100];
    int numEntrada = 0;
    start();

    do{
        scanf(" %[^\n]s", entrada[numEntrada]);
    } while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;
    Serie* serie[numEntrada];

    for(int i = 0; i < numEntrada; i++){
        serie[i] = (Serie*)malloc(sizeof(Serie));
        read(entrada[i], serie[i]);
        inserir(serie[i]);
    }

    quicksort();
    quicksort();
    quicksort();
    quicksort();
    
    mostrar();

    saveFile(clock() - t);
    return 0;
}