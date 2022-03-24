#include <stdio.h>

typedef struct {
    float real;
    float imag;
} Complexo;

Complexo soma_complexos(Complexo c1, Complexo c2) {
    Complexo c = { c1.real + c2.real, c1.imag + c2.imag };
    return c;
}

int main() {
    Complexo c1;
    Complexo c2;
    char s, i;

    scanf("%f%c%f%c", &c1.real, &s, &c1.imag, &i);
    if (s == '-') c1.imag = -c1.imag;

    scanf("%f%c%f%c", &c2.real, &s, &c2.imag, &i);
    if (s == '-') c2.imag = -c2.imag;

    Complexo res = soma_complexos(c1, c2);
    if (res.imag >= 0) {
        printf("%.2f+%.2fi\n", res.real, res.imag);
    } else {
        printf("%.2f%.2fi\n", res.real, res.imag);
    }

    return 0;
}
