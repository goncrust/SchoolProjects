/* O(n^2) */

void insertion_sort(int a[], int l, int r) {
    int i, j; 
    for (i = l+1; i < r+1; i++) {
        j = i-1;
        while (j >= l && a[i] < a[j]) {
            a[j+1] = a[j];
            j--;
        }
        a[j+1] = a[i];
    }
}
