#include <iostream>
#include <mpi.h>
#include <chrono>
#include <random>
#include <ctime>

using namespace std;

#define MAX 100005

int n1_size = 18;
int n2_size = 18;

int p;
int n1[MAX], n2[MAX], R[MAX], n1_section[MAX], n2_section[MAX];

void reverse_array(int v[], const int& size) {
	for (int i = 0; i < size / 2; i++) {
		swap(v[i], v[size - 1 - i]);
	}
}

void sequencial() {

	// necesarry variables

	int N = n1_size > n2_size ? n1_size : n2_size;
	int carry = 0;

	// addition

	for (int i = N - 1; i >= 0; i--) {

		R[i + 1] = (n1[i] + n2[i] + carry) % 10;
		carry = (n1[i] + n2[i] + carry) / 10;

	}

	// result may have one more digit

	if (carry == 1)
		R[0] = 1;

	// print result

	printf("\nResult: ");
	if (R[0] == 1) printf("%d", R[0]);;
	for (int i = 1; i <= N; i++)
		printf("%d", R[i]);
	printf("\n");

}


void mpi_firstSolution(int argc, char* argv[]) {

	int rank = 0;

	MPI_Init(&argc, &argv);

	MPI_Comm_size(MPI_COMM_WORLD, &p);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);

	int N = n1_size > n2_size ? n1_size : n2_size;
	int maxSize = N;
	int begin = 0;
	int lengths = 0;
	int* start = new int[p+1];

	// In procesul 0 inversam numerele si adaugam zerouri necesare

	if (rank == 0) {
		reverse_array(n1, n1_size);
		reverse_array(n2, n2_size);

		if (n1_size <= n2_size) {
			for (int i = n1_size; i < n2_size; i++)
				n1[i] = 0;
		}
		else {
			for (int i = n2_size; i < n1_size; i++)
				n2[i] = 0;
		}

	}

	int remaining = N % p;

	lengths = N / p;
	if (remaining == 0)
		lengths = N / p;
	else
		lengths = N / p + 1;

	if (rank == 0) {
		if(lengths * p > N)
			for (int i = N; i <= lengths * 4; i++) {
				n1[i] = n2[i] = 0;
			}
	}

	// Facem scatter de dimensiune N/p (+1 daca exista rest)

	MPI_Scatter(n1, lengths, MPI_INT, n1_section, lengths, MPI_INT, 0, MPI_COMM_WORLD);
	MPI_Scatter(n2, lengths, MPI_INT, n2_section, lengths, MPI_INT, 0, MPI_COMM_WORLD);

	int length = lengths;
	int* result = new int[length];

	int carry = 0;
	if (rank != 0) {
		// Pentru rankuri >0, primim carry de la procesul anterior
		MPI_Recv(&carry, 1, MPI_INT, rank - 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
	}

	int i;
	for (i = 0; i < length; i++) {
		int rez = (n1_section[i] + n2_section[i] + carry) % 10;
		carry = (n1_section[i] + n2_section[i] + carry) / 10;
		result[i] = rez;
	}

	if (rank < p - 1) 
		// Pentru toate procesele mai putin ultimul, trimitem carry la procesul rank+1
		MPI_Send(&carry, 1, MPI_INT, rank + 1, 0, MPI_COMM_WORLD);
	else { 
		// Ultimul proces trimite carry la procesul 0
		MPI_Send(&carry, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
	}

	// Facem gather la rezultate in R
	MPI_Gather(result, lengths, MPI_INT, R, lengths, MPI_INT, 0, MPI_COMM_WORLD);

	if (rank == 0) {
		// Procesul 0 primeste carryul
		MPI_Recv(&carry, 1, MPI_INT, p - 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

		reverse_array(R, N+1);

		// Carryul poate fi 0 din cauza adunarii 0+0 de pe ultimele pozitii alocate ultimului proces
		// Ne folosim de ultima valoare (inversat devine prima) si o afisam daca e 1
		printf("\nResult: ");
		if (R[0] == 1) printf("1");
		for (int j = 1; j < N+1; j++)
			printf("%d", R[j]);
		printf("\n");
	}

	// Final
	MPI_Finalize();

}


void mpi_secondSolution(int argc, char* argv[]) {

	int rank = 0;
	int id_proces_curent = 1;

	MPI_Init(&argc, &argv);

	MPI_Comm_size(MPI_COMM_WORLD, &p);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);

	int N = n1_size > n2_size ? n1_size : n2_size;
	MPI_Request req[10];
	MPI_Status stats[10];

	// Procesul 0 inverseaza numerele, pune zerourile necesare si se ocupa 
	// de distributia datelor
	// La final primeste rezultatele, le concateneaza si afiseaza raspunsul
	if (rank == 0) {
		reverse_array(n1, n1_size);
		reverse_array(n2, n2_size);

		if (n1_size <= n2_size) {
			for (int i = n1_size; i < n2_size; i++)
				n1[i] = 0;
		}
		else {
			for (int i = n2_size; i < n1_size; i++)
				n2[i] = 0;
		}

		int start = 0;
		for (int i = 0; i < p - 1; i++) {
			int remaining = N % (p - 1);
			int counts = N / (p - 1);
			if (i < remaining)
				counts++;

			// Se trimit chunk-uri din numere catre procese in ordine
			MPI_Isend(&counts, 1, MPI_INT, id_proces_curent, 0, MPI_COMM_WORLD, &req[2]);
			MPI_Isend(n1 + start, counts, MPI_INT, id_proces_curent, 0, MPI_COMM_WORLD, &req[0]);
			MPI_Isend(n2 + start, counts, MPI_INT, id_proces_curent, 0, MPI_COMM_WORLD, &req[1]);
			start += counts;

			id_proces_curent++;
		}

		int* segment_result = new int[N/p + 3];
		int* result = new int[N + 2];
		int kIndex = 0;

		int length, carry = 0;
		for (int i = 1; i < p; i++) {
			// Se primesc chunk-urile de la procese dar se ia in calcul si carry-ul
			MPI_Recv(&length, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			MPI_Recv(&carry, 1, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			MPI_Recv(segment_result, length, MPI_INT, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

			for (int j = 0; j < length; j++)
				R[kIndex++] = segment_result[j];

			if (carry && i == p - 1) R[kIndex++] = 1;
		}

		reverse_array(R, kIndex);

		// Afisarea rezultatului
		printf("\nResult: ");
		for (int j = 0; j < kIndex; j++)
			printf("%d", R[j]);
		printf("\n");

	}
	else {
		int* n1_section = new int[N / (p - 1) + 2];
		int* n2_section = new int[N / (p - 1) + 2];
		int* result = new int[N / (p - 1) + 2];
		int carry = 0;
		int length = 0;

		// Se primeste lungimea, se asteapta primirea, apoi se asteapta chunk-urile
		MPI_Irecv(&length, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &req[2]);
		MPI_Wait(&req[2], &stats[2]);

		MPI_Irecv(n1_section, length, MPI_INT, 0, 0, MPI_COMM_WORLD, &req[0]);
		MPI_Irecv(n2_section, length, MPI_INT, 0, 0, MPI_COMM_WORLD, &req[1]);

		MPI_Wait(&req[0], &stats[0]);
		MPI_Wait(&req[1], &stats[1]);

		if (rank > 1) {
			// Pentru procesele cu rank>1, se primeste carry-ul de la procesul anterior
			MPI_Recv(&carry, 1, MPI_INT, rank - 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		}

		// Calculele rezultatului specific procesului 
		int i;
		for (i = 0; i < length; i++) {
			int rez = (n1_section[i] + n2_section[i] + carry) % 10;
			carry = (n1_section[i] + n2_section[i] + carry) / 10;
			result[i] = rez;
		}

		if (rank < p - 1)
			// Pentru procesele cu rank < p - 1, se trimite carry-ul la urmatorul proces
			MPI_Isend(&carry, 1, MPI_INT, rank + 1, 0, MPI_COMM_WORLD, &req[3]);

		// Se trimit chunk-urile catre procesul 0
		MPI_Send(&length, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
		MPI_Send(&carry, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
		MPI_Send(result, length, MPI_INT, 0, 0, MPI_COMM_WORLD);
	}

	// Final
	MPI_Finalize();

}

int main(int argc, char* argv[]) {
	
	// generare valori

	/*srand((unsigned)time(0));

	for (int i = 0; i < n1_size; i++) {
		n1[i] = rand() % 10;
	}
	for (int i = 0; i < n2_size; i++) {
		n2[i] = rand() % 10;
	}*/

	for (int i = 0; i <= 8; i++) {
		n1[i] = n2[i] = n1[i + 9] = n2[i + 9] = i+1;
	}


	// Sequencial
	//sequencial();

	// Varianta 1
	//mpi_firstSolution(argc, argv);
	

	// Varianta 2
	mpi_secondSolution(argc, argv);
}