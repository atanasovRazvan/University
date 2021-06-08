#include "pet.h"
#include "MyList.h"
#include "petstoreservice.h"

#include <stdio.h>

void testAll() {
	testCreateDestroy();
	testCreateList();
	testIterateList();
	testCopyList();
	testAddPet();
}
/*
  Read pet from standard input and add to pet store
*/
void readPet(MyList* l) {
	printf("Type:");
	char tip[30];
	scanf("%s", tip);
	printf("Species:");
	char spe[30];
	scanf("%s", spe);
	printf("Price:");
	float p;
	scanf("%f", &p);
	addPet(l, tip, spe, p);
	printf("Pet added.\n");
}

void printAllPets(MyList* l) {
	printf("Pets:\n");
	for (int i = 0; i < size(l); i++) {
		Pet p = get(l, i);
		printf("Type:%s Species:%s Price:%f\n", p.type, p.species, p.price);
	}
}
void filterPets(MyList* l) {
	printf("Type filter substring:");
	char filterStr[30];
	scanf("%s", filterStr);
	
	MyList filteredL = getAllPet(l, filterStr);
	printAllPets(&filteredL);
}

void run() {
	MyList allPets = createEmpty();
	int ruleaza = 1;
	while (ruleaza) {
		printf("1 Add\n2 Filter\n3 All\n0 Exit\nCommand:");
		int cmd = 0;
		scanf("%d", &cmd);
		switch (cmd) {
		case 1:
			readPet(&allPets);
			break;
		case 2:
			filterPets(&allPets);
			break;
		case 3:
			printAllPets(&allPets);
			break;
		case 0:
			ruleaza = 0;		
			break;
		default:
			printf("Comanda invalida!!!\n");
		}
	}
}

int main() {
	testAll();
	run();
	return 0;
}