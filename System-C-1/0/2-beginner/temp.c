#include <stdio.h>
#include <stdlib.h>
const size_t BUFFER_SIZE = 1023; 

int main(int argc, char* argv[])
{
    if (argc != 3)
    {
        return 1;
    }

    FILE* pFileSrc = fopen(argv[1], "r");
    FILE* pFileDest = fopen(argv[2], "w");

    if (pFileSrc == NULL)
    {
        puts("ERROR: Source file doesn't exist / couldn't be opened!");
        return 2;
    }
    
    if (pFileDest == NULL)
    {
        puts("ERROR: Destination file doesn't exist / couldn't be opened!");
        return 2;
    }

    char* buffer = (char*) malloc((BUFFER_SIZE + 1) * sizeof(char));
    
    if (buffer == NULL)
    {
        puts("ERROR: Could not allocate enough free memory for buffer!");
        return 3;
    }
    
    while (!feof(pFile))
    {
        fread(buffer, sizeof(char), BUFFER_SIZE, pFile);

        if (ferror(pFile))
        {
            puts("ERROR: Could not read from file!");
            return 4;
        }
        
        buffer[BUFFER_SIZE] = 0;
        puts(buffer);
    }
    
    if (fclose(pFile) == EOF)
    {
        puts("ERROR: Could not close file!");
        return 5;
    }

    free(buffer);

    return 0;
}

