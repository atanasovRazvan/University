#include "ExpService.h"
#include <string.h>
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "Utils.h"

Expediture createExpediture() {
	Expediture exp;
	exp.allExp = createList(destroyExp);
	exp.undolist = createList(destroyList);
	return exp;
}

void destroyExpediture(Expediture* list_exp) {
	destroyList(list_exp->allExp);
	destroyList(list_exp->undolist);
}

void addExp(Expediture* list_exp, int nr_apart, float cost, char* type) {
	Expenses* exp = createExp(nr_apart, cost, type);
	list* Llist = copyList(list_exp->allExp, copyExp);
	add(list_exp->undolist, Llist);
	add(list_exp->allExp, exp);
	
}

int removeExp(Expediture* list_exp,int i) {
	if (size(list_exp->allExp)>i) {
		list* Llist = copyList(list_exp->allExp, copyExp);
		add(list_exp->undolist, Llist);
		rem(list_exp->allExp, i);
		return 0;}
	else {
		return -1;
	}
}

int modifyExp(Expediture* list_exp, int poz,int nr_apartament,float cost ,char*type) {
	if (poz < size(list_exp->allExp)) {
		Expenses* new = createExp(nr_apartament,cost, type);
		list* Llist = copyList(list_exp->allExp, copyExp);
		add(list_exp->undolist, Llist);
		mod(list_exp->allExp, poz, new);
		return 0;}
	else {
		return -1;
	}
}

list* filter_nr_apart(Expediture* list_exp, int nr_apart) {
	if (nr_apart < 0) {
		return copyList(list_exp->allExp,copyExp);
	}
	list* exp = createList(destroyExp);
	for (int i = 0; i < size(list_exp->allExp); i++) {
		Expenses* e = getExpenses(list_exp->allExp, i);
		if (e->nr_apart == nr_apart) {
			add(exp, copyExp(e));
		}
	}
	return exp;
}

list* filter_cost(Expediture* list_exp, float cost) {
	if (cost < 0) {
		return copyList(list_exp->allExp,copyExp);
	}
	list* exp = createList(destroyExp);
	for (int i = 0; i < size(list_exp->allExp); i++) {
		Expenses* e = getExpenses(list_exp->allExp, i);
		if (e->cost == cost) {
			add(exp, copyExp(e));
		}
	}
	return exp;

}

list* filter_type(Expediture* list_exp, char* type) {
	if (type == NULL || strlen(type)==0) {
		return copyList(list_exp->allExp,copyExp);
	}
	list* exp = createList(destroyExp);
	for (int i = 0; i < size(list_exp->allExp); i++) {
		Expenses* e = getExpenses(list_exp->allExp, i);
		if (strstr(e->type, type)!=NULL) {
			add(exp, copyExp(e));
		}
	}
	return exp;

}

int cmpCost(Expenses* exp1, Expenses* exp2) {
	return exp1->cost < exp2->cost;
}
/*void sort_list(list* list) {
	int i_it, j_it;
	for (i_it = 0; i_it < list->lg-1; i_it++) {
		for (j_it = i_it+1; j_it < list->lg; j_it++) {
			ElemType exp1 = getExpenses(list, i_it);
			ElemType exp2 = getExpenses(list, j_it);
			if (cmpCost(exp1,exp2)==1) {
				set(list, i_it, exp2);
				set(list, j_it, exp1);
			}
		}
	}
}*/

list* sort_by_cost(Expediture* list_exp) {
	list* sorted = copyList(list_exp->allExp,copyExp);
	sortare(sorted, cmpCost,-1);
	return sorted;
}
int cmpType(Expenses* exp1, Expenses* exp2) {
	return strcmp(exp1->type,exp2->type);
}
list* sort_by_type(Expediture*list_exp) {
	list* sorted = copyList(list_exp->allExp,copyExp);
	sortare(sorted, cmpType,1);
	return sorted;
}
int undo(Expediture*list_exp) {
	if (size(list_exp->undolist) > 0)
	{
		list*Llist = copyList(getExpenses(list_exp->undolist, size(list_exp->undolist) - 1),copyExp);
		rem(list_exp->undolist, size(list_exp->undolist) - 1);
		destroyList(list_exp->allExp);
		list_exp->allExp = Llist;
		return 0;}
	else {
		return -1;
	}
}

void test_add_filters_exp() {
	Expediture exp = createExpediture();
	addExp(&exp, 3, 200, "apa");
	addExp(&exp, 4, 500, "gaz");
	
	list* filtered = filter_nr_apart(&exp, 3);
	assert(size(filtered) == 1);
	destroyList(filtered);

	filtered = filter_nr_apart(&exp, -3);
	assert(size(filtered) == size(exp.allExp));
	destroyList(filtered);


	filtered = filter_cost(&exp, 200);
	assert(size(filtered) == 1);
	destroyList(filtered);

	filtered = filter_cost(&exp, -200);
	assert(size(filtered) == size(exp.allExp));
	destroyList(filtered);

	
	filtered = filter_type(&exp, NULL);
	assert(size(filtered) == size(exp.allExp));
	destroyList(filtered);

	filtered = filter_type(&exp, "apa");
	assert(size(filtered) == 1);
	destroyList(filtered);

	destroyExpediture(&exp);
	
}

void test_undo() {
	Expediture exp = createExpediture();
	addExp(&exp, 2, 200, "apa");
	addExp(&exp, 3, 500, "electricitate");
	assert(size(exp.allExp) == 2);
	assert(size(exp.undolist) == 2);
	undo(&exp);
	assert(size(exp.allExp) == 1);
	assert(size(exp.undolist) == 1);
	undo(&exp);
	int sw=undo(&exp);
	assert(sw == -1);
	destroyExpediture(&exp);
}

void test_sort() {
	Expediture exp = createExpediture();
	addExp(&exp, 2, 200, "apa");
	addExp(&exp, 3, 500, "electricitate");
	addExp(&exp, 4, 300, "gaz");
	list* l = sort_by_cost(&exp);
	Expenses* e = getExpenses(l, 0);
	float c =e ->cost;
	assert(c==500 );
	int apart = e->nr_apart;
	assert(apart == 3);
	destroyList(l);
	l = sort_by_type(&exp);
	e = getExpenses(l, 0);
	c = e->cost;
	assert(c == 300);
	apart = e->nr_apart;
	assert(apart == 4);
	destroyList(l);
	destroyExpediture(&exp);

}
void test_remove() {
	Expediture exp = createExpediture();
	addExp(&exp, 2, 3, "gaz");
	assert(size(exp.allExp) == 1);
	addExp(&exp, 4,2, "apa");
	assert(size(exp.allExp) == 2);
	removeExp(&exp, 0);
	assert(size(exp.allExp) == 1);
	int sw = removeExp(&exp, 2);
 	assert(sw == -1);
	destroyExpediture(&exp);
}
void test_modifyExp() {
	Expediture exp = createExpediture();
	addExp(&exp, 3, 200, "gaz");
	int sw = modifyExp(&exp, 0, 3, 234, "apa");
	assert(sw == 0);
	sw=modifyExp(&exp, 1, 3, 234, "apa");
	assert(sw == -1);
	destroyExpediture(&exp);
}

