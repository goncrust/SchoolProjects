#include <stdio.h>

int main() {
    int soma_n = 0;

    for (int i = 0; i < 100; i++) {
        int c = getchar();
        if (c == ' ') continue;
        if (c < '0' || c > '9') break;
        soma_n += c - 48;
    }

    if (soma_n % 9 == 0)
        printf("yes");
    else
        printf("no");

    return 0;
}
