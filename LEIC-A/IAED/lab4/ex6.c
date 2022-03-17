#include <stdio.h>

#define MAX 80

int leLinha(char s[]);

void maiusculas(char s[]) {

    int i = 0;
    while (s[i] != '\0') {
        if (s[i] >= 97 && s[i] <= 122) {
            s[i] -= 32;
        }
        i++;
    }

}

int main() {
    char linha[MAX];

    leLinha(linha);
    maiusculas(linha);

    printf("%s\n", linha);

    return 0;
}
