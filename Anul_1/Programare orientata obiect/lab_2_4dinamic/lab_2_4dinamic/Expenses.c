#include "Domain.h"
#include <string.h>
#include <stdlib.h>
#include <assert.h>

Expenses* createExp(int nr_apart, float cost, char* type) {
	Expenses* exp;
	exp = malloc(sizeof(Expenses));
	int lenght_type = strlen(type) + 1;
	exp->type = malloc(sizeof(char)*lenght_type);
	strcpy_s(exp->type, lenght_type, type);
	exp->nr_apart = nr_apart;
	exp->cost = cost;
	return exp;
}


void destroyExp(Expenses* exp) {
	free(exp->type);
	free(exp);
}

Expenses* copyExp(Expenses* exp) {
	return createExp(exp->nr_apart, exp->cost, exp->type);
}

void testCreateDestroy() {
	Expenses* exp = createExp(3, 200, "apa");
	assert(exp->nr_apart == 3);
	assert(exp->cost == 200);
	assert(strcmp(exp->type, "apa")==0);
	destroyExp(exp);

}