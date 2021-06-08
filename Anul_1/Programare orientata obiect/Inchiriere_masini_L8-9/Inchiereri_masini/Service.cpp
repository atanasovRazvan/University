#include "Service.h"
#include "Probability.h"
#include <assert.h>



void Service::addCar_service(const string& _nrI, const string& _prod, const string& _model, const string& _tip)
{
	Car car{ _nrI, _prod, _model, _tip };
	valid.validateCar(car);
	
	try
	{
		repo.addCar_repo(car);
		actiuneUndo.push_back(new UndoAdd{ repo, car });
	}
	catch (const ProbabilityError)
	{
		cout << "Nu a fost adaugat.";
	}

}


void Service:: removeCar_service(string& nrI) {

	Car car=repo.cauta(nrI);
	repo.removeCar_repo(nrI);
	actiuneUndo.push_back(new UndoDelete{ repo, car });
}

const vector<Car> Service::getCars() const{
	return repo.getAll();
}


void Service::updateCar_service(const string & _nrI, const string & _prod, const string & _model, const string & _tip){
	Car car{ _nrI, _prod, _model, _tip };
	valid.validateCar(car);
	repo.updateCar_repo(car);
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

const vector<Car> Service::sortare_nrI_service()
{
	return repo.sortare_nrI();
}

const vector<Car> Service::sortare_tip_service()
{
	return repo.sortare_tip();
}
const vector<Car> Service::sortare_prodMod_service()
{
	return repo.sortare_prodMod();
}

void Service::addCar_Wash_s(string & nrI){
	repo.addCar_Wash(nrI);
}

const vector<Car> Service::getAllWash() const{
	return repo.getWash();
}

void Service::goleste_Wash_s(){
	repo.goleste_Wash_r();
}

void Service::randomAdd_service(int total){
	repo.randomAdd_repo(total);
}

void Service::undo() {
	if (actiuneUndo.empty()) {
		throw RepoException{"Nu mai exista operatii" };
	}

	ActiuneUndo* act = actiuneUndo.back();
	act->doUndo();
	actiuneUndo.pop_back();
	delete act;
}


Service::~Service()
{
}


void test_add() {
	
	Repo r;
	Validate v;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	try {
		v.validateCar(dacia);
		r.addCar_repo(dacia);
		assert(r.getAll().size() == 1);
		Car o;
		assert(true);
	}
	catch (ValidateException) {
		assert(false);
	}

	Car audi = Car{ "", "", "", "" };
	try {
		v.validateCar(audi);
		assert(false);
	}
	catch (ValidateException) {
		assert(true);
	}

	try {
		r.addCar_repo(dacia);
		assert(false);
	}
	catch (RepoException) {
		assert(true);
	}

}

void test_remove() {
	Repo r;
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
		assert(r.getAll().size() == 2);
		assert(true);
	}

	catch (RepoException) {
		assert(false);
	}


	try {
		r.removeCar_repo(audi.get_nrInmatriculare());
		assert(r.getAll().size() == 1);
		assert(true);
	}

	catch (RepoException) {
		assert(false);
	}

	try {
		r.removeCar_repo(dacia.get_nrInmatriculare());
		assert(r.getAll().size() == 0);
		assert(true);
	}

	catch (RepoException) {
		assert(false);
	}



	try {
		r.removeCar_repo(dacia.get_nrInmatriculare());
		assert(false);
	}

	catch (RepoException) {
		assert(true);
	}
}

void test_update() {
	Repo r;
	Validate v;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car bmw = Car{ "sb13qqq","Audi","Q5","SUV" };
	try {
		v.validateCar(dacia);
		r.addCar_repo(bmw);
		r.addCar_repo(dacia);
		Car audi = Car{ "sb13brb","Audi","Q5","SUV" };
		r.updateCar_repo(audi);
		assert(r.getAll().size() == 2);
		vector <Car> c = r.getAll();
		assert(c[1].get_producator().compare(audi.get_producator()) == 0);
		assert(true);
	}
	catch (RepoException) {
		assert(false);
	}

	try {
		Car audi = Car{ "sqqqb","Audi","Q5","SUV" };
		r.updateCar_repo(audi);
		assert(false);
	}
	catch (RepoException) {
		assert(true);
	}
}

