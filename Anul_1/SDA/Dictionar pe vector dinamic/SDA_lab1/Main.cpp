#include <iostream>
#include <crtdbg.h>
#include "TestExtins.h"
#include "TestScurt.h"
#include <stdlib.h>
using namespace std;
int main()
{
	testAll();
	testAllExtins();
	_CrtDumpMemoryLeaks();
}