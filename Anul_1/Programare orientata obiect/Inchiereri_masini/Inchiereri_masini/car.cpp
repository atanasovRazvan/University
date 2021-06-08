#include "car.h"
#include <iostream>

const bool Car::operator==(const Car & ot)
{
	return this->get_nrInmatriculare() == ot.get_nrInmatriculare();
}

Car::~Car()
{
}
