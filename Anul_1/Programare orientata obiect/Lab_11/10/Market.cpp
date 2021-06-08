#include "Market.h"
#include "Domain.h"
#include <iostream>
#include <fstream>
#include <random>

Market::Market()
{

}

void Market::addCar(const Domain &car) {
	if (findCar(car))
		throw MarketException("Exista deja o masina!");
	this->repository.push_back(car);
}



void Market::removeCars()
{
	if (this->size() == 0)
		throw MarketException("Nu este nicio masina adaugata!");

	this->repository.erase(repository.begin(), repository.end());
}

void Market::generateCars(const int &count)
{
	for (int i = 0; i < count; i++) {
		std::string model("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		std::string type("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		std::string number("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		std::string manu("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");

		std::random_device rd;
		std::mt19937 generator(rd());

		std::shuffle(model.begin(), model.end(), generator);
		std::shuffle(type.begin(), type.end(), generator);
		std::shuffle(manu.begin(), manu.end(), generator);
		std::shuffle(number.begin(), number.end(), generator);

		Domain car(number.substr(0, 5), manu.substr(0, 5), model.substr(0, 5), type.substr(0, 5));
		addCar(car);
	}
}

int Market::size() noexcept{
	return this->repository.size();
}

vector<Domain>& Market::getRepository() noexcept{
	return this->repository;
}

bool Market::findCar(const Domain& car) {
	for (auto& carRepo : this->repository)
		if (carRepo == car)
			return true;

	return false;
}

Domain & Market::getCar(const int & index)
{
	return this->repository.at(index);
}

Domain& Market::getCar(const Domain & c)
{
	if (!findCar(c))
		throw MarketException("Masina nu exista!");
	for (auto& car : this->repository)
		if (car == c)
			return car;
	return this->repository.at(0);
}