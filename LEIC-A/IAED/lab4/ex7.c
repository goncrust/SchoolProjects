#include <stdio.h>

#define MAX 80

void apagaIndex(char s[], int index) {
    for (int i = index; i < MAX-1; i++) {
        s[i] = s[i+1];

        if (s[i+1] == '\0') break;
    }
}

void apagaCaracter(char s[], char c) {
    int i = 0; 
    while (s[i] != '\0') {
        if (s[i] == c) apagaIndex(s, i);

        i++;
    }
}

int main() {
    char linha[MAX] = "ola nao sei\0";

    apagaCaracter(linha, 'a');

    printf("%s\n", linha);

    return 0;
}
