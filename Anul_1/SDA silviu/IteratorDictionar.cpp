/*
 * IteratorDictionar.cpp
 *
 *  Created on: 25 feb. 2019
 *      Author: George
 */

#include "IteratorDictionar.h"
#include "Dictionar.h"

#include <iostream>//TODO remove this

using namespace std;//TODO remove this

IteratorDictionar::IteratorDictionar(const Dictionar& c): con(c) {
	// TODO Auto-generated constructor stub
	// initializare curent si alte atribute specifice
	curent = 0; // TODO fa-l sa faca sens.
	//Dictionar iteratorLocalc = c;// TODO cu sau fara '&' ?
}



/*
 * pre : i apartine I, curent este valid (refera un element din container)
 * post : e apartine TElement, e este elementul curent din iteratie
 * (elementul din container referit de curent)
 */
TElem IteratorDictionar::element(/*const Dictionar& con*/){
	//TBA
	return con.dict[curent]; // TODO please clarify what in the world is 'const Dictionar& con'
}



/*
 * pre : i apartine  I
 * post : valid = adev, daca curent refera o pozitie valida din container
			    = fals, altfel
 */
bool IteratorDictionar::valid() {
	//TBA
	return curent < con.count;
}



/*
 * pre : i apartine I, curent este valid
 * post : curent'=curent+1 refera 'urmatorul' element din container fata de cel referit de curent
 */
void IteratorDictionar::urmator() {
	curent++;
}



/*
 * pre : i apartine I
 * post : curent refera 'primul' element din container
 */
void IteratorDictionar::prim() {
	curent = 0;
}


IteratorDictionar::~IteratorDictionar() {
	// TODO Auto-generated destructor stub
}

