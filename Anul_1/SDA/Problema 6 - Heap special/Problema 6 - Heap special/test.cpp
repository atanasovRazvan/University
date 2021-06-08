#include <assert.h>
#include "CP2.h"
#include "Test.h"
#include <vector>
#include <exception>

using namespace std;

bool rel2(TPrioritate p1, TPrioritate p2) {
	if (p1 <= p2) {
		return true;
	}
	else {
		return false;
	}
}

bool rel1(TPrioritate p1, TPrioritate p2) {
	if (p1 >= p2) {
		return true;
	}
	else {
		return false;
	}
}

void testCreeaza() {
	CP2 pq(rel2);
	assert(pq.areCelMult1() == true);
	try {
		pq.element();
		assert(false);
	}
	catch (exception&) {
		assert(true);
	}
	try {
		pq.sterge();
		assert(false);
	}
	catch (exception&) {
		assert(true);
	}
	pq.adauga(5, 5);
	assert(pq.areCelMult1() == true);
	try {
		pq.element();
		assert(false);
	}
	catch (exception&) {
		assert(true);
	}
	try {
		pq.sterge();
		assert(false);
	}
	catch (exception&) {
		assert(true);
	}
}

void testAdauga() {
	CP2 pq(rel2);
	for (int i = 0; i < 10; i++) {
		pq.adauga(i, i);
	}
	assert(pq.areCelMult1() == false);
	for (int i = -10; i < 20; i++) {
		pq.adauga(i, i);
	}
	assert(pq.areCelMult1() == false);
	for (int i = -100; i < 100; i++) {
		pq.adauga(i, i);
	}
	assert(pq.areCelMult1() == false);

	for (int i = -1000; i <= 1000; i++) {
		pq.adauga(i, i);
	}
	assert(pq.areCelMult1() == false);
	assert(pq.element().second != 1000);
	assert(pq.element().second == -999);

	assert(pq.sterge().second == -999);
	assert(pq.element().second == -998);
	for (int i = -998; i <= -100; i++) {
		assert(pq.element().second == i);
		assert(pq.sterge().second == i);
	}
}

void testSterge() {
	CP2 pq(rel2);
	for (int i = 0; i < 10; i++) {
		pq.adauga(i, i);
	}
	assert(pq.element().second == 1);
	assert(pq.areCelMult1() == false);
	for (int i = -10; i < 20; i++) {
		pq.adauga(i, i);
	}
	assert(pq.element().second == -9);
	for (int i = 100; i > 50; i--) {
		pq.adauga(i, i);
	}
	assert(pq.element().second == -9);


	for (int i = -10; i < 0; i++) {
		assert(pq.sterge().second == i + 1);
	}
	assert(pq.sterge().second == 0);
	for (int i = 0; i < 9; i++) {
		assert(pq.sterge().second == i + 1);
		assert(pq.sterge().second == i + 1);
	}
	assert(pq.sterge().second == 10);
	for (int i = 10; i < 18; i++) {
		assert(pq.sterge().second == i + 1);
	}
	assert(pq.sterge().second == 19);
	for (int i = 51; i <= 100; i++) {
		assert(pq.sterge().second == i);
	}

	assert(pq.areCelMult1() == true);

}

void testCantitativ(Relatie rel) {
	CP2 pq(rel);
	for (int i = 1; i <= 10; i++) {
		for (int j = 500; j >= -500; j--) {
			pq.adauga(j, j);
		}
	}
	int count = 1;
	Element last = pq.sterge();
	while (pq.areCelMult1() == false) {
		Element current = pq.sterge();
		assert(rel
		(last.second, current.second));
		count++;
		last = current;
	}
	assert(count == 10009);
}

void test() {
	testCreeaza();
	testAdauga();
	testSterge();
	testCantitativ(rel2);
	testCantitativ(rel1);
}