#pragma once	
#include "car.h"
#include <iostream>
#include <vector>

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

	void adauga(const T& el);
	void sterge(T& el);
	Nod<T>* cauta(T& el);
	void modifica(int poz, const T& el2);
	vector<T> get_All();

	Nod<T>*& first(Lista<T>& l) {
		return l.prim;
	}
	const int get_size();



	~Lista() {
		dealoca();
	}

};

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
			if (next->val == el) { pre->urm = next->urm; delete(next); break; }
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
 