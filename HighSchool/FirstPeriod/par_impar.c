#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a;
    
    printf("N�mero: ");
    scanf("%d", &a);
    
    if (a % 2 == 0){
    	printf("%d � par\n", a);
    }else{
		printf("%d � �mpar\n", a);
	}
	
	system("pause");

}
