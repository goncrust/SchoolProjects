#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    
    if (argc != 3) return 1;

    float lim_inf = atoi(argv[1]);
    float lim_sup = atoi(argv[2]);

    for (float i = lim_inf; i <= lim_sup; i += 5) {
        float cels = (0.5555555)*(i-32);
        printf("%.1f \t %.2f\n", i, cels);
    }

    return 0;

}
