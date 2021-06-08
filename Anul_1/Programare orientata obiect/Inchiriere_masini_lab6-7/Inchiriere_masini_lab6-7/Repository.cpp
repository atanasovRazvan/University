#include <iostream>
#include <vector>
#include "domainH.h"
#include "repositoryH.h"

using namespace std;

void Repository::add(const Masina &car) {

	repo.push_back(car);

}

void Repository::del(Masina &car) {

	for (unsigned int i = 0; i < repo.size(); i++)
		if (car.get_numar_inmatriculare() == repo.at(i).get_numar_inmatriculare()) {
			repo.erase(repo.begin() + i);
			return;
		}}

void Repository::update(Masina &car1, Masina &car2) {

	for (auto &car : repo)
		if (car1.get_numar_inmatriculare() == car.get_numar_inmatriculare()) {

			car.set_numar_inmatriculare(car2.get_numar_inmatriculare());
			car.set_producator(car2.get_producator());
			car.set_model(car2.get_model());
			car.set_tip(car2.get_tip());
			return; }}

int Repository::get_dim() noexcept {

	return repo.size();

}

vector <Masina> Repository::get_all() {

	vector <Masina> v;
	for (auto &car : repo)
		v.push_back(car);
	return v;

}

Masina Repository::get_masina(int index) {

	return repo.at(index - 1);

}