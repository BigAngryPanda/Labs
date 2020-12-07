#include <unistd.h>
#include "windows.h"

int main()
{
	while (1)
	{
		STARTUPINFO s_info;
		ZeroMemory(&s_info, sizeof(STARTUPINFO));
		PROCESS_INFORMATION p_info;
		CreateProcess("lab.exe", NULL, NULL, NULL, FALSE, CREATE_NEW_PROCESS_GROUP, NULL, NULL, &s_info, &p_info);

		sleep(0.1);
	}

	return 0;
}