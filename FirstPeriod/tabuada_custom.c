#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    int a, i, res, b, c;
    
    printf("Tabuada: ");
    scanf("%d", &a);
    
    printf("Partir: ");
    scanf("%d", &b);
    
    printf("Termninar: ");
    scanf("%d", &c);
    
    if (c < b){
		printf("Erro\n");
	}
    
    for (i = b; i <= c; i = i + 1){
    	res = a * i;
    	printf("%d\n", res);
	}

	system("pause");

}
