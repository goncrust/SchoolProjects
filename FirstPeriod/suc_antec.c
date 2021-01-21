#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
	setlocale(LC_ALL, "");
    int a, suc, antec;
     
    printf("Número: ");
    scanf("%d", &a);
    
    suc = a + 1;
    antec = a - 1;
    
    printf("Sucessor: %d\nAntecessor: %d\n", suc, antec);

    system("pause");

}
