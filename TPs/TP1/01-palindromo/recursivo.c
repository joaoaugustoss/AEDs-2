#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isFim(char s[]){
    return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

bool isPalindromo(char s[], int i, int j, bool resp){
        if(resp == false && i < 0) //condição de parada recursividade
            return resp;
        else if(s[i] != s[j]){
            resp = false;
            isPalindromo(s, i = i - 1, j = j + 1, resp);
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
        if(isPalindromo(entrada[i], strlen(entrada[i]) - 1, 0, true) == true)
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    return 0;
}
