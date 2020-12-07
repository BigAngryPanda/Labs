#include <windows.h>
#include <tlhelp32.h>
#include <tchar.h>
#include <stdio.h>
#include <unistd.h>

unsigned long long GetProcessList()
{
	HANDLE hProcessSnap;
	HANDLE hProcess;
	PROCESSENTRY32 pe32;
	DWORD dwPriorityClass;

	// Take a snapshot of all processes in the system.
	hProcessSnap = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0);

	// Set the size of the structure before using it.
	pe32.dwSize = sizeof(PROCESSENTRY32);

	// Retrieve information about the first process
	!Process32First(hProcessSnap, &pe32);
	
	unsigned long long i = 0;

	// Now walk the snapshot of processes, and
	// display information about each process in turn
	do
	{
		++i;
	} 
	while (Process32Next(hProcessSnap, &pe32) );

	CloseHandle(hProcessSnap);

	return i;
}

int main()
{
	while (1)
	{

		FILE* log = fopen("log.txt", "a");

		fprintf(log, "%lld\n", GetProcessList());

		fclose(log);

		sleep(1);
	}

  return 0;
}