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

    size_t errcode = 0;
    char* buffer = (char*) malloc((BUFFER_SIZE + 1) * sizeof(char));
    
    if (buffer == NULL)
    {
        puts("ERROR: Could not allocate enough free memory for buffer!");
        errcode = 5;
        goto cleanup;
    }
    
    while (size_t chunkSize = fread(buffer, sizeof(char), BUFFER_SIZE, pFileSrc))
    {
        if (ferror(pFileSrc))
        {
            puts("ERROR: Could not read from source file!");
            errcode = 6;
        }
        
        if (fwrite(buffer, sizeof(char), chunkSize + 1, pFileDest) != BUFFER_SIZE + 1)
        {
            puts("ERROR: Could not write to destination file!");
            errcode = 7;
        }

        clearerr(pFileSrc);
    }

cleanup:
    if (fclose(pFileSrc) == EOF)
    {
        puts("ERROR: Could not close source file!");
        return 6;
    }
  
    if (fclose(pFileDest) == EOF)
    {
        puts("ERROR: Could not close destination file!");
        return 6;
    }

    free(buffer);

    return errcode;
}

