#include "Domain.h"
#include <string>
#include <iostream>

using std::string;
using std::cout;

Domain::Domain(const Domain &car) : registrationNumber{car.registrationNumber},
manufacturer{ car.manufacturer }, model{ car.model }, type{car.type} {
}

Domain::~Domain()
{
	this->registrationNumber.clear();
	this->manufacturer.clear();
	this->model.clear();
	this->type.clear();
}

string Domain::getRegistrationNumber() const {
	return this->registrationNumber;
}

string Domain::getManufacturer() const {
	return this->manufacturer;
}

string Domain::getModel()  const {
	return this->model;
}

string Domain::getType() const {
	return this->type;
}

bool Domain::operator==(Domain car) const {
	if (this->getRegistrationNumber() == car.getRegistrationNumber())
		return true;
	return false;
}
