#include <pthread.h>
#define NUM_THREADS 5

void *task_code(void *argument)
{
    pthread_t threads[NUM_THREADS];
    pthread_create(&threads[i], NULL, task_code, NULL);
    return NULL;
}

int main()
{
    pthread_t threads[NUM_THREADS];
    pthread_create(&threads[i], NULL, task_code, NULL);
}
