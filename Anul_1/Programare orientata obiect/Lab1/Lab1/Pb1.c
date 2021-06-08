/*
10. Tipareste un numar precizat de sume partiale din dezvoltarea



	sin(x) = x - x^3/3! + x^5/5! - x^7/7! + ...

*/


#include <stdio.h>

/*
Functia factorial primeste ca parametru un numar intreg
Calculeaza recursiv factorialul numarului
Returneaza un numar intreg
*/

int factorial(int x)
{	
	if (x == 1) return 1;
	return x * factorial(x - 1);
}

/*
Functia putere primeste ca parametru doua numere intregi
Calculeaza recursiv ridicarea la putere numar1^numar2
Returneaza un numar intreg
*/

int putere(int x, int a)
{
	if (a == 1) return x;
	return x * putere(x, a - 1);
}

/*
Functia calculSuma primeste ca parametri doua numere intregi
Calculeaza sumele partiale de ordin 1->k in punctul x
Returneaza un numar real
*/

double calculSuma(int k, int x)
{
	double s = 0;
	double minus = 1;
	for (int j = 1; j <= k; j++)
	{
		int val = 2 * (j - 1) + 1;
		double fact = factorial(val);
		double put = putere(x, val);
		s = s + minus * put / fact;
		minus = -1 * minus;
	}
	return s;
}

/*
Functia afisareSuma primeste ca parametru doua numere intregi
Afiseaza sumele partiale calculate
*/

void afisareSuma(int n, int x)
{
	for (int i = 1; i <= n; i++)
	{
		double s = calculSuma(i, x);
		printf("Suma partiala de ordin %d este: %.5f\n", i, s);
	}
	printf("\n\n");
}

/*
Functia main nu are parametri
Serveste la executarea programului (program principal)
*/

int main()
{
	while (1)
	{
		int n, x;
		printf("Se da dezvoltarea: sin(x) = x - x^3/3! + x^5/5! - x^7/7! + ...\n");
		printf("Dati valoarea pentru x: "); scanf("%d", &x);
		printf("Introduceti numarul de sume partiale dorite: ");
		scanf("%d", &n);
		afisareSuma(n,x);
	}
}