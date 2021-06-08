#pragma once
#include <iostream>
#include <vector>
#include "repositoryH.h"

/*
 Clasa care defineste entitatea Repository

 Campurile clasei sunt:
	  - repository

Functiile clasei sunt:
	  - constructor, destructor
	  - addSrv, delSrv, updateSrv (manevreaza repository-ul)
*/
class Service {

private:

	Repository Repo;

public:

	/*
	Functia de adaugare
	Primeste informatii despre o masina
	Adauga masina in repo
	*/
	void addSrv(Masina car);
	/*
	Functia de stergere
	Primeste informatii despre o masina
	Sterge masina din lista
	*/
	void delSrv(Masina car);
	/*
	Functia de modificare
	Primeste informatii despre masini
	Modifica car1 cu car2
	*/
	void updateSrv(Masina car1, Masina car2);
	/*
	Functie getter
	Nu primeste parametri
	Returneaza dimensiunea Repo-ului din service
	*/
	int get_dimSrv() noexcept ;
	/*
	Functie getter
	Primeste un parametru intreg index
	Returneaza masina de la index din Repo
	*/
	Masina get_masinaSrv(int index);

	/*
	Filtrare dupa model
	*/
	vector <Masina> filterCarsByModel(const string Model);

};








































































void __CrtDumpMemoryLeaks( );



