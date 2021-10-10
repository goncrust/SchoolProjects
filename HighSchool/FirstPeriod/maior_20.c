#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a;
    
    printf("Número: ");
    scanf("%d", &a);
    
    if (a > 20){
    	printf("%d é maior que 20\n", a);
    }else{
		printf("%d é menor que 20\n", a);
	}
	
	system("pause");

}
