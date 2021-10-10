#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

int main() {
	
	srand(time(NULL));
	
	int nms[100];
	
	int x;
	for (x = 0; x < 100; x++) {
		nms[x] = (rand() % 2000) - 1000; 
	}
	
	int y;
	bool pn[100];
	for (y = 0; y < 100; y++) {
		if (nms[y] < 0) {
			pn[y] = false;
		} else {
			pn[y] = true;
		}
	}
	
	int z;
	for (z = 0; z < 100; z++) {
		if (pn[z]) {
			printf("true: %d\n", nms[z]);
		} else {
			printf("false: %d\n", nms[z]);
		}
	}
	
}
