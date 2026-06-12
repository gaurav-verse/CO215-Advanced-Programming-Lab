#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ── 1. StringBuffer struct ─────────────────────────────────────────────── */
typedef struct {
    char   *data;       /* heap-allocated character buffer                  */
    size_t  length;     /* current number of characters (excluding \0)      */
    size_t  capacity;   /* total allocated bytes including space for \0     */
} StringBuffer;


/* ── 2. sb_init ─────────────────────────────────────────────────────────── */
StringBuffer *sb_init(size_t initial_capacity) {

    /* allocate the struct itself on the heap */
    StringBuffer *sb = (StringBuffer *)malloc(sizeof(StringBuffer));
    if (sb == NULL) {
        fprintf(stderr, "ERROR: malloc failed for StringBuffer struct.\n");
        return NULL;
    }

    /* allocate the internal data buffer */
    sb->data = (char *)malloc(initial_capacity);
    if (sb->data == NULL) {
        fprintf(stderr, "ERROR: malloc failed for data buffer.\n");
        free(sb);           /* free struct before returning NULL */
        return NULL;
    }

    sb->data[0]  = '\0';           /* start as empty string    */
    sb->length   = 0;
    sb->capacity = initial_capacity;

    printf("[sb_init]  Allocated buffer. Capacity: %zu bytes\n", sb->capacity);
    return sb;
}


/* ── 3. sb_append ───────────────────────────────────────────────────────── */
void sb_append(StringBuffer *sb, const char *str) {
    if (sb == NULL || str == NULL) return;

    size_t str_len   = strlen(str);
    size_t needed    = sb->length + str_len + 1;   /* +1 for null terminator */

    /* ── 4. grow with realloc if needed ─────────────────────────────────── */
    if (needed > sb->capacity) {

        /* double capacity until it fits */
        size_t new_capacity = sb->capacity;
        while (new_capacity < needed) {
            new_capacity *= 2;
        }

        /* SAFE realloc: use a temp pointer so original is NOT lost on failure */
        char *temp = (char *)realloc(sb->data, new_capacity);
        if (temp == NULL) {
            fprintf(stderr, "ERROR: realloc failed. Buffer unchanged.\n");
            return;                /* original sb->data still valid */
        }

        sb->data     = temp;       /* only update pointer on success          */
        sb->capacity = new_capacity;
        printf("[sb_append] Buffer grew! New capacity: %zu bytes\n", new_capacity);
    }

    /* copy new string onto the end */
    memcpy(sb->data + sb->length, str, str_len + 1);   /* +1 copies the \0  */
    sb->length += str_len;
}


/* ── 5. sb_free (destructor) ─────────────────────────────────────────────── */
void sb_free(StringBuffer *sb) {
    if (sb == NULL) return;
    free(sb->data);     /* free the internal character buffer first          */
    sb->data     = NULL;
    sb->length   = 0;
    sb->capacity = 0;
    free(sb);           /* free the struct itself                             */
    printf("[sb_free]  All memory freed.\n");
}


/* ── main: demonstrate buffer growing at least twice ────────────────────── */
int main(void) {

    printf("=== Dynamic String Buffer Demo ===\n\n");

    /* start with a tiny capacity of 8 bytes so we force growth quickly */
    StringBuffer *sb = sb_init(8);
    if (sb == NULL) return 1;

    printf("\n--- Append 1: \"Hello\" ---\n");
    sb_append(sb, "Hello");
    printf("  Content : \"%s\"\n", sb->data);
    printf("  Length  : %zu  |  Capacity: %zu\n", sb->length, sb->capacity);

    printf("\n--- Append 2: \", World!\" (forces 1st growth) ---\n");
    sb_append(sb, ", World!");
    printf("  Content : \"%s\"\n", sb->data);
    printf("  Length  : %zu  |  Capacity: %zu\n", sb->length, sb->capacity);

    printf("\n--- Append 3: \" This is a dynamic buffer.\" (forces 2nd growth) ---\n");
    sb_append(sb, " This is a dynamic buffer.");
    printf("  Content : \"%s\"\n", sb->data);
    printf("  Length  : %zu  |  Capacity: %zu\n", sb->length, sb->capacity);

    printf("\n--- Append 4: \" It grows automatically!\" ---\n");
    sb_append(sb, " It grows automatically!");
    printf("  Content : \"%s\"\n", sb->data);
    printf("  Length  : %zu  |  Capacity: %zu\n", sb->length, sb->capacity);

    printf("\n");
    sb_free(sb);

    printf("\n=== Program ended. No memory leaks. ===\n");
    return 0;
}
