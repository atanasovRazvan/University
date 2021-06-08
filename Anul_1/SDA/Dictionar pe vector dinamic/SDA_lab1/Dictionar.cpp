#include <iostream>
#include <stdlib.h>
#include "Dictionar.h"
#include "IteratorDictionar.h"

using namespace std;

// O(1)
Dictionar::Dictionar() {
	V = (TElem*)malloc(sizeof(TElem) * 5);
	size = 0;
	capacity = 5;
}

// O(1)
Dictionar::~Dictionar() {
	free(V);
}

// O(size)
TValoare  Dictionar::adauga(TCheie c, TValoare v) {
		TElem element;
		element.first = c;
		element.second = v;
		for (int i = 0; i < size; i++) {
			if (V[i].first == c) {
				TValoare val = V[i].second;
				V[i].second = v;
				return val;
			}
		}
		V[size++] = element;
		if (size - 1 == capacity) {

			capacity *= 2;
			TElem *VCopy = (TElem*)malloc(sizeof(TElem) * capacity);
			for (int i = 0; i < size; i++) {
				VCopy[i] = V[i];
			}
			V = VCopy;

		}
		return NULL_TVALOARE;
}

// O(size)
TValoare Dictionar::cauta(TCheie c) const {
	for (int i = 0; i < size; i++)
		if (V[i].first == c) return V[i].second;
	return -1;
}

// O(size^2)
TValoare Dictionar::sterge(TCheie c) {
	
	if (size < capacity/2) {

		capacity /= 2;
		TElem *VCopy = (TElem*)malloc(sizeof(TElem) * capacity);
		for (int i = 0; i < size; i++) {
			VCopy[i] = V[i];
		}
		V = VCopy;

	}
	
	for (int i = 0; i < size; i++)
		if (V[i].first == c) {
			int val = V[i].second; 
			for (int j = i; j < size-1; j++)
				V[j] = V[j + 1];
			size--;
			return val;
		}
	return -1;
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
IteratorDictionar Dictionar::iterator() const{
	return IteratorDictionar(*this);
}

// O(size(d)*size(this))
int Dictionar::adaugaInexistente(Dictionar &d) {

	IteratorDictionar it = d.iterator();
	it.prim();
	int nr = 0;
	while (it.valid())
	{
		TElem elem = it.element();
		if (this->cauta(elem.first) == -1) {
			nr++;
			this->adauga(elem.first, elem.second);
		}
		it.urmator();
	}
	size += nr;
	return nr;
}
