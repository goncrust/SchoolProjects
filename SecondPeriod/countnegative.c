#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
	
	srand(time(NULL));
	
	int nms[100];
	
	int x;
	for (x = 0; x < 100; x++) {
		nms[x] = (rand() % 2000) - 1000; 
	}
	
	int i;
	int count = 0;
	for (i = 0; i < 100; i++) {
		if (nms[i] < 0) {
			count++;
			printf("%d\n", nms[i]);
		}
	}
	
	printf("São: %d\n", count);
	
	system("pause");
	
}
