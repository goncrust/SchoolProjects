#include <stdio.h>

int main() {
    int n;
    float maior, menor;
    
    scanf("%d", &n);

    if (n != 0) {
        scanf("%f", &maior);
        menor = maior;
    }

    for (int i = 1; i < n; i++) {
        float temp;

        scanf("%f", &temp);

        if (temp < menor)
            menor = temp;
        if (temp > maior)
            maior = temp;
    }

    printf("min: %f, max: %f\n", menor, maior);

    return 0;
}
