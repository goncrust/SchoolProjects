#include <stdio.h>
#include <locale.h>

int main() {
	setlocale(LC_ALL, "");
	
	int pin=2003;
	int a;
	int erros=0;
	while(1){
		if(erros>=3){
			printf("Erraste demasiadas vezes\n");
			break;
		}
		printf("Qual � o pin? ");
		scanf("%d", &a);
		
		if(pin==a){
			printf("Bem-vindo");
			break;
		} else { 
			printf("Erraste o c�digo\n");
			erros++;
			continue;
		}
	}
	
}


