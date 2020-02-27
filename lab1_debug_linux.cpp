#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdint.h>
#include <string.h>
#include <sys/time.h>

int are_valid_args(int argc)
{
	if (argc != 3)
	{
		return 1;
	}

	return 0;
}

/*
	NULL if failed
	You must call free() to prevent memory leak
*/
char* read_file(const char* file_name, size_t* input_length)
{	
	FILE* source_handler = fopen(file_name, "rb");

	if (source_handler == NULL)
	{
	    return NULL;
	}

	// Sets the file position indicator for the file stream
	fseek(source_handler, 0, SEEK_END);

	// Length of file
	// Returns the current value of the file position indicator for the file stream
	size_t length = ftell(source_handler);

	// Return file position indicator to begining
	// Same as rewind()
	fseek(source_handler, 0, SEEK_SET);

	// Hope it will work on Windows
	char* buffer = (char*) malloc((length + 3)*(sizeof(char)));

	if (buffer == NULL)
	{
		return NULL;
	}

	// Read file
	size_t result = fread(buffer, sizeof(char), length, source_handler);

	fclose(source_handler);

	if (length != result)
	{
		return NULL;
	}

	buffer[length] = '\r';
	buffer[length+1] = '\n';
	buffer[length+2] = 0;

	*input_length = length + 3;

	return buffer;
}

int read_int(char* source, size_t* source_pointer, long int* output)
{
	int sign = 1;

	if (source[*source_pointer] == '-')
	{
		sign = -1;
		++*source_pointer;
	}

	if (!isdigit(source[*source_pointer]))
	{
		return 1;
	}

	*output = (long int) (source[*source_pointer] - '0');

	++*source_pointer;

	while (isdigit(source[*source_pointer]))
	{
		*output = (*output)*10 + ((long int) (source[*source_pointer] - '0'));

		// Amazing syntax
		++*source_pointer;
	}

	*output = (*output)*sign;

	return 0;
}

// 1 if nothing to parse
int read_num_of_lines(char* source, size_t* source_pointer, size_t* output)
{
	long int temp;

	if (read_int(source, source_pointer, &temp) || temp < 0)
	{
		return 1;
	}

	*output = (size_t) temp;

	return 0;
}

// 1 if failed
int check_end_of_line(char* source, size_t* source_pointer)
{
	if (source[*source_pointer] == '\r')
	{
		++*source_pointer;
	}

	if (source[*source_pointer] == '\n')
	{
		++*source_pointer;
	}
	else
	{
		return 1;
	}

	return 0;
}

// Return 1 if failed
// Checks space after each key
int check_space(char* source, size_t* source_pointer)
{
	if (source[*source_pointer] == ' ')
	{
		++*source_pointer;
	}
	else
	{
		return 1;
	}

	if (source[*source_pointer] != ' ' 
		&& source[*source_pointer] != '\t'
		&& source[*source_pointer] != '\r'
		&& source[*source_pointer] != '\n')
	{
		++*source_pointer;
	}
	else
	{
		return 1;
	}

	return 0;
}

void skip_until(char* source, size_t* source_pointer)
{
	while (source[*source_pointer] != '\n')
	{
		++*source_pointer;
	}
}

// Return 1 if failed
int read_line(char* source, size_t* source_pointer, long int* key)
{
	if (read_int(source, source_pointer, key))
	{
		return 1;
	}

	if (check_space(source, source_pointer))
	{
		return 1;
	}

	skip_until(source, source_pointer);

	return 0;
}

/*
	1 if failed
	You must call free() to prevent memory leak
*/
int parse(char* source, int** kp, size_t** vp, size_t** op, size_t* length)
{
	size_t source_pointer = 0;

	// Parsing num of lines
	if (read_num_of_lines(source, &source_pointer, length))
	{
		return 1;
	}

	if (check_end_of_line(source, &source_pointer))
	{
		return 1;
	}

	size_t* data = (size_t*) malloc((*length)*sizeof(int) + 2*(*length)*sizeof(size_t));

	int* keys = (int*) &data[0];
	size_t* values = &data[(*length)/2];
	size_t* offsets = &data[(*length) + (*length)/2];

	*kp = keys;
	*vp = values;
	*op = offsets;

	size_t line_counter = 0;

	long int temp;

	while (source[source_pointer] && line_counter < (*length))
	{
		values[line_counter] = source_pointer;

		if (read_line(source, &source_pointer, &temp))
		{
			return 1;
		}

		keys[line_counter] = (int) temp;

		++source_pointer;

		offsets[line_counter] = source_pointer - values[line_counter];

		++line_counter;
	}

	if (line_counter != (*length))
	{
		return 1;	
	}

	return 0;
}

void swap_int(int* i1, int* i2)
{
	int temp;

	temp = *i1;

	*i1 = *i2;

	*i2 = temp;
}

void swap_size_t(size_t* st1, size_t* st2)
{
	size_t temp;

	temp = *st1;

	*st1 = *st2;

	*st2 = temp;
}

void push_pair(size_t* stack, size_t* sp, size_t f_elem, size_t s_elem)
{
	stack[*sp] = f_elem;
	++*sp;
	stack[*sp] = s_elem;
	++*sp;
}

void pop(size_t* stack, size_t* sp)
{
	--*sp;
	--*sp;
}

