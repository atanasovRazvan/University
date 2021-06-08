#include "Cos.h"
#pragma once

void Cos::add(const Locatar& loc)
{
	l.push_back(loc);
}

const int Cos::find(const int nr)
{
	int i{ 0 };
	for (auto& x : l) {
		if (x.get_apartament() == nr) {
			return i;
		}
		i++;
	}
	throw CosException("Nu exista.\n");
}

void Cos::goleste() noexcept
{
	l.clear();
}

void Cos::salveaza() const
{
	std::ofstream fout(filename);
	if (!fout.is_open()) {
		throw(std::exception("The file could not be opened."));
	}
	for (auto &i : l) {
		fout << i.get_apartament() << ",";
		fout << i.get_nume() << ",";
		fout << i.get_tip() << ",";
		fout << i.get_suprafata() << std::endl;
	}
}

std::vector<Locatar> Cos::get_all() const
{
	return l;
}
