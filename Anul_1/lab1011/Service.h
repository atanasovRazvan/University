#pragma once
#include "Repository.h"
#include "Cos.h"
#include "Undo.h"

class Service
{
private:
	Repo& repo;
	Cos cos{"cos.txt" };
	std::mt19937 mt{ std::random_device{}() };
	std::vector<std::unique_ptr<ActiuneUndo>> undo;
	//std::vector<ActiuneUndo*> undo;

public:
	/*
	Constructor parametrizat
	*/
	Service(Repo& repository) :repo{ repository } {
	}

	/*
	Adauga in lista de apartamente un nou apartament specificat dupa parametri
	arunca exceptie daca apartamentul exista deja
	*/
	void add(const std::string&, const std::string&, const int apartament, const int suprafata);

	/*
	Sterge un apartament dupa numarul sau
	arunca exceptie daca apartamentul nu exista
	*/
	void del(const int apartament);

	/*
	Actualizeaza datele unui apartament
	daca apartamentul nu exista, arunca exceptie
	*/
	void update(const std::string&, const std::string&, const int apartament, const int suprafata);

	/*
	Suprascriu operatorul <<
	*/
	//friend std::ostream& operator<<(std::ostream& os, const Service&);

	/*
	Caut apartament dupa numar
	arunca exceptie daca nu gaseste apartamentul
	*/
	const Locatar cautare_apartament(const int apartament);

	/*
	Returneaza un vector cu toate apartamentele
	*/
	const std::vector<Locatar> get_all();

	/*
	Filtreaza lista de apartamente dupa tipul lor
	intoarce un vector care contine elementele filtrate
	*/
	const std::vector<Locatar> filtrare_apartament(const std::string &search);

	/*
	Filtreaza lista de apartamente dupa suprafata
	intoarce un vector cu toate apartamentele cu o suprafata mai mare sau egala cu cautarea
	*/
	const std::vector<Locatar> filtrare_suprafata(const int);

	/*
	Sorteaza apartamente dupa nume
	*/
	//void sortare_nume();
	std::vector<Locatar> sortare_nume();

	/*
	Sorteaza apartamente dupa suprafata
	*/
	//void sortare_suprafata();
	std::vector<Locatar> sortare_suprafata();

	/*
	Sorteaza apartamente dupa tip + suprafata
	*/
	//void sortare_apartament();
	std::vector<Locatar> sortare_apartament();

	/*
	Adauga in cos apartamentul cu numarul dat
	*/
	void adauga_cos(const int apartament);

	/*
	Goleste cosul
	*/
	void goleste_cos();

	/*
	Populeaza cosul cu apartamente aleatoare
	*/
	void populeaza(const int nr);

	/*
	Salveaza cosul in fisier
	*/
	void salveaza_cos() const noexcept;

	const Cos& get_cos_old() const noexcept;

	std::vector<Locatar> get_cos() const noexcept;

	std::vector<DTO> get_all_types() const;

	/*
	Reface ultima operatie
	*/
	void undo_me();

	/*
	Destructor pentru Undo
	*/
	~Service() {
		/*for (auto& el : undo) {
			delete el;
		}*/
	}
};

