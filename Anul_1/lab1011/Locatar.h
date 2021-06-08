#pragma once
#include <string>
#include <iostream>
using std::string;
class Locatar
{
private:
	int apartament, suprafata;
	std::string nume, tip;
public:
	/*
	Constructor default
	*/
	Locatar() noexcept;

	/*Locatar(const Locatar& ot) :nume{ ot.nume }, apartament{ ot.apartament }, tip{ ot.tip }, suprafata{ ot.suprafata }{
		std::cout << "copy" << std::endl;
	}*/

	/*
	Constructor parametrizat
	*/
	Locatar(const string& nume, const string& tip, int ap, int suprafata)
		:nume{ nume }, tip{ tip }, apartament{ ap }, suprafata{ suprafata }{

	}

	/*
	Functie getter
	returneaza numele Locatarului
	*/
	string get_nume() const;

	/*
	Functie setter
	*/
	void set_nume(const string&);

	/*
	Functie getter
	returneaza tipul apartamentului
	*/
	string get_tip() const;

	/*
	Functie setter
	*/
	void set_tip(const string&);

	/*
	Functie getter
	returneaza numarul apartamentului
	*/
	int get_apartament() const noexcept;

	/*
	Functie setter
	*/
	void set_apartament(const int) noexcept;

	/*
	Functie getter
	returneaza suprafata apartamentului
	*/
	int get_suprafata() const noexcept;

	/*
	Functie setter
	*/
	void set_suprafata(const int) noexcept;

	/*
	Suprascriu operatorul ==
	doua obiecte de tip Locatar sunt egale daca numarul de apartament este identic
	*/
	bool operator==(Locatar const&ot) const noexcept;

	/*
	Compar doua nume de locatari
	true daca t<ot
	false altfel
	*/
	friend bool cmp_nume(const Locatar& t, const Locatar& ot);

	/*
	Compar suprafetele a doua apartamente
	true daca t<ot
	false altfel
	*/
	friend bool cmp_suprafata(const Locatar& t, const Locatar& ot) noexcept;

	/*
	Compar tipurile a doua apartamente
	true daca t<ot
	false altfel
	*/
	friend bool cmp_ap(const Locatar& t, const Locatar& ot);

	/*
	Suprascriu operatorul <<
	*/
	friend std::ostream & operator<<(std::ostream & os, const Locatar &l);
};

