#include <stdio.h>
#include <math.h>
/*
Functie care calculeaza valoarea radicalului unui numar intreg
Primeste un numar intreg n
Returneaza un float
*/
long double calculareRadical(int n)
{
	return sqrt(n);
}

/*
Functie care afiseaza valoarea cu precizia P
*/
void afisare(long double radN, int p)
{
	printf("Valoarea radicalului este %f\n", radN);
	printf("Zecimale valide: primele %d\n", p);
	printf("\n");
}

/*
Programul principal
*/
int main()
{
	while (1)
	{
		int n, p;
		long double radN;
		printf("Introduceti valoarea dorita pentru calcularea radicalului: ");
		scanf("%d", &n);
		printf("Introduceti precizia: ");
		scanf("%d", &p);
		radN = calculareRadical(n);
		afisare(radN, p);
	}
	return 0;
}