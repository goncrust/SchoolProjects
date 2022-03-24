#include <stdio.h>

#define NOMESIZE 10
#define TITULOSMAX 1000

typedef struct {
    char nome[NOMESIZE+1];
    float valor;
    float pra;
} Stock;

int main() {
    Stock stocks[TITULOSMAX];

    int n;
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        int j = 0;
        char c;
        while ((c = getchar()) != ' ') {
            stocks[i].nome[j] = c;
            j++;
        }
        stocks[i].nome[j] = '\0';

        scanf("%f%f", &stocks[i].valor, &stocks[i].pra);
    }

    float max = 0;
    int max_i = 0;
    for (int s = 0; s < n; s++) {
        if (stocks[s].pra > max) {
            max = stocks[s].pra;
            max_i = s;
        }
    }

    printf("%s %.2f %.2f\n", stocks[max_i].nome, stocks[max_i].valor, stocks[max_i].pra);

    return 0;
}
