#include <unistd.h>

int main()
{
	while (1)
	{
	    fork();
	    sleep(0.1);
	}    

	return 0;
}
