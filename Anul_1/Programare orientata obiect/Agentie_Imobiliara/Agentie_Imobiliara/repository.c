#pragma once
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#include <stdio.h>
#include "domain.h"
#include "repository.h"
#include <string.h>
#include <assert.h>

/*
	Se aloca memorie pentru un obiect de tipul repo si pentru fiecare camp al acestuia.
	Primeste un parametru ce reprezinta dimensiunea initiala a array-ului de oferte.
	returneaza un pointer la aceasta memorie.
*/
repo* creaza_repo(int size) {
	repo* rep = malloc(sizeof(repo));
	rep->v = malloc(sizeof(oferta) * size);
	rep->len = 0;
	rep->dim = size;
	return rep;
}


/*
	Functia primeste un parametru, un pointer la rep.
	Se va elibera fiecare structura de tip oferta memorata in array
	Se va elibera array-ul
	Se va elibera rep.
*/
void distruge_rep(repo* rep) {
	for (int i = len(rep) - 1; i >= 0; i--) distruge_oferta(rep->v[i]);
	free(rep->v);
	free(rep);
}

/*
	Primeste un pointer la repo.
	Returneaza numarul elementelor array-ului retinut -> tip int.
*/
int len(repo* rep) {
	int len;
	len = rep->len;
	return len;
}
/*
	Primeste un pointer la repo.
	Returneaza dimensiunea array-ului retinut -> tip int.
*/
int dim(repo* rep) {
	int dim;
	dim = rep->dim;
	return dim;
}
/*
	Functia primeste doi paramterii: un pointer la repo si o valoare de tip int.
	In functie de aceasta valoare se va mari sau micsora dimensiunea array-ului.
	how == 0 -> se micsoreaza dimensiunea arrayului pe jumatate.
	how == 1 -> se mareste dimensiunea arrayului * 2.

	Se aloca dinamic memorie pentru un array cu dimensiunea necesara, se copiaza elementele
	si se elibereaza vechiul repo.
	Se returneaza un pointer la repo.
*/
repo* redimensionare_rep(repo* rep, int how) {
	if (how == 0) {
		repo* rep_copie = creaza_repo(dim(rep) / 2);
		rep_copie->len = len(rep);
		for (int i = 0; i < len(rep_copie); i++)
			rep_copie->v[i] = rep->v[i];
		free(rep->v);
		free(rep);
		return rep_copie;
	}
	if (how == 1) {
		repo* rep_copie = creaza_repo(dim(rep) * 2);
		rep_copie->len = len(rep);
		for (int i = 0; i < len(rep_copie); i++)
			rep_copie->v[i] = rep->v[i];
		free(rep->v);
		free(rep);
		return rep_copie;
	}
	return rep;
}

/*
	Functia primeste doi parametrii. un pointer la repo si un pointer la o oferta.
	Se va incerca adaugarea acestei oferte in array. Daca nu mai este loc, se va redimensiona arrayul si se va efectua adaugarea
	returneaza un pointer la rep.
*/
repo* adaugare(repo* rep, oferta* of) {
	if (len(rep) + 1 > dim(rep))
		rep = redimensionare_rep(rep, 1);
	rep->v[len(rep)] = creaza_oferta(get_tip(of), get_adresa(of), get_suprafata(of), get_pret(of));
	rep->len++;
	return rep;
}

/*
	Functia primeste doi paramterii, un pointer la repo si o pozitie.
	Se va incerca stergerea ofertei de la indexul indicat de pozitie.
	Dupa stergere, se va verifica daca sunt destul de putine elemente in array. In caz ca sunt mai putine elemente decat
	un sfert din dimensiunea array-ului, acesta se va redimensiona, fiind de doua ori mai mic.
	returneaza un pointer la rep.
*/
repo* stergere(repo* rep, int pozitie) {
	distruge_oferta(rep->v[pozitie]);
	for (int i = pozitie; i < len(rep); i++) rep->v[i] = rep->v[i + 1];
	rep->len--;
	if (len(rep) < dim(rep) / 4) 
		rep = redimensionare_rep(rep, 0);
	return rep;
}

