#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
	
	float c;
	float f;
	
    printf("Temperatura em C: ");
    scanf("%f", &c);
    
    f = (c * 9) / 5 + 32;
    
    printf("%.1fC s�o %.1fF.\n", c, f);

    system("pause");
    
}
