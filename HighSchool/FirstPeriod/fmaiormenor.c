#include <stdio.h>
#include <locale.h>

int maior(int nmr1, int nmr2) {
	if (nmr1 > nmr2) {
		return nmr1;
	} else {
		return nmr2;
	}
}

int menor(int nmr1, int nmr2) {
	if (nmr1 < nmr2) {
		return nmr1;
	} else {
		return nmr2;
	}
}

void maior_menor() {
	int a, b;
	
	printf("Primeiro nmr: ");
	scanf("%d", &a);
	printf("Segundo nmr: ");
	scanf("%d", &b);
	
	printf("O maior é %d\n", maior(a, b));
	printf("O menor é %d\n", menor(a, b));
}

int main() {
	setlocale(LC_ALL, "");
	
	maior_menor();
}
