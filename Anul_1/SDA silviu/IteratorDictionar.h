/*
 * IteratorDictionar.h
 *
 *  Created on: 25 feb. 2019
 *      Author: George
 */

#ifndef ITERATORDICTIONAR_H_
#define ITERATORDICTIONAR_H_

#include "Dictionar.h"


//iterator unidirectional pe Dictionar
class IteratorDictionar {

	friend class Dictionar;

private:

	int curent;

	const Dictionar& con;


	//iteratorul va referi primul element din container
	IteratorDictionar(const Dictionar& c);




	//(iteratorul memoreaza o referinta catre Dictionar)
	//contine o referinta catre containerul pe care il itereaza
	//aici alte atribute specifice: curent, etc
	//constructorul primeste o referinta catre Container

	/* aici e reprezentarea specifica a iteratorului*/


public:

	IteratorDictionar();


	TElem element(/*const Dictionar& con*/);

	bool valid();

	void urmator();

	void prim();


	~IteratorDictionar();
};

#endif /* ITERATORDICTIONAR_H_ */


/*
TElem urmator(TCheie c){
	int i=0;
	if(pozCurenta<=len-2){

		for (i=0;i<=len-1;i++){
			if (dict[i][0]==c){
				return <dict[i+1][0],dict[i+1][1]>
			}
		}

	}
	else{
		return NULL;
	}
}


TElem prim(){
	return <dict[0][0],dict[0][1]>;
}


bool valid(TCheie c, TValoare v){
	return true; // TODO valid?
}


TElem element(TCheie c){
	int i=0;
	for (i=0;i<=len-1;i++){
		if(dict[i][0]==c){
			return <dict[i][0],dict[i][1]>;
		}
	}
	return NULL;
}
 */
