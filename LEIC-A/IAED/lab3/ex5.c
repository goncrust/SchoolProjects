#include <stdio.h>

#define FORA 0
#define DENTRO 1

int main() {
    int c, c_last = ' ', estado = FORA, frase = 0, i = 0;
    char frases[10][100];

    while ((c = getchar()) != '\n') {
        if (c == '"' && c_last != '\\') {
            estado = !estado;
            if (estado == FORA) {
                frases[frase][i] = '\0';
                i = 0; 
                frase++;
            }
            continue;
        }
        
        if (estado == DENTRO) {
            if (!(c == '\\' && c_last != '\\')) {
                frases[frase][i] = c;
                i++;
            }
        }

        c_last = c;
    }


    for (int i = 0; i < frase; i++) {
        printf(frases[i]);
        printf("\n");
    }

    return 0;
}
