#include <stdio.h>

int main()
{
    // 10, 20, 50

    int value = 400;

    int resto50 = value % 50;

    if (resto50 == 0)
    {
        printf("50: %d", value / 50);
    }
    else
    {
        int resto20 = resto50 % 20;

        if (resto20 == 0)
        {
            printf("50: %d\n20: %d", value / 50, resto50 / 20);
        }
        else
        {
            printf("50: %d\n20: %d\n10: %d", value / 50, resto50 / 20, resto20 / 10);
        }
    }
}