#include <iostream>
#include <crtdbg.h>
#include "testsH.h"
#include "userInterfaceH.h"

using namespace std;
int main() {

	//testAll();
	UserInterface ui;
	ui.run();
	_CrtDumpMemoryLeaks( );

}