#include <stdio.h>
#include <stdbool.h>

bool verifica(int array, int num){
    bool resp;
        if(array == num)
            resp = true;
        else 
            resp = false;
    return resp;
}

int main(){
    int array[5] = {1, 2, 3, 4, 5}, num;
    bool resp;
    printf("Entre com um número a ser comparado: ");
    scanf("%d", &num);

    for(int i = 0; resp == false && i < 5; i++){
        if(verifica(array[i], num) == true)
            resp = true;
        
        else
            resp = false;
    }

    if(resp == true){
        printf("O número %d existe no array.\n", num);
        return 0; 
    } else {
       printf("O número %d não existe no array.\n", num);
        return 0;  
    }
}