#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a, i, res;
    
    printf("Tabuada: ");
    scanf("%d", &a);
    
    for (i = 0; i <= 10; i = i + 1){
    	res = a * i;
    	printf("%d\n", res);
	}

	system("pause");

}
