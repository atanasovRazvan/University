#pragma once
#include "Locatar.h"
#include <vector>
#include <fstream>
#include <stdexcept>
#include <algorithm>
#include <random>// std::default_random_engine
#include <chrono>// std::chrono::system_clock
#include <cstdlib>
#include <iostream>
#include <map>
#include <random>
#include <functional>
struct ExceptieDeMafiot : public std::exception
{
	std::string s;
	ExceptieDeMafiot(std::string ss) : s(ss) {}
	~ExceptieDeMafiot() throw () {} // Updated
	const char* what() const throw() { return s.c_str(); }
};

class DTO {
private:
	std::string tip;
	int nr;
public:
	DTO() :tip{ "" }, nr{ 0 }{};
	DTO(const DTO& d);
	DTO(const std::string& s, const int n) :tip{ s }, nr{ n }{

	}

	const std::string get_tip() const {
		return tip;
	}

	void set_tip(const std::string& s) {
		tip = s;
	}

	const int get_nr() const {
		return nr;
	}

	void set_nr(const int n) {
		nr = n;
	}

	bool operator==(DTO const&ot) const noexcept {
		return tip.compare(ot.get_tip()) == 0;
	}

	friend std::ostream & operator<<(std::ostream & os, const DTO &sv);
};

class Repo {
public:
	virtual void add(const Locatar& l) = 0;
	virtual Locatar update(const Locatar& l) = 0;
	virtual Locatar del(const Locatar& l) = 0;
	virtual const int find(const int apartament) = 0;
	virtual const std::vector<Locatar>& get_all() = 0;
	virtual void sort(bool(*cmp)(const Locatar&, const Locatar&)) = 0;
	virtual std::vector<DTO> get_all_types() = 0;
	virtual ~Repo() {};
};

class Repository : public Repo
{
protected:
	std::vector<Locatar> repo;
public:
	/*
	Constructor default
	*/
	Repository() noexcept;

	/*
	Adauga in repository un Locatar
	*/
	void add(const Locatar&) override;

	/*
	Modifica un Locatar din Repository
	arunca exceptie daca nu exista Locatarul in Repository
	*/
	Locatar update(const Locatar&) override;

	/*
	Sterge un locatar
	arunca exceptie daca nu exista Locatarul in Repository
	*/
	Locatar del(const Locatar&) override;

	/*
	Cauta un locatar in Repository
	returneaza pozitia din lista pe care se afla Locatarul
	arunca exceptie daca nu gaseste Locatarul
	*/
	const int find(const int apartament) override;

	/*
	Returneaza lista de apartamente
	*/
	const std::vector<Locatar>& get_all() override;

	/*
	Suprascriu operatorul <<
	*/
	//friend std::ostream & operator<<(std::ostream & os, const Repository &r);

	/*
	Sorteaza apartamentele dupa criteriul cmp
	cmp sa returneze true daca L1<L2, false altfel
	*/
	void sort(bool(*cmp)(const Locatar&, const Locatar&)) override;

	/*
	Gaseste toate tipurile de apartamente
	*/
	//std::vector<std::string> get_all_types() const;
	std::vector<DTO> get_all_types() override;

	~Repository() override {};
};

class FileRepo : public Repository {
	void save_to_file() const;
	void load_from_file();
	std::string file_name;
	bool is_file_opened;
public:

	/*
	Constructor parametrizat
	*/
	FileRepo(const std::string& s) :file_name{ s } {
		load_from_file();
		is_file_opened = true;
	}

	/*
	Adauga in repository un Locatar
	*/
	void add(const Locatar&) override;

	/*
	Modifica un Locatar din Repository
	arunca exceptie daca nu exista Locatarul in Repository
	*/
	Locatar update(const Locatar&) override;

	/*
	Sterge un locatar
	arunca exceptie daca nu exista Locatarul in Repository
	*/
	Locatar del(const Locatar&) override;

	/*
	Cauta un locatar in Repository
	returneaza pozitia din lista pe care se afla Locatarul
	arunca exceptie daca nu gaseste Locatarul
	*/
	const int find(const int apartament) override;

	/*
	Returneaza lista de apartamente
	*/
	const std::vector<Locatar>& get_all() override;

	/*
	Suprascriu operatorul <<
	*/
	//friend std::ostream & operator<<(std::ostream & os, const Repository &r);

	/*
	Sorteaza apartamentele dupa criteriul cmp
	cmp sa returneze true daca L1<L2, false altfel
	*/
	void sort(bool(*cmp)(const Locatar&, const Locatar&)) override;

	/*
	Gaseste toate tipurile de apartamente
	*/
	//std::vector<std::string> get_all_types() const;
	std::vector<DTO> get_all_types() override;

	~FileRepo() override {};
};

class ProbaRepo :public Repo {
	double probability;
	std::vector<Locatar> repo;
	std::mt19937::result_type mt = time(0);
	//std::chrono::high_resolution_clock::now().time_since_epoch().count()
	std::_Binder<std::_Unforced, std::uniform_real_distribution<double>, std::mt19937> random  = 
		std::bind(std::uniform_real_distribution<double>(0, 1), std::mt19937(mt));
public:

	/*
	Constructor parametrizat
	*/
	ProbaRepo(double p) :probability{ p } {
	
	};

	

	/*
	Adauga in repository un Locatar
	*/
	void add(const Locatar&) override;

	/*
	Modifica un Locatar din Repository
	arunca exceptie daca nu exista Locatarul in Repository
	*/
	Locatar update(const Locatar&) override;

	/*
	Sterge un locatar
	arunca exceptie daca nu exista Locatarul in Repository
	*/
	Locatar del(const Locatar&) override;

	/*
	Cauta un locatar in Repository
	returneaza pozitia din lista pe care se afla Locatarul
	arunca exceptie daca nu gaseste Locatarul
	*/
	const int find(const int apartament) override;

	/*
	Returneaza lista de apartamente
	*/
	const std::vector<Locatar>& get_all() override;

	/*
	Sorteaza apartamentele dupa criteriul cmp
	cmp sa returneze true daca L1<L2, false altfel
	*/
	void sort(bool(*cmp)(const Locatar&, const Locatar&)) override;

	/*
	Gaseste toate tipurile de apartamente
	*/
	std::vector<DTO> get_all_types() override;

	~ProbaRepo() override {};
};
