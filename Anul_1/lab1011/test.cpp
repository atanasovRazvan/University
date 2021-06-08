#include "test.h"
#include <assert.h>

void test_domain()
{
	Locatar x{ "dorel", "2 camere", 16, 78 };
	assert(x.get_nume().compare(std::string("dorel")) == 0);
	assert(x.get_tip().compare(std::string("2 camere")) == 0);
	assert(x.get_apartament() == 16);
	assert(x.get_suprafata() == 78);
	Locatar p{ x };
	assert(p.get_nume().compare(std::string("dorel")) == 0);
	assert(p.get_tip().compare(std::string("2 camere")) == 0);
	assert(p.get_apartament() == 16);
	assert(p.get_suprafata() == 78);
	assert(p == x);
	Locatar a;
	a.set_apartament(16);
	a.set_nume("damian");
	a.set_suprafata(40);
	a.set_tip("decomandat");
	assert(a.get_nume().compare(std::string("damian")) == 0);
	assert(a.get_tip().compare(std::string("decomandat")) == 0);
	assert(a.get_apartament() == 16);
	assert(a.get_suprafata() == 40);
	assert(a == p);
}

void test_repo() {
	std::ofstream ofs;
	ofs.open("test_repo.txt", std::ofstream::out | std::ofstream::trunc);
	ofs.close();
	FileRepo repo("test_repo.txt");
	Locatar a{ "dorel", "2 camere", 16, 40 };
	repo.add(a);
	assert(repo.get_all().size() == 1);
	a = Locatar("nedorel", "3 camere", 12, 40);
	repo.add(a);
	assert(repo.get_all().size() == 2);
	a = Locatar("dorel", "2 camere", 16, 40);
	repo.del(a);
	assert(repo.get_all().size() == 1);
	a = Locatar("dorel", "2 camere", 1, 40);
	try {
		repo.del(a);
		assert(false);
	}
	catch (std::exception) {
		assert(true);
	}
	a = Locatar("predorel", "semidecomandat", 12, 40);
	repo.update(a);
	assert(repo.get_all().at(0).get_nume().compare("predorel") == 0);
	repo.get_all_types();
	repo.del(a);
}

void test_service() {
	std::ofstream ofs;
	ofs.open("test_repo.txt", std::ofstream::out | std::ofstream::trunc);
	ofs.close();
	try {
		FileRepo repo("est_repo.txt");
		assert(false);
	}
	catch (std::exception) {
		assert(true);
	}
	FileRepo repo("test_repo.txt");
	Service sv{ repo };
	sv.add("dorel1", "2 camere", 12, 46);
	try {
		sv.add("dorel", "2 camere", 12, 40);
		assert(false);
	}
	catch (std::exception) {
		assert(true);
	}
	sv.add("dorel2", "2 camere", 16, 40);
	sv.add("dorel3", "2 camere", 5, 56);
	//assert(repo.get_all().size() == 3);
	sv.del(5);
	try {
		sv.del(765);
		assert(false);
	}
	catch(std::exception){
		assert(true);
	}
	//assert(repo.get_all().size() == 2);
	assert(sv.cautare_apartament(16).get_nume().compare("dorel2")==0);
	try {
		Locatar l = sv.cautare_apartament(434);
		assert(false);
	}
	catch (std::exception) {
		assert(true);
	}
	assert(sv.filtrare_apartament("2 camere").size() == 2);
	assert(sv.filtrare_apartament("dorel").size() == 0);
	assert(sv.filtrare_suprafata(10).size() == 2);
	assert(sv.filtrare_suprafata(100).size() == 0);
	sv.sortare_nume();
	sv.sortare_suprafata();
	sv.get_all_types();
	sv.add("dorel3", "3 camere", 11, 60);
	sv.sortare_apartament();
	sv.update("dorel4", "3 camere", 11, 60);
	sv.adauga_cos(11);
	try {
		sv.adauga_cos(5465);
		assert(false);
	}
	catch (std::exception) {
		assert(true);
	}
	sv.goleste_cos();
	sv.populeaza(50);
	sv.get_cos();
	sv.salveaza_cos();
	try {
		sv.adauga_cos(1111);
		assert(false);
	}
	catch (std::exception) {
		assert(true);
	}
	sv.goleste_cos();
	assert(true);
	sv.undo_me();
	sv.undo_me();
	sv.undo_me();
	sv.undo_me();
	sv.undo_me();
	sv.undo_me();
	try {
		sv.undo_me();
		assert(false);
	}
	catch (std::exception) {
		assert(true);
	}
	sv.add("dorel3", "3 camere", 11, 60);
}

void test() {
	test_domain();
	test_repo();
	test_service();
}
