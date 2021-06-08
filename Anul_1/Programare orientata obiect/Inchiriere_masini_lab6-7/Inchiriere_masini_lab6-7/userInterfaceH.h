#pragma once
#include <iostream>
#include "domainH.h"
#include "serviceH.h"

/*
Clasa de UserInterface
Are un camp - Service
Functii ale clasei:
     - private (addUI, deleteUI, updateUI care reprezinta submeniuri)
	 - public (run care porneste aplicatia)
*/

class UserInterface {

private:

	Service srv;
	
	/*
	Functie submeniu pentru adaugare
	Nu primeste parametri
	Colecteaza date si le da mai departe la Service
	*/
	void addUI();

	void filterUI();

	/*
	Functie submeniu pentru stergere
	Nu primeste parametri
	Colecteaza date si le da mai departe la Service
	*/
	void deleteUI();
	/*
	Functie submeniu pentru modificare
	Nu primeste parametri
	Colecteaza date si le da mai departe la Service
	*/
	void updateUI();
	/*
	Functie pentru afisarea tuturor entitatilor existente
	Primeste parametru service
	Afiseaza toate entitatile
	*/
	void printSrv();

public:
	/*
	Functie meniu
	Nu primeste parametri
	Colecteaza date si le da mai departe la submeniuri
	*/
	void run();

};