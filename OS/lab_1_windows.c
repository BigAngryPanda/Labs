#define _CRT_SECURE_NO_WARNINGS

#include "windows.h"
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

DWORD WINAPI get_stat()
{
	while (1)
	{
		MEMORYSTATUSEX stat;

		stat.dwLength = sizeof(MEMORYSTATUSEX);

		GlobalMemoryStatusEx(&stat);

		FILE* log = fopen("log.txt", "a");

		fprintf(log, "%ld\n", stat.dwMemoryLoad);

		fclose(log);

		sleep(1);
	}
}

int main()
{
	const int page_size = 1000*4*1024;

	HANDLE thread = CreateThread(NULL, 0, get_stat, NULL, 0, NULL);	
	
	srand(time(NULL));

	/*sleep(10);*/

	while (1)
	{
		LPVOID mem_ptr = VirtualAlloc(NULL, page_size, MEM_RESERVE, PAGE_READWRITE);
		mem_ptr = VirtualAlloc(mem_ptr, page_size, MEM_COMMIT, PAGE_READWRITE);
		
		/*void* mem_ptr = malloc(page_size);*/

		if (mem_ptr != NULL) memset(mem_ptr, rand(), page_size);
	}

	return 0;
}