#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

int main()
{
	std::string text;
	std::cin >> text;

	std::vector<std::pair<std::string, int>> suffixes;
	suffixes.reserve(text.length());

	for (size_t i = 0; i < text.length(); i++)
	{
		suffixes.push_back(std::pair<std::string, int>(text.substr(i, std::string::npos), i));
	}

	std::sort(suffixes.begin(), suffixes.end());

	for (size_t i = 0; i < suffixes.size(); i++)
	{
		std::cout << suffixes[i].second << ' ';
	}
}