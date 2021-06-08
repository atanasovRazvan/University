#pragma once
#include "Repository.h"
#include "Market.h"
#include "Undo.h"
#include <algorithm>
#include <map>

typedef bool(*Compare)(const string& elem1, const string& elem2);

class CarType {
	string type;
	int count;
public:
	CarType(string tip, int nr) : type{ tip }, count{ nr } {};
	string getType() {
		return this->type;
	}

	int getCount() {
		return this->count;
	}
};

class Service
{
	vector<Undo*>undoAction;
	Repository& repo;
	Market& market;
public:
	Service(Repository& rep, Market& market) noexcept : repo{ rep }, market{market}{}
	Service(const Service& ot) = delete;
	~Service() {
		for(auto &action : undoAction)
			delete[] action;
	}
	int size() noexcept{
		return this->repo.size();
	}

	void addCar(const string &number, const string &manu, 
		const string &model, const string &type);
	void generateCarsMarket(const int& count);
	void addCarMarket(const string &number, const string &manu,
		const string &model, const string &type);
	void removeMarket();

	void undo();

	void removeCar(const int &index);
	
	void modifyCar(const string &number, const string &manu,
		const string &model, const string &type);

	//vector<Domain> sort(const int &option);
	//vector<Domain> filter(Filtru filtru);

	const vector<Domain>& getAll() noexcept{
		return this->repo.getRepository();
	}

	const vector<Domain>& getMarket() noexcept {
		return this->market.getRepository();
	}

	vector<CarType> raport() {
		std::map<string, int> stat;
		for (auto film : repo.getRepository())
			stat[film.getType()]++;

		vector<CarType> list;
		for_each(stat.begin(), stat.end(), [&](std::map<string, int>::value_type &it) {
			list.push_back(CarType(it.first, it.second));
		});

		return list;
	}
};


