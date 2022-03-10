#include <stdio.h>

int main() {
    int n1, n2, n3;

    scanf("%d", &n1);
    scanf("%d", &n2);
    scanf("%d", &n3);

    printf("Maior: ");
    if (n1 > n2) {
        if (n1 > n3)
            printf("%d", n1);
        else
            printf("%d", n2);
    } else {
        if (n2 > n3)
            printf("%d", n2);
        else
            printf("%d", n3);
    }
    printf("\n");

    return 0;
}
