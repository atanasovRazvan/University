#include <iostream>
#include <map>
using namespace std;

int matriceAd[15][15] = { 0 }, freq[15] = { 0 };

int main() {

	int n, m;
	cin >> n;
	cin >> m;

	for (int i = 1; i <= m; i++) {

		int source, dest;
		cin >> source >> dest;
		matriceAd[source][dest] = matriceAd[dest][source] = 1;

	}

	for (int i = 1; i <= n; i++) {

		for (int j = 1; j <= n; j++) {

			if (matriceAd[i][j] == 1) freq[i]++;

		}

	}

	// 3 2 2 1 0 0

	cout << "Nodurile izolate din acest graf sunt: ";

	for (int i = 1; i <= n; i++) {

		if (freq[i] == 0) cout << i<<" ";

	}
	return 0;

}
