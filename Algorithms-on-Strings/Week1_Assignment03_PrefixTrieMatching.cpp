#include <iostream>
#include <vector>
#include <algorithm>

static const int LETTERS = 4;
static const int EMPTY = -1;

struct Node
{
	//  All strings contain only symbols A, C, G, T
	int next[LETTERS];
	bool pattern_end;

	Node(bool end = false) : pattern_end{end}
	{
		std::fill(next, next + LETTERS, EMPTY);
	}

	int& operator[](int index)
	{
		return next[index];
	}

	static int letter_index(char symbol)
	{
		switch (symbol)
		{
		case 'A': return 0; break;
		case 'C': return 1; break;
		case 'G': return 2; break;
		case 'T': return 3; break;
		}
		throw std::invalid_argument("Unknown letter");
	}
};

void build_trie(std::vector<Node> &trie, const std::vector<std::string> &patterns)
{
	trie.push_back(Node());

	int num_gen = 0;
	for (size_t i = 0; i < patterns.size(); i++)
	{
		int current_node = 0;

		for (size_t j = 0; j < patterns[i].length(); j++)
		{
			const char current_char = patterns[i][j];

			size_t index = Node::letter_index(current_char);

			if (trie[current_node][index] == EMPTY)
			{
				num_gen++;
				trie[current_node][index] = num_gen;

				if (j + 1 == patterns[i].length())
				{
					trie.push_back(Node(true));
				}
				else 
				{
					trie.push_back(Node());
				}
				current_node = num_gen;
			}
			else
			{
				current_node = trie[current_node][index];

				if (j + 1 == patterns[i].length())
				{
					trie[current_node].pattern_end = true;
				}
			}
		}
	}
}

void match_trie(const std::vector<Node> &trie, const std::string &text, const size_t min_index, std::vector<int> &ans)
{
	char symbol = text[min_index];
	size_t char_tracker = min_index;
	Node root = trie[0];

	while (true)
	{
		size_t index = Node::letter_index(symbol);
		if (root.pattern_end)
		{
			ans.push_back(min_index);
			return;
		}
		else if (root[index] != EMPTY)
		{
			root = trie[root[index]];

			if (char_tracker < text.length() - 1)
			{
				char_tracker++;
				symbol = text[char_tracker];
			}
			else if (root.pattern_end)
			{
				ans.push_back(min_index);
				return;
			}
			else return;
		}
		else return;
	}
}

int main()
{
	std::string text;
	std::cin >> text;

	size_t n;
	std::cin >> n;

	std::vector<std::string> patterns(n);

	for (size_t i = 0; i < n; i++)
	{
		std::string str;
		std::cin >> str;
		patterns[i] = str;
	}

	std::vector<Node> trie;
	build_trie(trie, patterns);

	std::vector<int> ans;

	for (size_t i = 0; i < text.length(); i++)
	{
		match_trie(trie, text, i, ans);
	}

	for (const auto i : ans)
	{
		std::cout << i << ' ';
	}
}