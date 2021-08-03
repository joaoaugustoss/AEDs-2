#include <stdio.h>
#define TAM 5

int main(){
    int array[TAM], min, max;
    for(int i = 0; i < TAM; i++){
        printf("Entre com o elemento %d do array: ", i+1);
        scanf("%d", &array[i]);

    }
    for(int i = 0; i < TAM; i++){
        printf("%d\t", array[i]);
        if(array[i-1] > array[i])
            min = array[i];
        else if(array[i] < array[i+1])
            max = array[i];
    }

    printf("\n");
    printf("O menor valor presente no array é: %d \nO maior valor presente no array é: %d\n", min, max);

    return 0;
}