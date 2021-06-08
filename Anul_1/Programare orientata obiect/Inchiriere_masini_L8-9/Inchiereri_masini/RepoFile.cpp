#include "RepoFile.h"
#include <algorithm>
#include <string.h>
#include "car.h"
#include <random>
#include <fstream>

void RepoFile::loadFromFile() {
	ifstream f(fileName);
	
	if (!f.is_open()) {
		string msg("Unable to open the file: " + fileName);
		throw RepoException(msg);
	}

	while (!f.eof()) {
		string nrI;
		string prod;
		string model;
		string tip;
		f >> nrI >> prod >> model >> tip;
		if (tip == "") {
			break;
		}
		Car c{ nrI,prod,model,tip };
		Repo::addCar_repo(c);
	}
	f.close();
}

void RepoFile::writeToFile() {
	ofstream f(fileName);

	if (!f.is_open()) {
		string msg("Unable to open the file: " + fileName);
		throw RepoException(msg);
	}

	for (auto car : Repo::getAll()) {
		f << car.get_nrInmatriculare()<<" ";
		f << car.get_producator()<<" ";
		f << car.get_model()<<" ";
		f << car.get_tip()<<endl;
	}
	f.close();
}


RepoFile::~RepoFile(){
}

void RepoFile::addCar_repo(const Car & car){
	Repo::addCar_repo(car);
	writeToFile();
}

void RepoFile::removeCar_repo(const string & nrI){
	Repo::removeCar_repo(nrI);
	writeToFile();
}

void RepoFile::updateCar_repo(const Car & car){
	Repo::updateCar_repo(car);
	writeToFile();
}
