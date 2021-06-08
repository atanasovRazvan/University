#pragma once
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#include <stdio.h>
#include "domain.h"
#include <string.h>
#include <assert.h>

/*
	Creaza o structura de tip oferta.
	Primeste ca parametrii tipul, adresa, suprafata si pretul ofertei.
	Tipul si adresa sunt alocate dinamic.
	returneaza un pointer la oferta creata.
*/

oferta* creaza_oferta(char* tip, char* adresa, int suprafata, int pret) {
	oferta* of = malloc(sizeof(oferta));
	of->tip = malloc(sizeof(char) * (strlen(tip) + 1));
	strcpy(of->tip, tip);
	of->adresa = malloc(sizeof(char) * (strlen(adresa) + 1));
	strcpy(of->adresa, adresa);
	of->pret = pret;
	of->suprafata = suprafata;
	return of;
}
//gettere

/*
	Primeste un pointer la o oferta.
	returneaza tipul ofertei -> tip char*.
*/
char* get_tip(oferta* of) {
	return of->tip;
}

/*
	Primeste un pointer la o oferta.
	returneaza tipul ofertei -> tip char*.
*/
char* get_adresa(oferta* of) {
	return of->adresa;
}

/*
	Primeste un pointer la o oferta.
	returneaza tipul ofertei -> tip int.
*/
int get_suprafata(oferta* of) {
	return of->suprafata;
}

/*
	Primeste un pointer la o oferta.
	returneaza pretul ofertei -> tip int.
*/
int get_pret(oferta* of) {
	return of->pret;
}
//settere

/*
	Primeste un pointer la o oferta si un pointer la un string.
	Elibereaza memoria ocupata de vechiul tip., si aloca o noua zona de memorie potrivita pentru noul tip.
	se efectueaza schimbarea.
*/
void set_tip(oferta* of, char* tip) {
	if (strlen(tip) > strlen(of->tip)) {
		free(of->tip);
		of->tip = malloc(sizeof(char) * (strlen(tip)  + 1));
	}
	strcpy(of->tip, tip);
}

/*
	Primeste un pointer la o oferta si un pointer la un string.
	Elibereaza memoria ocupata de vechia adresa, si aloca o noua zona de memorie potrivita pentru noua adresa.
	se efectueaza schimbarea.
*/
void set_adresa(oferta* of, char* adresa) {
	if (strlen(adresa) > strlen(get_adresa(of))) {
		free(of->adresa);
		of->adresa = malloc(sizeof(char) * (strlen(adresa) + 1));
	}
	strcpy(of->adresa, adresa);
}

/*
	Primeste un pointer la o oferta si o valoare int reprezentand suprafata.
	se efectueaza inlocuirea.
*/
void set_suprafata(oferta* of, int suprafata) {
	of->suprafata = suprafata;
}

/*
	Primeste un pointer la o oferta si o valoare int reprezentant pretul.
	se efectueaza inlocuirea.
*/
void set_pret(oferta* of, int pret) {
	of->pret = pret;
}


/*
	primeste un pointer la o oferta
	elibereaza zonele de memorie ocupate de campurile ofertei si se elibereaza oferta insasi.
*/
void distruge_oferta(oferta* of) {
	free(of->adresa);
	free(of->tip);
	free(of);
}
//teste ce verifica getterele si setterele.
void teste_oferta() {
	oferta* of = creaza_oferta("casa", "Str. Mihai Viteazul", 1000, 20000);
	//testare gettere
	assert(strcmp(get_tip(of), "casa") == 0);
	assert(strcmp(get_adresa(of), "Str. Mihai Viteazul") == 0);
	assert(get_suprafata(of) == 1000);
	assert(get_pret(of) == 20000);
	//testare settere
	set_tip(of, "apartament");
	set_adresa(of, "Mehedinti");
	set_suprafata(of, 15023);
	set_pret(of, 1000);
	assert(strcmp(get_tip(of), "apartament") == 0);
	assert(strcmp(get_adresa(of), "Mehedinti") == 0);
	assert(get_suprafata(of) == 15023);
	assert(get_pret(of) == 1000);
	//testare distrugere
	distruge_oferta(of);
}