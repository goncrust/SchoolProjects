
void myselection_sort(int a[], int n) {
    int i, j, min, aux;
    for (i = 0; i < n-1; i++) {
        min = i;
        for (j = i+1; j < n; j++) {
            if (a[min] > a[j]) {
                min = j;
            }
        }
        aux = a[min];
        a[min] = a[i];
        a[i] = aux;
    }
}
