#include "domainH.h"
#include "repositoryH.h"
#include "testsH.h"
#include "serviceH.h"
#include <assert.h>
#include <cstring>
#include <vector>


void testDomain() {
	
	Masina car ("a", "b", "c", "d");
	assert(car.get_numar_inmatriculare().compare("a") == 0);
	assert(car.get_producator().compare("b") == 0);
	assert(car.get_model().compare("c") == 0);
	assert(car.get_tip().compare("d") == 0);

	car.set_numar_inmatriculare("1");
	car.set_producator("2");
	car.set_model("3");
	car.set_tip("4");

	assert(car.get_numar_inmatriculare().compare("1") == 0);
	assert(car.get_producator().compare("2") == 0);
	assert(car.get_model().compare("3") == 0);
	assert(car.get_tip().compare("4") == 0);

}

void testRepository() {

	Repository Repo;
	Masina car1("a", "b", "c", "d");
	Masina car2("A", "B", "C", "D");
	Masina car3("1", "2", "3", "4");
	Masina car4("q", "w", "e", "r");
	Masina car5("Q", "W", "E", "R");

	assert(Repo.get_dim() == 0);
	Repo.add(car1);
	assert(Repo.get_dim() == 1);
	Repo.update(car1, car2);
	assert(Repo.get_masina(1).get_numar_inmatriculare() == "A");
	Repo.add(car3);
	assert(Repo.get_dim() == 2);
	Repo.add(car4);
	assert(Repo.get_dim() == 3);
	Repo.add(car5);
	assert(Repo.get_dim() == 4);
	Repo.del(car3);
	assert(Repo.get_dim() == 3);
	Repo.del(car5);
	assert(Repo.get_dim() == 2);
}

void testService() {

	Service srv;
	Masina car1("a", "b", "c", "d");
	Masina car2("A", "B", "C", "D");
	Masina car3("1", "2", "3", "4");
	Masina car4("q", "w", "e", "r");
	Masina car5("Q", "W", "E", "R");

	assert(srv.get_dimSrv() == 0);
	srv.addSrv(car1);
	assert(srv.get_dimSrv() == 1);
	srv.updateSrv(car1, car2);
	assert(srv.get_masinaSrv(1).get_numar_inmatriculare() == "A");
	srv.addSrv(car3);
	assert(srv.get_dimSrv() == 2);
	srv.addSrv(car4);
	assert(srv.get_dimSrv() == 3);
	srv.addSrv(car5);
	assert(srv.get_dimSrv() == 4);
	srv.delSrv(car3);
	assert(srv.get_dimSrv() == 3);
	srv.delSrv(car5);
	assert(srv.get_dimSrv() == 2);
	assert(srv.filterCarsByModel("asdf").size() == 0);

}

void testAll() {

	testDomain();
	testRepository();
	testService();

}