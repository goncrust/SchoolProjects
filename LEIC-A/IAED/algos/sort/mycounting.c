
#define MAX_NUM 100
#define MAX_SIZE 10000

void mycounting_sort(int a[], int n) {
    int cnt[MAX_NUM+1], sorted[MAX_SIZE], i;

    for (i = 0; i < MAX_NUM+1; i++) {
        cnt[i] = 0;
    }

    for (i = 0; i < n; i++) {
        cnt[a[i]+1]++;
    }

    for (i = 1; i < MAX_NUM+1; i++) {
        cnt[i] += cnt[i-1];
    }

    for (i = 0; i < n; i++) {
        sorted[cnt[a[i]]++] = a[i];
    }

    for (i = 0; i < n; i++) {
        a[i] = sorted[i-0];
    }
}
