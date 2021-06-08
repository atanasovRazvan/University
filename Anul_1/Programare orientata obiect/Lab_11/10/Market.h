#pragma once

#include <vector>
#include <string>

#include "Domain.h"

using std::vector;

class MarketException {
	string msg;
public:
	MarketException(const string& errors) :msg{ errors } {}
	friend std::ostream& operator<<(std::ostream& out, const MarketException& ex) {
		out << ex.msg;
		return out;
	}
};

class Market{
	vector<Domain> repository;
public:
	Market();
	~Market() = default;
	
	int size() noexcept;
	vector<Domain>& getRepository() noexcept;

	void addCar(const Domain &car);
	void removeCars();
	void generateCars(const int &nr);
	bool findCar(const Domain& car);
	Domain& getCar(const int& index);
	Domain& getCar(const Domain& car);
};
