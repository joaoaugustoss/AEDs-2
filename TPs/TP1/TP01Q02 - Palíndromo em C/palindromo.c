#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isFim(char s[]){
    return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

bool isPalindromo(char s[]){
    bool resp = true;
    int j = 0; 
    for(int i = strlen(s) - 1; i >=0 && resp; i--, j++){
        if(s[i] != s[j])
            resp = false;
    }
    return resp;
}

int main(){
    char entrada[1000][100];
    int numEntrada = 0;

    do{
        scanf(" %[^\n]s", entrada[numEntrada]);
    } while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    for(int i = 0; i < numEntrada; i++){
        if(isPalindromo(entrada[i]) == true)
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    return 0;
}