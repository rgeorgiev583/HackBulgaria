#include <stdio.h>
#include <unistd.h>
#define MAX_SIZE 1000

void forkw()
{
    pid_t pid = fork();

    if (pid == -1)
    {
        perror("error: fork failed");
        exit(1);
    }
    else if (pid != 0)
    {
        int status;
        (void) waitpid(pid, &status, 0);
    }
}

int main(int argc, char** argv)
{
    char cmd[MAX_SIZE];
    
    if (argc < 2)
    {
        scanf("%s\n", cmd);
    }

    if (!strcmp(cmd, "fork"))
    {
        forkw();
    }

    while (strcmp(cmd, "exec"))
    {
        scanf("%s\n", cmd);
    }

    forkw();
    execvp(cmd, NULL);
 
    return 0;
}
