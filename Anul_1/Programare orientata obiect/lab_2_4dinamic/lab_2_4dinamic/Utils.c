#include <stdio.h>
#include <string.h>
#include "Domain.h"
#include "Utils.h"

void print_menu() {
	printf("\tMeniu:\n");
	printf("1) Adaugarea de cheltuieli pentru un apartament\n");
	printf("2) Modificarea unei cheltuieli existente(tipul, suma)\n");
	printf("3) Stergere cheltuiala\n");
	printf("4) Vizualizare lista de cheltuieli filtrat dupa o proprietate(suma, tipul, apartament)\n");
	printf("5) Vizualizare lista de cheltuieli ordonat dupa suma descrescator");
	printf("6) Meniu\n");
	printf("7) Vizualizare lista\n");
	printf("8) Vizualizare lista de cheltuieli ordonat dupa tip crescator\n");
	printf("9) Undo\n");
	printf("0) Exit\n");
}
int read_cmd() {
	int cmd;
	int value = -1;
	printf("cmd>>");
	value = scanf_s("%d", &cmd);
	if (value == -1) {
		scanf_s("%*[^\n]");
		printf("Command violation\n");
	}
	return cmd;
}
void sortare(list *l, Comparare cmp,int o) {
	int i, j;
	for (i = 0; i < size(l) - 1; i++) {
		for (j = i + 1; j < size(l); j++) {
			Expenses* e1 = getExpenses(l, i);
			Expenses* e2 = getExpenses(l, j);
			if (o == -1)
			{
				if (cmp(e1, e2) > 0) {
					set(l, i, e2);
					set(l, j, e1);
				}
			}
			else if (o == 1)
			{
				if (cmp(e1, e2) < 0) {
					set(l, i, e2);
					set(l, j, e1);
				}
			}
		}
	}
}
int read_answer() {
	int answer = 0;
	int value = -1;
	int condition = 1;
	while (condition) {
		printf("answer>>");
		value = scanf_s("%d", &answer);
		if (value == -1) {
			scanf_s("%*[^\n]");
			printf("Invalid answer\n");
		}
		if (answer > 0) {
			condition = 0;
		}
	}
	return answer;
}

int read_nr_ap() {
	int nr_apart;
	int value = -1;
	int condition = 1;
	while (condition) {
		printf("nr_apart>>");
		value = scanf_s("%d", &nr_apart);
		if (value == -1) {
			scanf_s("%*[^\n]");
			printf("Invalid nr.apartment. Must be integer value.\n");
		}
		else {
			if (nr_apart > 0) {
				condition = 0;
			}
			else {
				printf("Invalid nr.apartment\n");
			}
		}
	}
	return nr_apart;
}

int read_cost() {
	int cost;
	int value=-1;
	int condition = 1;
	while (condition) {
		printf("cost>>");
		value = scanf_s("%d", &cost);
		if (value == -1) {
			scanf_s("%*[^\n]");
			printf("Invalid cost. Must be positive value.\n");
		}
		else {
			if (cost >= 0) {
				condition = 0;
			}
			else {
				printf("Invalid cost.\n");
			}
		}
	}
	return cost;
}

char* read_type() {
	static char type[10];
	int condition = 1;
	while (condition) {
		printf("type>>");
		scanf_s("%s", &type,sizeof(type));
		if (strlen(type) > 0) {
			condition = 0;
		}
	}
	return type;
}



