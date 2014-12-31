#include <unistd.h>

void bomb()
{
    fork();
    bomb();
    bomb();
}

int main()
{
    bomb();
    return 0;
}
