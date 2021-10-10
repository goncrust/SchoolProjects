#include <stdio.h>
#include <locale.h>
#include <math.h>
#include <stdlib.h>

int main () {
	
	setlocale(LC_ALL,"");
	
    int nms[5];
    int soma = 0, media = 0, min = 0, max = 0;

	int i;
	for (i = 0; i < 5; i++){
		printf("(%d) Número? ", i+1);
		scanf("%d", &nms[i]);
	}
	
	int z;
	for (z = 0; z < 5; z++){
		soma += nms[z];
	}
			
	printf("Soma:%d\n", soma);
	
	media = soma / 5;
	printf("Média: %d\n", media);
	
	int x;
	for (x = 0; x < 5; x++){
		
		if (nms[min] > nms[x]){
			min = x;
		}
		
	}
	
	int y;
	for (y = 0; y < 5; y++){
		
		if (nms[max] < nms[y]){
			max = y;
		}
		
	}	
	
	int menor = nms[min];
	printf("Menor: %d\n", menor);
	
	int maior = nms[max];
	printf("Maior: %d\n", maior);
	
	system("pause");

}
