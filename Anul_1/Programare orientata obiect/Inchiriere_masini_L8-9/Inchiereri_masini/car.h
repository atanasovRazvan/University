#pragma once
#include <string>
#include <iostream>

static int ct = 0;

using namespace std;
class Car
{
private:

	std::string nrInmatriculare;
	std::string producator;
	std::string model;
	std::string tip;

public:

	Car() :
		nrInmatriculare{ "" },
		producator{ "" },
		model{ "" },
		tip{ "" }{}

	Car(const string _nrInmatriculare, const string _producator, const string _model, const string _tip) :
		nrInmatriculare{ _nrInmatriculare },
		producator{ _producator },
		model{ _model },
		tip{ _tip }{}//ct++; cout << ct << ' '; }
		

/*	Car(const Car& car):
		nrInmatriculare{ car.get_nrInmatriculare()},
		producator{ car.get_producator() },
		model{ car.get_model() },
		tip{ car.get_tip() }{}
		*/



	 string get_nrInmatriculare() const {
		return nrInmatriculare;
	}

	 string get_producator() const {
		return producator;
	}

	string get_model() const {
		return model;
	}

	 string get_tip() const {
		return tip;
	}

	 bool cmp_nrI(const Car c1, const Car c2);
	 const bool cmp_tip(Car c1, Car c2);
	 const bool cmp_prod(Car c1, Car c2);
	 const bool cmp_mod(Car c1, Car c2);


	 const bool cmp(const Car& car1, const Car& car2);
	 const bool operator==(const Car& ot);

	 ~Car();
};

class DTO {

private:
	int number = 0;
	string type;

public:
	DTO(const string type, const int nr) :type{ type }, number{ nr } {}
	string getType() const;
	int getNumber() const;

};
