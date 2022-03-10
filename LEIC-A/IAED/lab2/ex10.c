#include <stdio.h>

int main() {
    int n, soma=0, count=0;

    scanf("%d", &n);

    while (n != 0) {
        soma += n % 10;
        count++;
        n /= 10;
    }

    printf("%d\n%d\n", count, soma);

    return 0;
}
