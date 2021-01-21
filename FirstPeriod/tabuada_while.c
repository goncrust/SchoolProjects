#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a, i = 0, res;
    
    printf("Tabuada: ");
    scanf("%d", &a);
    
    while(i <= 10){
    	
    	res = a * i;
    	
    	printf("%d\n", res);
    	
    	i = i + 1;
    	
	}

	system("pause");

}
