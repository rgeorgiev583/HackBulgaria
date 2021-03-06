#include <stdio.h>

int main(int argc, char* argv[])
{
    if (argc != 2)
    {
        return 1;
    }

    char* file;
    FILE* pFile = fopen(argv[1], "r");

    if (pFile == NULL)
    {
        puts("ERROR: File doesn't exist / couldn't be opened!");
        return 2;
    }

    if (fseek(file, 0, SEEK_END))
    {
        puts("ERROR: Could not determine EOF!");
        return 3;
    }

    size_t filesize = ftell(file);

    if (filesize == -1)
    {
        puts("ERROR: Could not determine file size!");
        return 4;
    }
    
    file = (char*) calloc(filesize + 1, sizeof(char));
    fread(file, sizeof(char), filesize, pFile);
    file[filesize] = 0;
    fclose(pFile);
    puts(file);

    return 0;
}
