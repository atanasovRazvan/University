#pragma once

/*
	Oferta:
	- retine un tip <-> char*
	- retine o adresa <-> char*
	- retine un pret <-> int
	- retine o adresa <-> int
	
*/
typedef struct {
	char* tip;
	char* adresa;
	int suprafata;
	int pret;
}oferta;

//malloc si free
oferta* creaza_oferta(char* tip, char* adresa, int suprafata, int pret);
void distruge_oferta(oferta* of);
//gettere
char* get_tip(oferta* of);
char* get_adresa(oferta* of);
int get_suprafata(oferta* of);
int get_pret(oferta* of);
//settere
void set_tip(oferta* of, char* tip);
void set_adresa(oferta* of, char* adresa);
void set_suprafata(oferta* of, int suprafata);
void set_pret(oferta* of, int pret);
//teste
void teste_oferta();