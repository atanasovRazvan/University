#include "Service.h"




void Service::add(const std::string &nume, const std::string &tip, const int apartament, const int suprafata)
{
	Locatar aux{ nume, tip, apartament, suprafata };
	try {
		repo.find(apartament);
	}
	catch (std::exception) {
		repo.add(aux);
		//undo.push_back(std::move(std::unique_ptr<UndoAdauga>(new UndoAdauga{ aux, repo })));
		undo.push_back(std::make_unique<UndoAdauga>(aux, repo));
		return;
	}
	throw std::exception("Locatar deja existent.");
}

void Service::del(const int apartament)
{
	Locatar aux{ "dorel", "dorel", apartament, 1 };
	auto auxi = repo.del(aux);
	//auto a = std::unique_ptr<UndoSterge>{ new UndoSterge{ auxi, repo } };
	//undo.push_back(std::move(std::unique_ptr<UndoSterge>(new UndoSterge{ auxi, repo })));
	undo.push_back(std::make_unique<UndoSterge>(auxi, repo));
}

void Service::update(const std::string &nume, const std::string &tip, const int apartament, const int suprafata)
{
	Locatar aux{ nume, tip, apartament, suprafata };
	auto p = repo.update(aux);
	//auto a = std::unique_ptr<UndoModifica>{ new UndoModifica{ p, repo } };
	//undo.push_back(std::move(std::unique_ptr<UndoModifica>(new UndoModifica{ p, repo })));
	undo.push_back(std::make_unique<UndoModifica>(p, repo));
}

const Locatar Service::cautare_apartament(const int apartament)
{
	return repo.get_all().at(repo.find(apartament));
}

const std::vector<Locatar> Service::get_all()
{
	return repo.get_all();
}

const std::vector<Locatar> Service::filtrare_apartament(const std::string &search) 
{
	std::vector<Locatar> r{ repo.get_all().size() };
	auto init = repo.get_all();
	auto it = std::copy_if(init.begin(), init.end(), r.begin(), [&](Locatar i) {return i.get_tip().compare(search) == 0; });
	r.resize(std::distance(r.begin(), it));
	return r;
}

const std::vector<Locatar> Service::filtrare_suprafata(const int  search) 
{
	std::vector<Locatar> aux{ repo.get_all().size() };
	auto init = repo.get_all();
	auto it = std::copy_if(init.begin(), init.end(), aux.begin(), [&](Locatar i) {return i.get_suprafata() >= search; });
	aux.resize(std::distance(aux.begin(), it));
	return aux;
}

bool cmp_nume(const Locatar& t, const Locatar& ot) {
	return t.get_nume().compare(ot.get_nume()) == -1;
}

bool cmp_suprafata(const Locatar& t, const Locatar& ot) noexcept {
	return t.get_suprafata() - ot.get_suprafata() < 0;
}

bool cmp_ap(const Locatar& t, const Locatar& ot) {
	return t.get_tip().compare(ot.get_tip()) == -1;
}

std::vector<Locatar> Service::sortare_nume()
{
	std::vector<Locatar> rez = repo.get_all();
	std::sort(rez.begin(), rez.end(), cmp_nume);
	return rez;
}

std::vector<Locatar> Service::sortare_suprafata()
{
	std::vector<Locatar> rez = repo.get_all();
	std::sort(rez.begin(), rez.end(), cmp_suprafata);
	return rez;
}

std::vector<Locatar> Service::sortare_apartament()
{
	std::vector<Locatar> rez = repo.get_all();
	std::sort(rez.begin(), rez.end(), cmp_suprafata);
	std::sort(rez.begin(), rez.end(), cmp_ap);
	return rez;
}

void Service::adauga_cos(const int apartament)
{
	/*try {
		repo.find(apartament);
		throw(std::exception("nem"));
	}
	catch (CosException) {
		int poz = repo.find(apartament);
		cos.add(repo.get_all().at(poz));
	}*/
	cos.add(repo.get_all().at(repo.find(apartament)));
}

void Service::goleste_cos()
{
	cos.goleste();
}

void Service::populeaza(const int nr)
{
	std::vector<Locatar> aux;
	for (auto& x : repo.get_all()) {
		aux.push_back(x);
	}
	for (int i = 0; i < nr && aux.size() > 0; i++) {
		std::uniform_int_distribution<> dist(0, aux.size() - 1);
		int rndNr = dist(mt);
		cos.add(aux.at(rndNr));
		aux.erase(aux.begin() + rndNr);
	}
}

void Service::salveaza_cos() const noexcept
{
	cos.salveaza();
}

const Cos & Service::get_cos_old() const noexcept
{
	return cos;
}

std::vector<Locatar> Service::get_cos() const noexcept
{
	return cos.get_all();
}

std::vector<DTO> Service::get_all_types() const
{
	return repo.get_all_types();
}

void Service::undo_me()
{
	if (undo.empty()) {
		throw std::exception("Nu se poate face undo.");
	}
	//auto e = undo.back();
	undo.back()->undo_me();
	undo.pop_back();
	//delete e;
}
