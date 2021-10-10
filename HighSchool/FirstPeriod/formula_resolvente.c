#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    float a, b, c, delta;
    
    printf("a: ");
    scanf("%f", &a);
    
    if (a == 0) {
    	printf("Não é equação de segundo grau");
    	return;
	}
    
    printf("b: ");
    scanf("%f", &b);
    
    printf("c: ");
    scanf("%f", &c);
    
    delta = pow(b, 2) - (4 * a * c);
    
    if (delta < 0) {
    	printf ("Impossível");
    	return;	
	}
	
	if (delta == 0) {
		
		int res = ((-b) + sqrt(delta)) / (2 * a);
		printf ("x = %.2f\n", res);
		
	} else {
		
		int res1 = ((-b) + sqrt(delta)) / (2 * a);
		int res2 = ((-b) - sqrt(delta)) / (2 * a);
		
		printf ("x = %.2f e x = %.2f\n", res1, res2);
		
	}

	system("pause");

}
