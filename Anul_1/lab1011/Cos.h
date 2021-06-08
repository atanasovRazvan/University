#pragma once
#include "Repository.h"
#include <vector>
#include <algorithm>
#include <random>// std::default_random_engine
#include <chrono>// std::chrono::system_clock
#include <fstream>

struct CosException : public std::exception
{
	std::string s;
	CosException(std::string ss) : s(ss) {}
	~CosException() throw () {} // Updated
	//const char* what() const throw() { return s.c_str(); }
};

class Cos {
private:
	std::vector<Locatar> l;
	const string filename;

public:
	/*
	Constructor default
	*/
	Cos() noexcept {};

	/*
	Constructor parametrizat
	*/
	Cos(const string& file) noexcept :filename{ file } {
		//l.resize(r.get_all().size());
	}

	/*
	Adauga in cos un Locatar
	*/
	void add(const Locatar&);

	/*
	Cauta un obiect in cos
	*/
	const int find(const int nr);

	/*
	Suprascriu operatorul <<
	*/
	friend std::ostream & operator<<(std::ostream & os, const Cos &sv);

	/*
	Goleste cosul
	*/
	void goleste() noexcept;

	/*
	Salveaza cosul in fisier format CVS
	*/
	void salveaza() const;

	/*
	Returneaza tot ce e in cos
	*/
	std::vector<Locatar> get_all() const;
};
