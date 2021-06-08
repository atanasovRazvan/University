#pragma once
#include <vector>
#include "car.h"
#include "Lista.h"
typedef Car T;

class Repo
{
	Lista<T> l;

public:
	Repo(Lista<T>& _l) : l{ _l }{}

	void addCar_repo(const Car& car);
	void removeCar_repo(const string& nrI);
	const bool exist_rg(const string& nrI);
	void updateCar_repo(const Car& car);

	//const vector <Car>& getAll() const ;
	const bool exist(const Car& car);
	vector<Car> get_from_list();
	Car cauta(string& car);
	const vector <Car> filtrare_producator(string& prod);
	const vector <Car> filtrare_tip(string& tip);
	const Lista<Car> sortare_nrI();
	const Lista<Car> sortare_tip();
	const Lista<Car> sortare_prodMod();

	const Lista<Car>& return_list() const;
	~Repo();
};

class RepoException
{
	string message;

public:
	RepoException(string _message): message{ _message }{}
	friend ostream& operator<<(ostream& out, const RepoException& ex);

};
ostream& operator<<(ostream& out, const RepoException& ex);