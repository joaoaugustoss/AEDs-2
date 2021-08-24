#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isFim(char s[]){ //Verifica se é FIM
    return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

bool isVogal(char s[]){ //Verifica se é vogal
    char x;
    bool resp = true;
    for(int i = 0; i < strlen(s); i++){
        x = s[i];
        if(!(x == 'a' || x == 'A' || x == 'e' || x == 'E' || x == 'i' || x == 'I' || x == 'o' || x == 'O' || x == 'u' || x == 'U'))
            resp = false;
    }
    return resp;
}

bool isConsoante(char s[]){ //Verifica se é consoante
    char x;
    bool resp = true;
    for(int i = 0; i < strlen(s); i++){
        x = s[i];
        if(x == 'a' || x == 'A' || x == 'e' || x == 'E' || x == 'i' || x == 'I' || x == 'o' || x == 'O' || x == 'u' || x == 'U' || x >= '0' && x <= '9')
            resp = false;
    }
    return resp;
}

bool isInt(char s[]){ //Verifica se é número inteiro
    char x;
    bool resp = true;
    for(int i = 0; i < strlen(s); i++){
        x = s[i];
        if(x >= 'A' && x <= 'Z' || x >= 'a' && x <= 'z' || x == '.' || x == ',' || x == ';')
            resp = false;
    }
    return resp;
}

bool isFloat(char s[]){ //Verifica se é número real
    char x;
    int count = 0;
    bool resp = false;
    for(int i = 0; i < strlen(s); i++){
        x = s[i];
        if(x == '.' || x == ',' || x == ';')
            count++;
        if(x >= 48 && x <= 57){
            resp = true;
        }
    }
    if(count > 1)
        resp = false;
    return resp;
}

int main(){
    char entrada[1000][100];
    int numEntrada = 0;

    do{
        scanf(" %[^\n]s", entrada[numEntrada]);
    } while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
    for(int i = 0; i < numEntrada; i++){
        if(isVogal(entrada[i]) == true)
            printf("SIM ");
        else
            printf("NAO ");
        if(isConsoante(entrada[i]) == true)
            printf("SIM ");
        else
            printf("NAO ");
        if(isInt(entrada[i]) == true)
            printf("SIM ");
        else
            printf("NAO ");
        if(isFloat(entrada[i]) == true)
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    
}