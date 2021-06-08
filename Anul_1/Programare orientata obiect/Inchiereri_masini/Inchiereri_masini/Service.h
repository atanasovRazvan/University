#pragma once
#include "Repo.h"
#include "Validate.h"
#include "car.h"
#include "Lista.h"

class Service{
private:
	Repo& repo;
	Validate& valid;
	
public:

	Service(Repo& _repo, Validate& _valid): repo{_repo}, valid{_valid}{}
	
	void addCar_service(const string& _nrI, const string& _prod, const string& _model, const string& _tip);
	void removeCar_service(const string& nrI);
	const vector <Car> getCars() const;
	void updateCar_service(const string& _nrI, const string& _prod, const string& _model, const string& _tip);
	const Lista<Car> getCarsFromList() const;
   Car cauta_service(string& nrI);
   const vector <Car> filtrare_prod_service(string& prod);
   const vector <Car> filtrare_tip_service(string& tip);
   const Lista<Car> sortare_nrI_service();
   const Lista<Car> sortare_tip_service();
   const Lista<Car> sortare_prodMod_service();

	~Service();
};

void test_All();