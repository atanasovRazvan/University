package Repositories;

import Entities.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClientRepository extends AbstractRepository<Long, Client> {


    public ClientRepository() throws FileNotFoundException {
        super("clients.txt");
    }

    @Override
    public void loadData() throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader("clients.txt"));
        String st;
        while((st = buffer.readLine()) != null){
            String[] arr = st.split(",", 2);
            this.save(new Client(Long.parseLong(arr[0]), arr[1]));
        }
    }

    @Override
    public void writeToFile() throws FileNotFoundException {

    }
}
