#include <sys/sysinfo.h>
#include <stdio.h>
#include <unistd.h>

unsigned long long get_proc_num()
{
	struct sysinfo stat;

	sysinfo(&stat);

	return stat.procs; // unsigned short
}

int main()
{
	while (1)
	{

		FILE* log = fopen("log", "a");

		fprintf(log, "%lld\n", get_proc_num());

		fclose(log);

		sleep(1);
	}

	return 0;
}
