#include <stdio.h>

int main () {
	
	char a[100];
	fgets(a,sizeof(a), stdin);
	
	int i;
	int count = 0;
	for (i = 0; i < sizeof(a); i++) {
		if (a[i] == NULL) {
			break;
		}
		
		
		printf("\n -> %c", a[i]);
		count++;
	}
	count--;
	printf("%d", count);
	
}


