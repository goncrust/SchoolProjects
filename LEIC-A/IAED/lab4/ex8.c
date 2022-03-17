#include <stdio.h>
#include <string.h>

#define MAX 80

int main() {
    char n1[MAX], n2[MAX];

    scanf("%s%s", n1, n2);

    int n1_len = strlen(n1);
    int n2_len = strlen(n2);

    if (n1_len > n2_len) {
        printf("%s\n", n1);
    } else if (n1_len < n2_len) {
        printf("%s\n", n2);
    } else {
        for (int i = 0; i < n1_len; i++) {
            if (n1[i] > n2[i]) {
                printf("%s\n", n1);
                break;
            } else if (n1[i] < n2[i]) {
                printf("%s\n", n2);
                break;
            }
        }
    }

    return 0;
    
}
