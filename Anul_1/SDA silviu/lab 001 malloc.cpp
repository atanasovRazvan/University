//============================================================================
// Name        : lab.cpp
// Author      : George Ciubotariu
// Version     : 1.0.0
// Copyright   : (c) march 2019; logo and trademarks are not copyright to author.
// Description : Container Dictionary, implemented using Dynamic Vector in C++
//============================================================================

#include <iostream>
#include "Dictionar.h"
#include "IteratorDictionar.h"

using namespace std;



#include <assert.h>


void printD(Dictionar& d){
	TElem e;
	IteratorDictionar id = d.iterator();
	while (id.valid()){
		e = id.element();
		cout << " ( " << e.first << " , " << e.second << " ) ";
		id.urmator();
	}
	cout << std::endl;
	cout << std::endl;

}


void testCreeaza() {
	Dictionar d;
	assert(d.dim() == 0);
	assert(d.vid() == true);

	cout<<"\n0\n";
	for (int i = -10; i < 10; i++) { //cautam in container vid
		assert(d.cauta(i) == NULL_TVALOARE);
	}
	cout<<"\n1\n";
	for (int i = -10; i < 10; i++) { //stergem din container vid
		assert(d.sterge(i) == NULL_TVALOARE);
	}
	cout<<"\n2\n";
	IteratorDictionar id = d.iterator(); //iterator pe multimea vida ar trebui sa fie invalid din start
	assert(id.valid() == false);
	cout<<"\n3\n";
	printD(d); // TODO
}


void testAdauga() {
	Dictionar d; //adaugam elemente [0, 10)
	for (int i = 0; i < 10; i++) {
		d.adauga(i,i);
	}
	cout<<"\n0\n";
	printD(d); // TODO
	assert(d.vid() == false);
	assert(d.dim() == 10);
	for (int i = -10; i < 20; i++) { //mai adaugam elemente [-10, 20)
		cout << i << std::endl;
		d.adauga(i,i);
	}
	cout<<"\n1\n";
	printD(d); // TODO
	assert(d.vid() == false);
	assert(d.dim() == 30);
	for (int i = -100; i < 100; i++) { //mai adaugam elemente [-100, 100)
		d.adauga(i,i);
	}
	cout<<"\n2\n";
	printD(d); // TODO AICI CRAPA TESTUL (exit value: -1.073.731.819) (crapa*)
	cout<<"\n2.25\n";
	assert(d.vid() == false);
	assert(d.dim() == 200);
	cout<<"\n2.5\n";
	for (int i = -200; i < 200; i++) {
		cout<<i<<" ";
		if (i < -100) {
			assert(d.cauta(i) == NULL_TVALOARE	);
		}
		else if (i < 0) {
			assert(d.cauta(i) == i);
		}
		else if (i < 100) {
			assert(d.cauta(i) == i);
		}
		else {
			assert(d.cauta(i) == NULL_TVALOARE);
		}
	}
	cout<<"\n3\n";
	for (int i = 10000; i > -10000; i--) { //adaugam mult, si acum prima data adaugam valori mari, dupa aceea mici
		d.adauga(i,i);
	}
	assert(d.dim()==20000);
	cout<<"\n4\n";
}


void testSterge() {
	Dictionar d;

	for (int i = -100; i < 100; i++) { //stergem din containerul vid
		assert(d.sterge(i) == NULL_TVALOARE);
	}
	assert(d.dim() == 0);
	printD(d); //TODO
	for (int i = -100; i < 100; i = i + 2) { //adaugam elemente din 2 in 2 (numere pare)
		d.adauga(i,i);
	}
	for (int i = -100; i < 100; i++) { //stergem tot (inclusiv elemente inexistente)
		if (i % 2 == 0) {
			assert(d.sterge(i) == i);
		}
		else {
			assert(d.sterge(i) == NULL_TVALOARE);
		}
	}
	assert(d.dim() == 0);
	printD(d); //TODO

	for (int i = -100; i <= 100; i = i + 2) { //adaugam elemente din 2 in 2
		d.adauga(i,i);
	}
	printD(d); //TODO
	for (int i = 100; i > -100; i--) { //stergem descrescator (in ordine inversa fata de ordinea adaugarii)
		if (i % 2 == 0) {
			assert(d.sterge(i) == i);
		}
		else {
			assert(d.sterge(i) == NULL_TVALOARE);
		}
	}

	assert(d.dim() == 1);
	printD(d); //TODO

	d.sterge(-100);
	assert(d.dim() == 0);

	for (int i = -100; i < 100; i++) { //adaugam de 5 ori pe fiecare element
		d.adauga(i,0);
		d.adauga(i,1);
		d.adauga(i,2);
		d.adauga(i,3);
		d.adauga(i,4);
	}
	printD(d); //TODO
	assert(d.dim() == 200);
	for (int i = -200; i < 200; i++) { //stergem elemente inexistente si existente
		if (i < -100 || i >= 100) {
			assert(d.sterge(i) == NULL_TVALOARE);
		}
		else {
			assert(d.sterge(i) == 4);
			assert(d.sterge(i) == NULL_TVALOARE);
		}
	}
	assert(d.dim() == 0);

 }



