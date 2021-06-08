//Dictionar.h
#pragma once
#include <utility>

#ifndef DICTIONAR_H_
#define DICTIONAR_H_

using namespace std;

typedef int TCheie;
typedef int TValoare;

typedef pair<TCheie, TValoare> TElem;

class IteratorDictionar;

#define NULL_TVALOARE -1

class Dictionar {

	friend class IteratorDictionar;

private:
	
	/* aici e reprezentarea */
	TElem* V;
	int size;
	int capacity;
	
public:

	// constructorul implicit al dictionarului
	Dictionar();

	// adauga o pereche (cheie, valoare) in dictionar	
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
	IteratorDictionar iterator() const;

	// destructorul dictionarului	
	~Dictionar();

	// functionalitate suplimentara
	int adaugaInexistente(Dictionar &d);

};

#endif