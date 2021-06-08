#include "Repository.h"
#include "Domain.h"
#include <iostream>
#include <fstream>

Repository::Repository() {}

void Repository::addCar(const Domain &car) {
	//if (findCar(car))
		//throw RepoException("Exista deja o masina!");
	this->repository.push_back(car);
}

void Repository::removeCar(const int &index) {
	if (index < 0 || index >= this->size())
		throw RepoException("Masina selectata nu exista!");
	this->repository.erase(this->repository.begin() + index);
}

void Repository::modifyCar(const Domain &car) {
	if (!findCar(car))
		throw RepoException("Masina nu exista!");
	for (auto& carRepo : this->repository)
		if (carRepo == car)
			carRepo = car;
}

int Repository::size() noexcept{
	return this->repository.size();
}

vector<Domain>& Repository::getRepository() noexcept{
	return this->repository;
}

bool Repository::findCar(const Domain& car) {
	for (auto& carRepo : this->repository)
		if (carRepo == car)
			return true;

	return false;
}

Domain& Repository::getCar(const int & index)
{
	return this->repository.at(index);
}

Domain& Repository::getCar(const Domain & c)
{
	if (!findCar(c))
		throw RepoException("Masina nu exista!");
	for (auto& car : this->repository)
		if (car == c)
			return car;
	return this->repository.at(0);
}

void RepositoryFile::loadFromFile() {
	std::ifstream in(fName);
	if (!in.is_open()) { //verify if the stream is opened		
		throw RepoException("Unable to open file:" + fName);
	}
	while (!in.eof()) {
		//number, manu, model, type
		std::string number;
		in >> number;
		std::string manu;
		in >> manu;
		std::string model;
		in >> model;
		std::string type;
		in >> type;

		Domain p{ number, manu, model, type};
		Repository::addCar(p);
	}
	in.close();
}

void RepositoryFile::writeToFile() {
	std::ofstream out(fName);
	if (!out.is_open()) { //verify if the stream is opened
		std::string msg("Unable to open file:");
		throw RepoException(msg);
	}
	for (auto& p : getRepository()) {
		out << p.getRegistrationNumber();
		out << std::endl;
		out << p.getManufacturer();
		out << std::endl;
		out << p.getModel();
		out << std::endl;
		out << p.getType();
		out << std::endl;
	}
	out.close();
}