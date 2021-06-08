#pragma once
#include <string>
#include "car.h"
#include "vector"

using namespace std;

class Validate
{
public:	
	void validateCar(Car& car);
	bool noDigit(const string& s);
};



class ValidateException {

	vector <string> msgs;
public:
	ValidateException(const vector <string> _messages): msgs{_messages}{}

	friend ostream& operator<<(ostream& out, const ValidateException& ex);
};

ostream& operator<<(ostream& out, const ValidateException& ex);

