#include "Repo.h"
#include <algorithm>
#include <string.h>


void Repo::addCar_repo(const Car& car)
{
	vector<Car> Cars = get_from_list();
	if (exist(car))
		throw RepoException("This car already exist!");

	l.adauga(car);
}

void Repo::removeCar_repo(const string& nrI)
{
	if(!exist_rg(nrI))
		throw RepoException("This car doesn't exist!"); else
	{
		vector<Car> Cars = get_from_list();
		for (int i = 0; i < (int)Cars.size(); i++)
			if (Cars[i].get_nrInmatriculare().compare(nrI) == 0)
			{
				l.sterge(Cars[i]);
				Cars.erase(Cars.begin() + i);
			}
	}
}



const bool Repo::exist(const Car& car) {
	vector<Car> Cars = get_from_list();
	return find_if(Cars.begin(), Cars.end(), [&](Car C) {return C == car; }) != Cars.end();
}


const bool Repo::exist_rg(const string& nrI) {
	vector<Car> Cars = get_from_list();
	return find_if(Cars.begin(), Cars.end(), [&](Car C) {return C.get_nrInmatriculare().compare(nrI)==0; }) != Cars.end();
}

void Repo::updateCar_repo(const Car & car){
	if(!exist_rg(car.get_nrInmatriculare()))
		throw RepoException("This car doesn't exist!"); else
	{
		vector<Car> Cars = get_from_list();
		for (int i = 0; i < (int)Cars.size(); i++)
			if (Cars[i].get_nrInmatriculare().compare(car.get_nrInmatriculare()) == 0) //la pozitia i trebuie facut update
			{ l.modifica(i, car); break; }
	}
}

vector<Car> Repo::get_from_list() {
	return l.get_All();
}

const vector<Car> Repo::filtrare_tip(string & tip)
{
	vector<Car> Cars = get_from_list();	
	vector<Car> rez;
	for (int i = 0; i < (int)Cars.size(); i++)
		if (Cars[i].get_tip().compare(tip) == 0) {
			rez.push_back(Cars[i]);
		}
	return rez;
}

Car Repo::cauta(string& nrI){
	if (!exist_rg(nrI))
		throw RepoException("This car doesn't exist!"); else
	{
		vector<Car> Cars = get_from_list();
		for (int i = 0; i < (int)Cars.size(); i++)
			if (Cars[i].get_nrInmatriculare().compare(nrI) == 0) {
				return Cars[i]; } } Car n; return n;
}

const vector<Car> Repo::filtrare_producator(string & prod){
	vector<Car> Cars = get_from_list();
	vector<Car> rez;
	for (int i = 0; i < (int)Cars.size(); i++)
		if (Cars[i].get_producator().compare(prod) == 0) {
			rez.push_back(Cars[i]);
		}
	return rez;
}



const Lista<Car> Repo::sortare_nrI()
{
	vector<Car> Cars = get_from_list();
	bool sorted = false;
	while (!sorted)
	{
		sorted = true;
		for (int i = 0; i < (int)Cars.size()-1; i++)
				if (Cars[i].get_nrInmatriculare().compare(Cars[i + 1].get_nrInmatriculare()) == 1) {
					swap(Cars[i], Cars[i + 1]);
					sorted = false;
				}	
	}
	Lista<Car> rez;
	for (int i = 0; i < (int)Cars.size(); i++)
		rez.adauga(Cars[i]);
	
	return rez;
}

const Lista<Car> Repo::sortare_tip()
{
	vector<Car> Cars = get_from_list();
	bool sorted = false;
	while (!sorted)
	{
		sorted = true;
		for (int i = 0; i < (int)Cars.size() - 1; i++)
			if (Cars[i].get_tip().compare(Cars[i + 1].get_tip()) == 1) {
				swap(Cars[i], Cars[i + 1]);
				sorted = false;
			}
	}
	Lista<Car> rez;
	for (int i = 0; i < (int)Cars.size(); i++)
		rez.adauga(Cars[i]);

	return rez;
}

const Lista<Car> Repo::sortare_prodMod()
{
	vector<Car> Cars = get_from_list();
	bool sorted = false;
	while (!sorted)
	{
		sorted = true;
		for (int i = 0; i < (int)Cars.size() - 1; i++)
			if (Cars[i].get_producator().compare(Cars[i + 1].get_producator()) == 1) { swap(Cars[i], Cars[i + 1]); sorted = false; } }
	
	sorted = false;
	while (!sorted)
	{
		sorted = true;
		for (int i = 0; i < (int)Cars.size() - 1; i++)
			if (Cars[i].get_producator().compare(Cars[i + 1].get_producator()) == 0 && Cars[i].get_model().compare(Cars[i + 1].get_model()) == 1) {
				swap(Cars[i], Cars[i + 1]);
				sorted = false;
			}

	}

	Lista<Car> rez;
	for (int i = 0; i < (int)Cars.size(); i++)
		rez.adauga(Cars[i]);

	return rez;
}

const Lista<Car>& Repo::return_list() const {
	return l;
}


ostream& operator<<(ostream& out, const RepoException& ex) { out << ex.message; return out; }

Repo::~Repo()
{
}
