#include "Repo.h"
#include <algorithm>
#include <string.h>
#include "car.h"
#include <random>


void Repo::addCar_repo(const Car& car)
{
	if (exist(car,Cars))
		throw RepoException("This car already exist!");
	Cars.push_back(car);
}

void Repo::removeCar_repo(const string& nrI)
{
	if(!exist_rg(nrI))
		throw RepoException("This car doesn't exist!");
	else
	{
		for (auto i: Cars)
			if (i.get_nrInmatriculare().compare(nrI) == 0)	
				Cars.erase(remove(Cars.begin(), Cars.end(), i), Cars.end());
	}
}



const bool Repo::exist(const Car& car, const vector<Car> Cars) {
	return find_if(Cars.begin(), Cars.end(), [&](Car C) {return C == car; }) != Cars.end();
}


const bool Repo::exist_rg(const string& nrI) {
	return find_if(Cars.begin(), Cars.end(), [&](Car C) {return C.get_nrInmatriculare().compare(nrI)==0; }) != Cars.end();
}

void Repo::updateCar_repo(const Car & car){
	if(!exist_rg(car.get_nrInmatriculare()))
		throw RepoException("This car doesn't exist!");
	else
	{
		for (auto i: Cars)
			if (i.get_nrInmatriculare().compare(car.get_nrInmatriculare()) == 0) //la pozitia i trebuie facut update
			{ replace(Cars.begin(),Cars.end(),i,car); break; }
	}
}

const vector<Car>& Repo::getAll() const{
	return Cars;
}


const vector<Car> Repo::filtrare_tip(string & tip)
{
	vector<Car> rez(Cars.size()); 
	//copy_if returneaza un iterator pe ultimul element adaugat in rez.
	auto it=copy_if(Cars.begin(), Cars.end(), rez.begin(), [&](Car c) {return c.get_tip().compare(tip) == 0;} );
	rez.resize(distance(rez.begin(), it));

	return rez;
}

Car Repo::cauta(string& nrI){
	if (!exist_rg(nrI))
		throw RepoException("This car doesn't exist!");
	else
	{
		for (auto i: Cars)
			if (i.get_nrInmatriculare().compare(nrI) == 0){
				return i;
			}
	}
	Car n;
	return n;
}

const vector<Car> Repo::filtrare_producator(string & prod){
	vector<Car> rez(Cars.size());
	auto it = copy_if(Cars.begin(), Cars.end(), rez.begin(), [&](Car c) {return c.get_producator().compare(prod) == 0; });
	rez.resize(distance(rez.begin(), it));
	return rez;
}

struct cmp_nrI
{
	inline bool operator() (const Car c1, const Car c2){
		return c1.get_nrInmatriculare().compare(c2.get_nrInmatriculare()) == -1;
	}
};

const vector<Car> Repo::sortare_nrI()
{
	sort(Cars.begin(), Cars.end(), cmp_nrI());
	return Cars;
}

struct cmp_tip
{
	inline bool operator() (const Car c1, const Car c2) {
		return c1.get_tip().compare(c2.get_tip()) == -1;
	}
};

const vector<Car> Repo::sortare_tip()
{
	sort(Cars.begin(), Cars.end(), cmp_tip());

	return Cars;
}


struct cmp_prod
{
	inline bool operator() (const Car c1, const Car c2) {
		return c1.get_producator().compare(c2.get_producator()) == -1;
	}
};

struct cmp_prodMod
{
	inline bool operator() (const Car c1, const Car c2) {
		return (c1.get_producator().compare(c2.get_producator()) == 0 && c1.get_model().compare(c2.get_model()) == -1);
	}
};

const vector<Car> Repo::sortare_prodMod()
{

	sort(Cars.begin(), Cars.end(), cmp_prod());
	sort(Cars.begin(), Cars.end(), cmp_prodMod());
	return Cars;
}

void Repo::addCar_Wash(string& nrI){

	Car car = cauta(nrI);
	if (exist(car, Wash))
		throw RepoException("This car already exist!");
	Wash.push_back(car);
}

void Repo::goleste_Wash_r(){
	Wash.clear();
}

void Repo::randomAdd_repo(int total){

	if (total > Cars.size())
		throw RepoException("Error! Too many cars!\n");
	else {
		std::mt19937 mt{ std::random_device{}() };
		std::uniform_int_distribution<> range(0, Cars.size() - 1);
		int ct = 1;
		while (ct <= total) {
			int rndNr = range(mt);
			if (!exist(Cars[rndNr], Wash)){
				Wash.push_back(Cars[rndNr]);
				ct++;
			}
		}
	}
	

}

const vector<Car>& Repo::getWash() const{
	return Wash;
}



ostream& operator<<(ostream& out, const RepoException& ex) {
	out << ex.message;
	return out;
}

Repo::~Repo()
{
}
