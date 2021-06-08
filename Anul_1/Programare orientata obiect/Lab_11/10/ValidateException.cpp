#include "ValidateException.h"

void ValidateException::validate(const Domain& p) {
	if (p.getManufacturer().size() == 0) msgs.push_back("Fabricare vida!");
	if (p.getModel().size() == 0) msgs.push_back("Model vid");
	if (p.getRegistrationNumber().size() == 0) msgs.push_back("Numar de matriculare vid!!");
	if (p.getType().size() == 0) msgs.push_back("Tip vid!!");
	if (msgs.size() > 0) {
		throw ValidateException(msgs);
	}
}

ostream & operator<<(ostream & out, const ValidateException & ex) {
	for (const auto& msg : ex.msgs) {
		out << msg << " ";
	}
	return out;
}
