#pragma once

#include <vector>
#include <string>

#include "Domain.h"

using std::vector;

class RepoException {
	string msg;
public:
	RepoException(const string& errors) :msg{ errors } {}
	friend std::ostream& operator<<(std::ostream& out, const RepoException& ex) {
		out << ex.msg;
		return out;
	}
};

class Repository {
	vector<Domain> repository;
public:
	Repository();
	~Repository() = default;

	int size() noexcept;
	vector<Domain>& getRepository() noexcept;

	virtual void addCar(const Domain &car);
	virtual void removeCar(const int &index);
	virtual void modifyCar(const Domain &car);
	virtual bool findCar(const Domain& car);
	Domain& getCar(const int& index);
	Domain& getCar(const Domain& car);
};

class RepositoryFile :public Repository {
private:
	std::string fName;
	void loadFromFile();
	void writeToFile();
public:
	RepositoryFile(std::string fName) :Repository(), fName{ fName } {
		loadFromFile();
	}
	void addCar(const Domain& car) override {
		Repository::addCar(car);
		writeToFile();
	}
	void removeCar(const int& index) override {
		Repository::removeCar(index);//apelam metoda din clasa de baza
		writeToFile();
	}

	void modifyCar(const Domain &car) {
		Repository::modifyCar(car);
		writeToFile();
	}
};
