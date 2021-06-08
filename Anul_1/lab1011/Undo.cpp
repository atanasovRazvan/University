#include "Undo.h"

void UndoAdauga::undo_me()
{
	repo.del(l_added);
}

void UndoSterge::undo_me()
{
	repo.add(l_added);
}

void UndoModifica::undo_me()
{
	repo.update(l_added);
}
