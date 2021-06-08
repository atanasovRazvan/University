#pragma once
#include "Domain.h"
typedef void* ElemType;
typedef void(*DestroyF)(ElemType e1);
typedef ElemType(*CopyF)(ElemType e2);
typedef struct {
	ElemType* elems;
	int lg;
	int cap;
	DestroyF destroyE;
}list;

list* createList(DestroyF destroyE);

void set(list* l, int poz, ElemType exp);

void destroyList(list* l);

ElemType getExpenses(list* l, int poz);

int size(list* l);

void add(list* l, ElemType el);

void rem(list* l, int index);

void mod(list* l, int poz, ElemType exp);

list* copyList(list* l,CopyF copyE);

void testCreateList();
void testIterateList();
void testCopyList();
void testResize();
void test_rem();
void test_mod();