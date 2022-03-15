#include <stdio.h>

#define NUM 0
#define OP  1
#define MAIS 0
#define MENOS 1

int main() {
    int c, total = 0, curr = 0, estado = NUM, estado_op = MAIS;

    while ((c = getchar()) != '\n') {
        if (estado == NUM) {

            if (c == ' ') {
                estado = OP;

                if (estado_op == MAIS)
                    total += curr;
                else if (estado_op == MENOS)
                    total -= curr;

                curr = 0;
                continue;
            }

            c -= 48;
            curr = curr*10 + c;
        } else if (estado == OP) {

            if (c == ' ') {
                estado = NUM;
                continue;
            }

            if (c == '+')
                estado_op = MAIS;
            else if (c == '-')
                estado_op = MENOS;
        }
    }

    if (estado_op == MAIS)
        total += curr;
    else if (estado_op == MENOS)
        total -= curr;

    printf("%d\n", total);

    return 0;
}
