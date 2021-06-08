
#include "MyGUI.h"
#include <QtWidgets/QApplication>

#include "Repository.h"
#include "Market.h"
#include "Service.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	RepositoryFile repo("date.in");
	Market market;
	Service service(repo, market);
	MyGUI g(service);
	g.show();
	return a.exec();
}
