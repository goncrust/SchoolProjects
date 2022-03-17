#include <stdio.h>

#define MAX 80

int leLinha(char s[]) {
    char c;
    int i = 0, count = 0;

    scanf("%c", &c);
    while (c != '\n' && c != EOF) {
        s[i] = c;
        i++;

        count++;

        scanf("%c", &c);
    }
    s[i] = '\0';

    return count;
}

/*
int main() {
    char s[MAX];

    int total = leLinha(s);

    printf("%s\n%d\n", s, total);
    
    return 0;
}
*/
