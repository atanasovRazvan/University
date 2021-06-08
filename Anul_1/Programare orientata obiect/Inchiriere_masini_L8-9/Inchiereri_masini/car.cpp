#include "car.h"
#include <iostream>
#include <algorithm>



const bool Car::operator==(const Car & ot)
{
	return this->get_nrInmatriculare() == ot.get_nrInmatriculare();
}

Car::~Car()
{
}

string DTO::getType() const {

	return type;

}

int DTO::getNumber() const {

	return number;

}
