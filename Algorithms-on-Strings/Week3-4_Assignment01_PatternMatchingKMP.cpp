#include <iostream>
#include <vector>

int main()
{
	std::string text;
	std::string pattern;
	std::cin >> pattern >> text;

	std::string join_string(pattern + '$' + text);
	int* prefix_array = new int[join_string.length()];
	prefix_array[0] = 0;

	int border = 0;
	for (size_t i = 1; i < join_string.length(); ++i)
	{
		while (border > 0 && join_string[i] != join_string[border])
		{
			border = prefix_array[border - 1];
		}

		if (join_string[i] == join_string[border])
		{
			++border;
		}
		else
		{
			border = 0;
		}

		prefix_array[i] = border;
	}

	//std::vector<size_t> occurences;

	int pattern_length = pattern.length();
	for (size_t i = pattern_length + 1; i < join_string.length(); ++i)
	{
		if (prefix_array[i] == pattern_length)
		{
			//occurences.push_back(i - 2 * pattern_length);
			std::cout << i - 2 * pattern_length << ' ';
		}
	}

	delete[] prefix_array;
}