#include <iostream>
#include "IteratorDictionar.h"
using namespace std;

// O(1)
IteratorDictionar::IteratorDictionar(const Dictionar& c) : c(c) {

	prim();
}

// O(1)
void IteratorDictionar::prim() {
	iter = 0;
}

// O(1)
void IteratorDictionar::urmator() {
	iter = iter + 1;
}

// O(1)
bool IteratorDictionar::valid() const {
	return iter < c.dim();
}

// O(size)
TElem IteratorDictionar::element() const {
	int contor = 0;
	nod *elem = c.lista.prim;
	while (elem != NULL) {
		if (contor == iter)
			return elem->info;
		elem = elem->urm;
		contor++;
	}
	return c.lista.prim->info;
}