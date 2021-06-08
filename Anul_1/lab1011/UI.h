#pragma once
#include "Service.h"
#include <iostream>

class UI
{
private:
	Service& sv;
	void show_sv(std::vector<Locatar> aux);
	void opt();
	void add();
	void del();
	void update();
	void find_ap();
	void filter_tip();
	void filter_supraf();
	void sort_nume();
	void sort_supraf();
	void sort_ap();
	void adauga_cos();
	void goleste_cos();
	void populeaza_cos();
	void arata_tipuri();
	void salveaza_cos();
	void undo();
public:
	UI(Service& s) :sv{ s } {};
	void run();;
};

