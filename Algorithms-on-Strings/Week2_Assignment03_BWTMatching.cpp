#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

int char_index(const char symbol)
{
	switch (symbol)
	{
	case '$': return 0;
	case 'A': return 1;
	case 'C': return 2;
	case 'G': return 3;
	case 'T': return 4;
	}
}

/*int char_count(const char symbol, const int i, const std::string &bwt)
{
	int count = 0;

	for (size_t j = 0; j < i; j++)
	{
		if (bwt[j] == symbol) count++;
	}

	return count;
}*/

void fill_count(const char symbol, std::vector<int>& list, const std::string& bwt)
{
	list.reserve(bwt.length() + 1);
	int current_count = 0;

	for (size_t i = 0; i < bwt.length(); i++)
	{
		list.push_back(current_count);
		if (bwt[i] == symbol) current_count++;
	}

	list.push_back(current_count);
}

bool contains(const char symbol, const int top, const int bottom, const std::string &bwt)
{
	for (size_t i = top; i <= bottom; i++)
	{
		if (bwt[i] == symbol) return true;
	}

	return false;
}

int bwt_matching(const int firstOccurence[5], const std::string &bwt, const std::string &pattern, const std::vector<std::vector<int>> &count)
{
	int top = 0;
	int bottom = bwt.length() - 1;
	int pattern_i = pattern.length() - 1;

	while (top <= bottom)
	{
		if (pattern_i != -1)
		{
			char symbol = pattern[pattern_i];
			pattern_i--;

			if (contains(symbol, top, bottom, bwt))
			{
				int index = char_index(symbol);
				top = firstOccurence[index] + count[index][top]; //char_count(symbol, top, bwt);
				bottom = firstOccurence[index] + count[index][bottom + 1] - 1; //char_count(symbol, bottom + 1, bwt) - 1;
			}
			else return 0;
		}
		else return bottom - top + 1;
	}
}

int main()
{
	std::string bwt;
	std::cin >> bwt;
	std::string sorted_bwt(bwt);
	std::sort(sorted_bwt.begin(), sorted_bwt.end());

	int n;
	std::cin >> n;

	int firstOccurences[] = { -1, -1, -1, -1, -1 };

	int count = 0; // stop searching if already found all 5 characters
	for (size_t i = 0; i < sorted_bwt.length() && count < 5; i++)
	{
		int index = char_index(sorted_bwt[i]);
		if (firstOccurences[index] == -1)
		{
			firstOccurences[index] = i;
			count++;
		}
	}

	std::vector<std::vector<int>> counts(5);
	char symbols[] = { '$', 'A', 'C', 'G', 'T' };

	for (size_t i = 0; i < 5; i++)
	{
		fill_count(symbols[i], counts[i], bwt);
	}

	for (size_t i = 0; i < n; i++)
	{
		std::string pattern;
		std::cin >> pattern;

		std::cout << bwt_matching(firstOccurences, bwt, pattern, counts) << ' ';
	}
}