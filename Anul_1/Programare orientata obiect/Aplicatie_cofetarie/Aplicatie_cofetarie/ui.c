#include "ui.h"
#include <stdlib.h>
#include "service.h"
#include <stdio.h>
#include <string.h>

//Modulul pentru interactiunea cu utilizatorul

void displayMenu() {
	printf("Alegeti una dintre urmatoarele optiuni: \n");
	printf("1 Adaugare materie prima \n2 Modificare materie prima \n3 Stergere materie prima \n4 Afisare produse care incep cu o litera data \n5 Afisare produse cu cantitatea mai mica decat un numar dat \n6 Sortare dupa nume \n7 Sortare dupa cantitate \n8 Undo \n9 Redo \n0 Inchideti aplicatia\n");
	printf("Introduceti o optiune: ");
}

void printList(Service* service) {
	printf("\nLista produse:\n");
	for (int i = 0; i < getNumberOfElems(service->repo); i++) {
		printf("%s - %s - %f \n", getName(getElement(getAll(service->repo), i)), getManufacturer(getElement(getAll(service->repo), i)), getQuantity(getElement(getAll(service->repo), i)));
	}
	printf("\n");
}

void addIngredientUI(Service* service) {
	char name[20];
	char manufacturer[20];
	float quantity;

	printf("Introduceti numele materiei prime: ");
	fgets(name, 20, stdin);
	fgets(name, 20, stdin);
	if ((strlen(name) > 0) && (name[strlen(name) - 1] == '\n'))
		name[strlen(name) - 1] = '\0';
	//scanf_s("%s", &name, 20);

	printf("Introduceti numele producatorului: ");
	fgets(manufacturer, 20, stdin);
	if ((strlen(manufacturer) > 0) && (manufacturer[strlen(manufacturer) - 1] == '\n'))
		manufacturer[strlen(manufacturer) - 1] = '\0';
	//scanf_s("%s", &manufacturer, 20);

	printf("Introduceti cantitatea: ");
	scanf("%f", &quantity);
	
	int rez = addIngredient(service, name, manufacturer, quantity);

	if (rez == 1)
		printf("\nIngredientul a fost adaugat!\n\n");
	else if (rez == 0)
		printf("\nIngredientul exista deja si a fost actualizat!\n\n");
	else
		printf("\nIngredientul nu este valid!\n\n");

	printList(service);
}

void modifyIngredientUI(Service* service) {
	char name[20];
	char manufacturer[20];
	float quantity;

	printf("Introduceti numele materiei prime pe care doriti sa o modificati: ");
	fgets(name, 20, stdin);
	fgets(name, 20, stdin);
	if ((strlen(name) > 0) && (name[strlen(name) - 1] == '\n'))
		name[strlen(name) - 1] = '\0';
	//scanf_s("%s", &name, 20);

	printf("Introduceti numele producatorului: ");
	fgets(manufacturer, 20, stdin);
	if ((strlen(manufacturer) > 0) && (manufacturer[strlen(manufacturer) - 1] == '\n'))
		manufacturer[strlen(manufacturer) - 1] = '\0';

	//scanf_s("%s", &manufacturer, 20);

	printf("Introduceti cantitatea: ");
	scanf("%f", &quantity);

	int rez = modifyIngredient(service, name, manufacturer, quantity);

	if (rez == 1)
		printf("\nIngredientul a fost modificat!\n\n");
	else if (rez == 0)
		printf("\nIngredientul cu numele %s nu exista!!!\n\n", name);
	else
		printf("\nDatele nu sunt valide!!!\n\n");

	printList(service);
}

void removeIngredientUI(Service* service) {
	char name[20];

	printf("Introduceti numele materiei prime pe care doriti sa o stergeti: ");
	fgets(name, 20, stdin);
	fgets(name, 20, stdin);
	if ((strlen(name) > 0) && (name[strlen(name) - 1] == '\n'))
		name[strlen(name) - 1] = '\0';
	//scanf_s("%s", &name, 20);

	if (removeIngredient(service, name))
		printf("\nIngredientul a fost sters!\n\n");
	else
		printf("\nIngredientul cu numele %s nu exista!!!\n\n", name);

	printList(service);
}

void nameFilterUI(Service* service) {
	char letter;
	printf("Introduceti litera dupa care filtrati materiile prime: ");
	scanf_s(" %c", &letter, 2);

	DynamicVect* resultList = nameFilter(service, letter);

	if (resultList->size == 0)
		printf("Nu exista materii prime ce incep cu litera %c!!!", letter);
	else {
		printf("\nLista produse:\n");
		for (int i = 0; i < getSize(resultList); i++) {
			printf("%s - %s - %f \n", getName(getElement(resultList, i)), getManufacturer(getElement(resultList, i)), getQuantity(getElement(resultList, i)));
		}
		printf("\n");
	}

	free(resultList->elems);
	free(resultList);
}

void quantityFilterUI(Service* service) {
	float number;
	printf("Introduceti cantitatea maxima: ");
	scanf("%f", &number);

	DynamicVect* resultList = quantityFilter(service, number);

	if (resultList->size == 0)
		printf("Nu exista materii prime cu cantitatea mai mica decat %f!!!", number);
	else {
		printf("\nLista produse:\n");
		for (int i = 0; i < getSize(resultList); i++) {
			printf("%s - %s - %f \n", getName(getElement(resultList, i)), getManufacturer(getElement(resultList, i)), getQuantity(getElement(resultList, i)));
		}
		printf("\n");
	}

	free(resultList->elems);
	free(resultList);
}

void sortByNameUI(Service* service) {
	sortByName(service);
	printList(service);
}

void sortByQuantityUI(Service* service) {
	sortByQuantity(service);
	printList(service);
}
void undoUI(Service* service) {
	int sw = undo(service);
	if (sw == 0) {
		printf("Undo facut cu succes\n\n");
	}
	else {
		printf("Undo nereusit\n\n");
	}
	printList(service);
}

void redoUI(Service* service) {
	int sw = redo(service);
	if (sw == 0) {
		printf("Redo facut cu succes\n\n");
	}
	else {
		printf("Redo nereusit\n\n");
	}
	printList(service);
}


void run(Service* service) {
	int optiune;
	while (1) {
		displayMenu();
		scanf("%d", &optiune);
		
		if (optiune == 0) {
			printf("Se inchide aplicatia...");
			break;
		}

		switch(optiune){
			case 1:
				addIngredientUI(service);
				break;
			case 2:
				modifyIngredientUI(service);
				break;
			case 3:
				removeIngredientUI(service);
				break;
			case 4:
				nameFilterUI(service);
				break;
			case 5:
				quantityFilterUI(service);
				break;
			case 6:
				sortByNameUI(service);
				break;
			case 7:
				sortByQuantityUI(service);
				break;
			case 8:
				undoUI(service);
				break;
			case 9:
				redoUI(service);
				break;
			default:
				printf("Optiunea nu exista!!!");
				break;
		}
	}
}