/* O(n^2) */

void selection_sort(int a[], int l, int r) {
    int i, j, min, aux;
    for (i = l; i < r; i++) {
        min = i;
        for (j = i+1; j < r+1; j++) {
            if (a[j] < a[min]) {
                min = j;
            }
        }

        aux = a[i];
        a[i] = a[min];
        a[min] = aux;
    }
}
