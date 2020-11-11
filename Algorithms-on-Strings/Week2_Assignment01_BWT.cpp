#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

std::string burrows_wheeler_transform(const std::string &text)
{
	int n = text.length();

	std::vector<std::string> rotation(n);

	rotation[0] = text;

	for (size_t i = 1; i < n; i++)
	{
		std::string str = rotation[i - 1].substr(1, n);
		str.push_back(rotation[i - 1][0]);
		rotation[i] = str;
	}

	std::sort(rotation.begin(), rotation.end());

	std::string output(n, '$');

	for (size_t i = 0; i < n; i++)
	{
		output[i] = rotation[i].back();
	}

	return output;
}

int main()
{
	std::string text;
	std::cin >> text;

	std::cout << burrows_wheeler_transform(text);
}