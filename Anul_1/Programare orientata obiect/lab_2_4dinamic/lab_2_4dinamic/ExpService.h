#pragma once
#include "list.h"
#include "Domain.h"


typedef struct {
	list* allExp;
	list* undolist;
} Expediture;

/*
create a Expediture
*/
Expediture createExpediture();

/*
Destroy the Expediture
*/
void destroyExpediture(Expediture* list_exp);

/*
Add an exp to the list_exp
*/
void addExp(Expediture* list_exp, int nr_apart, float cost, char* type);

/*
Remove an exp to the list_exp
*/
int removeExp(Expediture* list_exp, int i);

/*
Modify an exp to the list_exp
*/
int modifyExp(Expediture* list_exp, int poz, int nr_apartament, float cost, char*type);

int cmpType(Expenses* exp1, Expenses* exp2);

int undo(Expediture*list_exp);

/*
Filter the exp list by nr_appart
*/
list* filter_nr_apart(Expediture* list_exp, int nr_apart);

/*
Filter the exp list by cost
*/
list* filter_cost(Expediture* list_exp, float cost);

/*
Filter the exp list by type
*/
list* filter_type(Expediture* list_exp, char* type);

/*
Sort ascending by cost
*/
list* sort_by_cost(Expediture* list_exp);


void test_add_filters_exp();

void test_sort();

void test_remove();

void test_modifyExp();

void test_undo();