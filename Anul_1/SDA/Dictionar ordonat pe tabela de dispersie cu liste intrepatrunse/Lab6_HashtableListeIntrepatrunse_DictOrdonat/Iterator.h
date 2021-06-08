#pragma once

#include <iostream>
#include <vector>

#ifndef ITERATOR_H_
#define ITERATOR_H_

using namespace std;

typedef int TCheie;
typedef int TValoare;

typedef std::pair<TCheie, TValoare> TElem;

class DO;

//iterator unidirectional pe un Container
class Iterator {
private:

	friend class DO;
	unsigned int iter;

	vector <TElem> V;

	//contine o referinta catre containerul pe care il itereaza
	const DO& c;

	/* aici e reprezentarea specifica a iteratorului*/

public:

	Iterator(const DO& c);

	//reseteaza pozitia iteratorului la inceputul containerului
	void prim();

	bool sort_func(TElem a, TElem b);

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