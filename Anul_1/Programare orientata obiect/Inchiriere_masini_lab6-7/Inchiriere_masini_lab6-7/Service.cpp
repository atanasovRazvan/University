#include "repositoryH.h"
#include "serviceH.h"
#include "domainH.h"
#include <iostream>
#include <vector>
#include <string>

using namespace std;

void Service::addSrv(Masina car) {

	Repo.add(car);

}

void Service::delSrv(Masina car) {

	Repo.del(car);

}

void Service::updateSrv(Masina car1, Masina car2) {

	Repo.update(car1, car2);

}

int Service::get_dimSrv() noexcept {

	return Repo.get_dim();

}

Masina Service::get_masinaSrv(int index) {

	return Repo.get_masina(index);

}

vector <Masina> Service::filterCarsByModel(const string Model)
{
	vector <Masina> filtered_cars;
	for (auto &car : Repo.get_all()) {
		if (car.get_model()==Model) filtered_cars.push_back(car);
	}
	return filtered_cars;
}