/*
	Functia primeste 6 parametrii:
	1 -> un pointer la rep
	2 -> pozitia ofertei ce se doreste a fi actualizata
	3 -> noul tip
	4 -> noua adresa
	5 -> noua suprafata
	6 -> noul pret
	Se vor actualiza doar campurile dorite de catre utilizator.
*/
void actualizare(repo* rep, int pozitie, char* tip, char* adresa, int suprafata, int pret) {
	if (strlen(tip) > 0)
		set_tip(rep->v[pozitie], tip);
	if (strlen(adresa) > 0)
		set_adresa(rep->v[pozitie], adresa);
	if (suprafata != -1)
		set_suprafata(rep->v[pozitie], suprafata);
	if (pret != -1)
		set_pret(rep->v[pozitie], pret);
}

/*
	Functia primeste 4 parametrii. un pointer la rep, punctul de plecare si de terminare si un criteriu {0 = toate, 1 = apartamente, 2 = case, 3 = terenuri}
	Se vor copia ofertele incepand de la punctul de plecare pana la cel de oprire.
	Se va returna un pointer la rep.
*/
repo* copiere(repo* rep, int de_unde, int pana_unde, int criteriu) {
	repo* rep_copie = creaza_repo(len(rep));
	for (int i = de_unde; i < pana_unde; i++)
		if(criteriu == 0)
			rep_copie = adaugare(rep_copie, rep->v[i]);
		else if (criteriu == 1) {
			if(strcmp(get_tip(rep->v[i]), "apartament") == 0)
				rep_copie = adaugare(rep_copie, rep->v[i]);
		}
		else if (criteriu == 2) {
			if(strcmp(get_tip(rep->v[i]), "casa") == 0)
				rep_copie = adaugare(rep_copie, rep->v[i]);
		}
		else if (criteriu == 3) {
			if (strcmp(get_tip(rep->v[i]), "teren") == 0)
				rep_copie = adaugare(rep_copie, rep->v[i]);
		}
	return rep_copie;
}

/*
	Merge sort implementat pe structura de tip rep.
	Este dependenta de reprezentarea lui rep.
	Rep va fi sortat crescator in urma terminarii recursivitatii.
*/
void merge_sort(repo* rep, int(*cum)(oferta*) ) {
	if (len(rep) > 1) {
		repo* stanga = copiere(rep, 0, len(rep) / 2, 0);
		repo* dreapta = copiere(rep, len(rep) / 2, len(rep), 0);

		merge_sort(stanga, (*cum));
		merge_sort(dreapta, (*cum));

		int inds = 0, indd = 0, ind = 0;
		while (inds < len(stanga) && indd < len(dreapta)) {
			if ((*cum)(stanga->v[inds]) <= (*cum)(dreapta->v[indd])) {
				distruge_oferta(rep->v[ind]);
				rep->v[ind] = creaza_oferta(get_tip(stanga->v[inds]), get_adresa(stanga->v[inds]), get_suprafata(stanga->v[inds]), get_pret(stanga->v[inds]));
				inds++;
			}
			else {
				distruge_oferta(rep->v[ind]);
				rep->v[ind] = creaza_oferta(get_tip(dreapta->v[indd]), get_adresa(dreapta->v[indd]), get_suprafata(dreapta->v[indd]), get_pret(dreapta->v[indd]));
				indd++;
			}
			ind++;
		}
		while (inds < len(stanga)) {
			distruge_oferta(rep->v[ind]);
			rep->v[ind] = creaza_oferta(get_tip(stanga->v[inds]), get_adresa(stanga->v[inds]), get_suprafata(stanga->v[inds]), get_pret(stanga->v[inds]));
			inds++;
			ind++;
		}
		while (indd < len(dreapta)) {
			distruge_oferta(rep->v[ind]);
			rep->v[ind] = creaza_oferta(get_tip(dreapta->v[indd]), get_adresa(dreapta->v[indd]), get_suprafata(dreapta->v[indd]), get_pret(dreapta->v[indd]));
			indd++;
			ind++;
		}
		distruge_rep(stanga);
		distruge_rep(dreapta);
	}
}

