#pragma once
#include <iostream>
#include <vector>
//DO.h

#ifndef DICTIONAR_H_
#define DICTIONAR_H_

using namespace std;

typedef int TCheie;
typedef int TValoare;

typedef std::pair<TCheie, TValoare> TElem;

#define NULL_TVALOARE -1

typedef bool(*Relatie)(TCheie, TCheie);

struct HashTable {

	TElem val;
	int urm;

};

class Iterator;

class DO {

	HashTable* H;
	Relatie rel;
	int size;
	int capacity;
	friend class Iterator;

private:

	/* aici e reprezentarea */
public:

	// constructorul implicit al dictionarului
	DO(Relatie r);

	// face resize la un vector
	void resize();

	// adauga o pereche(cheie, valoare) in dictionar
	//daca exista deja cheia in dictionar, inlocuieste valoarea asociata cheii si returneaza vechea valoare
	// daca nu exista cheia, adauga perechea si returneaza null: NULL_TVALOARE
	TValoare adauga(TCheie c, TValoare v);

	//cauta o cheie si returneaza valoarea asociata (daca dictionarul contine cheia) sau null: NULL_TVALOARE
	TValoare cauta(TCheie c) const;

	//sterge o cheie si returneaza valoarea asociata (daca exista) sau null: NULL_TVALOARE
	TValoare sterge(TCheie c);

	//returneaza numarul de perechi (cheie, valoare) din dictionar 
	int dim() const;

	//verifica daca dictionarul e vid 
	bool vid() const;

	// se returneaza iterator pe dictionar
	// iteratorul va returna perechile in ordine dupa relatia de ordine (pe cheie)
	Iterator iterator() const;

	//se returneaza un vector cu toate valorile dictionarului
	vector<TValoare> colectiaValorilor() const;

	// destructorul dictionarului	
	~DO();

};

#endif