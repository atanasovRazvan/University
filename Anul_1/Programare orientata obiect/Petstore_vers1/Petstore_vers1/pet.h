#pragma once
typedef struct {
	char type[100];
	char species[100];
	float price;
} Pet;

/*
Create a new pet
*/
Pet createPet(char* type, char* species, float price);

/*
 Dealocate memory occupied by pet
*/
void destroyPet(Pet* p);

void testCreateDestroy();

