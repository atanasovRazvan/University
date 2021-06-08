#pragma once
#include <memory>
#include "Pet.h"
typedef Pet Element;
#define INITIAL_CAPACITY 5


class IteratorVectorSP;

class VectDinSmartPointer
{
public:
	/*
	Constructor default 
	*/
	VectDinSmartPointer();

	/*
	Constructor de copiere
	unique_ptr nu se poate copia, trebuie sa implementam noi
	*/
	VectDinSmartPointer(const VectDinSmartPointer& ot);

	/*
	Eliberam memoria
	(=default pentru ca unique_ptr elibereaza memoria la distrugere)
	*/
	~VectDinSmartPointer() = default;

	/*
	Operator assignment
	unique_ptr nu se poate copia, trebuie sa implementam
	*/
	VectDinSmartPointer& operator=(const VectDinSmartPointer& ot);


	/*
	Move constructor
	(=default pentru ca unique_ptr are move)
	*/
	VectDinSmartPointer(VectDinSmartPointer&& ot) = default;

	/*
	Move assignment
	(=default pentru ca unique_ptr are move)
	*/
	VectDinSmartPointer& operator=(VectDinSmartPointer&& ot) = default;


	void add(const Element& el);

	Element& get(int poz) const;

	void set(int poz, const Element& el);

	int size() const noexcept;

	friend class IteratorVectorSP;
	//functii care creaza iteratori
	IteratorVectorSP begin();
	IteratorVectorSP end();


private:
	int lg;//numar elemente
	int cap;//capacitate
	std::unique_ptr<Element[]> elems;//elemente

	void ensureCapacity();
};

class IteratorVectorSP {
private:
	const VectDinSmartPointer& v;
	int poz = 0;
public:
	IteratorVectorSP(const VectDinSmartPointer& v) noexcept;
	IteratorVectorSP(const VectDinSmartPointer& v, int poz)noexcept;
	bool valid()const noexcept;
	Element& element() const noexcept;
	void next() noexcept;
	Element& operator*();
	IteratorVectorSP& operator++();
	bool operator==(const IteratorVectorSP& ot)noexcept;
	bool operator!=(const IteratorVectorSP& ot)noexcept;
};



