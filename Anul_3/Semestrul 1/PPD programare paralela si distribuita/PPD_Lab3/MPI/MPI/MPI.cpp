#include <iostream>
#include <mpi.h>

using namespace std;

#define MAX 20 

int main(int argc, char* argv[])
{
    int error, id, p, chunk, len;
    int a[MAX], b[MAX], c[MAX];
    MPI_Status status;
    //init MPI 
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &p);
    MPI_Comm_rank(MPI_COMM_WORLD, &id);
    char name[MPI_MAX_PROCESSOR_NAME];
    MPI_Get_processor_name(name, &len);
    cout << "procesul: " << id << std::endl;
    chunk = MAX / p;
    if (id == 0) {
        for (int i = 0; i < MAX; i++) {
            a[i] = i;
            b[i] = 2;
        }
        int start_0, start_p, end_0, rest, size;
        start_0 = 0;
        end_0 = chunk;
        rest = MAX % p;
        if (rest > 0) {
            end_0++;
        }
        start_p = end_0;
        size = chunk;
        for (int j_id = 1; j_id < p; j_id++) {
            size = chunk;
            if (j_id < rest) {
                size++;
            }
            MPI_Send(a + start_p, size, MPI_INT, j_id, 10, MPI_COMM_WORLD);
            MPI_Send(b + start_p, size, MPI_INT, j_id, 11, MPI_COMM_WORLD);
            start_p += size;
        }
        for (int i = start_0; i < end_0; i++)
            c[i] = a[i] + b[i];
        int count_in0;
        int start_poz = end_0;
        for (int j = 1; j < p; j++) {
            MPI_Recv(&count_in0, 1, MPI_INT, j, 12, MPI_COMM_WORLD, &status);
            MPI_Recv(c + start_poz, count_in0, MPI_INT, j, 13, MPI_COMM_WORLD, &status);
            start_poz += count_in0;
        }
        for (int i = 0; i < MAX; i++)
            cout << c[i] << " ";
    }
    else
    {
        int count;
        MPI_Recv(a, chunk + 1, MPI_INT, 0, 10, MPI_COMM_WORLD, &status);
        MPI_Recv(b, chunk + 1, MPI_INT, 0, 11, MPI_COMM_WORLD, &status);
        MPI_Get_count(&status, MPI_INT, &count);
        for (int i = 0; i < count; i++)
            c[i] = a[i] + b[i];
        MPI_Send(&count, 1, MPI_INT, 0, 12, MPI_COMM_WORLD);
        MPI_Send(c, count, MPI_INT, 0, 13, MPI_COMM_WORLD);
    }
    MPI_Finalize();
}