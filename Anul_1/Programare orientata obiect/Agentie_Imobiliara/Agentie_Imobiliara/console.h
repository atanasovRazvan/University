#pragma once
#include "domain.h"
#include "repository.h"
#include "service.h"
/*
	Structura console are un camp reprezentand un pointer la o structura srv.
*/
typedef struct {
	serv* srv;
}console;
//functii creaza si distruge
console* creaza_cons(serv* srv);
void distruge_cons(console* cons);
//functia necesara pentru a porni aplicatia.
void startUI(console* cons);