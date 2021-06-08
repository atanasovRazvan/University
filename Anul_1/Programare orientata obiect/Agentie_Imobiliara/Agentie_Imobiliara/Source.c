#include <crtdbg.h>
#include "domain.h"
#include "repository.h"
#include "service.h"
#include "console.h"
#define START_SIZE 10

int main() {
	//teste
	teste_oferta();
	teste_repo();
	teste_service();
	//creez obiectele
	repo* rep = creaza_repo(START_SIZE);
	serv* srv = creaza_srv(rep);
	console* cons = creaza_cons(srv);
	//start aplicatie
	startUI(cons);
	//memory leaks
	_CrtDumpMemoryLeaks();
	return 0;
}