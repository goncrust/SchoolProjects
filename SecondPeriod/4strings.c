#include <stdio.h>

int main () {
	
	char a[300];
	fgets(a, sizeof(a), stdin);
	
	int i;
	for (i=0; i<4; i++){
	 	printf ("%c", a[i]); 	
	 }
	
}
