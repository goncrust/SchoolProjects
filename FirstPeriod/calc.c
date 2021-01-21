#include <stdio.h>
#include <locale.h>
#include <math.h>
#include <stdbool.h>

int main () {
	
	setlocale(LC_ALL,"");
	
	char op, continuar;
	bool running = true;
    int a, b, res;
    
    do{
    
    	printf("Opera��o: ");
    	scanf(" %c", &op);
    	printf("Primeiro n�mero: ");
    	scanf("%d", &a);
    	printf("Segundo n�mero: ");
    	scanf("%d", &b);
    	 
		switch(op){
			case '+':
				res = a + b;
				printf("%d + %d = %d\n", a, b, res);
				break;
			case '-':
				res = a - b;
				printf("%d - %d = %d\n", a, b, res);
				break;
			case '*':
				res = a * b;
				printf("%d * %d = %d\n", a, b, res);
				break;
			case '/':
				if (b == 0){
					printf("Imposs�vel\n");
				}else{
					res = a / b;
					printf("%d / %d = %d\n", a, b, res);
				}
				break;
			default:
				printf("NADA\n");
				break;
		}
		
		printf("Deseja continuar? \n");
		scanf(" %c", &continuar);
		
		if (continuar != 's') {
			running = false;
		}
		
	
	}while (running);
	
	
	system("pause");

}
