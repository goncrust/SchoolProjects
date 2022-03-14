#include <stdio.h>

void piramide(int N) {

    for (int i = 1; i <= N; i++) {
        // espacos iniciais
        for (int j = 0; j < N - i; j++) {
            printf("  ");
        }
        // decrescente
        for (int j = 1; j <= i; j++) {
            printf("%d ", j);
        }
        // crescente
        for (int j = i-1; j > 0; j--) {
            printf("%d ", j);
        }
        printf("\n");
    }

}

int main() {
    int n;
    scanf("%d", &n);
    piramide(n);

    return 0;
}

