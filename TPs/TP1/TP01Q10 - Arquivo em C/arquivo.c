#include <stdio.h>

void openFile(FILE *fp){
    if(!feof(fp)){
        double n;
        fread(&n, sizeof(double), 1, fp);
        openFile(fp);
        printf("%.2f\n", n);
    }
}

int main(){
    FILE *fpo = fopen("arquivo.bin", "rb"); 
    FILE *fpw = fopen("arquivo.bin", "wb"); 

    openFile(fpo);





    return 0;
}