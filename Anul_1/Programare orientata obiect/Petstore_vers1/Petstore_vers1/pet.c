#include "pet.h"

#include <string>
#include <assert>
/*
Create a new pet
*/
Pet createPet(char* type, char* species, float price) {
	Pet rez;
	strcpy(rez.type, type);
	strcpy(rez.species, species);
	rez.price = price;
	return rez;
}

/*
Dealocate memory occupied by pet
*/
void destroyPet(Pet* p) {
	//no memory is allocated on the heap
	//nothing to dealocate
	//mark that pet is destroyed, avoid accidental use after destroy
	p->type[0] = '\0';
	p->species[0] = '\0';
	p->price = -1;
}

void testCreateDestroy() {
	Pet p = createPet("dog", "buldog", 100);
	assert(strcmp(p.type, "dog") == 0);
	assert(strcmp(p.species, "buldog") == 0);
	assert(p.price == 100.0);

	destroyPet(&p);
	assert(strlen(p.type) == 0);
	assert(strlen(p.species) == 0);
	assert(p.price==-1);
}