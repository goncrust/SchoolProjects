#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int menor_array(int a_nms[]){
	int x;
	int menor=a_nms[0];
	for (x=1;x<100;x++){
		if (a_nms[x]<menor)	{
			menor=a_nms[x];		
		}
	}
    return menor;
}


int main (){
	srand(time(NULL));
	int nms [100];
	int i;
	for (i=0; i<100;i++){
		nms[i]=rand()%2000;
		printf("%d\t", nms[i]);
		
	}
   printf("\n\n%d\n",menor_array(nms));
   system ("pause");
}
