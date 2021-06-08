#pragma once
#include "domain.h"

//Modulul utilitar al aplicatiei(contine implementarea vectorului dinamic)

typedef void* ElemType;
typedef void(*DestroyFunction)(ElemType);
typedef ElemType(*CopyF)(ElemType e2);

typedef struct {
	ElemType* elems;
	DestroyFunction destroy;
	int size;
	int capacity;
}DynamicVect;

/*
	Description: creeaza un vector dinamic

	In:
		- f - functia de distrugere a elementelor din vector
	Out:
		- vect - pointer la vector
	*/
DynamicVect* createVector(DestroyFunction f);

/*
Description: elibereaza memoria ocupata de vector(distruge vectorul)

In:
	- vect - pointer la vector
*/
void destroyVector(DynamicVect* vect);

//Description: returneaza pointer-ul la elementul de pe pozitia poz
ElemType getElement(DynamicVect* vect, int poz);

//Description: returneaza lungimea vectorului
int getSize(DynamicVect* vect);

/*
	Description: adauga un element la vector

	In:
		- vect - pointer la vector
		- void* - pointer la element
*/
void append(DynamicVect* vect, ElemType elem);

/*
Description: sterge un element din vector

In:
	- vect - pointer la vector
	- poz - pozitia elementului de sters
*/
void removeElement(DynamicVect* vect, int poz);

//Description: seteaza un element al vectorului de pe o pozitie data
void setElement(DynamicVect* vect, int poz, ElemType value);

/*
Description: sorteaza materiile prime

In:
	- repo - repository de materii prime
	- cmpFct - functie de comparare
*/
void sort(DynamicVect* vect, int(*cmpFct)(ElemType e1, ElemType e2));

DynamicVect* copyList(DynamicVect* l, CopyF copyE);