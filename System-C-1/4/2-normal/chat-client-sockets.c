#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h> 
#include <pthread.h>
#define MAX_LENGTH 65536

void* writer(void* arg)
{
    int n = 0, sockfd = *((int*) arg);
    char* recvBuff = (char*) malloc(MAX_LENGTH + 40);
    memset(recvBuff, '0', sizeof(recvBuff));

    while ( (n = read(sockfd, recvBuff, sizeof(recvBuff) - 1)) > 0 )
    {
        recvBuff[n] = 0;

        if (puts(recvBuff) == EOF)
        {
            printf("\n Error : puts error\n");
        }
    } 

    if (n < 0)
    {
        printf("\n Read error \n");
    }
}

int main(int argc, char *argv[])
{
    int sockfd = 0;
    struct sockaddr_in serv_addr; 

    if(argc != 2)
    {
        printf("\n Usage: %s <ip of server> \n",argv[0]);
        return 1;
    } 

    if((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        printf("\n Error : Could not create socket \n");
        return 1;
    } 

    memset(&serv_addr, '0', sizeof(serv_addr)); 

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(5000);

    if (inet_pton(AF_INET, argv[1], &serv_addr.sin_addr) <= 0)
    {
        printf("\n inet_pton error occured\n");
        return 1;
    }

    if (connect(sockfd, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) < 0)
    {
        printf("\n Error : Connect Failed \n");
        return 1;
    } 

    pthread_t thread;
    pthread_create(&thread, NULL, writer, (void*) &sockfd);

    char* sendBuff = (char*) malloc(MAX_LENGTH);
    memset(sendBuff, '0', sizeof(sendBuff));

    while(1)
    {
        //ticks = time(NULL);
        //snprintf(sendBuff, sizeof(sendBuff), "%.24s\r\n", ctime(&ticks));
        fgets(sendBuff, MAX_LENGTH, stdin);
        write(sockfd, sendBuff, strlen(sendBuff));
        close(sockfd);
        sleep(1);
    }

    return 0;
}

