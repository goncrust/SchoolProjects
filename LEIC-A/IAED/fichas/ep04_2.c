#include <stdlib.h>

typedef struct {
    unsigned lin, col;
    double val;
} Entry;

double* getLineValues(Entry *mat, int n, int line, int nColumns) {
    int i;
    /* double* lineValues = (double *) malloc(sizeof(double)*nColumns); */
    double* lineValues = (double *) calloc(nColumns, sizeof(double));

    /* for (i = 0; i < nColumns; i++) {
        lineValues[i] = 0;
    }
    */

    for (i = 0; i < n; i++) {
        if (mat[i].lin == line)
            lineValues[mat[i].col] = mat[i].val;
    }

    return lineValues;
}
