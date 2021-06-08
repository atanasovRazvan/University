#pragma once
#include "Lista.h"

template <typename T>
class IteratorLista
{
	Lista<T>& l;
	int poz = 0;

public:
	IteratorLista(Lista<T>& _l) noexcept : l{ L } {}
	IteratorLista(Lista<T>& _l, int _poz) noexcept : l{ _l }, poz{_poz}{}

	T& element() const;
	void next();
	bool valid() const;
	T& operator*();
	IteratorLista& operator++();

	bool operator==(IteratorLista& ot) noexcept;
	bool operator!=(IteratorLista& ot) noexcept;
	~IteratorLista();
};


template<typename T>
T& IteratorLista<T>::element() const{
	vector<T> rez = l.get_All();
	return rez[poz];
}

template<typename T>
void IteratorLista<T>::next(){
	poz++;
}

template<typename T>
bool IteratorLista<T>::valid() const {
	return poz < l.get_size();
}

template<typename T>
T& IteratorLista<T>::operator*(){
	return element();
}

template<typename T>
IteratorLista<T>& IteratorLista<T>::operator++() {
	next();
	return *this;
}

template<typename T>
bool IteratorLista<T>::operator==(IteratorLista<T>& ot)noexcept{
	return poz == ot.poz;
}

template<typename T>
bool IteratorLista<T>::operator!=(IteratorLista<T>& ot)noexcept{
	return !(*this == ot);
}
