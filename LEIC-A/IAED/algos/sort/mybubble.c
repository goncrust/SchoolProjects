/* done!! */

void mybubble_sort(int a[], int n) {
    int i, j, aux, done;
    for (i = 0; i < n-1; i++) {
        done = 1; 
        for (j = 0; j < n-1-i; j++) {
            if (a[j] > a[j+1]) {
                aux = a[j];
                a[j+1] = a[j];
                a[j] = aux;

                done = 0;
            }
        }
        if (done)
            break;
    }
}
