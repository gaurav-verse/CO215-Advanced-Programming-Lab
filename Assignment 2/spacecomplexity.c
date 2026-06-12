#include <stdio.h>
#include <stdlib.h>

/* -------- CONSTANT SPACE O(1) -------- */
void constantSpace() {
    int a = 10, b = 20;
    int c = a + b;
}

/* -------- LINEAR SPACE O(n) -------- */
void linearSpace(int n) {
    int *arr = (int *)malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
        arr[i] = i;

    free(arr);
}

/* -------- QUADRATIC SPACE O(n^2) -------- */
void quadraticSpace(int n) {
    int **matrix = (int **)malloc(n * sizeof(int *));

    for (int i = 0; i < n; i++)
        matrix[i] = (int *)malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            matrix[i][j] = i + j;

    for (int i = 0; i < n; i++)
        free(matrix[i]);
    free(matrix);
}

int main() {
    int n;

    printf("Enter input size: ");
    scanf("%d", &n);

    constantSpace();
    printf("\nConstant Space O(1) executed");

    linearSpace(n);
    printf("\nLinear Space O(n) executed");

    quadraticSpace(n);
    printf("\nQuadratic Space O(n^2) executed");

    return 0;
}
