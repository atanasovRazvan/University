#include "TestExtins.h"
#include "TestScurt.h"
#include <iostream>

using namespace std;

int main() {

	testAll();
	cout << "TEST SCURT OK\n\n";
	testAllExtins();
	cout << "\nTEST EXTINS OK\n\n";
	system("pause");
	return 0;

}