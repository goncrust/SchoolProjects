#include <stdio.h>
#include <time.h>

int dado(int menor, int maior) {
	return (rand() % maior) + menor;
}

int main() {
	srand(time(NULL));
	
	printf("Dado: %d", dado(1, 6));
}
