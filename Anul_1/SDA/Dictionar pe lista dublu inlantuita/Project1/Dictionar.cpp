#include <iostream>
#include <stdlib.h>
#include "Dictionar.h"
#include "IteratorDictionar.h"

using namespace std;

// O(1)
Dictionar::Dictionar() {
	lista.prim = NULL;
	lista.ultim = NULL;
	size = 0;
}

// O(1)
Dictionar::~Dictionar() {
	;
}

// O(size)
TValoare  Dictionar::adauga(TCheie c, TValoare v) {
	
	nod *x = new nod;
	x->prev = NULL;
	x->urm = NULL;
	x->info.first = c;
	x->info.second = v;
	if (lista.prim == NULL) {
		lista.prim = x;
		size++;
		return NULL_TVALOARE;
	}

	if (lista.ultim == NULL && lista.prim->info.first!=c) {
		x->prev = lista.prim;
		lista.ultim = x;
		lista.prim->urm = lista.ultim;
		size++;
		return NULL_TVALOARE;
	}

	nod *primul = lista.prim;
	while (primul != NULL)
	{	
		if (primul->info.first == c) {
			TValoare val = primul->info.second;
			primul->info.second = v;
			return val;
		}
		primul = primul->urm;
	}
	lista.ultim->urm = x;
	x->prev = lista.ultim;
	lista.ultim = x;
	size++;
	return NULL_TVALOARE;
}

// O(size)
TValoare Dictionar::cauta(TCheie c) const {
	nod *primul = lista.prim;
	while (primul != NULL)
	{
		if (primul->info.first == c)
			return primul->info.second;
		primul = primul->urm;
	}
	return -1;
}

// O(size)
TValoare Dictionar::sterge(TCheie c) {
	
	if (lista.prim == NULL) return NULL_TVALOARE;
	
	if (lista.prim->info.first == c) {
		
		if (size == 1) {
			lista.ultim = NULL;
		}
		
		if (lista.ultim == NULL) {
			TValoare val = lista.prim->info.second;
			lista.prim = NULL;
			size--;
			return val;
		}

		TValoare val = lista.prim->info.second;
		lista.prim = lista.prim->urm;
		lista.prim->prev = NULL;
		size--;
		return val;
	}

	if (lista.ultim->info.first == c) {
		TValoare val = lista.ultim->info.second;
		lista.ultim = lista.ultim->prev;
		lista.ultim->urm = NULL;
		size--;
		return val;
	}

	nod *primul = lista.prim;
	int w=0;
	while (primul != NULL)
	{
		if (primul->info.first == c) {
			TValoare val = primul->info.second;
			primul->prev->urm = primul->urm;
			primul->urm->prev = primul->prev;
			primul = NULL;
			size--;
			return val;
		}
		primul = primul->urm;
	}
	
	return NULL_TVALOARE;
}

// O(1)
int Dictionar::dim() const {
	
	return size;
}

// O(1)
bool Dictionar::vid() const {
	return size == 0;
}

// O(1)
IteratorDictionar Dictionar::iterator() const {
	return IteratorDictionar(*this);
}

// O(size(d)*size(this))
int Dictionar::actualizeazaValori(Dictionar &d) {

	IteratorDictionar it = d.iterator(); it.prim();
	int nr = 0;
	while (it.valid()) {
		TElem elem = it.element();
		if (this->cauta(elem.first) != -1) {
			nr++;
			this->adauga(elem.first, elem.second);

		}
		it.urmator();
	}
	return nr;
}