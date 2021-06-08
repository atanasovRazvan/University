#pragma once
#define _CRTDBG_MAP_ALLOC

#include "domain.h"
#include "repository.h"
#include "service.h"
#include "console.h"

#include <stdio.h>
#include <stdlib.h>
#include <crtdbg.h>
#include <ctype.h>
#include <string.h>
#include <assert.h>
/*
	Functia creaza primeste un pointer la serv.
	Aloca memorie pentru strucutra consola si atribuie campului srv, pointerul primit.
	Returneaza un pointer la consola.
*/
console* creaza_cons(serv* srv) {
	console* cons = malloc(sizeof(console));
	cons->srv = srv;
	return cons;
}
/*
	Primeste un pointer la consola.
	Apeleaza functia distruge_srv pentru campul srv.
	elibereaza consola.
*/
void distruge_cons(console* cons) {
	distruge_srv(cons->srv);
	free(cons);
}
/*
	Functie ajutatoare ce rezolva probleme la citirea unui string cu spatii.
*/
void citeste_adresa(char* arr) {
	int contor = 0, c = getchar(); c = 0;
	do {
		c = getchar();
		arr[contor++] = (char)c;

	} while (c != '\n');
	arr[contor - 1] = '\0';
}
/*
	Functia de adaugare din consola.
	Primeste un pointer la consola.
	Se ocupa de citirea datelor de la tastatura si de instruirea userului.
	Adauga oferta introdusa in memorie in cazul in care datele introduse sunt valide.
*/
void adaugare_cons(console* cons) {
	char tip[20], adresa[100]; tip[0] = '\0'; adresa[0] = '\0';
	int suprafata = 0, pret = 0;
	printf("Introduceti datele ofertei:\n");
	printf("Dati tipul (teren/ casa/ apartament): ");
	scanf(" %s", tip);
	printf("Dati adresa: ");
	citeste_adresa(adresa);
	printf("Dati suprafata (trebuie sa fie un numar natural!): ");
	scanf(" %d", &suprafata);
	printf("Dati pretul (trebuie sa fie un numar natural!): ");
	scanf(" %d", &pret);
	oferta* of = creaza_oferta(tip, adresa, suprafata, pret);

	if (adaugare_oferta(cons->srv, of) == 0)
		printf("\nOferta a fost adaugata cu succes!");
	distruge_oferta(of);
}
/*
	Functie ajutatoare.
	Primeste un pointer la rep.
	Afiseaza toate ofertele memorate.
*/
void afisare_oferte(repo* rep, int how) {
	if(how == 0)
		for (int i = 0; i < len(rep); i++) 
			printf("%2d: Tip: %10s, Adresa: %30s, Suprafata: %5d, Pret: %5d\n", i + 1, get_tip(rep->v[i]), get_adresa(rep->v[i]), get_suprafata(rep->v[i]), get_pret(rep->v[i]));
	if (how == 1) {
		int contor = 0;
		for (int i = len(rep) - 1; i > 0; i--)
			printf("%2d: Tip: %10s, Adresa: %30s, Suprafata: %5d, Pret: %5d\n", contor++, get_tip(rep->v[i]), get_adresa(rep->v[i]), get_suprafata(rep->v[i]), get_pret(rep->v[i]));

	}
}
/*
	Functia primeste un pointer la consola.
	Cere pozitia ofertei ce se doreste stearsa in urma afisarii acestora pe ecran.
	In urma validarii acestei pozitii, se va sterge oferta sau nu.
*/
void stergere_cons(console* cons) {
	int pozitie = 0;
	afisare_oferte(cons->srv->rep, 0);
	printf("Introduceti indexul ofertei ce doriti sa fie stearsa: ");
	scanf(" %d", &pozitie);
	if (stergere_oferta(cons->srv, pozitie) == 0)
		printf("\nOferta a fost stearsa cu succes!");
	else 
		printf("Ati introdus un index gresit!");
}
/*
	Primeste un pointer la consola
	Citeste datele de la tastatura.
	Daca aceste date sunt corecte, se va modifica oferta.
*/
void modificare_cons(console* cons) {
	char tip[20], adresa[100]; tip[0] = '\0'; adresa[0] = '\0';
	int suprafata = -1, pret = -1, pozitie = 0, decizie = -1;
	afisare_oferte(cons->srv->rep, 0);
	printf("Introduceti indexul ofertei ce doriti sa fie modoficate: ");
	scanf(" %d", &pozitie);
	printf("Ce doriti sa modificati la aceasta oferta?\n");
	printf("Doriti sa modificati tipul? (0 = Da / 1 = Nu): ");
	scanf(" %d", &decizie);
	if (decizie == 0) {
		printf("Dati tipul (teren/ casa/ apartament): ");
		scanf(" %s", tip);
		decizie = -1;
	}
	else decizie = -1;

	printf("Doriti sa modificati adresa? (0 = Da / 1 = Nu): ");
	scanf(" %d", &decizie);
	if (decizie == 0) {
		printf("\nDati adresa: ");
		citeste_adresa(adresa);
		decizie = -1;
	}
	else decizie = -1;

	printf("Doriti sa modificati suprafata? (0 = Da / 1 = Nu): ");
	scanf(" %d", &decizie);
	if (decizie == 0) {
		printf("\nDati suprafata (trebuie sa fie un numar natural!): ");
		scanf(" %d", &suprafata);
		decizie = -1;
	}
	else decizie = -1;

	printf("Doriti sa modificati pretul? (0 = Da / 1 = Nu): ");
	scanf(" %d", &decizie);
	if (decizie == 0) {
		printf("\nDati pretul (trebuie sa fie un numar natural!): ");
		scanf(" %d", &pret);
	}
	else decizie = -1;

	if (actualizare_oferta(cons->srv, pozitie, tip, adresa, suprafata, pret) == 0)
		printf("\nOferta a fost modificata cu succes!\n");
}
/*
	Primeste un pointer la consola.
	Afiseaza ofertele memorate sortate in funcite de pret, crescator sau descrescator.
*/
void sortare_cons(console* cons) {
	int criteriu = -1;
	int decizie = -1;
	printf("Cum sa fie ordonate ofertele? (0 = pret, 1 = tip): \n");
	scanf(" %d", &criteriu);
	if (criteriu == 0) {
		printf("Cum doriti sa fie ordonate ofertele? (0 = crescator/ 1 = descrescator): \n");
		scanf(" %d", &decizie);
		if (decizie == 1 || decizie == 0) {
			repo* rep = sortare_oferte(cons->srv, criteriu);
			if (decizie == 0) afisare_oferte(rep, 0);
			else afisare_oferte(rep, 1);
			distruge_rep(rep);
		}
	}
	else if (criteriu == 1) {
		printf("Cum doriti sa fie ordonate ofertele? (0 = crescator/ 1 = descrescator): \n");
		scanf(" %d", &decizie);
		if (decizie == 1 || decizie == 0) {
			repo* rep = sortare_oferte(cons->srv, criteriu);
			if (decizie == 0) afisare_oferte(rep, 0);
			else afisare_oferte(rep, 1);
			distruge_rep(rep);
		}
	}
}

