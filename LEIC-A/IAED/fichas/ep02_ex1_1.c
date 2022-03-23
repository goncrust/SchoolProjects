#include <stdio.h>

double pot(double x, int i) {
    double res = 1;

    if (i == 0) return 1;

    for (int j = 0; j < i; j++) {
        res *= x;
    }

    return res;
}

double polyval(double pol[], int size, double x) {
    double res = 0;
    for (int i = 0; i < size; i++) {
        res += pol[i] * pot(x, i);
    }

    return res;
}

int main() {
    double pol[] = {1, 3, 2};

    printf("%.2f\n", polyval(pol, 3, 2));

    return 0;
}
