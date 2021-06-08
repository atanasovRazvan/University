#include "Validate.h"
#include <stdio.h>
#include <algorithm>

using namespace std;


bool Validate::noDigit(const string& s) {

	return find_if(s.begin(), s.end(), [](char c) { return isdigit(c); }) == s.end(); //if any digits
	
}

void Validate::validateCar(Car & car)
{
	vector<string> msgs; //errors
	string nrI = car.get_nrInmatriculare();
	string prod = car.get_producator();
	string model = car.get_model();
	string tip = car.get_tip();

	if (nrI.size() == 0)
		msgs.push_back("No registration number.");
	if (prod.size() == 0)
		msgs.push_back("No producer.");
	if (model.size() == 0)
		msgs.push_back("No model.");
	if (tip.size() == 0)
		msgs.push_back("No type.");

	if (!noDigit(prod)) msgs.push_back("Bad producer.");
	if (!noDigit(tip)) msgs.push_back("Bad type.");




	if (msgs.size() > 0)
		throw ValidateException(msgs);

}

ostream& operator<<(ostream& out, const ValidateException& ex) { for (const auto& msg : ex.msgs) out << msg<<' '; return out; }
