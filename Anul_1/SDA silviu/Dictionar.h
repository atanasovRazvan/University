/*
 * Dictionar.h
 *
 *  Created on: 25 feb. 2019
 *      Author: George
 */
#ifndef DICTIONAR_H_
#define DICTIONAR_H_

#include <utility>




typedef int TCheie;
typedef int TValoare;

typedef std::pair <TCheie, TValoare> TElem;

class IteratorDictionar;

#define NULL_TVALOARE -1




class Dictionar {

	friend class IteratorDictionar;

private:

	/* aici e reprezentarea datelor */
	// declaram variabile de clasa cum ar fi numarul de inregistrari (pt dim, vid)
	int count,fullSize;
	//TElem dict[10000]; // TODO
	//int* dict = (int*)malloc(fullSize * sizeof(int) ); // nu uita de   free(dict);
	TElem* dict;
	//dict = new TElem [100];
	//int * foo;
	//foo = new (nothrow) int [fullSize];

public:

	// constructorul implicit al dictionarului
	Dictionar();

	// adauga o pereche (cheie, valoare) in dictionar
	//daca exista deja cheia in dictionar, inlocuieste valoarea asociata cheii si returneaza vechea valoare
	// daca nu exista cheia, adauga perechea si returneaza NULL
	TValoare adauga(TCheie c, TValoare v);

	//cauta o cheie si returneaza valoarea asociata (daca dictionarul contine cheia) sau NULL
	TValoare cauta(TCheie c) const;

	//sterge o cheie si returneaza valoarea asociata (daca exista) sau NULL
	TValoare sterge(TCheie c);//TODO bool sterge(TCheie c)

	//returneaza numarul de perechi (cheie, valoare) din dictionar
	int dim() const;

	//verifica daca dictionarul e vid
	bool vid() const;

	// se returneaza iterator pe dictionar

	IteratorDictionar iterator() const;


	// destructorul dictionarului

	~Dictionar();

};

#endif /* DICTIONAR_H_ */




