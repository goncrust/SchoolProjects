#include <stdio.h>

float calcule(char temp, float tempValue) {
	
	if (temp == 'c') {
		return ((9*tempValue/5)+32);
	} else {
		return (5*(tempValue-32)/9);
	}
		
}

int main() {
	char temp;
	float value;
	
	printf("Qual tipo? (C/F) ");
	scanf(" %c", &temp);
	
	switch(temp) {
		case 'c':
			printf("Graus em C: ");
			scanf("%f", &value);
			printf("%.2fC em F: %.2f", value, calcule('c', value));
			break;
		case 'f':
			printf("Graus em F: ");
			scanf("%f", &value);
			printf("%.2fF em C: %.2f", value, calcule('f', value));
			break;
		default:
			break;
	}
}
