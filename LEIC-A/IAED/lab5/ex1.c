#include <stdio.h>

#define ALUNOS 10
#define DISCIPLINAS 5

long score_disciplina(int disciplina, int valores[ALUNOS][DISCIPLINAS]) {
    long score = 0;

    for (int i = 0; i < ALUNOS; i++) {
        score += valores[i][disciplina];
    }

    return score;
}

long score_aluno(int aluno, int valores[ALUNOS][DISCIPLINAS]) {
    long score = 0;

    for (int i = 0; i < DISCIPLINAS; i++) {
        score += valores[aluno][i];
    }

    return score;
}

int main() {
    int valores[ALUNOS][DISCIPLINAS];

    /* Inicializar entusiasmos a 0 */
    for (int i = 0; i < ALUNOS; i++) {
        for (int j = 0; j < DISCIPLINAS; j++) {
            valores[i][j] = 0;
        }
    }

    int n;
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        int a, d, v;
        scanf("%d%d%d", &a, &d, &v);

        if (a < ALUNOS && d < DISCIPLINAS && (v <= 100 && v >= -100)) {
            valores[a][d] = v;
        }
    }

    int d_max = score_disciplina(0, valores);
    int d_res = 0;
    for (int d = 1; d < DISCIPLINAS; d++) {
        int curr = score_disciplina(d, valores);

        if (curr > d_max) {
            d_max = curr;
            d_res = d;
        }
    }

    int a_max = score_aluno(0, valores);
    int a_res = 0;
    for (int a = 1; a < DISCIPLINAS; a++) {
        int curr = score_aluno(a, valores);

        if (curr > a_max) {
            a_max = curr;
            a_res = a;
        }
    }

    printf("%d\n%d\n", d_res, a_res);

    return 0;

}
