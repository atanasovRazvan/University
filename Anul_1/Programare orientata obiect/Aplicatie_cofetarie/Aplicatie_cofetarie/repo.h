#pragma once
#include "utils.h"

//Modul pentru operatiile de stocare a informatiilor

typedef struct {
	DynamicVect* list;
}Repository;

/*
Description: functie de creare a unui repository

In: vect - vector dinamic

Out: repo - repository
*/
Repository* createRepo(DynamicVect* v);

/*
Description: stocheaza un element in repo

In:
	- repo - repository
	- elem - elementul de stocat
*/
void store(Repository* repo, ElemType elem);

//Description: returneaza numarul de elemente din repo
int getNumberOfElems(Repository* repo);

//Description: distruge repo(elibereaza memoria)
void destroyRepo(Repository* repo);

//Description: returneaza un dynamic array cu toate elementele din repo
DynamicVect* getAll(Repository* repo);

//Description: returneaza un element din repo, avand indexul dat
//In: repo, index
//Out: elementul
ElemType getElem(Repository* repo, int index);

//Description: sterge un element din repo de la un index dat
void deleteElem(Repository* repo, int index);