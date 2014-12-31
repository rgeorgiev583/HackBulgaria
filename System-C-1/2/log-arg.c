#include <syslog.h>

int main(int argc, char* argv[])
{
    char* message;
    int level;

    if (argc == 2)
    {
        message = argv[1];
    }
    else if (argc == 3)
    {
        level = atoi(argv[1]);
        message = argv[2];
    }

    openlog("helloworld", 0, level);
    syslog(LOG_INFO, message);
    closelog();
    return 0;
}
