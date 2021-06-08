#pragma once
#include <vector>
#include "car.h"
#include "Lista.h"
#include <fstream>
#include "Repo.h"

typedef Car T;

class RepoFile: public Repo{

	string fileName;
	void loadFromFile();
	void writeToFile();


public:
	RepoFile() = default;
	RepoFile(string _fileName) : Repo(), fileName{ _fileName }{
		loadFromFile();
	}

	void addCar_repo(const Car& car)override;
	void removeCar_repo(const string& nrI) override;
	void updateCar_repo(const Car& car) override;


	~RepoFile();
};
