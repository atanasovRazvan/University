#pragma once
//iterator unidirectional pe un Container
#include "Dictionar.h"

#ifndef ITERATORDICTIONAR_H_
#define ITERATORDICTIONAR_H_

using namespace std;

class IteratorDictionar {
	friend class Dictionar;
private:
	//constructorul primeste o referinta catre Container
	//iteratorul va referi primul element din container

	//contine o referinta catre containerul pe care il itereaza
	const Dictionar& c;

	/* aici e reprezentarea specifica a iteratorului*/
	int iter;

public:

	IteratorDictionar(const Dictionar& c);

	//reseteaza pozitia iteratorului la inceputul containerului
	void prim();

	//muta iteratorul in container
	// arunca exceptie daca iteratorul nu e valid
	void urmator();

	//verifica daca iteratorul e valid (indica un element al containerului)
	bool valid() const;

	//returneaza valoarea elementului din container referit de iterator
	//arunca exceptie daca iteratorul nu e valid
	TElem element() const;
};

#endif