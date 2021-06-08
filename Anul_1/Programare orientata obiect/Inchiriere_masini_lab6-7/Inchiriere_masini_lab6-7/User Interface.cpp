#include <iostream>
#include <cstring>
#include <stdlib.h>
#include <string>
#include "domainH.h"
#include "serviceH.h"
#include "userInterfaceH.h"

using namespace std;

void UserInterface::addUI() {

	string nrInmatriculare, producator, model, tip;
	cout << "------------------------Meniu Adaugare------------------------\n";
	cout << "Introduceti numarul de inmatriculare: ";
	cin >> nrInmatriculare;
	cout << "Introduceti numele producatorului: ";
	cin >> producator;
	cout << "Introduceti modelul masinii: ";
	cin >> model;
	cout << "Introduceti tipul masinii (break, berlina, etc): ";
	cin >> tip;
	Masina car(nrInmatriculare, producator, model, tip);
	srv.addSrv(car);
	cout << '\n' << "Masina adaugata cu succes!\n\n";
}

void UserInterface::deleteUI() {

	string nrInmatriculare, producator, model, tip;
	cout << "------------------------Meniu Stergere------------------------\n";
	cout << "Introduceti numarul de inmatriculare: ";
	cin >> nrInmatriculare;
	cout << "Introduceti numele producatorului: ";
	cin >> producator;
	cout << "Introduceti modelul masinii: ";
	cin >> model;
	cout << "Introduceti tipul masinii (break, berlina, etc): ";
	cin >> tip;
	Masina car(nrInmatriculare, producator, model, tip);
	srv.delSrv(car);
	cout << '\n' << "Masina stearsa cu succes!\n\n";

}

void UserInterface::updateUI() {

	string nrInmatriculare1, producator1, model1, tip1;
	cout << "------------------------Meniu Modificare------------------------\n";
	cout << "\nIntroduceti datele masinii care doriti sa fie modificata\n";
	cout << "Introduceti numarul de inmatriculare: ";
	cin >> nrInmatriculare1;
	cout << "Introduceti numele producatorului: ";
	cin >> producator1;
	cout << "Introduceti modelul masinii: ";
	cin >> model1;
	cout << "Introduceti tipul masinii (break, berlina, etc): ";
	cin >> tip1;
	Masina car1(nrInmatriculare1, producator1, model1, tip1);

	string nrInmatriculare2, producator2, model2, tip2;
	cout << "\nIntroduceti datele masinii noi\n";
	cout << "Introduceti numarul de inmatriculare: ";
	cin >> nrInmatriculare2;
	cout << "Introduceti numele producatorului: ";
	cin >> producator2;
	cout << "Introduceti modelul masinii: ";
	cin >> model2;
	cout << "Introduceti tipul masinii (break, berlina, etc): ";
	cin >> tip2;
	Masina car2(nrInmatriculare2, producator2, model2, tip2);

	srv.updateSrv(car1, car2);
	cout << '\n' << "Masina modificata cu succes!\n\n";

}

void UserInterface::printSrv() {

	cout << "---------------Lista de masini---------------\n";

	for (int i = 0; i < srv.get_dimSrv(); i++) {
		Masina car = srv.get_masinaSrv(i+1);

		cout << 1 << ". Numar de inmatriculare: ";
		for (unsigned int j = 0; j < car.get_numar_inmatriculare().size(); j++)
			cout << car.get_numar_inmatriculare().at(j);
		cout << " | ";

		cout << 2 << ". Producator: ";
		for (unsigned int j = 0; j < car.get_producator().size(); j++)
			cout << car.get_producator().at(j);
		cout << " | ";

		cout << 3 << ". Model: ";
		for (unsigned int j = 0; j < car.get_model().size(); j++)
			cout << car.get_model().at(j);
		cout << " | ";

		cout << 4 << ". Tip: ";
		for (unsigned int j = 0; j < car.get_tip().size(); j++)
			cout << car.get_tip().at(j);
		cout << "\n";
	}

	cout << "\n\n";

}

void UserInterface::filterUI() {

	string Model;
	cout << "Introduceti modelul dupa care sa se faca filtrarea: ";
	cin >> Model;
	for (auto &car : srv.filterCarsByModel(Model)) {
		cout << car.get_numar_inmatriculare() << " ; " << car.get_producator() << " ; " << car.get_model() << " ; " << car.get_tip() << '\n';
	}

}

void UserInterface::run() {
	
	while (1) {

		cout << "------------------------Meniu Principal------------------------\n";
		cout << "1. Adaugati o masina\n";
		cout << "2. Modificati o masina\n";
		cout << "3. Stergeti o masina\n";
		cout << "4. Filtrare dupa model\n";
		cout << "0. Inchideti aplicatia\n";
		cout << "Introduceti comanda (cifra corespunzatoare): ";
		char cmd; cin >> cmd;
		cout << "\n\n";
		if (cmd == '1') addUI();
		if (cmd == '2') updateUI();
		if (cmd == '3') deleteUI();
		if (cmd == '4') filterUI();
		if (cmd == '0') break;
		printSrv();

	}

	cout << "|Thanks for using my app. Byee|\n";

	system("pause");

}