#include "Console.h"
#include <iostream>
#include "car.h"

using namespace std;

const void Console::showCars(Lista<Car> cars) const{
	Lista<Car> l = cars;
	auto nod = l.first(cars);
	while (nod != nullptr) {
		cout << nod->val.get_nrInmatriculare() << "  " << nod->val.get_producator() << "  " << nod->val.get_model() << "  " << nod->val.get_tip() << "\n";
		nod = nod->urm;
	}		
}

const void Console::showCars_V(vector<Car> cars) const {
	if (size(cars) == 0)
		cout << "This type of car doesn't exist!\n";

	for (int i = 0; i < (int)size(cars); i++) {
		cout << cars[i].get_nrInmatriculare() << "  " << cars[i].get_producator() << "  " << cars[i].get_model() << "  " << cars[i].get_tip() << "\n";
	}
}



void Console::cautaUI(){
	cout << "\nInsert the car registration number: ";
	string nrI;
	cin >> nrI;
	Car car=ctr.cauta_service(nrI);
	cout << "Your car: \n";
	cout << car.get_nrInmatriculare() << " " << car.get_producator() << " " << car.get_model() << " " << car.get_tip() << "\n";
}

void Console::filtrareProdUI(){
	cout << "\nInsert producer: ";
	string prod;
	cin >> prod;
	vector<Car> cars = ctr.filtrare_prod_service(prod);
	showCars_V(cars);
}

void Console::filtrareTipUI(){
	cout << "\nInsert type: ";
	string type;
	cin >> type;
	vector<Car> cars = ctr.filtrare_prod_service(type);
	showCars_V(cars);
}

const void  Console::sortare_nrIUI(){
	Lista<Car> l = ctr.sortare_nrI_service();
	showCars(l);
}

const void  Console::sortare_tipUI(){
	Lista<Car> l = ctr.sortare_tip_service();
	showCars(l);
}

const void Console::sortare_prodModUI(){
	Lista<Car> l = ctr.sortare_prodMod_service();
	showCars(l);
}


void Console:: addUI(){
	string nrI;
	string prod;
	string model;
	string tip;

	cout << "Nr. inmatriculare: ";
	cin >> nrI;
	cout << "Producator: ";
	cin >> prod;
	cout << "Model: ";
	cin >> model;
	cout << "Tip: ";
	cin >> tip;

	ctr.addCar_service(nrI, prod, model, tip);
	cout << "Done!\n";
}

void Console::removeUI() {
	cout << "\nInsert the car registration number: ";
	string nrI;
	cin >> nrI;
	ctr.removeCar_service(nrI);
	cout << "Done!\n";
}

void Console::updateUI() {

	string nrI;
	string prod;
	string model;
	string tip;


	cout << "Nr. inmatriculare: ";
	cin >> nrI;
	cout << "Producator: ";
	cin >> prod;
	cout << "Model: ";
	cin >> model;
	cout << "Tip: ";
	cin >> tip;

	ctr.updateCar_service(nrI, prod, model, tip);
	cout << "Done!\n";

}
void Console::run() {

	while (true) {

		int cmd;
		cout << "\n-1.Exit\n 0.Show cars\n 1. Add car\n 2. Remove car\n 3.Update car\n 4.Search car\n 5.Filter producer\n 6.Filter type\n 7.Sort nrI\n  8.Sort type\n  9.Sort Model+Producator\n";
		cin >> cmd;
		try {
			switch (cmd) {
				case -1:
					return;
				case 0:
					showCars(ctr.getCarsFromList());
					break;
				case 1:
					addUI();
					break;
				case 2:
					removeUI();
					break;
				case 3:
					updateUI();
					break;
				case 4:
					cautaUI();
					break;
				case 5:
					filtrareProdUI();
					break;
				case 6:
					filtrareTipUI();
					break;
				case 7:
					sortare_nrIUI();
					break;
				case 8:
					sortare_nrIUI();
					break;
				case 9:
					sortare_nrIUI();
					break;
				default:
					cout << "Invalid command\n";

			}
		}

			catch (ValidateException& ex){
					cout << ex;
			}
			catch (RepoException& ex) {
				cout << ex;
			}
	}
}

Console::~Console()
{
}
