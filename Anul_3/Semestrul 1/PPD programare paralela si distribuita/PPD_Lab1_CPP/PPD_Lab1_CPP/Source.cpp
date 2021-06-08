#include <fstream>
#include <string>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <chrono>

using namespace std;

void createFile(string fname, int size, int min, int max) {

	int generatedNumber;
	srand(time(NULL));
	ofstream file(fname);
	for (int i = 1; i <= size; i++) {
		generatedNumber = rand() % max + min;
		file << generatedNumber << " ";
	}
	file.close();

}

bool checkFileEquality(string fname1, string fname2, string type) {

	if (type == "INT") {

		ifstream f1(fname1);
		ifstream f2(fname2);

		int a, b;
		while (f1 >> a && f2 >> b) {
			if (a != b) return false;
		}

		f1.close();
		f2.close();
		return true;

	}
	else if (type == "DOUBLE") {

		ifstream f1(fname1);
		ifstream f2(fname2);

		double a, b;
		while (f1 >> a && f2 >> b) {
			if (a != b) return false;
		}

		f1.close();
		f2.close();
		return true;

	}
	else throw exception("Type specified wrong");

}

int main(int argc, char* argv[]){

	auto start = std::chrono::system_clock::now();
	
	int p = 0;
	for (int i = 0; argv[0][i]; i++)
		p = p * 10 + (argv[0][i] - '0');

	createFile("test.txt", 100000, 0, 10000);
	ifstream f("test.txt"); int x, sum=0;
	while (f >> x)
		sum += x;
	auto end = std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds = end - start;
	cout << p << '\n';
	cout << "Time: " << elapsed_seconds.count() << "'s" << "\n\n";

	return 0;

}