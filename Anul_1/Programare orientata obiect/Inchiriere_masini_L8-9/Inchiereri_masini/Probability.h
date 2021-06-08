#pragma once

#include "Repo.h"
#include <stdlib.h>
#include <time.h>
#include <string>

class ProbabilityError {

public:
	ProbabilityError(std::string mesaj) noexcept : message(mesaj) {
	}

	const std::string& getMessage() const noexcept {
		return message;
	}

private:
	std::string message;
};

class ProbabilityRepo: public Repo {

	double pr;

public:
	ProbabilityRepo(double pr) : pr{ pr } {
	}

	void addCar_repo(const Car& car) override {
		const int val = rand() % 100;
		if (val < pr * 100) {

			std::string s = "Probabilitate mica";
			throw ProbabilityError(s);
		}

		Repo::addCar_repo(car);
	}

};