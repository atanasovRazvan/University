#include "Service.h"
#include "Repository.h"
#include "ValidateException.h"
#include <iostream>
#include <random>

using std::vector;
using std::copy_if;

void Service::addCar(const string &number, const string &manu,
	const string &model, const string &type) {

	Domain car{ number, manu, model, type };
	ValidateException val;
	val.validate(car);
	this->repo.addCar(car);
	this->undoAction.push_back(new UndoAdd(this->repo, this->repo.size()-1));
}

void Service::generateCarsMarket(const int & count)
{
	this->market.generateCars(count);
}

void Service::addCarMarket(const string & number, const string & manu, const string & model, const string & type)
{
	Domain car{ number, manu, model, type };
	ValidateException val;
	val.validate(car);
	this->market.addCar(car);
}

void Service::removeMarket()
{
	this->market.removeCars();
}

void Service::undo(){
	if (undoAction.empty()) {
		throw RepoException{ "Nu mai exista operatii" };
	}

	Undo* act = undoAction.back();
	act->doUndo();
	undoAction.pop_back();
	delete act;
}

void Service::removeCar(const int &index) {
	Domain car = this->repo.getCar(index);
	this->repo.removeCar(index);
	this->undoAction.push_back(new UndoRemove(this->repo, car));
}

void Service::modifyCar(const string &number, const string &manu,
	const string &model, const string &type) {
	
	Domain car(number, manu, model, type);
	ValidateException val;
	val.validate(car);

	Domain c = this->repo.getCar(car);
	this->repo.modifyCar(car);
	this->undoAction.push_back(new UndoModify(this->repo, c));
}

/*vector<Domain> Service::sort(const int &option){
	sort(this->, arr + n); option += 1;
}



vector<Domain> Service::filter(Filtru filtru){
	int a = 0;
	a++;
}*/