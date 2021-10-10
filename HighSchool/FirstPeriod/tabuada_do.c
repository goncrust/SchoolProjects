#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a, i, res;
    
    i = 0;
    
    printf("Tabuada do: ");
    scanf("%d", &a);
    
    do{
    	
    	res = a * i;
    	
    	printf("%d\n", res);
    	
    	i = i + 1;
    	
	} while (i <= 10);

	system("pause");

}
