#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a;
    
    printf("N�mero: ");
    scanf("%d", &a);
    
    if (a > 20){
    	printf("%d � maior que 20\n", a);
    }else{
		printf("%d � menor que 20\n", a);
	}
	
	system("pause");

}
