#pragma once
#include "car.h"
#include "Repo.h"
class ActiuneUndo {


public:
	virtual void doUndo()=0;
	virtual ~ActiuneUndo() = default;
};

class UndoAdd : public ActiuneUndo {
	Car addedCar;
	Repo& repo;

public:
	UndoAdd(Repo& _repo, const Car& _car) : repo{ _repo }, addedCar{ _car }{}
	void doUndo() override{
		repo.removeCar_repo(addedCar.get_nrInmatriculare());
	}
};


class UndoDelete : public ActiuneUndo {
	Car deletedCar;
	Repo& repo;

public:
	UndoDelete(Repo& _repo, const Car& _car) : repo{ _repo }, deletedCar{ _car }{}
	void doUndo() override {
		repo.addCar_repo(deletedCar);
	}
};
