#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int dia;
 
    printf("Que dia da semana? ");
    scanf("%d", &dia);
    
    switch (dia) {
    	
    	case 1:
    		printf("Domingo\n");
    		break;
    		
    	case 2:
    		printf("Segunda\n");
    		break;
    		
     	case 3:
    		printf("Terça\n");
    		break;
    		
      	case 4:
    		printf("Quarta\n");
    		break;
    		
    	case 5:
    		printf("Quinta\n");
    		break;
    		
     	case 6:
    		printf("Sexta\n");
    		break;
    		
    	case 7:
    		printf("Sábado\n");
    		break;
    		
    	default:
    		printf("Inválido\n");
    		break;
    	
	}
    
    system("pause");
    
}
