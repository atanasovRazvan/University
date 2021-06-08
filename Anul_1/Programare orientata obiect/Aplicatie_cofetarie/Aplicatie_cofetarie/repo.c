#include "repo.h"
#include "utils.h"
#include <stdlib.h>

//Modul pentru operatiile de stocare a informatiilor


Repository* createRepo(DynamicVect* vect) {
	Repository* repo = malloc(sizeof(Repository));
	repo->list = vect;
	return repo;
}

void store(Repository* repo, ElemType elem) {
	append(repo->list, elem);
}

int getNumberOfElems(Repository* repo) {
	return getSize(repo->list);
}

void destroyRepo(Repository* repo) {
	destroyVector(repo->list);
	free(repo);
}

DynamicVect* getAll(Repository* repo) {
	return repo->list;
}

ElemType getElem(Repository* repo, int index) {
	return getElement(repo->list, index);
}

void deleteElem(Repository* repo, int index) {
	removeElement(repo->list, index);
}