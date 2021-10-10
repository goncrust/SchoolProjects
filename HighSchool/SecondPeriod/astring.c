#include <stdio.h>

int main () {
	
	char a[30];
	fgets(a, 30, stdin);
	
	if (a[0]=='a' || a[0] == 'A') {
		printf("%s", a);
	}
	
}

