#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
	int a;
	int b;
	
	printf("a: ");
    scanf("%d", &a);

    printf("b: ");
    scanf("%d", &b);
    
    printf("--------------\n");
    
	a = a + b;
	b = a - b;
	a = a - b;
	
	printf("a: %d\nb: %d\n", a, b);

    system("pause");
		
}
