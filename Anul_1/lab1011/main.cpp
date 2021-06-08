#include "Lab1011.h"
#include "UI.h"
#include "test.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
	test();
	QApplication a(argc, argv);
	FileRepo repo{ "apartamente.txt" };
	Service sv{ repo };
	Lab1011 w{ sv };
	w.show();
	return a.exec();
	//UI ui{ sv };
	//ui.run();
	//return 0;
}


