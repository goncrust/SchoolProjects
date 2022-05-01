/* O(N + M) */
/* stable */

#define M 6 /* MAX NUMBER */
#define N 10 /* MAX ARRAY SIZE */

void counting_sort(int a[], int l, int r) {
    int i, n = l-r+1;
    int cnt[M+1], sorted[N];

    /* Reset cnt array */
    for (i = 0; i < M+1; i++) {
        cnt[i] = 0;
    }

    for (i = l; i <= r; i++) {
        cnt[a[i]+1]++;
    }

    for (i = 1; i < M+1; i++) {
        cnt[i] += cnt[i-1];
    }

    for (i = l; i <= r; i++) {
        sorted[cnt[a[i]]++] = a[i];
    }

    /* Update original array */
    for (i = l; i <= r; i++) {
        a[i] = sorted[i-l];
    }
}
