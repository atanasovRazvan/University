#include "VectDinSmartPointer.h"


/*
Constructor default
Alocam loc pentru 5 elemente
*/
VectDinSmartPointer::VectDinSmartPointer() :elems{ std::make_unique<Element[]>(INITIAL_CAPACITY) }, cap{ INITIAL_CAPACITY }, lg{ 0 } {}


VectDinSmartPointer::VectDinSmartPointer(const VectDinSmartPointer& ot): elems{ std::make_unique<Element[]>(ot.cap) }, cap{ ot.cap}, lg{ ot.lg } {
	for (int i = 0; i < lg; i++) {
		elems[i] = ot.elems[i];
	}
}

VectDinSmartPointer& VectDinSmartPointer::operator=(const VectDinSmartPointer& ot) {
	if (this == &ot) {
		return *this;//avoid self asignment problems
	}
	elems = std::make_unique<Element[]>(ot.cap);
	for (int i = 0; i < ot.lg; i++) {
		elems[i] = ot.elems[i];
	}
	cap = ot.cap;
	lg = ot.lg;
	return *this;
}

void VectDinSmartPointer::add(const Element& el) {
	ensureCapacity();//daca e nevoie mai alocam memorie
	elems[lg++] = el;
}

Element& VectDinSmartPointer::get(int poz) const {
	return elems[poz];
}

void VectDinSmartPointer::set(int poz, const Element& el) {
	elems[poz] = el;
}

int VectDinSmartPointer::size() const noexcept {
	return lg;
}

void VectDinSmartPointer::ensureCapacity() {
	if (lg < cap) {
		return; //mai avem loc
	}
	cap *= 2;
	auto aux = std::make_unique<Element[]>(cap);	
	for (int i = 0; i < lg; i++) {
		aux[i] = elems[i];
	}
	elems.reset();
	std::swap(elems, aux);
}


IteratorVectorSP VectDinSmartPointer::begin()
{
	return IteratorVectorSP(*this);
}

IteratorVectorSP VectDinSmartPointer::end()
{
	return IteratorVectorSP(*this, lg);
}


IteratorVectorSP::IteratorVectorSP(const VectDinSmartPointer& v) noexcept:v{ v } {}
IteratorVectorSP::IteratorVectorSP(const VectDinSmartPointer& v, int poz)noexcept : v{ v }, poz{ poz } {}

bool IteratorVectorSP::valid()const noexcept {
	return poz < v.lg;
}
Element& IteratorVectorSP::element() const noexcept {
	return v.elems[poz];
}
void IteratorVectorSP::next() noexcept{
	poz++;
}
Element& IteratorVectorSP::operator*() {
	return element();
}
IteratorVectorSP& IteratorVectorSP::operator++() {
	next();
	return *this;
}
bool IteratorVectorSP::operator==(const IteratorVectorSP& ot) noexcept {
	return poz == ot.poz;
}
bool IteratorVectorSP::operator!=(const IteratorVectorSP& ot)noexcept {
	return !(*this == ot);
}
