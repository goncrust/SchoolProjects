#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a;
    
    printf("N�mero: ");
    scanf("%d", &a);
    
    if (a % 5 == 0){
    	printf("Divis�vel por 5\n");
    }else{
		printf("N�o divis�vel por 5\n");
	}
	
	system("pause");

}
