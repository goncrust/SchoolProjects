/* O(n^2) */
/* stable */

void insertion_sort(int a[], int l, int r) {
    int i, j, temp; 
    for (i = l+1; i < r+1; i++) {
        temp = a[i];
        j = i-1;
        while (j >= l && temp < a[j]) {
            a[j+1] = a[j];
            j--;
        }
        a[j+1] = temp;
    }
}
