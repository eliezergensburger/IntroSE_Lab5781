#include <iostream>
using namespace std;

int main()
{
	int size = 10;
	char** arr = new char* [size];
	for (int i = 0; i < size; ++i)
		arr[i] = new char[i];

	for (int i = 0; i < size; i++)
		for (int j = 0; j < i; ++j)
			arr[i][j] = '*';
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < i; ++j)
			cout << arr[i][j];
		cout << endl;
	}
}