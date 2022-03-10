#include <stdio.h>

int main() {
    int n, divisores=2;
    scanf("%d", &n);

    if (n == 1) {
        printf("1 divisor\n");
        return 0;
    }

    for (int i = 2; i <= n/2; i++) {
        if (n % i == 0)
            divisores++;
    }

    printf("%d divisores\n", divisores);

    return 0;
}
