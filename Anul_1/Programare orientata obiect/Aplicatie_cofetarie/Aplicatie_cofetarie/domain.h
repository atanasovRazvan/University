#pragma once

//Modul pentru definirea entitatilor din aplicatie

typedef struct {
	char* name;
	char* manufacturer;
	float quantity;
}Ingredient;

/*
	Description: creeaza o materie prima

	In:
		- name - numele
		- manufacturer - producatorul
		- quantity - cantitatea

	Out:
		- ing - pointer la materia prima
*/
Ingredient* createIngredient(char* name, char* manufacturer, float quantity);

//Description: returneaza numele materiei prime
char* getName(Ingredient* ingr);

//Description: seteaza numele materiei prime
void setName(Ingredient* ingr, char* name);

//Description: returneaza producatorul materiei prime
char* getManufacturer(Ingredient* ingr);

//Description: seteaza producatorul materiei prime
void setManufacturer(Ingredient* ingr, char* manufacturer);

//Description: returneaza cantitatea materiei prime
float getQuantity(Ingredient* ingr);

//Description: seteaza cantitatea materiei prime
void setQuantity(Ingredient* ingr, float quantity);

//Description: elibereaza memoria unei materii prime(distruge materia prima)
void destroyIngredient(Ingredient* ingr);

//Description: valideaza o materie prima
int validate(Ingredient* ingr);

Ingredient* copyIng(Ingredient* ing);