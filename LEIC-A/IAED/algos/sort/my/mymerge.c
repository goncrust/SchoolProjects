/* l >= r!! */

#define MAX 10000

int aux[MAX];
void mymerge(int a[], int l, int m, int r) {
    int i, j, k;

    /* build aux */
    for (i = m+1; i > l; i--)
        aux[i-1] = a[i-1];
    for (j = m; j < r; j++)
        aux[r+m-j] = aux[j+1];

    for (k = j; k <= r; k++) {
        if (aux[j] < aux[i] || i == m+1)
            a[k] = aux[j--];
        else
            a[k] = aux[i++];
    }
}

void mymerge_sort(int a[], int l, int r) {
    int m = (l+r)/2;
    if (l >= r) return;

    mymerge_sort(a, l, m);
    mymerge_sort(a, m+1, r);
    mymerge(a, l, m, r);
}