/*
	Functia primeste 4 parametrii.
	rep_ap -> repository cu oferte de tipul apartament
	rep_ca -> repository cu oferte de tipul casa
	rep_te -> repository cu oferte de tipul teren
	lung -> lungimea repository-ului principal, din care s-au format cele 3 repository-uri formate mai sus
	returneaza o copie a repository-ului principal, cu ofertele sortate lexicografic in functie de tip.
*/
repo* concatenare_rep(repo* rep_ap, repo* rep_ca, repo* rep_te, int lung) {
	repo* rep = creaza_repo(lung);
	//rep->len = 0;
	for (int i = 0; i < len(rep_ap); i++)
		rep = adaugare(rep, rep_ap->v[i]);
	for (int i = 0; i < len(rep_ca); i++)
		rep = adaugare(rep, rep_ca->v[i]);
	for (int i = 0; i < len(rep_te); i++)
		rep = adaugare(rep, rep_te->v[i]);
	return rep;
}

/*
	Are doi parametrii, un pointer la rep si un criteriu {0 = dupa pret, 1 dupa tip}
	Se va crea o copie cu elementele acestui rep.
	Se vor sorta aceste elemente folosind merge_sort sau in cazul in care se va sorta dupa tip,
	ofertele vor fi sortate lexicografic.
	Se va returna un pointer la aceasta copie.
*/
repo* sortare(repo* rep, int criteriu) {
	if (criteriu == 0) {
		repo* rep_copie = copiere(rep, 0, len(rep), 0);
		merge_sort(rep_copie, get_pret);
		return rep_copie;  }
	else if(criteriu == 1){
		repo* rep_apartament = copiere(rep, 0, len(rep), 1);
		repo* rep_casa = copiere(rep, 0, len(rep), 2);
		repo* rep_teren = copiere(rep, 0, len(rep), 3);
		repo* rep_con = concatenare_rep(rep_apartament, rep_casa, rep_teren, len(rep));
		distruge_rep(rep_apartament);
		distruge_rep(rep_casa);
		distruge_rep(rep_teren);
		return rep_con;
	}
	return rep;
}
/*
	Primeste trei parametrii:
	rep -> un pointer la o structura repository
	criteriu -> filtrul introdus de utilizator; poate fi {suprafata, tip, pret}
	tip -> in cazul in care se doreste filtrarea dupa tip, in tip se va trimite tipul ofertei ce se doreste filtrate
			filtrul poate fi {apartament, casa, teren}
	se returneaza un pointer la rep;
*/
repo* filtrare(repo* rep, char* criteriu, char* tip) {
	if (strcmp(criteriu, "suprafata") == 0) {
		repo* rep_suprafata = copiere(rep, 0, len(rep), 0);
		merge_sort(rep_suprafata, get_suprafata);
		return rep_suprafata;
	}
	if (strcmp(criteriu, "tip") == 0) {
		if (strcmp(tip, "apartament") == 0) {
			repo* rep_apartament = copiere(rep, 0, len(rep), 1);
			return rep_apartament;   }
		else if (strcmp(tip, "casa") == 0) {
			repo* rep_casa = copiere(rep, 0, len(rep), 2);
			return rep_casa;   }
		else if (strcmp(tip, "teren") == 0) {
			repo* rep_teren = copiere(rep, 0, len(rep), 3);
			return rep_teren;   }
	}
	if (strcmp(criteriu, "pret") == 0)
		return sortare(rep, 0);

	return rep;
}


