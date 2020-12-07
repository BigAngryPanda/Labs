#include <pthread.h>
#include <sys/sysinfo.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>

void* get_stat()
{
	while (1)
	{
		struct sysinfo stat;

		sysinfo(&stat);

		long long total_mem = stat.totalram + stat.totalswap;
		total_mem *= stat.mem_unit;

		long long used_mem = stat.totalram - stat.freeram;
		used_mem += stat.totalswap - stat.freeswap;
		used_mem *= stat.mem_unit;

		FILE* log = fopen("log", "a");

		fprintf(log, "%lld\n", used_mem*100/total_mem);

		fclose(log);

		sleep(1);
	}
}

int main()
{
    const int page_size = 100*4*1024;    

    pthread_t t;

    pthread_create(&t, NULL, get_stat, NULL);

    while (1)
    {
        char* data = (char*) mmap(NULL, page_size*sizeof(char), PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, 0, 0);

        if (data != MAP_FAILED) memset(data, 0, page_size*sizeof(char));

        sleep(0.1);
    }

    return 0;
}
