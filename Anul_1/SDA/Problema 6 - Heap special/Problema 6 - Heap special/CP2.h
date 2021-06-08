#pragma once
#include <utility>
#define INITIAL_CAPACITY 5

using namespace std;

typedef int TElem;
typedef int TPrioritate;
typedef std::pair<TElem, TPrioritate> Element;
typedef bool(*Relatie)(TPrioritate p1, TPrioritate p2);

class CP2
{
private:
	//aici reprezentarea
	Relatie relation;
	int lenght, capacity;
	Element * elements;

	void urca(Element elem);
	void coboara(int index);
	void resize();
public:
	//constructor implicit
	CP2(Relatie r);

	//adauga un element cu o anumita prioritate
	void adauga(TElem e, TPrioritate p);

	//returneaza al doilea cel mai prioritar element in raport cu relatia de ordine
	//arunca exceptie daca in coada sunt mai putin de 2 elemente
	Element element()  const;

	//sterge si returneaza al doilea cel mai prioritar element in raport cu relatia de ordine
	//arunca exceptie daca in coada sunt mai putin de 2 elemente
	Element sterge();

	//verifica daca e cel mult un element in coada
	bool areCelMult1() const;

	//returneaza indexul elementului celui de-al doilea element prioritar
	int getIndex() const;

	//destructor
	~CP2();
};