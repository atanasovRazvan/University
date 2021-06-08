#include <iostream>
#include "car.h"
#include "Service.h"
#include "Repo.h"
#include "Console.h"
#include "Validate.h"
#include "Lista.h"
#include <assert.h>

#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>

void test_all() {
	test_All();
}


using namespace std;
int main() {

	{
		Lista<Car> l;
		Repo repo{ l };
		Validate valid;
		Service ctr{ repo, valid };
		Console ui{ ctr };
		
		test_all();
		ui.run();

	/*
		Car car1 = Car("a", "b", "c", "d");
		Car car2 = Car("m", "n", "o", "p");

		Lista<Car> l;

		l.adauga(car1);
		l.adauga(car2);
		l.adauga(car1);

		assert(l.get_size() == 3);
		l.sterge(car1);
		assert(l.get_size() == 2);
		l.sterge(car1);
		assert(l.get_size() == 1);
		*/
	}
	_CrtDumpMemoryLeaks();

	return 0;
}