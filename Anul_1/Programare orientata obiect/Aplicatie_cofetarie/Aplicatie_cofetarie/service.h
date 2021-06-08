#pragma once
#include "utils.h"
#include "repo.h"

//Modul pentru functionalitatile aplicatiei

typedef struct{
	Repository* repo;
	DynamicVect* undo_list;
	DynamicVect* redo_list;
}Service;

//Description: creeaza un service
Service* createService(Repository* repository);

/*
Description: adauga o materie prima intr-o lista

In:
	- repo - repository de materii prime
	- name - numele materiei prime
	- manufacturer - numele producatorului
	- quantity - cantitatea

Out:
	- ok - 0 daca materia prima este exista si 1 in caz contrar
*/
int addIngredient(Service* service, char* name, char* manufacturer, float quantity);

/*
Description: modifica o materie prima din lista

In:
	- repo - repository de materii prime
	- name - numele materiei prime
	- manufacturer - numele producatorului
	- quantity - cantitatea

Out:
	- ok - 0 daca materia prima este inexistenta si 1 in caz contrar
*/
int modifyIngredient(Service* service, char* name, char* manufacturer, float quantity);

/*
Description: sterge o materie prima din lista

In:
	- repo - repository de materii prime
	- name - numele materiei prime

Out:
	- ok - 0 daca materia prima este inexistenta si 1 in caz contrar
*/
int removeIngredient(Service* service, char* name);

/*
Description: filtreaza materiile prime dupa nume (numele care incep cu o litera data)

In:
	- repo - repository de materii prime
	- letter - litera pentru filtrare
*/
DynamicVect* nameFilter(Service* service, char letter);

/*
Description: filtreaza materiile prime dupa cantitate (au cantitatea mai mica decat cea data)

In:
	- repo - repository de materii prime
	- number - cantitatea pentru filtrare
*/
DynamicVect* quantityFilter(Service* service, float number);

/*
Description: sorteaza materiile prime crescator dupa nume

In:
	- repo - repository de materii prime
*/
void sortByName(Service* service);

/*
Description: sorteaza materiile prime descrescator dupa cantitate

In:
	- repo - repository de materii prime
*/
void sortByQuantity(Service* service);

int undo(Service* service);

int redo(Service* service);

void destroyService(Service*service);