// naive aproach
// Hoare partition scheme
void qsort(int* keys, size_t* values, size_t* offsets, size_t length)
{
	if (!length)
	{
		return;
	}

	size_t stack[128];
	size_t sp = 0;  // point to firs free size_t

	size_t i, j, left_index, right_index;

	push_pair(stack, &sp, 0, length-1);

	while (sp)
	{	
		right_index = stack[sp-1];
		left_index = stack[sp-2];

		i = left_index-1;
		j = right_index+1;

		pop(stack, &sp);

		int pivot = keys[(left_index + right_index)/2];

		do
		{
			do
			{
				++i;
			}
			while(keys[i] < pivot);

			do
			{
				--j;
			}
			while (keys[j] > pivot);

			if (i < j)
			{
				swap_int(&keys[i], &keys[j]);
				swap_size_t(&values[i], &values[j]);
				swap_size_t(&offsets[i], &offsets[j]);
			}
			else
			{
				break;
			}
		}
		while (i < j);

		// Right branch
		if (j != right_index)
		{
			push_pair(stack, &sp, j+1, right_index);
		}

		// Left branch
		if (j != left_index)
		{
			push_pair(stack, &sp, left_index, j);
		}
	}
}

char* postprocessing(char* source, size_t sl, size_t* values, size_t* offsets, size_t length)
{
	char* result = &source[sl];

	size_t rp = 0;

	while (source[rp] && source[rp] != '\n')
	{
		++rp;
	}

	++rp;
	memmove(&result[0], &source[0], rp);

	for (size_t i = 0; i < length; ++i)
	{
		memmove(&result[rp], &source[values[i]], offsets[i]);

		rp = rp + offsets[i];
	}

	return result;
}

// Return 1 if failed
int write_file(char* output, size_t ol, size_t length, const char* file_name)
{
	FILE* output_handler = fopen(file_name, "wb");

	if (output_handler == NULL)
	{
	    return 1;
	}

	fwrite(output, sizeof(char), ol-3, output_handler);

	fclose(output_handler);

	return 0;
}

int naive_write_file(char* output, size_t output_length, size_t* values, size_t* offsets, size_t length, const char* file_name)
{
	FILE* output_handler = fopen(file_name, "wb");

	if (output_handler == NULL)
	{
	    return 1;
	}

	if (length)
	{
		fprintf(output_handler, "%ld\r\n", length);

		for (size_t i = 0; i < length-1; ++i)
		{
			fwrite(&output[values[i]], sizeof(char), offsets[i], output_handler);
		}

		fwrite(&output[values[length-1]], sizeof(char), offsets[length-1] - 2, output_handler);
	}
	else
	{
		fprintf(output_handler, "0");
	}

	fclose(output_handler);

	return 0;
}

int main(int argc, char const *argv[])
{
	/*if (are_valid_args(argc))
	{
		return 1;
	}*/

//--------------------------------------------------------------------------------------------
	struct timeval T1, T2;

	gettimeofday(&T1, NULL);

	size_t input_length;

	char* input = read_file("tests/zero_test", &input_length);

	gettimeofday(&T2, NULL);

	long int delta_ms = T2.tv_sec - T1.tv_sec;

	if (input == NULL)
	{
		return 2;
	}

	printf("Reading stage: %ld sec.\n", T2.tv_sec - T1.tv_sec);

//--------------------------------------------------------------------------------------------

	gettimeofday(&T1, NULL);

	size_t length;

	int* keys;
	size_t* values;
	size_t* offsets;

	if (parse(input, &keys, &values, &offsets, &length))
	{
		return 3;
	}

	gettimeofday(&T2, NULL);

	delta_ms += T2.tv_sec - T1.tv_sec;

	printf("Parsing stage: %ld sec.\n", T2.tv_sec - T1.tv_sec);

//--------------------------------------------------------------------------------------------

	gettimeofday(&T1, NULL);

	qsort(keys, values, offsets, length);

	gettimeofday(&T2, NULL);

	delta_ms += T2.tv_sec - T1.tv_sec;

	printf("Sorting stage: %ld sec.\n", T2.tv_sec - T1.tv_sec);


//--------------------------------------------------------------------------------------------
/*
	gettimeofday(&T1, NULL);

	char* output = postprocessing(input, input_length, values, offsets, length);

	gettimeofday(&T2, NULL);

	delta_ms += T2.tv_sec - T1.tv_sec;

	printf("Postprocessing stage: %ld sec.\n", T2.tv_sec - T1.tv_sec);

	free(keys);

//--------------------------------------------------------------------------------------------

	gettimeofday(&T1, NULL);

	if (write_file(input, input_length, length, "output"))
	{
		return 2;
	}

	gettimeofday(&T2, NULL);

	delta_ms += T2.tv_sec - T1.tv_sec;

	printf("Writing stage: %ld sec.\n", T2.tv_sec - T1.tv_sec);
*/
//--------------------------------------------------------------------------------------------

	gettimeofday(&T1, NULL);

	if (naive_write_file(input, input_length, values, offsets, length, "output"))
	{
		return 2;
	}

	gettimeofday(&T2, NULL);

	delta_ms += T2.tv_sec - T1.tv_sec;

	printf("Writing stage: %ld sec.\n", T2.tv_sec - T1.tv_sec);

	printf("Total time: %ld sec.\n", delta_ms);

	// validate args

	// read file

	// preprocessing?

	// sort

	// postprocessing?

	// write file
	return 0;
}
