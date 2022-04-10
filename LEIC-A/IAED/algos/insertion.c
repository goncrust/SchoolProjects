void insertion_sort(int a[], int l, int r) {
    int i; 
    for (i = l+1; i < r+1; i++) {
        j = i-1;
        while (j >= left && a[i] < a[j]) {
            a[j+1] = a[j];
            j--;
        }
        a[j+1] = a[i];
    }
}
