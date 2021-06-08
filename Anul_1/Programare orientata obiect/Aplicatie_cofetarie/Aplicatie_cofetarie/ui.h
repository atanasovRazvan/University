#pragma once
#include "utils.h"
#include "repo.h"
#include "service.h"

//Modulul pentru interactiunea cu utilizatorul

void run(Service* service);
void addIngredientUI(Service* service);
void removeIngredientUI(Service* service);
void displayMenu();
void nameFilterUI(Service* service);
void quantityFilterUI(Service* service);
void sortByNameUI(Service* service);
void sortByQuantityUI(Service* service);
void undoUI(Service* service);
void redoUI(Service* service);