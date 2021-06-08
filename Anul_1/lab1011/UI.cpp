#include "UI.h"



void UI::show_sv(std::vector<Locatar> aux)
{
	try {
		//auto aux = sv.get_all();
		for (auto& el : aux) {
			std::cout << el;
		}
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
}

void UI::opt()
{
	std::cout << "---------------------------------------------\n";
	std::cout << "1. Adaugare\n";
	std::cout << "2. Stergere\n";
	std::cout << "3. Afisare\n";
	std::cout << "4. Actualizare\n";
	std::cout << "5. Cautare dupa nr apartament\n";
	std::cout << "6. Filtrare dupa tip apartament\n";
	std::cout << "7. Filtrare dupa suprafata\n";
	std::cout << "8. Sortare dupa nume proprietar\n";
	std::cout << "9. Sortare dupa suprafata\n";
	std::cout << "10. Sortare dupa tip apartament + suprafata\n";
	std::cout << "11. Adauga in lista de apartamente dupa numar\n";
	std::cout << "12. Goleste lista de apartamente\n";
	std::cout << "13. Adauga aleator apartamente\n";
	std::cout << "14. Afiseaza cate apartamente sunt din fiecare tip\n";
	std::cout << "15. Salveaza cosul in fisier CVS\n";
	std::cout << "16. Undo\n";
	std::cout << "17. Iesire\n";
	std::cout << "---------------------------------------------\n";
}

void UI::add()
{
	std::string nume, tip;
	int apartament, suprafata;
	std::cout << "Numele locatarului: ";
	std::getline(std::cin, nume);
	std::cout << "Numarul apartamentului: ";
	std::cin >> apartament;
	std::cin.ignore(1024, '\n');
	std::cout << "Tipul apartamentului: ";
	std::getline(std::cin, tip);
	std::cout << "Suprafata apartamentului: ";
	std::cin >> suprafata;
	std::cin.ignore(1024, '\n');
	try {
		sv.add(nume, tip, apartament, suprafata);
		std::cout << "Locatar adaugat cu succes.\n";
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
	catch (std::exception) {
		std::cout << "Apartamentul este deja inregistrat pentru un alt locatar.\n";
	}
}

void UI::del()
{
	int apartament;
	std::cout << "Numarul de apartament: ";
	std::cin >> apartament;
	std::cin.ignore(1024, '\n');
	try {
		sv.del(apartament);
		std::cout << "Locatar sters cu succes.\n";
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
	catch (std::exception) {
		std::cout << "Apartamentul dat nu exista.\n";
	}
}

void UI::update()
{
	std::string nume, tip;
	int apartament, suprafata;
	std::cout << "Numele locatarului: ";
	std::getline(std::cin, nume);
	std::cout << "Numarul apartamentului: ";
	std::cin >> apartament;
	std::cin.ignore(1024, '\n');
	std::cout << "Tipul apartamentului: ";
	std::getline(std::cin, tip);
	std::cout << "Suprafata apartamentului: ";
	std::cin >> suprafata;
	std::cin.ignore(1024, '\n');
	try {
		sv.update(nume, tip, apartament, suprafata);
		std::cout << "Locatar actualizat cu succes.\n";
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
	catch (std::exception) {
		std::cout << "Apartament inexistent.\n";
	}
}

void UI::find_ap()
{
	int ap;
	std::cout << "Numar apartament: ";
	std::cin >> ap;
	std::cin.ignore(1024, '\n');
	try {
		Locatar x = sv.cautare_apartament(ap);
		std::cout << "Locatar gasit: " << x;
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
	catch (std::exception) {
		std::cout << "Nu exista apartament cu acest numar.\n";
	}
}

void UI::filter_tip()
{
	try {
		std::string search;
		std::cout << "Filtreaza dupa: ";
		std::getline(std::cin, search);
		for (const auto& e : sv.filtrare_apartament(search)) {
			std::cout << e;
		}
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
}

void UI::filter_supraf()
{
	try {
		int search;
		std::cout << "Filtreaza dupa: ";
		std::cin >> search;
		std::cin.ignore(1024, '\n');
		std::vector<Locatar> v = sv.filtrare_suprafata(search);
		if (v.size()) {
			for (Locatar x : v) {
				std::cout << x;
			}
		}
		else {
			std::cout << "Nu au fost gasite rezultate pentru cautare.\n";
		}
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
}

void UI::sort_nume()
{
	try {
		show_sv(sv.sortare_nume());
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
}

void UI::sort_supraf()
{
	try {
		show_sv(sv.sortare_suprafata());
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
}

void UI::sort_ap()
{
	try {
		show_sv(sv.sortare_apartament());
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
}

void UI::adauga_cos()
{
	int i{ 0 };
	std::cout << "Introduceti numarul apartamentului: ";
	std::cin >> i;
	try {
		sv.adauga_cos(i);
		std::cout << "Apartamentul a fost introdus cu succes.\n";
	}
	catch (std::exception) {
		std::cout << "Apartamentul nu s-a putut introduce.\n";
	}
}

void UI::goleste_cos()
{
	sv.goleste_cos();
	std::cout << "Lista dumneavoastra a fost golita!\n";
}

void UI::populeaza_cos()
{
	int i{ 0 };
	std::cout << "Introduceti numarul maxim de apartamente: ";
	std::cin >> i;
	sv.populeaza(i);
}

void UI::arata_tipuri() {
	try {
		auto aux = sv.get_all_types();
		for_each(aux.begin(), aux.end(), [&](const auto& x) {
			std::cout << x;
		});
	}
	catch (ExceptieDeMafiot) {
		std::cout << "Din intamplare am crapat.\n";
	}
}

void UI::salveaza_cos() {
	sv.salveaza_cos();
}

void UI::undo()
{
	try {
		sv.undo_me();
		std::cout << "Undo!\n";
	}
	catch (std::exception) {
		std::cout << "Nu se mai poate face undo.\n";
	}
}

std::ostream & operator<<(std::ostream & os, const Locatar & l)
{
	os << l.get_nume() << " ";
	os << l.get_apartament() << " ";
	os << l.get_tip() << " ";
	os << l.get_suprafata() << "\n";
	return os;
}
/*
std::ostream & operator<<(std::ostream & os, const Service &sv)
{
	os << "NR NUME NR.APARTAMENT TIP SUPRAFATA(M^2)\n------------------------------------\n";
	os << sv.repo;
	os << "------------------------------------\n";
	return os;
}

std::ostream & operator<<(std::ostream & os, const Repository &repo)
{
	for (unsigned int i = 0; i < repo.repo.size(); i++) {
		os << i << " ";
		os << repo.repo.at(i);
	}
	return os;
}*/

std::ostream & operator<<(std::ostream & os, const DTO &sv) {
	os << sv.get_tip() << " " << sv.get_nr() << "\n";
	return os;
}

std::ostream & operator<<(std::ostream & os, const Cos &cos)
{
	os << "\nLista apartamente:\nNR NUME NR.APARTAMENT TIP SUPRAFATA(M^2)\n------------------------------------\n";
	for (unsigned int i = 0; i < cos.l.size(); i++) {
		os << i << " ";
		os << cos.l.at(i);
	}
	os << "------------------------------------\n";
	return os;
}

void UI::run()
{
	int option{ 0 };
	while (true) {
		opt();
		std::cout << "Comanda: ";
		std::cin >> option;
		std::cin.ignore(1024, '\n');
		if (option == 1) {
			add();
		}
		else if (option == 2) {
			del();
		}
		else if (option == 3) {
			show_sv(sv.get_all());
		}
		else if (option == 4) {
			update();
		}
		else if (option == 5) {
			find_ap();
		}
		else if (option == 6) {
			filter_tip();
		}
		else if (option == 7) {
			filter_supraf();
		}
		else if (option == 8) {
			sort_nume();
		}
		else if (option == 9) {
			sort_supraf();
		}
		else if (option == 10) {
			sort_ap();
		}
		else if (option == 11) {
			adauga_cos();
		}
		else if (option == 12) {
			goleste_cos();
		}
		else if (option == 13) {
			populeaza_cos();
		}
		else if (option == 14) {
			arata_tipuri();
		}
		else if (option == 15) {
			salveaza_cos();
		}
		else if (option == 16) {
			undo();
		}
		else if (option == 17) {
			std::cout << "La revedere.\n";
			return;
		}
		else {
			std::cout << "Optiune invalida.\n";
		}
		std::cout << sv.get_cos_old();
	}
}
