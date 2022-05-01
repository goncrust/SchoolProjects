/* O(n^2) */
/* stable */

void bubble_sort(int a[], int l, int r) {
    int i, j, aux, done;

    for (i = l; i < r; i++) {
        done = 1;
        for (j = l; j < r-i + l; j++) {
            if (a[j+1] < a[j]) {
                aux = a[j];
                a[j] = a[j+1];
                a[j+1] = aux;
                done = 0;
            }
        }
        if (done)
            break;
    }
}
