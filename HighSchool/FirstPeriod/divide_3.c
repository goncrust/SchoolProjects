#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
    float a;
	float b;
    
    printf("Número: ");
    scanf("%f", &a);
    
    b = a / 3;
    
    printf("%.2f/3 é %.2f.\n", a, b);

    system("pause");
    
}
