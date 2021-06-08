
#include "domainH.h"
#include <iostream>
#include <vector>
#include <stdlib.h>
#include <cstring>
#include <string>

using namespace std;

Masina::Masina(const string numar_inmatriculare, const string producator, const string model, const string tip) {

	if (numar_inmatriculare.size()>0 && producator.size()>0 && model.size()>0 && tip.size()>0) {
		Numar_inmatriculare.assign(numar_inmatriculare);
		Producator.assign(producator);
		Model.assign(model);
		Tip.assign(tip);
	}

}

Masina::Masina(const Masina &ot) {
	Numar_inmatriculare = ot.Numar_inmatriculare;
	Producator = ot.Producator;
	Model = ot.Model;
	Tip = ot.Tip;
	cout << "copy";
}

string Masina::get_numar_inmatriculare() {

	return Numar_inmatriculare;

}

string Masina::get_producator() {

	return Producator;

}

string Masina::get_model() {

	return Model;

}

string Masina::get_tip() {

	return Tip;

}

void Masina::set_numar_inmatriculare(string numar_inmatriculare) {

	//Numar_inmatriculare.clear();
	Numar_inmatriculare.assign(numar_inmatriculare);

}

void Masina::set_producator(string producator) {

	//Producator.clear();
	Producator.assign(producator);

}

void Masina::set_model(string model) {

	//Model.clear();
	Model.assign(model);

}

void Masina::set_tip(string tip) {

	//Tip.clear();
	Tip.assign(tip);

}









