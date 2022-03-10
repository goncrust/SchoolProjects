#include <stdio.h>

int main() {
    int n;
    float total=0, media;

    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        float temp;
        scanf("%f", &temp);
        total += temp;
    }

    media = total / n;

    printf("%.2f\n", media);

    return 0;
}
