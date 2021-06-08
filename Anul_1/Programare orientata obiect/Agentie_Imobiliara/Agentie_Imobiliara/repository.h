#pragma once
#include "domain.h"

/*
	Structura repo.
	Are trei campuri:
	-> Un array de pointere la structuri oferta.
	-> lungimea acestui array, reprezentand cate oferte sunt momentan memorate.
	-> dimensiunea acetui aray, reprezentand maximul de oferte ce pot fi memorate la un moment dat.
		Cand se atinge aceasta limita, se va aloca o memorie de 2 ori mai mare.
*/
typedef struct {
	oferta** v;
	int dim;
	int len;
}repo;

//functii legate de reprezentarea in memorie a structurii repo
repo* creaza_repo(int size);
void distruge_rep(repo* rep);
int len(repo* rep);
int dim(repo* rep);
repo* redimensionare_rep(repo* rep, int how);
//operatii cu repositoryul.
repo* adaugare(repo* rep, oferta* of);
repo* stergere(repo* rep, int pozitie);
void actualizare(repo* rep, int pozitie, char* tip, char* adresa, int suprafata, int pret);
repo* sortare(repo* rep, int criteriu);
repo* filtrare(repo* rep, char* criteriu, char* tip);
//teste
void teste_repo();