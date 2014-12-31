#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[])
{
    if (argc != 2)
    {
        return 1;
    }

    char* mapsPath = (char*) malloc((12 + strlen(argv[1])) * sizeof(char));
    char* memPath = (char*) malloc((11 + strlen(argv[1])) * sizeof(char));

    sprintf(mapsPath, "/proc/%s/maps", argv[1]);
    FILE* pMapsFile = fopen(mapsPath, "r");

    sprintf(memPath, "/proc/%s/mem", argv[1]);
    FILE* pMemFile = fopen(memPath, "r+");

    FILE* pStdoutFile = fopen("/dev/stdout", "w");

    size_t start, end;
    char permr, permw;

    while (fscanf(pMapsFile, "%x-%x %c%c\n", &start, &end, &permr, &permw))
    {
        fseek(pMemFile, start, SEEK_SET);
        size_t chunkSize = end - start;
        char* chunk = (char*) malloc(chunkSize * sizeof(char));

        if (permw == 'w')
        {
            size_t i;
            for (i = 0; i < chunkSize; i++)
            {
                chunk[i] = 'a';
            }

            fwrite(chunk, sizeof(char), chunkSize, pMemFile);
        }

        if (permr == 'r')
        {
            fread(chunk, sizeof(char), chunkSize, pMemFile);
            fwrite(chunk, sizeof(char), chunkSize, pStdoutFile);
        }

    }

    fclose(pStdoutFile);
    fclose(pMemFile);
    fclose(pMapsFile);

    return 0;
}
