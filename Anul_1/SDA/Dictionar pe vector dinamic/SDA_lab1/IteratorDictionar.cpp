#include <iostream>
#include "IteratorDictionar.h"
using namespace std;

// O(1)
IteratorDictionar::IteratorDictionar(const Dictionar& c) : c(c){
	
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
bool IteratorDictionar::valid() const{
	return iter < c.dim();
}

// O(1)
TElem IteratorDictionar::element() const{
	return c.V[iter];
}