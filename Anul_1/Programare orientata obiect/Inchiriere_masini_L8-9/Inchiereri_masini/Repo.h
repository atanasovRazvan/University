#pragma once
#include <vector>
#include "car.h"
#include "Lista.h"
typedef Car T;

class RepoVirtual {

private:
	vector <Car> Cars;
	vector <Car> Wash;

public:
	virtual void addCar_repo(const Car& car) = 0;
	virtual void removeCar_repo(const string& nrI) = 0;
	virtual const bool exist_rg(const string& nrI) = 0;
	virtual void updateCar_repo(const Car& car) = 0;
	virtual const vector <Car>& getAll() const = 0;
	virtual const bool exist(const Car& car, const vector<Car> Cars) = 0;
	virtual Car cauta(string& car) = 0;
	virtual const vector <Car> filtrare_producator(string& prod) = 0;
	virtual const vector <Car> filtrare_tip(string& tip) = 0;
	virtual const vector<Car> sortare_nrI() = 0;
	virtual const vector<Car> sortare_tip() = 0;
	virtual const vector<Car> sortare_prodMod() = 0;

	virtual void addCar_Wash(string& car) = 0;
	virtual void goleste_Wash_r() = 0;
	virtual void randomAdd_repo(int total) = 0;
	virtual const vector <Car>& getWash() const = 0;

};

class Repo : public RepoVirtual
{
	
	vector<Car> Cars;
	vector<Car> Wash;

public:
	Repo() = default;

	virtual void addCar_repo(const Car& car) override;
	virtual void removeCar_repo(const string& nrI)override;
	const bool exist_rg(const string& nrI)override;
	virtual void updateCar_repo(const Car& car)override;
	const vector <Car>& getAll() const override;
	const bool exist(const Car& car, const vector<Car> Cars)override;
	Car cauta(string& car)override;
	const vector <Car> filtrare_producator(string& prod)override;
	const vector <Car> filtrare_tip(string& tip)override;
	const vector<Car> sortare_nrI()override;
	const vector<Car> sortare_tip()override;
	const vector<Car> sortare_prodMod()override;

	void addCar_Wash(string& car)override;
	void goleste_Wash_r()override;
	void randomAdd_repo(int total)override;
	const vector <Car>& getWash() const override;

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