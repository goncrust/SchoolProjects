#include <stdio.h>

int *arraya();

int main()
{
    int *b = arraya();
    printf("%d", b[0]);

    int c[5];
    arrayc(c);
    printf("%d", c[4]);
}

int *arraya()
{
    int *a = malloc(3);

    int i;
    for (i = 0; i < 3; i++)
    {
        a[i] = i;
    }

    return a;
}

void arrayc(int *c)
{
    int i;
    for (i = 0; i < 5; i++)
    {
        c[i] = i;
    }
}