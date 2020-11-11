#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

int char_index(char symbol)
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

int main()
{
	/*
	 0         $  ?  ?  ?  ?  ?  a             4
     1         a  ?  ?  ?  ?  ?  n             0
     2         a  ?  ?  ?  ?  ?  n             5
     3         a  ?  ?  ?  ?  ?  b             6
     4         b  ?  ?  ?  ?  ?  $             3
     5         n  ?  ?  ?  ?  ?  a             1
     6         n  ?  ?  ?  ?  ?  a             2
	*/

	
	std::string bwt;
	std::cin >> bwt;

	std::string sorted_bwt(bwt);
	std::sort(sorted_bwt.begin(), sorted_bwt.end());

	std::string output;
	output.reserve(bwt.length());

	std::vector<std::vector<int>> occurences(5);

	for (size_t i = 0; i < bwt.length(); i++)
	{
		int index = char_index(bwt[i]);

		occurences[index].push_back(i);
	}

	std::vector<int> pointers(bwt.length());

	int current = 0;
	for (size_t i = 0; i < 5; i++)
	{
		int n = occurences[i].size();
		for (size_t j = 0; j < n; j++)
		{
			pointers[current] = occurences[i][j];
			current++;
		}
	}

	int index = pointers[0];
	while (index != 0)
	{
		output.push_back(sorted_bwt[index]);
		index = pointers[index];
	}
	output.push_back('$');

	std::cout << output;
}