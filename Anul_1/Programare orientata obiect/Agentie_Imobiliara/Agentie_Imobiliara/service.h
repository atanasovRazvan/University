#pragma once
#include "domain.h"
#include "repository.h"

/*
	Structura serv are doua campuri.
	Un pointer la o structura rep.
	Un pointer la o structura validator.
*/
typedef struct {
	repo* rep;
}serv;
//functii dependente de reprezentare
serv* creaza_srv(repo* rep);
void distruge_srv(serv* srv);
//operatii cu serv
int adaugare_oferta(serv* srv, oferta* of);
int stergere_oferta(serv* srv, int pozitie);
int actualizare_oferta(serv* srv, int pozitie, char* tip, char* adresa, int suprafata, int pret);
repo* sortare_oferte(serv* srv, int criteriu);
repo* filtrare_oferte(serv* srv, char* criteriu, char* tip);
//teste
void teste_service();