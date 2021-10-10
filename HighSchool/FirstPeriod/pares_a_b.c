#include <stdio.h>
#include <locale.h>
#include <math.h>

int main() {
	
		setlocale(LC_ALL, "");
		
		int a, b, i, res = 0;
		
		printf("Primeiro Número: ");
		scanf("%d", &a);
		printf("Segundo Número: ");
		scanf("%d", &b);
		
	if (a < b){
	   
		if (a % 2 != 0){
			a++;
		}
		
		for (i = a; i <= b; i = i + 2){
			res += i;
		}
		
	} else{
	
		if (b % 2 != 0){
				b++;
		}
		
		for (i = b; i <= a; i = i + 2){
			res += i;
		}
		
	}
		
	printf("%d\n", res);
		
	system("pause");
	
}
