#include <stdio.h>

#define VECMAX 100

int main() {
    int n, max = 0;
    scanf("%d", &n);
    if (n >= 100) return 0;

    int nums[n];
    for (int i = 0; i < n; i++) {
        int new_num;
        scanf("%d", &new_num);
        nums[i] = new_num;

        if (new_num > max) max = new_num;
    }

    //for (int i = 0; i < n; i++) nums[i] = max-nums[i];

    for (int i = 0; i < max; i++) {
        for (int j = 0; j < n; j++) {
            if (max-nums[j] > 0) {
                printf(" ");
                nums[j]++;
            } else {
                printf("*");
            }
        }
        printf("\n");
    }

    return 0;
}


