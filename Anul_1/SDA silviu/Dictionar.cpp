/*
 * Dictionar.cpp
 *
 *  Created on: 25 feb. 2019
 *      Author: George
 */

#include "Dictionar.h"
#include "IteratorDictionar.h"
#include <iostream>

Dictionar::Dictionar() {
	// TODO Auto-generated constructor stub
	count = 0;
	fullSize = 9;
	dict = new TElem [fullSize];

}

IteratorDictionar Dictionar::iterator() const {
	return IteratorDictionar(*this);
}


Dictionar::~Dictionar() {
	// TODO Auto-generated destructor stub
	free(dict);
//	int indx=0;
//	for(indx=0;indx<fullSize;indx++){
		//delete[] dict[indx];
		//free(dict[indx]);
//		free(dict[indx]->first);
//		free(dict[indx]->second);
//	}
//	free(dict);
}

// restul operatiilor

//std::get<0>(p) - getFirst
//std::get<1>(p) - getLast
// desi nu merge... of, c++-ul asta...



/*
 * pre: d  apartine D, c apartine TCheie, v apartine TValoare
 * post d' apartine D, d' = d+ (c,v) ( (se adauga in dictionar perechea (c, v) )
 * daca exista deja cheia in dictionar,
 * se inlocuieate valoarea asociata cheii ai se poate returna vechea valoare
 * Daca nu exista cheia, adauga perechea si se poate returna 0TValoare
 */
TValoare Dictionar::adauga(TCheie c, TValoare v){
	int i=0,gasit=0,indexGasit=0;

	while(i<count){ // verificam existenta cheii in Dictionar
		if((dict[i]).first==c){
			gasit = 1;
			indexGasit = i;
		}
		i++;
	}



	// TODO
	if (count >= fullSize){
		//TElem* dictPrim = (TElem*)malloc( 2 * fullSize * sizeof(TElem) );
		TElem* dictPrim = new TElem[2*fullSize];
		fullSize = fullSize * 2;
		//std::cout<<"ceva\n";
		int indx=0;
		for (indx=0;indx<count;indx++)
			dictPrim[indx]=dict[indx];
		free(dict);
		dict=dictPrim;
		//std::cout << "altceva\n";
	}




	if (gasit){
		int ret = dict[indexGasit].second;
		dict[indexGasit].second=v;
		return ret; // returnam vechea valoare, inlocuind-o
	}
	else{
		dict[count].first=c;
		dict[count].second=v;
		count++;
		return NULL_TVALOARE; // daca cheia nu mai apare, o vom introduce noi.
	}
}



/*
 * pre: d apartine D, c apartine TCheie
 * post: cauta = Adevarat, daca (c,v) apartine d (returnam v. v apartine TValoare este valoarea asociata cheii)
 * 			   = Fals, altfel. returnam v = 0TValoare
 */
TValoare Dictionar::cauta(TCheie c) const{
	int i=0;

	while(i<count){

		if(dict[i].first==c){
			return dict[i].second; // found!
		}
		i++;

	}
	return NULL_TVALOARE; // not found...
}



/*
 * pre: d apartine D, c apartine TCheie
 * post: v apartine TValoare
 * returnam v daca perechea (c, v) este stearsa din dictionar, daca c apartine d
 * returnam v = TValoare 0 in caz contrar.
 */
TValoare Dictionar::sterge(TCheie c){
	int i=0,j;

	while(i<count){

		if(dict[i].first==c){ // am gasit ce vrem sa stergem

			TValoare localV = dict[i].second;// salvam TValoare care va fi sters

			for(j=i;j<=count-2;j++){ // stergem, fara a crea goluri in memorie
				dict[j]=dict[j+1];
				//dict[j].first=dict[j+1].first;
				//dict[j].second=dict[j+1].second;
			}
			count--; // decrementam numarul elementelor

			return localV;//returnam fosta TValoare, acum stearsa

		}

		i++;

	}
	return NULL_TVALOARE; // nu am gasit...
}



/*
 * pre: d apartine D
 * post: dim = dimensiunea dictionarului d (numarul de elemente) apartine N*
 */
int Dictionar::dim() const{
	return count;
}



/*
 * pre: d apartine D
 * post: vid = Adevarat, in cazul in care dictionarul e vid
 * 			 = False, altfel.
 */
bool Dictionar::vid() const{
	if(count==0){
		return true;
	}
	return false;
}



/*
chei(d,m)
pre: d apartine D
post: c apartine Col, c este colectia valorilor din dictionarul d


perechi(d,m)
pre: d apartine D
post: m apartine M, m este multimea perechilor (cheie,valoare) din dictionarul d


iterator(d,i)
se creeaza un iterator pe dictionarul d
pre: d apartine D
post:i apartine I, i este iterator pe dictionarul d


*/
