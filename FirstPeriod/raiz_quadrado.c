#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    float a, b;
    
    printf("a: ");
    scanf("%f", &a);
    
    printf("b: ");
    scanf("%f", &b);
    
    if (a > b && a > 0){
    	a = sqrt(a);
    	printf("A raiz quadrada de a � %.2f\n", a);
    	
    	b = pow(b, 2);
    	printf("O quadrado de b � %.2f\n", b);
	}else if (b > a && b > 0){
		a = pow(a, 2);
    	printf("O quadrado de a � %.2f\n", a);
    	
		b = sqrt(b);
		printf("A raiz quadrada de b � %.2f\n", b);
	}else{
		printf("IMPOSS�VEL\n");
	}

	system("pause");

}
