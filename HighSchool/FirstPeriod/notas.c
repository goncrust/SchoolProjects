#include <stdio.h>
#include <locale.h>
#include <math.h>
#include <stdbool.h>

int main(){
	
	setlocale(LC_ALL, "");
	
    float nota;
    
    while (true){
	
	    printf("Nota: ");
	    scanf("%f", &nota);
	    
	    if (nota > 0 && nota <= 8){
	    	printf("Reprovado \n\n");
		}else if (nota > 8 && nota < 16){
		    printf("Passaste \n\n");
		}else if (nota >= 16 || nota <= 20){
			printf("Defesa de nota \n\n");
		}else{
			printf("Erro \n\n");
		}
		
	}

	system("pause");
	
}
