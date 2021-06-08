#pragma once
#include "domainH.h"
#include <vector>
#include <iostream>
using namespace std;


/*
 Clasa care defineste entitatea Repository

 Campurile clasei sunt:
	  - repo (lista de Masina)

Functiile clasei sunt:
	  - constructor, destructor
	  - add, del, update (manevreaza vectorul)
*/
class Repository {
private:

	vector <Masina> repo;

public:

	/*
	Functia de adaugare
	Primeste o masina
	Adauga masina in lista
	*/
	void add(const Masina &car);
	/*
	Functia de stergere
	Primeste o masina
	Sterge masina din lista
	*/
	void del(Masina &car);
	/*
	Functia de modificare
	Primeste o masina
	Modifica masina
	*/
	void update(Masina &car1, Masina &car2);
	/*
	Functie getter
	Nu primeste parametri
	Returneaza dimensiunea listei
	*/
	int get_dim() noexcept ;
	/*
	Functie getter
	Primeste un parametru intreg index
	Returneaza masina de la index
	*/
	Masina get_masina(int index);
	/*
	Destructorul clasei
	*/

	vector <Masina> get_all();

};
