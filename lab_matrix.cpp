#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <algorithm>

struct sqr_matrix
{
	unsigned int size;
	double* data;
};

sqr_matrix init_matrix(char const* filename)
{
	sqr_matrix result;

	FILE* input_handler = fopen(filename, "r+");

	fscanf(input_handler, "%d", &result.size);

	result.data = (double*) malloc(result.size*result.size*sizeof(double));

	for (int i = 0; i < result.size*result.size; ++i)
	{
		fscanf(input_handler, "%lf", &result.data[i]);				
	}

	fclose(input_handler);

	return result;
}

sqr_matrix init_matrix(unsigned int size)
{
	double* memory = (double*) malloc(size*size*sizeof(double));

	sqr_matrix result = {size: size, data: memory};

	return result;
}

sqr_matrix init_id_matrix(unsigned int size)
{
	double* memory = (double*) calloc(size*size, sizeof(double));

	sqr_matrix result = {size: size, data: memory};

	for (int i = 0; i < size; ++i)
	{
		result.data[i*size + i] = 1.0;
	}

	return result;
}

int is_not_zero(sqr_matrix& m, unsigned int index)
{
	if (std::fpclassify(m.data[index]) != FP_ZERO)
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

void swap_rows(sqr_matrix& m, unsigned int src, unsigned int dst)
{
	for (int i = 0; i < m.size; ++i)
	{
		std::swap(m.data[src*m.size + i], m.data[dst*m.size + i]);
	}
}

unsigned int find_non_zero(sqr_matrix& m, unsigned int curr_row)
{
	for (int i = curr_row + 1; i < m.size; ++i)
	{
		if (is_not_zero(m, i*m.size + curr_row))
		{
			return i;
		}
	}

	return curr_row;
}

// Return 1 if failed
// 0 otherwise
int normalize(sqr_matrix& a, sqr_matrix& b, unsigned int row)
{
	if (!is_not_zero(a, row*a.size + row))
	{
		unsigned int new_row = find_non_zero(a, row);

		if (new_row == row)
		{
			return 1;
		}

		swap_rows(a, row, new_row);
		swap_rows(b, row, new_row);

		return 1;
	}

	double coef = 1.0/a.data[row*a.size + row];

	for (int i = 0; i < b.size; ++i)
	{
		a.data[row*a.size + i] *= coef; 
		b.data[row*b.size + i] *= coef;
	}

	return 0;
}

void mul_and_add(sqr_matrix& m, unsigned int src, unsigned int dst, double coef)
{
	for (int i = 0; i < m.size; ++i)
	{
		m.data[dst*m.size + i] += coef*m.data[src*m.size + i];
	}
}

// Return 1 if failed
// 0 otherwise
void zero_column(sqr_matrix& a, sqr_matrix& b, unsigned int row)
{
	for (unsigned int i = 0; i < a.size; ++i)
	{
		if (i == row)
		{
			continue;
		}

		double coef = -a.data[i*a.size + row];
		mul_and_add(a, row, i, coef);
		mul_and_add(b, row, i, coef);
	}
}

// Return 1 if failed
// ab = ba = I, b must be id
int inverse_matrix(sqr_matrix& a, sqr_matrix& b)
{
	for (unsigned int i = 0; i < a.size; ++i)
	{
		if (normalize(a, b, i))
		{
			return 1;
		}

		zero_column(a, b, i);
	}

	return 0;
}

double mul_vec(sqr_matrix& a, sqr_matrix& b, unsigned int row, unsigned int column)
{
	double result = 0.0;

	for (unsigned int i = 0; i < a.size; ++i)
	{
		result += a.data[row*a.size + i]*b.data[i*b.size + column];
	}

	return result;
}

sqr_matrix mul(sqr_matrix& a, sqr_matrix& b)
{
	sqr_matrix result = init_matrix(a.size);

	for (unsigned int i = 0; i < a.size; ++i)
	{
		for (unsigned int j = 0; j < b.size; ++j)
		{
			result.data[i*result.size + j] = mul_vec(a, b, i, j);
		}
	}

	return result;
}

void print_matrix(sqr_matrix& m)
{
	for (int i = 0; i < m.size; ++i)
	{
		for (int j = 0; j < m.size; ++j)
		{
			printf("%lf ", m.data[i*m.size + j]);
		}

		printf("\n");
	}
}

void write_matrix(sqr_matrix& m, char const* filename)
{
	FILE* output_handler = fopen(filename, "w+");

	fprintf(output_handler, "%d\n", m.size);

	for (unsigned int i = 0; i < m.size; ++i)
	{
		for (unsigned int j = 0; j < m.size; ++j)
		{
			fprintf(output_handler, "%lf ", m.data[i*m.size + j]);
		}

		fprintf(output_handler, "\n");
	}

	fclose(output_handler);
}

void test(sqr_matrix& b)
{
	sqr_matrix a = init_matrix("input");

	sqr_matrix c = mul(a, b);

	print_matrix(c);
}

int main(int argc, char const *argv[])
{
	sqr_matrix a = init_matrix("input");

	sqr_matrix b = init_id_matrix(a.size);

	if (inverse_matrix(a, b))
	{
		printf("Matrix is not invertible\n");
	}
	else
	{
		write_matrix(b, "output");
	}

	test(b);

	return 0;
}
