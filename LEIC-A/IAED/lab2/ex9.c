#include <stdio.h>

int main() {
    int seg, min, hr;
    scanf("%d", &seg);

    min = seg / 60;
    seg %= 60;
    hr = min / 60;
    min %= 60;

    printf("%02d:%02d:%02d\n", hr, min, seg);

    return 0;
}
