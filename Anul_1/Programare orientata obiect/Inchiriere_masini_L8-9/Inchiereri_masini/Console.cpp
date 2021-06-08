#include "Console.h"
#include <iostream>
#include "car.h"
#include "Service.h"


using namespace std;

void Console::adauga_cateva() {
	ctr.addCar_service("sb13brb", "Dacia", "Logan", "Berlina");
	ctr.addCar_service("cj19npc", "Dacia", "Solenza", "Berlina");
	ctr.addCar_service("x", "Audi", "Q7", "SUV");
	ctr.addCar_service("b01alo", "Mercedes", "AMG GT R", "Coupe");
}


const void Console::showCars_V(vector<Car> cars) const {
	if (size(cars) == 0)
		cout << "No more cars!\n";

	for(auto i: cars)
		cout << i.get_nrInmatriculare() << "  " << i.get_producator() << "  " << i.get_model() << "  " << i.get_tip() << "\n";
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
	vector<Car> l = ctr.sortare_nrI_service();
	showCars_V(l);
}

const void  Console::sortare_tipUI(){
	vector<Car> l = ctr.sortare_tip_service();
	showCars_V(l);
}

const void Console::sortare_prodModUI(){
	vector<Car> l = ctr.sortare_prodMod_service();
	showCars_V(l);
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

	
	//adauga_cateva();

	while (true) {

		int cmd;
		cout << "-------------------------------------------------------------\n";
		cout << "-2. Show wash list\n -1.Exit\n 0.Show cars\n 1. Add car\n 2. Remove car\n 3.Update car\n 4.Search car\n 5.Filter producer\n 6.Filter type\n 7.Sort nrI\n 8.Sort type\n 9.Sort Model+Producator\n";
		cout << "10. Goleste spalatorie\n 11. Adauga spalatorie\n 12. Genereaza automat spalatorie\n 13.Undo\n 14. Raport\n";
		cin >> cmd;
		cout << "-------------------------------------------------------------\n";
		try {
			switch (cmd) {
				case -2:
					showCars_V(ctr.getAllWash());
					break;
				case -1:
					return;
				case 0:
					showCars_V(ctr.getCars());
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
				case 10:
					ctr.goleste_Wash_s();
					cout << "Number of current cars: " << ctr.getAllWash().size()<<"\n";
					break;
				case 11:{
					string nrI;
					cout << "Nr. inmatriculare: ";
					cin >> nrI;
					ctr.addCar_Wash_s(nrI);
					cout << "Number of current cars: "<<ctr.getAllWash().size()<<"\n";
					break;
				}

				case 12: {
					int total;
					cout << "Total cars:  ";
					cin >> total;
					ctr.randomAdd_service(total);
					cout << "Number of current cars: " << ctr.getAllWash().size() << "\n";
					break;
				}
				case 13: {
					ctr.undo();
					cout << "Done!";
					break;
				}
				case 14: {
					//something
				}
					
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

Console::~Console(){

}