/*
	Teste repo.
	Se verifica getterele si setterele.
	Se verifica redimensionarea array-ului.
	Se verifica adaugarea, modificarea, stergerea si filtrarea.
*/
void teste_repo() {
	repo* rep = creaza_repo(10);
	oferta* of = NULL;
	of = creaza_oferta("Ap- 1", "Str- 1", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);
	assert(strcmp(get_tip(rep->v[0]), "Ap- 1") == 0);
	assert(strcmp(get_adresa(rep->v[0]), "Str- 1") == 0);
	assert(get_suprafata(rep->v[0]) == 1);
	assert(get_pret(rep->v[0]) == 2);

	of = creaza_oferta("Ap- 2", "Str- 2", 1, 1);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	rep = stergere(rep, 1);

	of = creaza_oferta("Ap- 2", "Str- 2", 1, 1);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("Ap- 3", "Str- 3", 1, 5);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	
	repo* rep_sortat = NULL;
	rep_sortat = sortare(rep, 0);
	assert(get_pret(rep_sortat->v[0]) == 1);
	assert(get_pret(rep_sortat->v[1]) == 2);
	assert(get_pret(rep_sortat->v[2]) == 5);
	distruge_rep(rep_sortat);

	of = creaza_oferta("Ap- 4", "Str- 4", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("Ap- 5", "Str- 5", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("Ap- 6", "Str- 6", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	actualizare(rep, 5, "Ap- 6", "Str- 6", 1, 2);

	of = creaza_oferta("Ap- 7", "Str- 7", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("Ap- 8", "Str- 8", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("Ap- 9", "Str- 9", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	assert(strcmp(get_tip(rep->v[8]), "Ap- 9") == 0);
	assert(strcmp(get_adresa(rep->v[8]), "Str- 9") == 0);
	assert(get_suprafata(rep->v[8]) == 1);
	assert(get_pret(rep->v[8]) == 2);
	actualizare(rep, 8, "Modificare", "Modificare", -1, -1);
	assert(strcmp(get_tip(rep->v[8]), "Modificare") == 0);
	assert(strcmp(get_adresa(rep->v[8]), "Modificare") == 0);
	assert(get_suprafata(rep->v[8]) == 1);
	assert(get_pret(rep->v[8]) == 2);

	of = creaza_oferta("Ap-10", "Str-10", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("Ap-11", "Str-11", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	rep = stergere(rep, 5);

	of = creaza_oferta("Ap-12", "Str-12", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	rep_sortat = sortare(rep, 0);
	distruge_rep(rep_sortat);

	of = creaza_oferta("casa", "Strada 1", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("teren", "Strada 2", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	of = creaza_oferta("apartament", "Strada 3", 1, 2);
	rep = adaugare(rep, of);
	distruge_oferta(of);

	rep_sortat = sortare(rep, 1);
	assert(strcmp(get_tip(rep_sortat->v[0]), "apartament") == 0);
	assert(strcmp(get_tip(rep_sortat->v[1]), "casa") == 0);
	assert(strcmp(get_tip(rep_sortat->v[2]), "teren") == 0);
	assert(len(rep_sortat) == 3);
	distruge_rep(rep_sortat);

	repo* rep_filtrat = NULL;
	rep_filtrat = filtrare(rep, "nimic", "nimic2");
	rep_filtrat = filtrare(rep, "tip", "apartament");
	assert(len(rep_filtrat) == 1);
	distruge_rep(rep_filtrat);

	rep_filtrat = filtrare(rep, "tip", "casa");
	assert(len(rep_filtrat) == 1);
	distruge_rep(rep_filtrat);

	rep_filtrat = filtrare(rep, "tip", "teren");
	assert(len(rep_filtrat) == 1);
	distruge_rep(rep_filtrat);


	rep_filtrat = filtrare(rep, "suprafata", "\0");
	distruge_rep(rep_filtrat);

	rep_filtrat = filtrare(rep, "pret", "\0");
	distruge_rep(rep_filtrat);

	distruge_rep(rep);
}