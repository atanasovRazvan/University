#include "service.h"
#include "repo.h"
#include "domain.h"
#include "utils.h"
#include <string.h>
#include <stdlib.h>

//Modul pentru functionalitatile aplicatiei

Service* createService(Repository* repository) {
	Service* myService = malloc(sizeof(Service));
	myService->repo = repository;
	myService->undo_list = createVector(destroyVector);
	myService->redo_list = createVector(destroyVector);
	return myService;
}

int addIngredient(Service* service, char* name, char* manufacturer, float quantity) {
	int ok = 1;

	for (int i = 0; i < getNumberOfElems(service->repo); i++) {
		if (strcmp(getName(getElem(service->repo, i)), name) == 0) {
			setManufacturer(getElem(service->repo, i), manufacturer);
			setQuantity(getElem(service->repo, i), quantity);
			ok = 0;
			break;
		}
	}

	if (ok == 1) {
		Ingredient* ingredient = createIngredient(name, manufacturer, quantity);
		if (validate(ingredient)) {
			DynamicVect* Llist = copyList(getAll(service->repo), copyIng);
			append(service->undo_list, Llist);
			destroyVector(service->redo_list);
			service->redo_list = createVector(destroyVector);
			store(service->repo, ingredient);
		}
		else {
			destroyIngredient(ingredient);
			ok = -1;
		}
	}

	return ok;
}

int modifyIngredient(Service* service, char* name, char* manufacturer, float quantity) {
	int ok = 0;

	Ingredient* ingredient = createIngredient(name, manufacturer, quantity);
	if (validate(ingredient))
		for (int i = 0; i < getNumberOfElems(service->repo); i++) {
			if (strcmp(getName(getElem(service->repo, i)), name) == 0) {
				DynamicVect* Llist = copyList(getAll(service->repo), copyIng);
				append(service->undo_list, Llist);
				destroyVector(service->redo_list);
				service->redo_list = createVector(destroyVector);
				setManufacturer(getElem(service->repo, i), manufacturer);
				setQuantity(getElem(service->repo, i), quantity);
				ok = 1;
				break;
			}
		}
	else {
		ok = -1;
	}

	destroyIngredient(ingredient);
	return ok;
}

int removeIngredient(Service* service, char* name) {
	int ok = 0;
	int poz = 0;

	for (int i = 0; i < getNumberOfElems(service->repo); i++) {
		if (strcmp(getName(getElem(service->repo, i)), name) == 0) {
			poz = i;
			ok = 1;
			break;
		}
	}

	if (ok == 1) {
		DynamicVect* Llist = copyList(getAll(service->repo), copyIng);
		append(service->undo_list, Llist);
		destroyVector(service->redo_list);
		service->redo_list = createVector(destroyVector);
		deleteElem(service->repo, poz);
	}
		
	return ok;
}

DynamicVect* nameFilter(Service* service, char letter) {
	DynamicVect* resultV = createVector(destroyIngredient);
	for (int i = 0; i < getNumberOfElems(service->repo); i++) {
		if (getName(getElem(service->repo, i))[0] == letter)
			append(resultV, getElem(service->repo, i));
	}

	return resultV;
}

DynamicVect* quantityFilter(Service* service, float number) {
	DynamicVect* resultV = createVector(destroyIngredient);
	for (int i = 0; i < getNumberOfElems(service->repo); i++) {
		if (getQuantity(getElem(service->repo, i)) < number)
			append(resultV, getElem(service->repo, i));
	}

	return resultV;
}

int cmpName(Ingredient* i1, Ingredient* i2 ) {
	return strcmp(getName(i1), getName(i2)) > 0;
}

int cmpQuantity(Ingredient* i1, Ingredient* i2) {
	return getQuantity(i2) > getQuantity(i1);
}

void sortByName(Service* service) {
	DynamicVect* vect = getAll(service->repo);
	sort(vect, cmpName);
}

void sortByQuantity(Service* service) {
	DynamicVect* vect = getAll(service->repo);
	sort(vect, cmpQuantity);
}

int undo(Service* service) {
	if (getSize(service->undo_list) > 0)
	{
		DynamicVect*Llist = copyList(getElement(service->undo_list, getSize(service->undo_list) - 1), copyIng);
		removeElement(service->undo_list, getSize(service->undo_list) - 1);
		DynamicVect* RList = copyList(getAll(service->repo), copyIng);
		append(service->redo_list, RList);
		destroyRepo(service->repo);
		service->repo = createRepo(Llist);
		return 0;
	}
	else {
		return -1;
	}
}
int redo(Service* service) {
	if (getSize(service->redo_list) > 0)
	{
		DynamicVect*RList = copyList(getElement(service->redo_list, getSize(service->redo_list) - 1), copyIng);
		removeElement(service->redo_list, getSize(service->redo_list) - 1);
		DynamicVect* Llist = copyList(getAll(service->repo), copyIng);
		append(service->undo_list, Llist);
		destroyRepo(service->repo);
		service->repo = createRepo(RList);
		return 0;
	}
	else {
		return -1;
	}
}


void destroyService(Service*service) {
	destroyRepo(service->repo);
	destroyVector(service->undo_list);
	destroyVector(service->redo_list);
	free(service);
}