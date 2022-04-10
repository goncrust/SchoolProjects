int partition(int a[], int l, int r) {
    int i = l-1;
    int j = r;
    int aux;
    int v = a[r];
    while (i < j) {
        while (a[++i] < v);
        while(v < a[--j]) {
            if (j == l)
                break;
        }
        if(i < j)
            aux = a[i];
            a[i] = a[j];
            a[j] = aux;
    }
    aux = a[i];
    a[i] = a[r];
    a[r] = a[i];
    return i;
}

void quick_sort(int a[], int l, int r) {
    int i;
    if (r <= l) return;
    i = partition(a, l, r);
    quick_sort(a, l, r-1);
    quick_sort(a, i+1, r);
}
