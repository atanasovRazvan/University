#pragma once
#include "Locatar.h"
#include "Repository.h"
#include <memory>

class ActiuneUndo {
public:
	virtual void undo_me() = 0;
	virtual ~ActiuneUndo() {};
};

class UndoAdauga : public ActiuneUndo {
	Locatar l_added;
	Repo& repo;
public:
	UndoAdauga(Locatar& l, Repo& r) :l_added{ l }, repo{ r }{};
	void undo_me() override;
};

class UndoSterge : public ActiuneUndo {
	Locatar l_added;
	Repo& repo;
public:
	UndoSterge(Locatar& l, Repo& r) :l_added{ l }, repo{ r }{};
	void undo_me() override;
};

class UndoModifica : public ActiuneUndo {
	Locatar l_added;
	Repo& repo;
public:
	UndoModifica(Locatar& l, Repo& r) :l_added{ l }, repo{ r }{};
	void undo_me() override;
};