#include <iostream>

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

void sort_characters(const std::string &str, int count[5], int* order)
{
	for (size_t i = 0; i < str.length(); ++i)
	{
		count[char_index(str[i])]++;
	}
	for (size_t i = 1; i < 5; ++i)
	{
		count[i] = count[i] + count[i - 1];
	}
	for (int i = str.length() - 1; i >= 0; i--)
	{
		int index = char_index(str[i]);
		count[index]--;
		order[count[index]] = i;
	}
}

void create_classes(const std::string &str, const int* order, int* classes)
{
	for (size_t i = 1; i < str.length(); ++i)
	{
		if (str[order[i]] == str[order[i - 1]])
		{
			classes[order[i]] = classes[order[i - 1]];
		}
		else
		{
			classes[order[i]] = classes[order[i - 1]] + 1;
		}
	}
}

int* sort_doubled(const int str_len, int shift_length, int* order, const int* classes)
{
	int* count = new int[str_len]();
	int* new_order = new int[str_len];

	for (size_t i = 0; i < str_len; ++i)
	{
		count[classes[i]] = count[classes[i]] + 1;
	}
	for (size_t i = 1; i < str_len; ++i)
	{
		count[i] = count[i] + count[i - 1];
	}
	for (int i = str_len - 1; i >= 0; i--)
	{
		int start = (order[i] - shift_length + str_len) % str_len;
		int cl = classes[start];
		count[cl] = count[cl] - 1;
		new_order[count[cl]] = start;
	}

	delete[] order;
	delete[] count;
	return new_order;
}

int* update_classes(const int n, const int* new_order, int* classes, int shift_length)
{
	int* new_class = new int[n]();

	for (size_t i = 1; i < n; ++i)
	{
		int current = new_order[i];
		int prev = new_order[i - 1];

		int mid = (current + shift_length) % n;
		int mid_prev = (prev + shift_length) % n;

		if (classes[current] != classes[prev] || classes[mid] != classes[mid_prev])
		{
			new_class[current] = new_class[prev] + 1;
		}
		else
		{
			new_class[current] = new_class[prev];
		}
	}

	delete[] classes;
	return new_class;
}

int main()
{
	std::string str;
	std::cin >> str;

	int count[5] = { 0, 0, 0, 0, 0 }; // $ A C G T
	int n = str.length();
	int* order = new int[n];
	int* classes = new int[n]();

	sort_characters(str, count, order);
	create_classes(str, order, classes);

	int shift_length = 1;
	while (shift_length < n)
	{
		order = sort_doubled(n, shift_length, order, classes);
		classes = update_classes(n, order, classes, shift_length);

		shift_length *= 2;
	}

	for (size_t i = 0; i < n; ++i)
	{
		std::cout << order[i] << ' ';
	}

	delete[] order;
	delete[] classes;
}