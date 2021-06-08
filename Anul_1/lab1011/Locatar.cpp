#include "Locatar.h"



Locatar::Locatar() noexcept:apartament{ 0 }, nume{ "" }, suprafata{ 0 }, tip{ "" }
{
}

std::string Locatar::get_nume() const
{
	return nume;
}

void Locatar::set_nume(const std::string &s_nume)
{
	this->nume = s_nume;
}

std::string Locatar::get_tip() const
{
	return tip;
}

void Locatar::set_tip(const std::string &s_tip)
{
	this->tip = s_tip;
}

int Locatar::get_apartament() const noexcept
{
	return apartament;
}

void Locatar::set_apartament(const int ap) noexcept
{
	apartament = ap;
}

int Locatar::get_suprafata() const noexcept
{
	return suprafata;
}

void Locatar::set_suprafata(const int supraf) noexcept
{
	suprafata = supraf;
}

bool Locatar::operator==(Locatar const & ot) const noexcept
{
	return apartament == ot.apartament;
}

/*Locatar Locatar::operator=(Locatar const & ot)
{
	apartament = ot.apartament;
	suprafata = ot.suprafata;
	nume = ot.nume;
	tip = ot.tip;
	return *this;
}*/
