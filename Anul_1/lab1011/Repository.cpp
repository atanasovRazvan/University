#include "Repository.h"



void FileRepo::save_to_file() const
{
	std::ofstream fout(file_name);
	if (!fout.is_open()) {
		throw std::exception("Nu s-a putut deschide fisierul.\n");
	}
	for (auto& el : repo) {
		fout << el.get_nume() << std::endl;
		fout << el.get_apartament() << std::endl;
		fout << el.get_tip() << std::endl;
		fout << el.get_suprafata() << std::endl;
	}
	fout.close();
}

void FileRepo::load_from_file()
{
	repo.clear();
	std::ifstream fin(file_name);
	if (!fin.is_open()) {
		throw std::exception("Nu s-a putut deschide fisierul.\n");
	}
	while (!fin.eof()) {
		std::string nume, tip, apar, su;
		int ap, supraf;
		getline(fin, nume);
		if (fin.eof()) {
			break;
		}
		getline(fin, apar);
		ap = std::stoi(apar);
		getline(fin, tip); 
		getline(fin, su);
		supraf = std::stoi(su);
		const Locatar loc{ nume, tip, ap, supraf };
		repo.push_back(loc);
	}
	fin.close();
}

void FileRepo::add(const Locatar &l)
{
	//repo.push_back(l);
	Repository::add(l);
	save_to_file();
}

Locatar FileRepo::update(const Locatar &l)
{
	auto aux = Repository::update(l);
	save_to_file();
	return aux;
}

Locatar FileRepo::del(const Locatar &l)
{
	auto aux = Repository::del(l);
	save_to_file();
	return aux;
}

const int FileRepo::find(const int apartament)
{
	load_from_file();
	return Repository::find(apartament);
}

const std::vector<Locatar>& FileRepo::get_all()
{
	load_from_file();
	return Repository::get_all();
}

void FileRepo::sort(bool(*cmp)(const Locatar&, const Locatar&))
{
	Repository::sort(cmp);
}

std::vector<DTO> FileRepo::get_all_types()
{
	return Repository::get_all_types();
}

DTO::DTO(const DTO & d)
{
	tip = d.get_tip();
	nr = d.get_nr();
}






Repository::Repository() noexcept
{
	//is_file_opened = false;
}

void Repository::add(const Locatar &l)
{
	repo.push_back(l);
}

Locatar Repository::update(const Locatar &l)
{
	const int i = find(l.get_apartament());
	auto aux = repo.at(i);
	repo.at(i) = l;
	return aux;
}

Locatar Repository::del(const Locatar &l)
{
	const int i = find(l.get_apartament());
	auto aux = repo.at(i);
	repo.erase(repo.begin() + i);
	return aux;
}

const int Repository::find(const int apartament)
{
	int i = 0;
	for (auto &x : repo) {
		if (x.get_apartament() == apartament) {
			return i;
		}
		i++;
	}
	throw std::exception("Elementul nu a fost gasit");
}

const std::vector<Locatar>& Repository::get_all()
{
	return repo;
}

void Repository::sort(bool(*cmp)(const Locatar&, const Locatar&))
{
	std::sort(repo.begin(), repo.end(), cmp);
}

std::vector<DTO> Repository::get_all_types()
{
	std::map<std::string, int> aux;
	std::for_each(repo.begin(), repo.end(), [&](const Locatar& x) {
		aux[x.get_tip()]++;
	});
	std::vector<DTO> a;
	std::for_each(aux.begin(), aux.end(), [&](const auto &auxPair) {
		a.push_back(DTO(auxPair.first, auxPair.second));
	});
	return a;
}






void ProbaRepo::add(const Locatar &l)
{
	//std::uniform_real_distribution<double> unif(0.0, 1.0);
	//std::default_random_engine re;
	//double rand = unif(re);
	//auto rand = Rand_double{ 0, 1.0 };
	auto rand = random();
	if (rand < probability) {
		std::cout << rand << std::endl;
		throw(ExceptieDeMafiot("Haha te-am prins"));
	}
	repo.push_back(l);
}

