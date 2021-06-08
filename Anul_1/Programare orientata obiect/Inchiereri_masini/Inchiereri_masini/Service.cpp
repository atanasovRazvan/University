#include "Service.h"
#include <assert.h>
#include <string>
#include <iostream>


void Service::addCar_service(const string& _nrI, const string& _prod, const string& _model, const string& _tip)
{
	Car car{ _nrI, _prod, _model, _tip };
	valid.validateCar(car);
	repo.addCar_repo(car);

}


void Service:: removeCar_service(const string& nrI) {
	repo.removeCar_repo(nrI);
}


void Service::updateCar_service(const string & _nrI, const string & _prod, const string & _model, const string & _tip){
	Car car{ _nrI, _prod, _model, _tip };
	valid.validateCar(car);
	repo.updateCar_repo(car);
}

const Lista<Car> Service::getCarsFromList() const{
	return repo.return_list();
}

Car Service::cauta_service(string & nrI){
	return repo.cauta(nrI);
}

const vector<Car> Service::filtrare_prod_service(string & prod)
{
	return repo.filtrare_producator(prod);
}

const vector<Car> Service::filtrare_tip_service(string & tip)
{
	return repo.filtrare_tip(tip);
}

const Lista<Car> Service::sortare_nrI_service()
{
	return repo.sortare_nrI();
}

const Lista<Car> Service::sortare_tip_service()
{
	return repo.sortare_tip();
}
const Lista<Car> Service::sortare_prodMod_service()
{
	return repo.sortare_prodMod();
}

Service::~Service()
{
}

void test_add() {

	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	try {
		v.validateCar(dacia);
		r.addCar_repo(dacia);
		assert(r.get_from_list().size() == 1); assert(true); } catch (ValidateException) { assert(false); }

	Car audi = Car{ "", "", "", "" };
	try {
		v.validateCar(audi); assert(false); }
	catch (ValidateException) {
		assert(true);
	}

	try {
		r.addCar_repo(dacia); assert(false); }
	catch (RepoException) {
		assert(true);
	}

}

void test_remove() {
	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car audi = Car{ "sb13mnw","Audi","Q5","SUV" };
	Car bmw = Car{ "sb13qqq","bmw","X5","SUV" };

	try {
		v.validateCar(dacia);
		r.addCar_repo(dacia);
		r.addCar_repo(audi);
		r.addCar_repo(bmw);
		r.removeCar_repo(bmw.get_nrInmatriculare());
		assert(r.get_from_list().size() == 2); assert(true); } catch (RepoException) { assert(false); }


	try {
		r.removeCar_repo(audi.get_nrInmatriculare());
		assert(r.get_from_list().size() == 1); assert(true); } catch (RepoException) { assert(false); }

	try {
		r.removeCar_repo(dacia.get_nrInmatriculare());
		assert(r.get_from_list().size() == 0); assert(true); } catch (RepoException) { assert(false); }



	try {
		r.removeCar_repo(dacia.get_nrInmatriculare()); assert(false); }

	catch (RepoException) {
		assert(true);
	}
}

void test_update() {
	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car bmw = Car{ "sb13qqq","Audi","Q5","SUV" };
	try {
		v.validateCar(dacia);
		r.addCar_repo(bmw);
		r.addCar_repo(dacia);
		Car audi = Car{ "sb13brb","Audi","Q5","SUV" };
		r.updateCar_repo(audi);
		assert(r.get_from_list().size() == 2);
		vector <Car> c = r.get_from_list();
		assert(c[1].get_producator().compare(audi.get_producator()) == 0);
		assert(true);
	} catch (RepoException) { assert(false); }

	try {
		Car audi = Car{ "sqqqb","Audi","Q5","SUV" };
		r.updateCar_repo(audi); assert(false); }
	catch (RepoException) {
		assert(true);
	}
}