void filtrare_cons(console* cons) {
	afisare_oferte(cons->srv->rep, 0);
	int decizie = -1;
	printf("Dupa ce criteriu vreti sa se efectueze filtrare? (0 = suprafata, 1 = tip, 2 = pret)\n");
	scanf(" %d", &decizie);
	if (decizie == 0){
		int suprafata, how;
		char tip[2], *criteriu;
		criteriu = malloc(sizeof(char) * 10);
		strcpy(criteriu, "suprafata");
		strcpy(tip, "\0");

		printf("Dati o suprafata ce se va comporta ca o limita:\n");
		scanf("%d", &suprafata);

		printf("Doriti sa se afiseze ofertele cu o suprafata mai mica sau mai mare decat cea introdusa?(0 = mai mic sau egal, 1 = mai mare sau egal)\n");
		scanf("%d", &how);

		repo* rep = filtrare_oferte(cons->srv, criteriu, tip);
		if (how == 0) {
			int contor = 1;
			for (int i = 0; i < len(rep); i++)
				if (get_suprafata(rep->v[i]) <= suprafata)
					printf("%2d: Tip: %10s, Adresa: %30s, Suprafata: %5d, Pret: %5d\n", contor++, get_tip(rep->v[i]), get_adresa(rep->v[i]), get_suprafata(rep->v[i]), get_pret(rep->v[i]));
		}
		else if (how == 1) {
			int contor = 1;
			for (int i = 0; i < len(rep); i++)
				if (get_suprafata(rep->v[i]) >= suprafata)
					printf("%2d: Tip: %10s, Adresa: %30s, Suprafata: %5d, Pret: %5d\n", contor++, get_tip(rep->v[i]), get_adresa(rep->v[i]), get_suprafata(rep->v[i]), get_pret(rep->v[i]));

		}
		distruge_rep(rep);
		free(criteriu);
	}
	else if (decizie == 1) {
		char *tip = NULL, *criteriu = NULL;
		criteriu = malloc(sizeof(char) * 4);
		strcpy(criteriu, "tip");
		decizie = -1;
		printf("Ce tip de oferta doriti sa fie afisata? (0 = apartament, 1= casa, 2 = teren)\n");
		scanf("%d", &decizie);
		if (decizie == 0) {
			tip = malloc(sizeof(char) * 11);
			strcpy(tip, "apartament");
		}
		else if (decizie == 1) {
			tip = malloc(sizeof(char) * 5);
			strcpy(tip, "casa");
		}
		else if (decizie == 2) {
			tip = malloc(sizeof(char) * 6);
			strcpy(tip, "teren");
		}

		repo* rep = filtrare_oferte(cons->srv, criteriu, tip);
		afisare_oferte(rep, 0);

		distruge_rep(rep);
		free(tip);
		free(criteriu);
	}
	else if (decizie == 2) {
		int pret, how;
		char tip[2], *criteriu;
		criteriu = malloc(sizeof(char) * 5);
		strcpy(criteriu, "pret");
		strcpy(tip, "\0");

		printf("Dati un pret ce se va comporta ca limita:\n");
		scanf("%d", &pret);

		printf("Doriti sa se afiseze ofertele cu un pret mai mic sau mai mare decat cel introdus?(0 = mai mic sau egal, 1 = mai mare sau egal)\n");
		scanf("%d", &how);

		repo* rep = filtrare_oferte(cons->srv, criteriu, tip);
		if (how == 0) {
			int contor = 1;
			for (int i = 0; i < len(rep); i++)
				if (get_pret(rep->v[i]) <= pret)
					printf("%2d: Tip: %10s, Adresa: %30s, Suprafata: %5d, Pret: %5d\n", contor++, get_tip(rep->v[i]), get_adresa(rep->v[i]), get_suprafata(rep->v[i]), get_pret(rep->v[i]));
		}
		else if (how == 1) {
			int contor = 1;
			for (int i = 0; i < len(rep); i++)
				if (get_pret(rep->v[i]) >= pret)
					printf("%2d: Tip: %10s, Adresa: %30s, Suprafata: %5d, Pret: %5d\n", contor++, get_tip(rep->v[i]), get_adresa(rep->v[i]), get_suprafata(rep->v[i]), get_pret(rep->v[i]));

		}
		distruge_rep(rep);
		free(criteriu);
	}
}

