/* O(nlogn) */

#define MAX 10000

int aux[MAX];
void merge(int a[], int l, int m, int r) {
    int i, j, k;
    for (i = m+1; i > l; i--)
        aux[i-1] = a[i-1];
    for (j = m; j < r; j++)
        aux[r+m-j] = a[j+1];
    for (k = l; k <= r; k++) {
        if (aux[j] < aux[i] || i == m+1)
            a[k] = aux[j--];
        else
            a[k] = aux[i++];
    }
}

void mergesort(int a[], int l, int r) {
    int m = (l+r)/2;
    if (r <= l) return;

    mergesort(a, l, m);
    mergesort(a, m+1, r);
    merge(a, l, m, r);
}
