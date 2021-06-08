#pragma once
#include "Service.h"
#include "car.h"
#include "Lista.h"
#include "RepoFile.h"

class Console
{

	Service& ctr;
	void addUI();
	void removeUI();
	void updateUI();
	void cautaUI();
	void filtrareProdUI();
	void filtrareTipUI();
	const void  sortare_nrIUI();
	const void  sortare_tipUI();
	const void sortare_prodModUI();
	void adauga_cateva();

	const void showCars_V(vector<Car> cars) const;

public:
	Console(Service& _ctr) : ctr{ _ctr }{}

	void run();

	~Console();
};

