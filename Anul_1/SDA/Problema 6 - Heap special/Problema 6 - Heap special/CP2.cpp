#include "CP2.h"
#include <exception>


int CP2::getIndex() const
{
	int index = 1;
	if (lenght >= 3 && !relation(elements[index].second, elements[2].second)) {
		index = 2;
	}
	if (lenght >= 4 && !relation(elements[index].second, elements[3].second)) {
		index = 3;
	}
	return index;
}

void CP2::urca(Element elem)
{
	int index = lenght;
	lenght++;

	while (index != 0 && !relation(elements[(index - 1) / 3].second, elem.second)) {
		elements[index] = elements[(index - 1) / 3];
		index = (index - 1) / 3;
	}

	elements[index] = elem;
}

void CP2::coboara(int index)
{
	Element elem = elements[index];
	int i = index;
	int j = 3 * index + 1;
	while (j < lenght) {
		if (j < lenght - 2) {
			int aux1 = j + 1, aux2 = j + 2;
			if (!relation(elements[j].second, elements[aux1].second)) {
				j = aux1;
			}
			if (!relation(elements[j].second, elements[aux2].second)) {
				j = aux2;
			}
		}
		else if (j < lenght - 1) {
			if (!relation(elements[j].second, elements[j + 1].second)) {
				j = j + 1;
			}
		}
		if (!relation(elements[j].second, elem.second)) {
			j = lenght + 1;
		}
		else {
			elements[i] = elements[j];
			i = j;
			j = 3 * j + 1;
		}
	}
	elements[i] = elem;
}

void CP2::resize()
{
	Element * temporary_array = new Element[2 * capacity];
	for (int i = 0; i < lenght; ++i) {
		temporary_array[i] = elements[i];
	}
	delete[] elements;
	elements = temporary_array;
	capacity *= 2;
}

CP2::CP2(Relatie r) : relation{ r }, lenght{ 0 }, capacity{ INITIAL_CAPACITY }
{
	elements = new Element [INITIAL_CAPACITY];
}

void CP2::adauga(TElem e, TPrioritate p)
{
	if (lenght == capacity) {
		resize();
	}
	Element elem = make_pair(e, p);
	urca(elem);
}

Element CP2::element() const
{
	if (areCelMult1()) {
		std::exception e;
		throw e;
	}
	return elements[getIndex()];
}

Element CP2::sterge()
{
	if (areCelMult1()) {
		std::exception e;
		throw e;
	}
	int index = getIndex();
	Element elem_to_return = elements[index];
	elements[index] = elements[lenght - 1];
	lenght--;
	coboara(index);
	return elem_to_return;
}

bool CP2::areCelMult1() const
{
	return (lenght <= 1);
}

CP2::~CP2()
{
	delete[] elements;
}
