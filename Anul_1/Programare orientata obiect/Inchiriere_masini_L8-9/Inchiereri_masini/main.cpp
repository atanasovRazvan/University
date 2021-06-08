#include <iostream>
#include "car.h"
#include "Service.h"
#include "Repo.h"
#include "Console.h"
#include "Validate.h"
#include "Lista.h"
#include <assert.h>
#include "RepoFile.h"
#include "Probability.h"

#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>


void test_all() {
	test_All();
}


using namespace std;
int main() {

	{
		
		Repo repo;
		RepoFile repoF{"data.txt" };
		ProbabilityRepo repoP{ 0.5 };
		Validate valid;
		Service ctr{ repoP, valid };
		Console ui{ ctr };
		
	test_all();

	ui.run();
	}
	_CrtDumpMemoryLeaks();

	return 0;
}