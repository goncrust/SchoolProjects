#include <stdio.h>

int main() {
    int n, k;

    scanf("%d%d", &n, &k);
    
    for (int i = 1; i <= n; i++) {
        if (k == 0) break;
        if (n % i == 0) {
            printf("%d\n", i);
            k--;
        }
    }

    return 0;
}
