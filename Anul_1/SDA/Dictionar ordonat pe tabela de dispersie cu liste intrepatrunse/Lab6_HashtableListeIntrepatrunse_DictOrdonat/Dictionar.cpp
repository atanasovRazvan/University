#include "Dictionar.h"

Iterator DO::iterator() const {

		return Iterator(*this);

	}