#include <stdio.h>
#define MAX_SIZE 100

int main()
{
    char word[MAX_SIZE];

    if (scanf("%s", word) != 1)
    {
        return 1;
    }

    printf("%s\n", word);

    return 0;
}

