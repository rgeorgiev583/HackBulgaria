#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <time.h>
#include <pthread.h>
#define MAX_LENGTH 65536

struct thread_args
{
    int connfd, clid;
};

void* reader(void* arg)
{
    char* recvBuff = (char*) malloc(MAX_LENGTH + 25);
    memset(recvBuff, 0, sizeof(recvBuff));
    struct thread_args* args = (struct thread_args*) arg;
    int n = 0, connfd = args->connfd, clid = args->clid;
    time_t ticks;

    while ( (n = read(connfd, recvBuff, MAX_LENGTH + 25)) > 0 )
    {
        ticks = time(NULL);
        recvBuff[n] = 0;

        printf("%sClient %d said: %s", ctime(&ticks), clid, recvBuff);

        if (ferror())
        {
            printf("\n Error : puts error\n");
        }
    } 

    if (n < 0)
    {
        printf("\n Read error \n");
    }

    free(recvBuff);
}

void* writer(void* arg)
{
    pthread_t reader_thread;
    struct thread_args* args = (struct thread_args*) arg;
    int connfd = args->connfd, clid = args->clid;
    pthread_create(&reader_thread, NULL, reader, (void*) &connfd);
    time_t ticks = time(NULL);
    char* sendBuff = (char*) malloc(MAX_LENGTH + 40);
    memset(sendBuff, 0, sizeof(sendBuff));
    char* inputBuff = (char*) malloc(MAX_LENGTH);
    memset(inputBuff, 0, sizeof(inputBuff));
    printf("Writing to client #%d: ", clid);
    fgets(inputBuff, MAX_LENGTH, stdin);
    snprintf(sendBuff, sizeof(sendBuff), "%sServer said: %s\n\n", ctime(&ticks), inputBuff);
    write(connfd, sendBuff, strlen(sendBuff));
    free(inputBuff);
    free(sendBuff);
    pthread_join(reader_thread, NULL);
    close(connfd);
}

int main(int argc, char *argv[])
{
    int listenfd = 0, connfd = 0;
    struct sockaddr_in serv_addr; 

    listenfd = socket(AF_INET, SOCK_STREAM, 0);
    memset(&serv_addr, 0, sizeof(serv_addr));
    
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(5000); 

    bind(listenfd, (struct sockaddr*) &serv_addr, sizeof(serv_addr)); 

    listen(listenfd, 10);
    pthread_t thread;
    struct thread_args writer_args;
    int i = 0;
    
    while (1)
    {
        connfd = accept(listenfd, (struct sockaddr*) NULL, NULL);
        writer_args.connfd = connfd;
        writer_args.clid = i;
        pthread_create(&thread, NULL, writer, (void*) &writer_args);
        i++;
    }
}