Locatar ProbaRepo::update(const Locatar &l)
{
	//std::uniform_real_distribution<double> unif(0.0, 1.0);
	//std::default_random_engine re;
	//double rand = unif(re);
	//auto rand = Rand_double{ 0, 1.0 };
	auto rand = random();
	if (rand < probability) {
		std::cout << rand << std::endl;
		throw(ExceptieDeMafiot("Haha te-am prins"));
	}
	const int i = find(l.get_apartament());
	auto aux = repo.at(i);
	repo.at(i) = l;
	return aux;
}

Locatar ProbaRepo::del(const Locatar &l)
{
	//std::uniform_real_distribution<double> unif(0.0, 1.0);
	//std::default_random_engine re;
	//double rand = unif(re);
	//auto rand = Rand_double{ 0, 1.0 };
	auto rand = random();
	if (rand < probability) {
		std::cout << rand << std::endl;
		throw(ExceptieDeMafiot("Haha te-am prins"));
	}
	const int i = find(l.get_apartament());
	auto aux = repo.at(i);
	repo.erase(repo.begin() + i);
	return aux;
}

const int ProbaRepo::find(const int apartament)
{
	//std::uniform_real_distribution<double> unif(0.0, 1.0);
	//std::default_random_engine re;
	//auto rand = Rand_double{ 0, 1.0 };
	//double rand = unif(re);
	auto rand = random();
	if (rand < probability) {
		std::cout << rand << std::endl;
		throw(ExceptieDeMafiot("Haha te-am prins"));
	}
	int i = 0;
	for (auto &x : repo) {
		if (x.get_apartament() == apartament) {
			return i;
		}
		i++;
	}
	throw std::exception("Elementul nu a fost gasit");
}

const std::vector<Locatar>& ProbaRepo::get_all()
{
	//std::uniform_real_distribution<double> unif(0.0, 1.0);
	//std::default_random_engine re;
	//double rand = unif(re);
	//auto rand = Rand_double{ 0, 1.0 };
	//std::uniform_int_distribution<> dist(0, 100);
	//int rndNr = dist(mt);
	//double rand = (double)dist(mt) / 100;
	auto rand = random();
	if (rand < probability) {
		std::cout << rand << std::endl;
		throw(ExceptieDeMafiot("Haha te-am prins"));
	}
	return repo;
}

void ProbaRepo::sort(bool(*cmp)(const Locatar &, const Locatar &))
{
	//std::uniform_real_distribution<double> unif(0.0, 1.0);
	//std::default_random_engine re;
	//double rand = unif(re);
	//auto rand = Rand_double{ 0, 1.0 };
	//std::uniform_int_distribution<> dist(0, 100);
	//int rndNr = dist(mt);
	//double rand = (double)dist(mt) / 100;
	auto rand = random();
	if (rand < probability) {
		std::cout << rand << std::endl;
		throw(ExceptieDeMafiot("Haha te-am prins"));
	}
	std::sort(repo.begin(), repo.end(), cmp);
}

std::vector<DTO> ProbaRepo::get_all_types()
{
	//std::uniform_real_distribution<double> unif(0.0, 1.0);
	//std::default_random_engine re;
	//auto rand = Rand_double{ 0, 1.0 };
	/*std::uniform_int_distribution<> dist(0, 100);
	int rndNr = dist(mt);
	double rand = (double)dist(mt) / 100;*/
	auto rand = random();
	if (rand < probability) {
		std::cout << rand << std::endl;
		throw(ExceptieDeMafiot("Haha te-am prins"));
	}
	std::map<std::string, int> aux;
	std::for_each(repo.begin(), repo.end(), [&](const Locatar& x) {
		aux[x.get_tip()]++;
	});
	std::vector<DTO> a;
	std::for_each(aux.begin(), aux.end(), [&](const auto &auxPair) {
		a.push_back(DTO(auxPair.first, auxPair.second));
	});
	return a;
}
