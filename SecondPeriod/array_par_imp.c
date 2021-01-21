#include <stdio.h>
#include<time.h>
#include<stdlib.h>

int main(){
	srand(time(NULL));
    
    int nms[100];
    int i;
    for (i=0; i<100;i++){
    	nms[i]=rand()%2000;
	}
   int x;
    for (x=0; x<100;x++){
    	printf("%d", nms[x]);
    	if (nms[x]%2==0){
    		nms [x]=1;
    	}else{
    		nms[x]=-1;
    	}
    	printf("-> %d\n",nms[x]);
		}
	system("pause");
}
