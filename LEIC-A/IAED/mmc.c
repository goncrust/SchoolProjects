#include <stdio.h>

int main() {
    int a, b;
    scanf("%d%d", &a, &b);

    int a_m = 1, b_m = 1;
    while (a*a_m != b*b_m) {
        if (a*a_m < b*b_m) {
            a_m++; 
        } else {
            b_m++;
        }
    }
    
    printf("%d\n", a*a_m);

    return 0;
}
