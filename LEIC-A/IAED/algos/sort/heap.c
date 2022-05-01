/* O(nlogn) */
/* not stable */

int parent(int i) {
    return ((i+1)/2)-1;
}

int left(int i) {
    return 2*i+1;
}

int right(int i) {
    return 2*(i+1);
}

void fixdown(int a[], int l, int r, int k) {
    int il, ir, largest=k, aux;

    il = l+left(k-l);
    ir = l+right(k-l);

    if (il <= r && a[largest] < a[il])
        largest = il;
    if (ir <= r && a[largest] < a[ir])
        largest = ir;

    if(largest != k) {
        aux = a[k];
        a[k] = a[largest];
        a[largest] = aux;

        fixdown(a, l, r, largest);
    }
}

void buildheap(int a[], int l, int r) {
    int k, heapsize = r-l+1;
    for (k = heapsize/2-1; k >= 0; k--)
        fixdown(a, l, r, l+k);
}

void heapsort(int a[], int l, int r) {
    int aux;

    buildheap(a, l, r);
    while (r-l > 0) {
        aux = a[l];
        a[l] = a[r];
        a[r] = aux;

        fixdown(a, l, --r, l);
    }
}
