#include <iostream>
#include <string>
#include <vector>
#include <map>

int main()
{
	size_t n;
	std::cin >> n;

	std::vector<std::string> patterns(n);

	for (size_t i = 0; i < n; ++i)
	{
		std::string str;
		std::cin >> str;
		patterns[i] = str;
	}

	std::vector<std::map<char, int>> trie;
	trie.push_back(std::map<char, int>());

	int num_gen = 0;
	for (size_t i = 0; i < patterns.size(); i++)
	{
		int current_node = 0;
		for (size_t j = 0; j < patterns[i].length(); j++)
		{
			char current_char = patterns[i][j];

			if (trie[current_node].find(current_char) == trie[current_node].end())
			{
				num_gen++;
				trie[current_node][current_char] = num_gen;

				trie.push_back(std::map<char, int>());
				current_node = num_gen;
			}
			else
			{
				current_node = trie[current_node][current_char];
			}
		}
	}

	for (size_t i = 0; i < trie.size(); i++)
	{
		for (const auto& branch : trie[i])
		{
			std::cout << i << "->" << branch.second << ":" << branch.first << '\n';
		}
	}
}