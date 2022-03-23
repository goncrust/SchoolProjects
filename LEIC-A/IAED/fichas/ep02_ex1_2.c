#include <stdio.h>

#define MAX 100

int moda() {
    int n, times[MAX], max_n = 0, max = 0;

    /* Initialize all times as 0 */
    for (int i = 0; i < MAX; i++) {
        times[i] = 0;
    }

    scanf("%d", &n);
    while (n > -1) {
        times[n] += 1;

        scanf("%d", &n);
    }

    for (int i = 0; i < MAX; i++) {
        if (times[i] > max) {
            max = times[i];
            max_n = i;
        }
    }

    return max_n;
}

int main() {
    printf("%d\n", moda());

    return 0;
}
