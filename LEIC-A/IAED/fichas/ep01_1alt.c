#include <stdio.h>

int main() {
    int n, k, i = 1;

    scanf("%d%d", &n, &k);

    while (k > 0 && i <= n) {
        if (n % i == 0) {
            printf("%d\n", i);
            k--;
        }
        i++;
    }

    return 0;
}
