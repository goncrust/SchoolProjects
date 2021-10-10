#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a;
    
    printf("Número: ");
    scanf("%d", &a);
    
    if (a % 2 == 0){
    	printf("%d é par\n", a);
    }else{
		printf("%d é ímpar\n", a);
	}
	
	system("pause");

}
