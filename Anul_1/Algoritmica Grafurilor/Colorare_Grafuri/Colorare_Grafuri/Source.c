/* Colorarea unui graf cu un numar dat de culori */

#include <stdio.h>
#include <stdlib.h>

typedef struct e {
	int dest;	/* nodul de la celalalt capat */
	struct e *next;	/* urmatoarea muchie */
} edge_t;	/* tipul muchie */

typedef struct {
	int col;	/* culoarea, initial zero */
	edge_t *links;/* muchiile cu care nodul e legat */
} node_t;	/* tipul unui nod */

int maxcol;	/* nr. max. de culori */
int maxnode;	/* nr. max. de noduri */

node_t *g;	/* graful e pointer la un tablou de noduri; va fi alocat */

int errmem(void) { perror("memorie insuficienta\n"); return 1; }

int goodcol(unsigned node, unsigned col)
{
	edge_t *e;
	for (e = g[node].links; e; e = e->next)/* pentru toti vecinii lui node */
		if (g[e->dest].col == col) return 0; /* vecin cu aceeasi culoare */
	return 1;	/* culoare buna, nu genereaza conflict */
}

void ins_edge(unsigned src, unsigned dest) /* insereaza muchie in graf */
{
	edge_t *e = malloc(sizeof(edge_t));
	if (!e) exit(errmem());
	e->dest = dest; e->next = g[src].links; g[src].links = e;
}

void print(void)
{
	unsigned n;
	printf("O colorare valida:\n");
	for (n = 0; n < maxnode; ++n)
		printf("Nodul %u cu culoarea %u\n", n, g[n].col);
}

int trycolor(unsigned node) /* incearca colorarea lui node */
{
	unsigned col;
	if (node == maxnode) { print(); return 1; }
	for (col = 0; col < maxcol; ++col)
		if (goodcol(node, col)) {
			g[node].col = col;
			if (trycolor(node + 1)) return 1;	/* daca am reusit, nu mai cautam */
		}
	g[node].col = 0;
	return 0;
}

int main(void)
{
	unsigned n1, n2;

	printf("Datele se citesc -in ordinea:\nnr. noduri\nnr. culori\nperechi n1 n2 legate prin muchie\n");
	scanf("%u%u", &maxnode, &maxcol);
	/* consideram nodurile numerotate de la 0 */
	/* calloc initializeaza memoria alocata cu zero: si culoarea, si pointerul */
	if (!(g = calloc(maxnode, sizeof(node_t)))) return errmem();
	while (scanf("%u%u", &n1, &n2) == 2) {
		ins_edge(n1, n2);
		ins_edge(n2, n1);
	}
	if (!trycolor(0)) printf("Graful nu se poate colora cu %u culori\n", maxcol);
	system("pause");
	return 0;
}