void test_sortareNrI() {
	Repo r;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car audi = Car{ "bc13brb","Audi","Q5","SUV" };
	r.addCar_repo(dacia);
	r.addCar_repo(audi);
	vector<Car> rez = r.sortare_nrI();
	assert(rez[0].get_nrInmatriculare() == audi.get_nrInmatriculare());
	assert(rez[1].get_nrInmatriculare() ==dacia.get_nrInmatriculare());

}
void test_sortareTip() {
	Repo r;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car audi = Car{ "sb14brb","Audi","Q5","SUV" };
	r.addCar_repo(dacia);
	r.addCar_repo(audi);
	vector<Car> rez = r.sortare_tip();
	assert(rez[0].get_nrInmatriculare() == audi.get_nrInmatriculare());
	assert(rez[1].get_nrInmatriculare() == dacia.get_nrInmatriculare());
}
void test_sortareProdMod() {
	Repo r;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	vector<Car> rez = r.sortare_prodMod();
	assert(rez[0].get_nrInmatriculare() == dacia.get_nrInmatriculare());
	assert(rez[1].get_nrInmatriculare() == dacia2.get_nrInmatriculare());
}

void test_filtrare_prod() {
	Repo r;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	string s = "Dacia";
	vector<Car> rez = r.filtrare_producator(s);
	assert(rez.size() == 2);
}
void test_filtrare_tip() {
	Repo r;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	string s = "SUV";
	vector<Car> rez = r.filtrare_tip(s);
	assert(rez.size() == 1);
}

void test_cauta() {
	Repo r;
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia2);
	r.addCar_repo(dacia);
	string s = "sb18brb";
	Car rez = r.cauta(s);
	assert(rez.get_nrInmatriculare() == s);

	try {
		string q = "ssss";
		Car rrez = r.cauta(q);
		assert(false);
	}
	catch (RepoException) {
		assert(true);
	}
	
}

void test_addS() {
	Repo r;
	Validate v;
	Service s{ r ,v };
	s.addCar_service("sb13brb", "Dacia", "Logan", "berlina");
	assert(r.getAll().size() == 1);
}

void test_removeS() {
	Repo r;
	Validate v;
	Service s{ r ,v };
	s.addCar_service("sb13brb", "Dacia", "Logan", "berlina");
	string b{ "sb13brb" };
	s.removeCar_service(b);
	assert(r.getAll().size() == 0);
}

void test_updateS() {
	Repo r;
	Validate v;
	Service s{ r ,v };
	s.addCar_service("sb13brb", "Dacia", "Logan", "berlina");
	s.updateCar_service("sb13brb", "Dacia", "Solenza", "berlina");
	assert(r.getAll()[0].get_model() == "Solenza");
}

void test_filtrareS() {
	Repo r;
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
	Repo r;
	Validate v;
	Service s{ r ,v };
	Car dacia = Car{ "sb13brb","Dacia","Logan","berlina" };
	Car dacia2 = Car{ "sb18brb","Dacia","Solenza","SUV" };
	r.addCar_repo(dacia);
	r.addCar_repo(dacia2);

	vector<Car> rez = s.sortare_prodMod_service();
	assert(rez[0].get_nrInmatriculare() == dacia.get_nrInmatriculare());
	assert(rez[1].get_nrInmatriculare() == dacia2.get_nrInmatriculare());

	rez = s.sortare_tip_service();
	assert(rez[0].get_nrInmatriculare() == dacia2.get_nrInmatriculare());
	assert(rez[1].get_nrInmatriculare() == dacia.get_nrInmatriculare());

	rez = s.sortare_nrI_service();
	assert(rez[0].get_nrInmatriculare() == dacia.get_nrInmatriculare());
	assert(rez[1].get_nrInmatriculare() == dacia2.get_nrInmatriculare());
	
	string j = "sb13brb";
	Car carr=s.cauta_service(j);
	assert(carr.get_nrInmatriculare() == j);
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
