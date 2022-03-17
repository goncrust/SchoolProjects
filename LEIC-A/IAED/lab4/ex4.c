#include <stdio.h>
#include <string.h>

#define MAX 80

int main() {
    char palavra[MAX];

    scanf("%s", palavra);
    int len = strlen(palavra);

    for (int i = 0; i < len/2; i++) {
        if (palavra[i] != palavra[len-i-1]) {
            printf("no\n");
            return 0;
        }
    }

    printf("yes\n");
    return 0;

}
