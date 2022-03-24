#include <stdio.h>

int mediana(int v[], int size) {
    int freqs[21];

    for (int i = 0; i < 21; i++) {
       freqs[i] = 0;
    }

    for (int i = 0; i < size; i++) {
       freqs[v[i]]++;
    }

    int n, count = 0, limit = size/2;
    for (n = 0; n < 21; n++) {
        count += freqs[n];

        if (count > limit) {
            break;
        }
    }

    return n;

}

int main() {
    int nums[] = {3, 5, 5, 8, 10, 14, 19};
    printf("%d\n", mediana(nums, 7));

    return 0;
}
