#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/* -------- CONSTANT TIME O(1) -------- */
void constantTime() {
    int a = 10, b = 20;
    int c = a + b;
}

/* -------- LINEAR SEARCH O(n) -------- */
/* Space Complexity: O(1) */
int linearSearch(int arr[], int n, int key) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == key)
            return i;
    }
    return -1;
}

/* -------- SELECTION SORT O(n^2) -------- */
/* Space Complexity: O(1) */
void selectionSort(int arr[], int n) {
    int min, temp;
    for (int i = 0; i < n - 1; i++) {
        min = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min])
                min = j;
        }
        temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }
}

/* -------- BINARY SEARCH ITERATIVE O(log n) -------- */
/* Space Complexity: O(1) */
int binarySearchIterative(int arr[], int n, int key) {
    int low = 0, high = n - 1, mid;

    while (low <= high) {
        mid = (low + high) / 2;
        if (arr[mid] == key)
            return mid;
        else if (arr[mid] < key)
            low = mid + 1;
        else
            high = mid - 1;
    }
    return -1;
}

/* -------- BINARY SEARCH RECURSIVE O(log n) -------- */
/* Space Complexity: O(log n) due to recursion stack */
int binarySearchRecursive(int arr[], int low, int high, int key) {
    if (low <= high) {
        int mid = (low + high) / 2;
        if (arr[mid] == key)
            return mid;
        else if (arr[mid] < key)
            return binarySearchRecursive(arr, mid + 1, high, key);
        else
            return binarySearchRecursive(arr, low, mid - 1, key);
    }
    return -1;
}

int main() {
    int n;
    clock_t start, end;
    double time_taken;
    long iters;

    printf("Enter input size: ");
    scanf("%d", &n);

    int *arr = (int *)malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
        arr[i] = i + 1;

    int key = n;

    /* -------- CONSTANT TIME -------- */
    iters = 2000000;
    start = clock();
    for (int j = 0; j < iters; j++) {
        constantTime();
    }
    end = clock();
    time_taken = (double)(end - start) / CLOCKS_PER_SEC;
    printf("\nConstant Time O(1)       : %.6f sec", time_taken);

    /* -------- LINEAR SEARCH -------- */
    iters = 20000;
    start = clock();
    for (int j = 0; j < iters; j++) {
        linearSearch(arr, n, key);
    }
    end = clock();
    time_taken = (double)(end - start) / CLOCKS_PER_SEC;
    printf("\nLinear Search O(n)       : %.6f sec", time_taken);

    /* -------- SELECTION SORT -------- */
    iters = 100;
    start = clock();
    for (int j = 0; j < iters; j++) {
        selectionSort(arr, n);
    }
    end = clock();
    time_taken = (double)(end - start) / CLOCKS_PER_SEC;
    printf("\nSelection Sort O(n^2)    : %.6f sec", time_taken);

    /* -------- BINARY SEARCH ITERATIVE -------- */
    iters = 2000000;
    start = clock();
    for (int j = 0; j < iters; j++) {
        binarySearchIterative(arr, n, key);
    }
    end = clock();
    time_taken = (double)(end - start) / CLOCKS_PER_SEC;
    printf("\nBinary Search (Iter)     : %.6f sec", time_taken);

    /* -------- BINARY SEARCH RECURSIVE -------- */
    iters = 2000000;
    start = clock();
    for (int j = 0; j < iters; j++) {
        binarySearchRecursive(arr, 0, n - 1, key);
    }
    end = clock();
    time_taken = (double)(end - start) / CLOCKS_PER_SEC;
    printf("\nBinary Search (Rec)      : %.6f sec", time_taken);

    free(arr);
    return 0;
}