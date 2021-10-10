#include <stdio.h>
#include <locale.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>

int main() {

    setlocale(LC_ALL, "");
	
	srand(time(NULL));
    
    int x = (rand() % 10) + 1;

    printf("Adivinhe o número de 1 a 10\n");

	int i, a;
	for (i = 1; i <= 3; i++){
		printf("(%d) ", i);
		scanf("%d", &a);
		
	 	if (a > x){
        	printf("Mais abaixo\n");
     	} else if (a < x){
        	printf("Mais acima\n");
     	} else if (a == x){
        	printf("Acertou\n");
        	break;
     	}
}
	    
    system("pause");
}
