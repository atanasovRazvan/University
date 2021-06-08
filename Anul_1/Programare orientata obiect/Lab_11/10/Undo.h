#pragma once
#include "Domain.h"
#include "Repository.h"

class Undo {
public:
	virtual void doUndo() = 0;
	virtual ~Undo() = default;
};

class UndoAdd : public Undo {
	int indice;
	Repository& rep;
public:
	UndoAdd(Repository& rep, const  int& indice) :rep{ rep }, indice{ indice } {}
	void doUndo() override {
		rep.removeCar(indice);
	}
};

class UndoRemove : public Undo {
	Domain car;
	Repository& rep;
public:
	UndoRemove(Repository& rep, const  Domain& car) :rep{ rep }, car{ car } {}
	void doUndo() override {
		rep.addCar(car);
	}
};

class UndoModify : public Undo {
	Domain car;
	Repository& rep;
public:
	UndoModify(Repository& rep, const  Domain& car) :rep{ rep }, car{ car } {}
	void doUndo() override {
		rep.modifyCar(car);
	}
};