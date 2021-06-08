/*
5. Tipareste un numar precizat de termeni din sirul

   1, 2,1, 3,2,1, 4,2,2, 5,4,3,2,1, 6,2,2,3,3,3, 7,6, ...

   obtinut din sirul numerelor naturale prin inlocuirea fiecarui

   numar natural n printr-un grup de numere. Numarul prim p este

   inlocuit prin numerele p,p-1,...3,2,1, iar numarul compus n

   este inlocuit prin n urmat de toti divizorii sai proprii,

   un divizor d repetandu-se de d ori.
*/

#include <stdio.h>

/*
Functia numarPrim primeste ca parametru un numar intreg
Verifica daca numarul este sau nu prim
Returneaza 1 - este prim sau 0 - nu este prim
*/

int numarPrim(int x)
{
	if (x < 2) return 0;
	if (x == 2) return 1;
	if (x % 2 == 0) return 0;
	for (int i = 3; i*i <= x; i+=2)
		if (x%i == 0) return 0;
	return 1;
}

/*
Functia calculareSecventa primeste ca parametru un numar intreg si o lista de numere considerata goala initial
Functia face operatiile necesare obtinerii secventei din cerinta
Returneaza numarul de elemente din lista creata
*/

int calculareSecventa(int n, int v[])
{
	v[1] = n;
	int k=1;
	if (numarPrim(n))
	{
		n--;
		while (n)
		{
			v[++k] = n;
			n--;
		}
	}
	else
	{
		for (int d = 2; d <= n / 2; d++)
			if (n%d == 0)
				for (int i = 1; i <= d; i++)
					v[++k] = d;
	}
	return k;
}

/*
Functia afisareSecventa afiseaza lista de numere obtinuta anterior
Primeste ca parametru un numar intreg n
*/

void afisareSecventa(int n)
{
	int v[1005];
	int k=calculareSecventa(n, v);
	for (int i = 1; i <= k ; i++)
		printf("%d ", v[i]);
	printf("\n\n");
}


/*
Programul principal
*/

int main()
{	
	while (1)
	{
		int n;
		printf("Introduceti valoarea dorita: ");
		scanf("%d", &n);
		afisareSecventa(n);
	}
	return 0;
}