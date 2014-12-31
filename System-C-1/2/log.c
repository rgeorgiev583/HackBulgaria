#include <syslog.h>

int main()
{
    openlog("helloworld", 0, 0);
    syslog(LOG_DEBUG, "Disclaimer: just playing with system logging.");
    syslog(LOG_INFO, "Hello!");
    syslog(LOG_WARNING, "Houston, we have a problem.");
    syslog(LOG_PERROR, "Oh shit! Something just got fucked up!");
    closelog();
    return 0;
}
