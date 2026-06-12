#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NUM_THREADS          5
#define INCREMENT_PER_THREAD 100000

/* shared global counter */
int counter = 0;

/* mutex to protect the critical section */
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

void *increment(void *arg) {
    for (int i = 0; i < INCREMENT_PER_THREAD; i++) {

        pthread_mutex_lock(&lock);     /* LOCK — only one thread enters at a time */
        counter++;                     /* critical section — now safe             */
        pthread_mutex_unlock(&lock);   /* UNLOCK — next thread can now enter      */
    }
    return NULL;
}

int main(void) {
    pthread_t threads[NUM_THREADS];

    printf("=== WITH MUTEX (Correct Output) ===\n");
    printf("Threads      : %d\n", NUM_THREADS);
    printf("Increments   : %d per thread\n", INCREMENT_PER_THREAD);
    printf("Expected     : %d\n", NUM_THREADS * INCREMENT_PER_THREAD);

    /* create all threads */
    for (int i = 0; i < NUM_THREADS; i++) {
        if (pthread_create(&threads[i], NULL, increment, NULL) != 0) {
            fprintf(stderr, "ERROR: pthread_create failed.\n");
            return 1;
        }
    }

    /* wait for all threads to finish */
    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Actual result: %d\n", counter);
    printf("Difference   : %d\n", (NUM_THREADS * INCREMENT_PER_THREAD) - counter);
    printf("===================================\n");
    return 0;
}