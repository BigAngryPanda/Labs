#include <iostream>
#include <ctime>
#include <cstring>

// Real time = clock ticks / clock factor
int main(int argc, char const *argv[])
{
	size_t req_size = 1024; // 1 Kb

	clock_t begin, after_malloc, total;

    std::cout << "Clock factor: " << CLOCKS_PER_SEC << std::endl;

	for (int i = 0; i < 20; ++i)
	{
		begin = clock();

		void* ptr = malloc(req_size*(1 << i));

        memset(ptr, 1, req_size*(1 << i));

		after_malloc = clock();

		std::cout << req_size*(1 << i) << " " << after_malloc - begin; 

		free(ptr);

		total = clock();

		std::cout << " " << total - begin << std::endl;
	}

	return 0;
}
