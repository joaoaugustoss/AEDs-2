#include <stdio.h>
#include <math.h>

double funcao(int n, double x);
double parSobreImpar(int n);
int par(int n);
double umSobreImpar(int n);
void exercicio07();
double funcao07(int n, double x);

double funcao07(int n, double x){
    double aux = 0.0;
    for(int i = 1; i<n; i++){
        aux = funcao(1,x) * funcao(i,x);
    }
    return aux;
}

double funcao(int n, double x){

    int numeradorCalculo = 0 ;
    double denominadorCalculo = 0.0;
    double calculo = 0.0;
    int soma = 1;

    numeradorCalculo = par(n);
    denominadorCalculo = umSobreImpar(n);

    for(int i = 1; i<n; i++){
        soma+=1;
    }
    
    calculo = numeradorCalculo * pow(x,soma) / denominadorCalculo;
    
    return calculo;
}

double parSobreImpar(int n){
    int numerador=0;
    double denominadorCalculo=0.0;
    double resultado=0.0;

    numerador = par(n);
    denominadorCalculo = umSobreImpar(n);

    resultado = (double) numerador / denominadorCalculo;
    
    return resultado;
}

int par(int n){
    int soma=2;

    for(int i=1; i<n; i++){
        soma=soma+2;
    }
    return soma;
}

double umSobreImpar(int n){
    double denominador=3.0;

    for(int i = 1; i < n; i++){
        denominador = denominador + 2.0;
    }
    return denominador;
}
void exercicio07(){
    int n=0;
    double x=0.0;

    printf("Insira um numero inteiro: ");
    scanf("%i",&n);

    printf("Insira um numero real: ");
    scanf("%lf",&x);

    printf("O produto dos %i termos e: %g",n,funcao07(n,x));

}
int main(){
    exercicio07();
    return 0;
}