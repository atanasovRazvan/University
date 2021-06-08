#include "Dictionar.h"
#include "Iterator.h"
#include <vector>
#include <algorithm>
#include <functional>

using namespace std;
using namespace std::placeholders;

// Complexitate O(1)
bool Iterator::sort_func(TElem a, TElem b) {

	return c.rel(a.first, b.first);

}

// Complexitate O( size )
Iterator::Iterator(const DO& c) : c(c) {

	for (int i = 0; i < c.capacity; i++) {
		if(c.H[i].val.first != NULL_TVALOARE)
			V.push_back(c.H[i].val);
	}

	sort(V.begin(), V.end(), std::bind(&Iterator::sort_func, this, _1, _2));
	prim();

}

//reseteaza pozitia iteratorului la inceputul containerului
// Complexitate O( 1 )
void Iterator::prim() {

	iter = 0;

}

//muta iteratorul in container
// arunca exceptie daca iteratorul nu e valid
// Complexitate O( 1 )
void Iterator::urmator() {

	iter++;

}

//verifica daca iteratorul e valid (indica un element al containerului)
// Complexitate O( 1 )
bool Iterator::valid() const {

	return iter < V.size();

}

//returneaza valoarea elementului din container referit de iterator
//arunca exceptie daca iteratorul nu e valid
// Complexitate O( 1 )
TElem Iterator::element() const {

	return V[iter];

}