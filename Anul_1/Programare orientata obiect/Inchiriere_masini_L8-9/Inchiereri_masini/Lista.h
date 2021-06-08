#pragma once	
#include "car.h"
#include <iostream>
#include <vector>
#include <assert.h>

template <typename T>
class IteratorLista;

template <typename T>
class Nod {

public:
	T val;
	Nod<T>* urm;
	Nod(const T& _val) : val{ _val }, urm{ nullptr }{}
	Nod(const T& _val, Nod<T>* _ptr) : val{ _val }, urm{ _ptr }{}

};





template<typename T>
class Lista
{
private:
	
	Nod<T>* prim;
	Nod<T>* copie(Nod<T>* nodCurent) {
		if (nodCurent == nullptr)
			return nullptr;
		return new Nod<T>{ nodCurent->val, copie(nodCurent->urm) }; //Facem o copie fiecarui nod
	}

	void dealoca() {
		while (prim != nullptr) {
			auto rez = prim->urm;
			delete prim;
			prim = rez;
		}
	}

public:

	Lista() : prim{ nullptr } {}
	
	Lista(const Lista& ot) { //returneaza o copie!!
		prim = copie(ot.prim);
	}

	Lista<T>& operator=(Lista<T> ot) {
		dealoca();
		prim = copie(ot.prim);
		return *this;
	}


	void adauga_inceput(const T& el) {
		prim = new Nod<T>(el, prim);
	}
      T& at(const int &idx) {
		auto cr = prim;
		for (int i = 0; i < idx; i++) {
			cr = cr->urm;
		}
		return cr->val;
	}
	void adauga(const T& el);
	void sterge(T& el);
	Nod<T>* cauta(T& el);
	void modifica(int poz, const T& el2);
	vector<T> get_All();
	const int get_size();




	IteratorLista<T> iterator() const;
	IteratorLista<T> begin();
	IteratorLista<T> end();
	Nod<T>*& first(Lista<T>& l) {
		return l.prim;
	}
	~Lista() {
		dealoca();
	}

};

template<typename T>
IteratorLista<T> Lista<T>::begin(){
	return IteratorLista<T>(*this);
}

template<typename T>
IteratorLista<T> Lista<T>::end(){
	int lg = get_size();
	return IteratorLista<T>(*this, lg);	
}

template <typename T>
const int Lista<T>::get_size() {
	int lg = 0;
	auto r = prim;
	while (r != nullptr) {
		lg++;
		r = r->urm;
	}
	return lg;
}

//template<typename T>
//IteratorLista<T> Lista<T>::iterator() const{
//
//	
//}

template <typename T>
void Lista<T>::adauga(const T& el) {

	if (prim == nullptr) {
		prim = new Nod<T>(el, prim);
	}
	else {
		Nod<T>* nou = new Nod<T>(el, nullptr);
		if (prim->urm == nullptr) {  //adauga dupa prim
			prim->urm = nou;
		}
		else {
			auto it = prim;
			while (it->urm != nullptr) { //adauga dupa ultim
				it = it->urm;
			}
			it->urm = nou;
		}
	}
}

template <typename T>
void Lista<T>::sterge(T& el) {

	if (prim->val == el) {
		auto next = prim->urm;
		delete prim;
		prim = next;
	}
	else {
		auto next = prim->urm;
		auto pre = prim;
		while (next->urm != nullptr) {
			if (next->val == el) {
				pre->urm = next->urm;
				delete(next);
				break;
			}
			pre = next;
			next = next->urm;
		}
		if (next->urm == nullptr && next->val == el) //daca e ultimul
		{
			delete(next);
			pre->urm = nullptr;
		}

	}
}


template <typename T>
void Lista<T>::modifica(int poz, const T& el2) {
	auto it = prim;
	int i = 0;
	
	while (it != nullptr) {
		if (i == poz) {
			it->val = el2;
			break;
		}
		it=it->urm;
		i++;
	}
}

template <typename T>
Nod<T>* Lista<T>::cauta(T& el) {
	auto it = prim;
	while (it != nullptr) {
		if (it->val == el)
			return it;
	}
}

template <typename T>
vector<T> Lista<T>::get_All() {

	vector<T> rez;
	auto it = prim;
		while (it != nullptr) {
			rez.push_back(it->val);
			it = it->urm;
		}
	return rez;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

template <typename T>
class IteratorLista
{
	Lista<T>& l;
	int poz = 0;

public:
	IteratorLista(Lista<T>& _l) noexcept : l{ _l } {}
	IteratorLista(Lista<T>& _l, int _poz) noexcept : l{ _l }, poz{ _poz }{}

	T& element() const;
	void next();
	bool valid() const;


	IteratorLista operator=(const IteratorLista& ot) noexcept {
		l = ot.l;
		poz = ot.poz;
	}
	T& operator*();
	IteratorLista& operator++();
	bool operator==(const IteratorLista& ot) noexcept;
	bool operator!=(const IteratorLista& ot) noexcept;
	~IteratorLista() = default;
};


template<typename T>
T& IteratorLista<T>::element() const {
	return l.at(poz);
}

template<typename T>
void IteratorLista<T>::next() {
	poz++;
}

template<typename T>
bool IteratorLista<T>::valid() const {
	return poz < l.get_size();
}

template<typename T>
T& IteratorLista<T>::operator*() {
	return element();
}

template<typename T>
IteratorLista<T>& IteratorLista<T>::operator++() {
	next();
	return *this;
}

template<typename T>
bool IteratorLista<T>::operator==(const IteratorLista<T>& ot)noexcept {
	return poz == ot.poz;
}

template<typename T>
bool IteratorLista<T>::operator!=(const IteratorLista<T>& ot)noexcept {
	return !(*this == ot);
}


inline void test_it() {
	Lista<Car> l;
	Car car;
	car = Car("a", "b", "c", "d");
	Car car2 = Car("q", "b", "c", "d");

	l.adauga(car);
	l.adauga(car2);

	IteratorLista<Car> it = IteratorLista<Car>(l);
	assert(it.valid() == true);
	assert(it.element().get_nrInmatriculare()=="a");
	it.next();
	assert(it.element().get_nrInmatriculare() == "q");

	
	IteratorLista<Car> it2 = IteratorLista<Car>(l);
	assert(it2 != it);
	assert(!(it2 == it)); //++, *, begin, end
	
	IteratorLista<Car> beg = l.begin();
	assert(beg.element().get_nrInmatriculare() == "a");
	IteratorLista<Car> end = l.end();
	assert(end.valid() == false);
}