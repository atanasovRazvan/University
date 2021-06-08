#pragma once
#include <vector>
#include "Domain.h"

using std::vector;
using std::ostream;

class ValidateException {
	vector<string> msgs;
public:
	ValidateException() = default;
	ValidateException(const vector<string>& errors) :msgs{ errors } {}
	void validate(const Domain& p);
	friend ostream& operator<<(ostream& out, const ValidateException& ex);
};