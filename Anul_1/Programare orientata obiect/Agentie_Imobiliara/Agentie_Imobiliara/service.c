#pragma once
#define _CRTDBG_MAP_ALLOC

#include "domain.h"
#include "repository.h"
#include "service.h"

#include <stdlib.h>
#include <crtdbg.h>
#include <ctype.h>
#include <string.h>
#include <assert.h>
/*
	Se primesc doi parametrii, un pointer la rep si un pointer la validator.
	Se aloca memorie pentru strucutra serv.
	Se asigneaza la campurile srv parametrii primiti.
	Se returneaza un pointer la srv.
*/
serv* creaza_srv(repo* rep) {
	serv* srv = malloc(sizeof(serv));
	srv->rep = rep;

	return srv;
}

/*
	Primeste un parametru, un pointer la srv.
	Se elibereaza campurile lui srv.
	Se elibereaza srv
*/
void distruge_srv(serv* srv) {
	distruge_rep(srv->rep);
	free(srv);
}

/*
	Se primesc doi parametrii, un pointer la serv si un pointer la oferta
	Se valideaza oferta. In cazul in care este valida, se adauga la campul rep.
	Functia returneaza 0 daca adaugarea a avut succes, 1 in caz contrar.
*/
int adaugare_oferta(serv* srv, oferta* of) {
	
	srv->rep = adaugare(srv->rep, of);
	return 0;
}

/*
	Functia primeste doi parametrii, un pointer la srv si o pozitie.
	Daca pozitia este valida, se va sterge oferta indicata de aceasta pozitie.
	Returneaza 0 daca pozitia e valida, 1 in caz contrar.
*/
int stergere_oferta(serv* srv, int pozitie) {
	srv->rep = stergere(srv->rep, pozitie - 1);
	return 0;
}

/*
	Functia primeste 6 parametrii. 
	1 -> un pointer la serv
	2 -> pozitia ofertei ce se doreste a fi actualizata
	3 -> noul tip
	4 -> noua adresa
	5 -> noua suprafata
	6 -> noul pret	

	Se vor valida (2 - 5) si se va modifica oferta indicata de pozitie.
	Se returneaza 0 in cazul in care aceste date sunt valide, 1 in caz contrar.
*/
int actualizare_oferta(serv* srv, int pozitie, char* tip, char* adresa, int suprafata, int pret) {
	oferta* of = creaza_oferta(tip, adresa, suprafata, pret);
	if (strlen(tip) == 0)
		set_tip(of, "teren");
	if (strlen(adresa) == 0)
		set_adresa(of, "Strada Castanelor");
	if (pozitie >= 1 && pozitie <= len(srv->rep)) {
		actualizare(srv->rep, pozitie - 1, tip, adresa, suprafata, pret);
		distruge_oferta(of);
		return 0;
	}

	//oferta sau pozitia au fost gresite
	distruge_oferta(of);
	return 1;
}

/*
	Primeste doi parametrii, un pointer la srv si un criteriu de sortare {criteriu = 0, dupa pret, criteriu = 1, lexicografic dupa tip}
	Returneaza o copie a campului rep al srv, ordonat.
*/
repo* sortare_oferte(serv* srv, int criteriu) {
	return sortare(srv->rep, criteriu);
}

/*
	Primeste trei parametrii:
	srv -> un pointer la o structura service
	criteriu -> filtrul introdus de utilizator; poate fi {suprafata, tip, pret}
	tip -> in cazul in care se doreste filtrarea dupa tip, in tip se va trimite tipul ofertei ce se doreste filtrate
			filtrul poate fi {apartament, casa, teren}
	se returneaza un pointer la rep;
*/
repo* filtrare_oferte(serv* srv, char* criteriu, char* tip) {
	return filtrare(srv->rep, criteriu, tip);
}


/*
	Se verifica toate functionalitatile de mai sus.
*/
void teste_service() {
	repo* rep = creaza_repo(10);
	serv* srv = creaza_srv(rep);
	
	oferta* of = NULL;
	of = creaza_oferta("teren", "strada castanelor", 200, 100);
	assert(adaugare_oferta(srv, of) == 0);
	distruge_oferta(of);

	assert(strcmp(get_tip(srv->rep->v[0]), "teren") == 0);
	assert(strcmp(get_adresa(srv->rep->v[0]), "strada castanelor") == 0);
	assert(get_suprafata(srv->rep->v[0]) == 200);
	assert(get_pret(srv->rep->v[0]) == 100);

	assert(actualizare_oferta(srv, 1, "", "", 100, 200) == 0);
	assert(actualizare_oferta(srv, 12312, "", "", 100, 200) == 1);

	assert(stergere_oferta(srv, 1) == 0);

	of = creaza_oferta("teren", "strada castanelor", 200, 100);
	assert(adaugare_oferta(srv, of) == 0);
	distruge_oferta(of);

	of = creaza_oferta("nuteren", "strada castanelor", 200, 100);
	assert(adaugare_oferta(srv, of) == 0);
	distruge_oferta(of);

	of = creaza_oferta("teren", "strada castanelor", 200, 100);
	assert(adaugare_oferta(srv, of) == 0);
	distruge_oferta(of);

	of = creaza_oferta("teren", "strada castanelor", 200, 100);
	assert(adaugare_oferta(srv, of) == 0);
	distruge_oferta(of);

	assert(len(srv->rep) == 4);

	repo* rep_sortat = NULL;
	rep_sortat = sortare_oferte(srv, 0);
	distruge_rep(rep_sortat);

	rep_sortat = sortare_oferte(srv, 1);
	distruge_rep(rep_sortat);

	repo* rep_filtrat = NULL;
	rep_filtrat = filtrare_oferte(srv, "tip", "apartament");
	distruge_rep(rep_filtrat);

	rep_filtrat = filtrare_oferte(srv, "tip", "casa");
	distruge_rep(rep_filtrat);

	rep_filtrat = filtrare_oferte(srv, "tip", "teren");
	distruge_rep(rep_filtrat);

	rep_filtrat = filtrare_oferte(srv, "suprafata", "\0");
	distruge_rep(rep_filtrat);

	rep_filtrat = filtrare_oferte(srv, "pret", "\0");
	distruge_rep(rep_filtrat);


	distruge_srv(srv);
}