void test_sortareNrI() {
	Lista<Car> l;
	Repo r{ l };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car audi = Car{ "bc13brb","Audi","Q5","SUV" };
	r.addCar_repo(dacia);
	r.addCar_repo(audi);
	Lista<Car> rez = r.sortare_nrI();
	assert(rez.first(rez)->val.get_nrInmatriculare() == audi.get_nrInmatriculare());
	assert(rez.first(rez)->urm->val.get_nrInmatriculare() ==dacia.get_nrInmatriculare());

}
void test_sortareTip() {
	Lista<Car> l;
	Repo r{ l };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car audi = Car{ "sb14brb","Audi","Q5","SUV" };
	r.addCar_repo(dacia);
	r.addCar_repo(audi);
	Lista<Car> rez = r.sortare_tip();
	assert(rez.first(rez)->val.get_nrInmatriculare() == audi.get_nrInmatriculare());
	assert(rez.first(rez)->urm->val.get_nrInmatriculare() == dacia.get_nrInmatriculare());
}
void test_sortareProdMod() {
	Lista<Car> l;
	Repo r{ l };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	Lista<Car> rez = r.sortare_prodMod();
	assert(rez.first(rez)->val.get_nrInmatriculare() == dacia.get_nrInmatriculare());
	assert(rez.first(rez)->urm->val.get_nrInmatriculare() == dacia2.get_nrInmatriculare());
	rez = r.return_list();
}

void test_filtrare_prod() {
	Lista<Car> l;
	Repo r{ l };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	string s = "Dacia";
	vector<Car> rez = r.filtrare_producator(s);
	assert(rez.size() == 2);
}
void test_filtrare_tip() {
	Lista<Car> l;
	Repo r{ l };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	string s = "SUV";
	vector<Car> rez = r.filtrare_tip(s);
	assert(rez.size() == 1);
}

void test_cauta() {
	Lista<Car> l;
	Repo r{ l };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	string s = "sb18brb";
	Car rez = r.cauta(s);
	assert(rez.get_nrInmatriculare() == s);

	try {
		string q = "ssss";
		Car rrez = r.cauta(q); assert(false); }
	catch (RepoException) {
		assert(true);
	}
	
}

void test_addS() {
	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Service s{ r ,v };
	s.addCar_service("sb13brb", "Dacia", "Logan", "berlina");
	assert(r.get_from_list().size() == 1);
}

void test_removeS() {
	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Service s{ r ,v };
	s.addCar_service("sb13brb", "Dacia", "Logan", "berlina");
	s.removeCar_service("sb13brb");
	assert(r.get_from_list().size() == 0);
}

void test_updateS() {
	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Service s{ r ,v };
	s.addCar_service("sb13brb", "Dacia", "Logan", "berlina");
	s.updateCar_service("sb13brb", "Dacia", "Solenza", "berlina");
	assert(r.get_from_list()[0].get_model() == "Solenza");
}

void test_filtrareS() {
	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Service s{ r ,v };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	r.addCar_repo(dacia);
	
	string n = "Dacia";
	string q = "berlina";
	vector<Car> rez =s.filtrare_prod_service(n);
	assert(rez[0].get_nrInmatriculare() == "sb13brb");
	rez=s.filtrare_tip_service(q);
	assert(rez[0].get_nrInmatriculare() == "sb13brb");
}

void test_sortareS() {
	Lista<Car> l;
	Repo r{ l };
	Validate v;
	Service s{ r ,v };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia);
	r.addCar_repo(dacia2);

	Lista<Car> rez = s.sortare_prodMod_service();
	assert(rez.first(rez)->val.get_nrInmatriculare() == dacia.get_nrInmatriculare());
	assert(rez.first(rez)->urm->val.get_nrInmatriculare() == dacia2.get_nrInmatriculare());

	rez = s.sortare_tip_service();
	assert(rez.first(rez)->val.get_nrInmatriculare() == dacia2.get_nrInmatriculare());
	assert(rez.first(rez)->urm->val.get_nrInmatriculare() == dacia.get_nrInmatriculare());

	rez = s.sortare_nrI_service();
	assert(rez.first(rez)->val.get_nrInmatriculare() == dacia.get_nrInmatriculare());
	assert(rez.first(rez)->urm->val.get_nrInmatriculare() == dacia2.get_nrInmatriculare());
	
	string j = "sb13brb";
	Car carr=s.cauta_service(j);
	assert(carr.get_nrInmatriculare() == j);
	Lista<Car> q=s.getCarsFromList();
}

void test_All() {
//for repo
	test_add();
	test_remove();
	test_update();
	test_sortareNrI();
	test_sortareTip();
	test_sortareProdMod();
	test_filtrare_prod();
	test_filtrare_tip();
	test_cauta();
//for service
	test_addS();
	test_removeS();
	test_updateS();
	test_filtrareS();
	test_sortareS();

}
