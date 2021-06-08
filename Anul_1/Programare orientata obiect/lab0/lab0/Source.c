#include <stdio.h>
int main()
{	
	int n, s = 0, number;
	printf("Introduceti numarul: ");  scanf("%d", &n);
	for (int i = 1; i <= n; i++)
	{
		scanf("%d", &number);
		s += number;
	}
	printf("Suma numerelor este %d", s);
	return 0;
}