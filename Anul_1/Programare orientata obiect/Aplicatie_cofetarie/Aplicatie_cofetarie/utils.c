#include "utils.h"
#include "domain.h"
#include <stdlib.h>

//Modulul utilitar al aplicatiei(contine implementarea vectorului dinamic)

DynamicVect* createVector(DestroyFunction f) {
	
	DynamicVect* vect = malloc(sizeof(DynamicVect));
	vect->elems = malloc(sizeof(ElemType));
	vect->size = 0;
	vect->capacity = 1;
	vect->destroy = f;
	return vect;
}

void removeElement(DynamicVect* vect, int poz) {

	if (poz != vect->size - 1) {
		ElemType aux = vect->elems[poz];
		vect->elems[poz] = vect->elems[vect->size - 1];
		vect->elems[vect->size - 1] = aux;
	}

	vect->destroy(vect->elems[vect->size - 1]);
	vect->size -= 1;
}

void destroyVector(DynamicVect* vect) {
	for (int i = 0; i < vect->size; i++) {
		vect->destroy(vect->elems[i]);
	}
	free(vect->elems);
	free(vect);
}

//Description: redimensioneaza vectorul vect la capacitate dubla
void resizeVector(DynamicVect* vect) {
	vect->capacity *= 2;
	ElemType* newArray = malloc(sizeof(ElemType) * vect->capacity);
	for (int i = 0; i < vect->size; i++)
		newArray[i] = vect->elems[i];
	free(vect->elems);
	vect->elems = newArray;
}

void append(DynamicVect* vect, ElemType elem) {
	
	if (vect->size == vect->capacity) {
		resizeVector(vect);
	}
	vect->elems[vect->size] = elem;
	vect->size ++;
}

int getSize(DynamicVect* vect) {
	return vect->size;
}

ElemType getElement(DynamicVect* vect, int poz) {
	return vect->elems[poz];
}

void setElement(DynamicVect* vect, int poz, ElemType value) {
	vect->elems[poz] = value;
}

void sort(DynamicVect* vect, int(*cmpFct)(ElemType e1, ElemType e2)) {
	for (int i = 0; i < getSize(vect) - 1; i++) {
		for (int j = i + 1; j < getSize(vect); j++) {
			ElemType el1 = getElement(vect, i);
			ElemType el2 = getElement(vect, j);
			if (cmpFct(el1, el2)) {
				setElement(vect, i, el2);
				setElement(vect, j, el1);
			}
		}
	}
}

DynamicVect* copyList(DynamicVect* l, CopyF copyE) {
	DynamicVect* list_ing = createVector(destroyIngredient);
	for (int i = 0; i < getSize(l); i++) {
		ElemType ing = getElement(l, i);
		append(list_ing, copyE(ing));
	}
	return list_ing;
}