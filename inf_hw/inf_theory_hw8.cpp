#include <fstream>
#include <vector>
#include <iostream>
#include <sstream>
#include <iterator>
#include <cmath>

typedef std::vector<long double> vec;
typedef std::vector<vec> matrix;

std::string vec_to_string(const vec& v)
{
	std::string result;

	for (long double elem : v)
	{
		result += std::to_string(elem) + std::string(" ");
	}

	return result;
}

std::string matrix_to_string(const matrix& m)
{
	std::string result;

	for (const std::vector<long double>& vec_ref : m)
	{
		result += vec_to_string(vec_ref);
		result += std::string("\n");
	}

	return result;
}

struct SystemConfig
{
	size_t n; // num of packets
	size_t m; // num of workers
	matrix probs; // P_nm
	vec mu; // 1/ms

	std::string to_string() const
	{
		std::string result = std::string("N: ") + std::to_string(n) + std::string("\nM: ") + std::to_string(m);

		result += "\nMatrix P_nm:\n";

		for (const std::vector<long double>& vec_ref : probs)
		{
			result += vec_to_string(vec_ref);
			result += std::string("\n");
		}
		result += std::string("\nmu: ");
		result += vec_to_string(mu);
		result += std::string("\n");		

		return result;
	}
};

const char* cfg_name = "configuration";

vec read_vec(std::ifstream& file)
{
	std::vector<long double> result;

	std::string line;
	std::getline(file, line);

	std::istringstream iss(line);

	std::vector<std::string> tokens{std::istream_iterator<std::string>{iss}, std::istream_iterator<std::string>{}};

	for (const std::string& s : tokens)
	{
		result.push_back(std::stold(s));
	}
	
	return result;
}

SystemConfig read_config(const char* cfg_file)
{
	SystemConfig result;
	std::ifstream file;

	try
	{
		file.open(cfg_file);
	}
	catch(...)
	{
		throw std::runtime_error("Cannot open config file");
	}

	std::string line;

	// read N
	std::getline(file, line);

	result.n = std::stoll(line);

	// read M
	std::getline(file, line);

	result.m = std::stoll(line);

	result.probs.push_back(read_vec(file));

	if (result.probs[0].size() != result.m)
	{
		throw std::runtime_error("Wrong probability vector size, expect " + std::to_string(result.m) + " found " + std::to_string(result.probs[0].size()));
	}

	vec temp(result.m, 0.0);
	temp[0] = 1.0;

	for (int i = 1; i < result.n; ++i)
	{
		result.probs.push_back(temp);
	}

	result.mu = read_vec(file);

	if (result.mu.size() != result.m)
	{
		throw std::runtime_error("Wrong intensity vector size, expect " + std::to_string(result.m) + " found " + std::to_string(result.mu.size()));
	}

	return result;
}

vec calc_vec_x(const SystemConfig& cfg, const vec& mu)
{
	vec result(cfg.probs.size());

	result[0] = mu[0]/mu[0];

	for (int i = 1; i < cfg.probs.size(); ++i)
	{
		result[i] = cfg.probs[0][i]*mu[0]/mu[i];
	}

	return result;
}

matrix calc_matrix_g(const SystemConfig& cfg, const vec& x)
{
	matrix result(cfg.n + 1, vec(cfg.m));

	for (int i = 0; i < cfg.m; ++i)
	{
		result[0][i] = 1.0;
	}

	for (int i = 0; i < cfg.n + 1; ++i)
	{
		result[i][0] = std::pow(x[0], i);
	}

	for (int i = 1; i < cfg.n + 1; ++i)
	{
		for (int j = 1; j < cfg.m; ++j)
		{
			result[i][j] = result[i][j-1] + x[j]*result[i-1][j];
		}
	}

	return result;
}

vec calc_vec_prob(const matrix& g, long double x0)
{
	vec result(g.size() - 1);

	for (int i = 1; i < g.size(); ++i)
	{
		result[i-1] = x0*g[i-1].back()/g[i].back();
	}

	return result;
}

long double calc_l(const matrix& g, long double x0)
{
	long double result = 0.0;

	for (int i = 0; i < g.size() - 1; ++i)
	{
		result += std::pow(x0, i)*g[i].back();
	}

	result /= g.back().back();

	return result;
}

vec calc_vec_t(const SystemConfig& cfg)
{
	vec result(cfg.mu.size());

	for (int i = 0; i < result.size(); ++i)
	{
		result[i] = 1/cfg.mu[i];
	}

	return result;
}

int main(int argc, char const *argv[])
{
	SystemConfig cfg;

	try
	{
		cfg = read_config(cfg_name);
	}
	catch(std::runtime_error e)
	{
		std::cout << e.what() << std::endl;

		return 1;
	}

	std::cout << cfg.to_string() << std::endl;

	vec x = calc_vec_x(cfg, cfg.mu);

	std::cout << "Vector x: " << vec_to_string(x) << "\n" << std::endl;

	matrix g = calc_matrix_g(cfg, x);

	std::cout << "Matrix G:\n" << matrix_to_string(g) << "\n" << std::endl;

	vec s_probs = calc_vec_prob(g, x[0]);

	std::cout << "State probabilities: " << vec_to_string(s_probs) << "\n" << std::endl;

	long double l = calc_l(g, x[0]);

	std::cout << "Avg. queue length: " << l << "\n" << std::endl;

	vec t = calc_vec_t(cfg);

	std::cout << "Waiting time, ms: " << vec_to_string(t) << "\n" << std::endl;

	return 0;
}