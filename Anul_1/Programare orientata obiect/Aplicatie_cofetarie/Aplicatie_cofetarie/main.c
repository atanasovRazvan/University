//Modulul principal al aplicatiei
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#include <stdio.h>
#include "repo.h"
#include "service.h"
#include "ui.h"
#include "tests.h"

int main() {
	Repository* myRepo = createRepo(createVector(destroyIngredient));
	Service* myService = createService(myRepo);
	testFunctions();
	//run(myService);
	destroyService(myService);
	_CrtDumpMemoryLeaks();
	return 0;
}