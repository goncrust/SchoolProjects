/* j == l!! */

int mypartition(int a[], int l, int r) {
    int i = l-1, j = r, aux;
    while (i < j) {
        while (a[++i] < a[r]);
        while (a[--j] > a[r]) {
            if (j == l)
                break;
        }

        if (i < j) {
            aux = a[i];
            a[i] = a[j];
            a[j] = aux;
        }
    }
    aux = a[i];
    a[i] = a[r];
    a[r] = aux;

    return i;
}

void myquick_sort(int a[], int l, int r) {
    int i;
    i = mypartition(a, l, r);
    myquick_sort(a, l, i-1);
    myquick_sort(a, i+1, r);
}
