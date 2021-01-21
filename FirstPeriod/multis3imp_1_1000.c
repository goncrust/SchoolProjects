#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {

    setlocale(LC_ALL, "");
    
    int i = 0, res = 0;

    for (i = 3; i <= 1000; i = i + 3){
            if(i % 2 != 0){
                res += i; 
                }
		}

    printf("%d\n", res);
}
