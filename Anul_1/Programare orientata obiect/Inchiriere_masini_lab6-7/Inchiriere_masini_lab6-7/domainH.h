#pragma once
#include <vector>
#include <iostream>
#include <string>
using namespace std;

/*
 Clasa care defineste entitatea masina
 
 Campurile clasei sunt:
      - numar_inmatriculare (sir de caractere)
	  - producator (sir de caractere)
	  - model (sir de caractere)
	  - tip (sir de caractere)

Functiile clasei sunt:
	  - constructor, destructor
	  - gettere (get_numar_inmatriculare, get_producator, get_model, get_tip)
	  - settere (set_numar_inmatriculare, set_producator, set_model, set_tip)
 */
class Masina{
private:

	string Numar_inmatriculare;
	string Producator;
	string Model;
	string Tip;

public:

	/*
	Constructorul clasei
	Primeste patru parametri ca siruri de caractere
	Initializeaza campurile private cu parametrii primiti
	*/
	Masina(const string numar_inmatriculare, const string producator, const string model, const string tip);
	/*
	Functie de copiere a clasei
	Primeste ca parametru o referinta la un obiect
	Initializeaza campurile clasei cu cele ale obiectului
	*/
	Masina(const Masina &ot);
	/*
	Functie getter pentru numar_inmatriculare
	Nu primeste parametri
	Returneaza un sir de caractere
	*/
	string get_numar_inmatriculare();
	/*
	Functie getter pentru producator
	Nu primeste parametri
	Returneaza un sir de caractere
	*/
	string get_producator();
	/*
	Functie getter pentru model
	Nu primeste parametri
	Returneaza un sir de caractere
	*/
	string get_model();
	/*
	Functie getter pentru tip
	Nu primeste parametri
	Returneaza un sir de caractere
	*/
	string get_tip();
	/*
	Functie setter pentru numar_inmatriculare
	Primeste parametru un sir de caractere
	Inlocuieste campul numar_inmatriculare cu valoarea parametrului primit
	*/
	void set_numar_inmatriculare(string numar_inmatriculare);
	/*
	Functie setter pentru producator
	Primeste parametru sir de caractere
	Inlocuieste producator cu valoarea parametrului primit
	*/
	void set_producator(string producator);
	/*
	Functie setter pentru model
	Primeste parametru un sir de caractere
	Inlocuieste model cu valoarea parametrului primit
	*/
	void set_model(string model);
	/*
	Functie setter pentru tip
	Primeste un parametru sir de caractere
	Inlocuieste tip cu valoarea parametrului primit
	*/
	void set_tip(string tip);

};