/*
	Functia populeaza.
	Se adauga 10 oferte valide.
*/
void populeaza(console* cons) {
	oferta* of = NULL;
	of = creaza_oferta("casa", "Strada Mihai Viteazul", 123, 143);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("casa", "Strada Matei Basarab", 124, 132);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("teren", "Strada Stefan cel Mare", 125, 133);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("teren", "Strada Mircea cel Batran", 126, 121);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("apartament", "Strada Dorobantilor", 127, 122);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("apartament", "Strada Mehedinti", 128, 152);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("casa", "Strada Manastur", 129, 163);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("teren", "Strada Motilor", 130, 171);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("apartament", "Strada Sud", 131, 121);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);

	of = creaza_oferta("casa", "Strada Nord", 132, 112);
	cons->srv->rep = adaugare(cons->srv->rep, of);
	distruge_oferta(of);
	printf("S-au adaugat 10 oferte!\n");
}

void curata_ecran() {
	for (int i = 0; i < 25; i++)
		printf("\n");
}

void print_menu() {
	printf("\n-----------------Meniu-----------------\n");
	printf(" 0. Inchideti aplicatia.\n");
	printf(" 1. Adaugati o oferta.\n");
	printf(" 2. Stergeti o oferta.\n");
	printf(" 3. Modificati o oferta.\n");
	printf(" 4. Vizualizare oferte sortate.\n");
	printf(" 5. Filtrare oferte.\n");
	printf("19. Populati lista.\n");
	printf("20. Curatati ecranul.\n");
}

void startUI(console* cons) {
	int comanda;
	while (1) {
		print_menu();
		printf("Introduceti o comanda: ");
		scanf(" %d", &comanda);

		if (comanda == 1)
			adaugare_cons(cons);
		else if (comanda == 2)
			stergere_cons(cons);
		else if (comanda == 3)
			modificare_cons(cons);
		else if (comanda == 4)
			sortare_cons(cons);
		else if (comanda == 5)
			filtrare_cons(cons);
		else if (comanda == 0) {
			distruge_cons(cons);
			break;
		}
		else if (comanda == 19)
			populeaza(cons);
		else if (comanda == 20)
			curata_ecran();
	}
}