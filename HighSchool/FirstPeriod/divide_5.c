#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a;
    
    printf("Número: ");
    scanf("%d", &a);
    
    if (a % 5 == 0){
    	printf("Divisível por 5\n");
    }else{
		printf("Não divisível por 5\n");
	}
	
	system("pause");

}