void testIterator() {
	Dictionar d;
	IteratorDictionar id = d.iterator(); //iterator pe container vid
	assert(id.valid() == false);

	for (int i = 0; i < 100; i++) {  //adaug de 100 de ori valoarea 33
		d.adauga(33,33);
	}
	cout<<"\n0\n";
	IteratorDictionar id2 = d.iterator(); //daca iterez doar 33 poate sa-mi dea iteratorul
	assert(id2.valid() == true);
	TElem el = id2.element();
	assert(el.first == 33);
	id2.urmator();
	assert(id2.valid() == false);
	cout<<"\n1\n";
	id2.prim(); //resetam pe primul elemente
	assert(id2.valid() == true);

	Dictionar d2;
	for (int i = -100; i < 100; i++) { //adaug 200 de elemente, fiecare de 3 ori
		d2.adauga(i,i);
		d2.adauga(i,i);
		d2.adauga(i,i);
	}
	cout<<"\n2\n";
	IteratorDictionar id3 = d2.iterator();
	assert(id3.valid() == true);
	for (int i = 0; i < 200; i++) {
		id3.urmator();
	}
	assert(id3.valid() == false);
	id3.prim();
	assert(id3.valid() == true);

	cout<<"\n3\n";
	Dictionar d3;
	for (int i = 0; i < 200; i= i + 4) { //adaugam doar multipli de 4
		d3.adauga(i,i);
	}

	IteratorDictionar id4 = d3.iterator();
	assert(id4.valid() == true);
	int count = 0;
	while (id4.valid()) { //fiecare element e multiplu de 4 si sunt in total 50 de elemente
		TElem e = id4.element();
		assert(e.first % 4 == 0);
		id4.urmator();
		count++;
	}
	cout<<"\n4\n";
	assert(count == 50);
}



void testQuantity() {//scopul e sa adaugam multe date
	Dictionar d;

	cout<<"0";
	for (int i = 10; i >= 1; i--) {
		for (int j = -30000; j < 30000; j = j + i) {
			d.adauga(j,j);
		}
	}
	cout<<"1";
	assert(d.dim() == 60000);
	IteratorDictionar id = d.iterator();
	assert(id.valid() == true);
	for (int i = 0; i < d.dim(); i++) {
		id.urmator();
	}
	cout<<"2";
	id.prim();
	while (id.valid()) { //fiecare element returnat de iterator trebuie sa fie in container
		TElem e = id.element();
		assert(d.cauta(e.first) == e.first);
		id.urmator();
	}
	cout<<"3";
	assert(id.valid() == false);
	for (int i = 0; i < 10; i++) { //stergem multe elemente existente si inexistente
		for (int j = 40000; j >= -40000; j--) {
			d.sterge(j);
		}
	}
	assert(d.dim() == 0);
}



// nu stim reprezentarea, putem testa doar anumite lucruri generale, nu stim in ce ordine vor fi afisate elementele
void testAllExtins() {
	cout<<"\nStart testExtins\n";
	testCreeaza(); // pass FOARTE BINE
	testAdauga(); // runtime fail
	testSterge(); // pass FOARTE BINE
	testIterator(); // pass FOARTE BINE
	testQuantity(); // pass FOARTE BINE
}







void testAll() { //apelam fiecare functie sa vedem daca exista
	Dictionar d;
	assert(d.vid() == true);
	assert(d.dim() == 0); //adaug niste elemente
	assert(d.adauga(5,5)==NULL_TVALOARE);
	assert(d.adauga(1,111)==NULL_TVALOARE);
	assert(d.adauga(10,110)==NULL_TVALOARE);
	assert(d.adauga(7,7)==NULL_TVALOARE);
	assert(d.adauga(1,1)==111);
	assert(d.adauga(10,10)==110);
	assert(d.adauga(-3,-3)==NULL_TVALOARE);
	assert(d.dim() == 5);
	assert(d.cauta(10) == 10);
	assert(d.cauta(16) == -1);
	assert(d.sterge(1) == 1);
	assert(d.sterge(6) == -1);
	assert(d.dim() == 4);


	TElem e;
	IteratorDictionar id = d.iterator();
	id.prim();
	int s1 = 0, s2 = 0;
	while (id.valid()) {
		e = id.element(/*d*/);
		s1 += e.first;
		s2 += e.second;
		id.urmator();
	}
	assert(s1 == 19);
	assert(s2 == 19);

}

int main() {
	cout<<"Start main";
	//testAll();
	testAllExtins();
	cout<<"End";
	return 0;



	Dictionar c;
	// iterare
	IteratorDictionar ic = c.iterator();
	while (ic.valid()) {
		TElem e = ic.element(/*c*/);
		// prelucrare element
		ic.urmator();
	}

	cout<<"End";


}
