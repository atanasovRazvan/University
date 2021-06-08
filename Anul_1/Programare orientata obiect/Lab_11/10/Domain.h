#pragma once
#include <string>

using std::string;

class Domain
{
	string registrationNumber;
	string manufacturer;
	string model;
	string type;
public:
	Domain() = default;
	Domain(string registrationNumber, string manufacturer,
		string model, string type) : registrationNumber{ registrationNumber }, manufacturer{manufacturer},
		model{ model }, type{ type }{};

	Domain(const Domain &car);
	
	~Domain();

	string getRegistrationNumber() const;
	string getManufacturer() const;
	string getModel() const;
	string getType() const;
	string getAll() const {
		return registrationNumber + " " + manufacturer + " " + model + " " + type;
	}
	bool operator==(Domain car) const;
};

/*bool equalityRegistrationNumber(const string& reg1, const string& reg2) {
	return reg1 < reg2;
}

bool equalityManufacturer(const Domain& car, const string& value) {
	if (car.getManufacturer() == value)
		return true;
	return false;
}

bool equalityModel(const Domain& car, const string& value) {
	if (car.getModel() == value)
		return true;
	return false;
}

bool equalityType(const Domain& car, const string& value) {
	if (car.getType() == value)
		return true;
	return false;
}*/