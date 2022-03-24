#include <stdio.h>

void troca(char s[]) {

    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] <= 122 && s[i] >= 97) {
            s[i] -= 32;
        }
    }

}

int main() {
    char s1[] = "IAED: desde 1989.";
    troca(s1);

    printf("%s\n", s1);
    
    return 0;
}
