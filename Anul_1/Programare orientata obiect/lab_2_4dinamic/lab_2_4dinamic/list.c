#include "list.h"
#include <assert.h>
#include <string.h>
#include <stdlib.h>

list* createList(DestroyF destroyE) {
	list* exp;
	exp = malloc(sizeof(list));
	exp->lg = 0;
	exp->cap = 5;
	exp->elems = malloc(sizeof(ElemType)*exp->cap);
	exp->destroyE = destroyE;
	return exp;
}

void destroyList(list* l) {
	for (int i = 0; i < l->lg; i++) {
		l->destroyE(l->elems[i]);
	}
	free(l->elems);
	free(l);
}

ElemType getExpenses(list* l, int poz) {
	return l->elems[poz];
}

int size(list* l) {
	return l->lg;
}

void ensureCapacity(list* l) {
	if (l->lg < l->cap) {
		return;
	}
	ElemType* nElems = malloc(sizeof(ElemType)*(l->cap + 5));
	for (int i = 0; i < l->lg; i++) {
		nElems[i] = l->elems[i];
	}
	
	free(l->elems);
	l->elems = nElems;
	l->cap += 5;
}

void add(list* l, ElemType el) {
	ensureCapacity(l);
	l->elems[l->lg] = el;
	l->lg++;
}

void mod(list* l, int poz, ElemType exp) {
	l->destroyE(l->elems[poz]);
	l->elems[poz] = exp;
}

void set(list* l, int poz, ElemType exp) {
	l->elems[poz] = exp;
}

void rem(list* l, int index) {
	int i = 0;
	if (l->lg > 0) {
		l->destroyE(l->elems[index]);
		for (i = index + 1; i < l->lg; i++)
			l->elems[i - 1] = l->elems[i];
		l->lg--;
		
	}
	
}

list* copyList(list* l,CopyF copyE) {
	list* list_exp = createList(destroyExp);
	for (int i = 0; i < size(l); i++) {
		ElemType exp = getExpenses(l, i);
		add(list_exp, copyE(exp));
	}
	return list_exp;
}

void testCreateList() {
	list* l = createList(destroyExp);
	assert(size(l) == 0);
	destroyList(l);
}
void testIterateList() {
	list* l = createList(destroyExp);
	add(l, createExp(4, 200, "gaz"));
	add(l, createExp(3, 100, "apa"));
	assert(size(l) == 2);
	destroyList(l);
}

void testCopyList() {
	list* l = createList(destroyExp);
	add(l, createExp(2, 100, "apa"));
	add(l, createExp(5, 500, "gaz"));
	list* l2 = copyList(l,copyExp);
	assert(size(l2) == 2);
	Expenses* exp = getExpenses(l2, 0);
	assert(exp->nr_apart== 2 );
	destroyList(l);
	destroyList(l2);
}

void testResize() {
	list* l = createList(destroyExp);
	for (int i = 0; i < 10; i++) {
		add(l, createExp(4, 200, "apa"));
	}
	assert(size(l) == 10);
	destroyList(l);
}
void test_rem() {
	list* l = createList(destroyExp);
	Expenses* exp1 = createExp(2, 300, "apa");
	Expenses* exp2 = createExp(3, 200, "apa");
	add(l, exp1);
	add(l, exp2);
	assert(size(l) == 2);
	rem(l, 1);
	assert(size(l) == 1);
	rem(l, 0);
	assert(size(l) == 0);
	destroyList(l);
}

void test_mod() {
	list* l = createList(destroyExp);
	Expenses* exp1 = createExp(2, 300, "apa");
	Expenses* exp2 = createExp(3, 200, "apa");
	add(l, exp1);
	mod(l, 0,exp2);
	assert(size(l) == 1);
	destroyList(l);
}