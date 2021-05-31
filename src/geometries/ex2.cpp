/************************
*AUTHOR: Hanina Cohen   *
*ID:     337615041		*
*						*
*TARGIL: 5 , 						*
*			 			*
*						*
************************/
#include<iostream>             
using namespace std;

void shtut(int *, int, int);
void print(int*, int);
int bsearch(int *arr, int size, int what);
int update(int);
void shiftright(int*v, int n, int place);
void insertSortOne(int *v, int n);

int main()
{
	int arr[] = { 504,9081,10385,29758,30015,41828,44098,59967 };
	int size = sizeof(arr) / sizeof(arr[0]);
	
	print(arr, size);

	//int nb = 29758;   === >> 30869
	//	int arr[] = { 504,9081,10385,30015,41828,44098,59967,30869}
	//	int arr[] = { 504,9081,10385,30015,41828,44098,30869,59967}
	//	int arr[] = { 504,9081,10385,30015,41828,30869,44098,59967}
	//	int arr[] = { 504,9081,10385,30015,30869,41828,44098,59967}



	int nb = 29758;
	//cout << "enter a num"<< endl ;
	//cin >> nb;
	shtut(arr, size,nb);

	print(arr, size);


	system("pause");
	return 0;
}

void shtut(int * v, int n, int what)
{
	int place = bsearch (v,n,what);
	if (place != -1)
	{
		int newval = update(v[place]);
		shiftright(v, n, place);
		v[n - 1] = newval;
		insertSortOne(v, n);
	}
}

void print(int *v, int n)
{
	for (int i = 0; i < n; i++)
	{
		cout << v[i];
		if (i < n-1)
		{
			cout << ", ";
		}
	}
	cout << endl;
}

int bsearch(int * arr, int size, int what)
{
	int l = 0;
	int r = size - 1;

	while (l <= r) {
		int m = l + (r - l) / 2;

		// Check if what is present at mid
		if (arr[m] == what)
			return m;

		// If what greater, ignore left half
		if (arr[m] < what)
			l = m + 1;

		// If what is smaller, ignore right half
		else
			r = m - 1;
	}

	// if we reach here, then element was
	// not present
	return -1;
}

int update(int number)
{
	int counter = 1;
	int val = 0;
	while (number > 0) 
	{
		int digit = (number + 1) % 10;
		val = val + digit * counter;
		counter *= 10;
		number /= 10;
	}
	return val;
}

void shiftright(int * v, int n, int place)
{
	for (int i = place; i < n-1; i++)
	{
		v[i] = v[i + 1];
	}
}

void insertSortOne(int * v, int n)
{
	int what = v[n - 1];
	bool finish = false;
	for (int i = n-2; i  > 0 && !finish ; i--)
	{
		if (v[i] > what)
		{
			int tmp = v[i];
			v[i] = v[i + 1];
			v[i + 1] = tmp;
		}
		else
			finish = true;

	}
}
