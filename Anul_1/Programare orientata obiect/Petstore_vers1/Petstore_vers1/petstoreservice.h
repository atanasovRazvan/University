#pragma once

#include "MyList.h"
/*
Add a pet to the store
*/
int addPet(MyList* l, char* type, char* species, float price);

/*
  Filter pets in the store 
  typeSubstring - cstring
  return all pets where typeSubstring is a substring of the type
*/
MyList getAllPet(MyList* l, char* typeSubstring);


void testAddPet();