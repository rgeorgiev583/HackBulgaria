#include <stdio.h>
#define MAX_SIZE 100

int main(int argc, char* argv[])
{
    if (argc != 2)
    {
        return 1;
    }
    
    char word[MAX_SIZE];

    if (scanf("%s", word) != 1)
    {
        return 2;
    }

    FILE* pFile;
    pFile = fopen(argv[1], "w"); 
    
    if (pFile == NULL)
    {
        puts("ERROR: File doesn't exist / couldn't be opened!");
        return 3;
    }

    if (fputs(word, pFile) == EOF)
    {
        puts("ERROR: File cannot be written to!");
    }

    if (fclose(pFile) == EOF)
    {
        puts("ERROR: File could not be closed!");
    }

    return 0;
}

