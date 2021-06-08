#include "Dictionar.h"
#include "Iterator.h"
#include <vector>

using namespace std;

// Complexitate O( sqrt(n) );
bool prim(int x) {

	if (x < 2) return 0;
	if (x == 2) return 1;
	if (x % 2 == 0) return 0;
	for (int d = 3; d*d <= x; d += 2) {

		if (x%d == 0) return 0;

	}
	return 1;

}

// Complexitate O(  )
void get_cap(int &old_cap) {

	int new_cap = 2 * old_cap;
	while (!prim(new_cap)) new_cap++;
	old_cap = new_cap;

}

// constructorul implicit al dictionarului
DO::DO(Relatie r) {

	rel = r;
	size = 0;
	capacity = 7;
	H = new HashTable[capacity];
	for (int i = 0; i < capacity; i++) {

		TElem e;
		e.first = -1;
		e.second = -1;
		HashTable h;
		h.val = e;
		h.urm = -1;
		H[i] = h;

	}

}

// Resize + redispersare
// Complexitate O( size )
void DO::resize() {

	vector <TElem> v;
	int old_cap = capacity;
	get_cap(capacity);

	for (int i = 0; i < old_cap; i++) {
		if (H[i].val.first != -1)
			v.push_back(H[i].val);
	}

	delete[] H;
	HashTable* newH = new HashTable[capacity];

	for (int i = 0; i < capacity; i++) {

		TElem e;
		e.first = -1;
		e.second = -1;
		HashTable h;
		h.val = e;
		h.urm = -1;
		newH[i] = h;

	}

	H = newH;


	for (unsigned int i = 0; i < v.size(); i++) {
		adauga(v[i].first, v[i].second);
	}

}

// adauga o pereche(cheie, valoare) in dictionar
//daca exista deja cheia in dictionar, inlocuieste valoarea asociata cheii si returneaza vechea valoare
// daca nu exista cheia, adauga perechea si returneaza null: NULL_TVALOARE
// Complexitate O( 1 )
TValoare DO::adauga(TCheie c, TValoare v) {

	if (size >= capacity / 2) {
		size = 0;
		resize();
	}
	int i;
	TElem elem;
	elem.first = c;
	elem.second = v;
	int a = abs(c) % capacity;

	if (H[a].val.first == -1) {

		H[a].val = elem;
		size++;
		return NULL_TVALOARE;

	}

	while (H[a].val.first != -1) {
		
		if (H[a].val.first == c) {
			int return_val = H[a].val.second;
			H[a].val.second = elem.second;
			return return_val;
		}
		
		if (H[a].urm == -1) break;
		a = H[a].urm;

	}

	if (H[abs(c)%capacity].val.first != -1) {
		for (i = capacity - 1; i >= 0, H[i].val.first != -1; i--);
		H[i].val = elem;
		int x = abs(c)%capacity;
		while (H[x].urm != -1) {

			x = H[x].urm;

		}
		H[x].urm = i;
		size++;
	}

	return NULL_TVALOARE;

}

//cauta o cheie si returneaza valoarea asociata (daca dictionarul contine cheia) sau null: NULL_TVALOARE
// Complexitate O( 1 )
TValoare DO::cauta(TCheie c) const {

	int x = abs(c) % capacity;

	while (H[x].val.first != c && H[x].urm != -1) {

		x = H[x].urm;

	}
	if (H[x].val.first == c) return H[x].val.second;
	return NULL_TVALOARE;

}


//sterge o cheie si returneaza valoarea asociata (daca exista) sau null: NULL_TVALOARE
// Complexitate O ( 1 )
TValoare DO::sterge(TCheie c) {

	int x = abs(c)%capacity;

	if (size == 0) return NULL_TVALOARE;

	while (H[x].val.first != c && H[x].urm != -1) {
		x = H[x].urm;
	}

	TValoare val = H[x].val.second;

	while (H[x].urm != -1) {

		H[x] = H[H[x].urm];
		x = H[x].urm;

	}

	if (val != NULL_TVALOARE) {

		H[x].val.first = -1;
		H[x].val.second = -1;
		H[x].urm = -1;
		size--;

	}

	return val;

}

//returneaza numarul de perechi (cheie, valoare) din dictionar 
// Complexitate O( 1 )
int DO::dim() const {

	return size;

}

//verifica daca dictionarul e vid 
// Complexitate O( 1 )
bool DO::vid() const {

	return size == 0;

}

// returneaza un vector cu toate valorile dictionarului
// Complexitate O( size )
vector<TValoare> DO::colectiaValorilor() const {

	vector <TValoare> V;
	for (int i = 0; i < size; i++) {
		V.push_back(H[i].val.second);
	}
	return V;

}

// se returneaza iterator pe dictionar
// iteratorul va returna perechile in ordine dupa relatia de ordine (pe cheie)
// Complexitate O( 1 )
Iterator DO::iterator() const {

	return Iterator(*this);

}


// destructorul dictionarului	
DO::~DO() {

	delete[] H;

}

