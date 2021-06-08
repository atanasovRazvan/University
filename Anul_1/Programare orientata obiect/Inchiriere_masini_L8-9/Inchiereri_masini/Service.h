#pragma once
#include "Repo.h"
#include "Validate.h"
#include "car.h"
#include "Lista.h"
#include "Undo.h"
#include <string>
#include <map>
#include <vector>
#include <algorithm>

class Service{
private:
	Repo& repo;
	Validate& valid;
	vector <ActiuneUndo*> actiuneUndo;

public:

	Service(Repo& _repo, Validate& _valid): repo{_repo}, valid{_valid}{}
	
	void addCar_service(const string& _nrI, const string& _prod, const string& _model, const string& _tip);
	void removeCar_service(string& nrI);
   const vector <Car> getCars() const;
   void updateCar_service(const string& _nrI, const string& _prod, const string& _model, const string& _tip);
   Car cauta_service(string& nrI);
   const vector <Car> filtrare_prod_service(string& prod);
   const vector <Car> filtrare_tip_service(string& tip);
   const vector<Car> sortare_nrI_service();
   const vector<Car> sortare_tip_service();
   const vector<Car> sortare_prodMod_service();
   void addCar_Wash_s(string& nrI);
   const vector <Car> getAllWash() const;
   void goleste_Wash_s();
   void randomAdd_service(int total);
   void undo();

   vector <DTO> raport() {

	   map <string, int> statistica;
	   for (auto &car : repo.getAll()) {
		   statistica[car.get_tip()]++;
	   }

	   vector <DTO> lista;
	   for_each(statistica.begin(), statistica.end(), [&](map<string, int>::value_type & it) {lista.push_back(DTO(it.first, it.second)); });
	   return lista;
   }

	~Service();
};

void test_All();