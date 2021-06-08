#define _CRTDBG_MAP_ALLOC


#include <stdlib.h>
#include <crtdbg.h>
#include "UI.h"
int main() {
	ui_tests();
	run();
	_CrtDumpMemoryLeaks();
	return 0;
}