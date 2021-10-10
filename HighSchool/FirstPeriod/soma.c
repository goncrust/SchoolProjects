#include <stdio.h>
#include <locale.h>

int main() {
    
    setlocale(LC_ALL, "");
    
    int a, b, soma;

    printf("Número 1: ");
    scanf("%d", &a);

    printf("Número 2: ");
    scanf("%d", &b);

    soma = a + b;

    printf("%d + %d = %d\n", a, b, soma);

    system("pause");

}
