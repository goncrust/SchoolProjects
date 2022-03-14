#include <stdio.h>

int main() {
    
    int c, n = 0;
    while ((c = getchar()) != EOF) {
        if (c >= '0' && c <= '9') {
            c -= 48;
            n = n*10 + c;
        }
    }
    printf("%d\n", n);

    return 0;
}
