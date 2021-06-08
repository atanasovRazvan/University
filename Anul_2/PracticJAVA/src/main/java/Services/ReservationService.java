package Services;

import Entities.Reservation;
import Repositories.ReservationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService extends AbstractService<Long, Reservation>{

    public ReservationService(ReservationRepository repo){
        super(repo);
    }

    public String nrRezervariAnCurent(){
        StringBuilder R = new StringBuilder();
        Integer currentYear = LocalDateTime.now().getYear();
        List<Reservation> l;

        l = this.findAll().stream()
                .filter(x->x.getStartDate().getYear() == currentYear)
                .collect(Collectors.toList());
        List<Integer> aux = new ArrayList<>();

        for(int i = 0; i < l.size(); i++){
            Integer nr = 0;
            if(!aux.contains(i))
            for(int j = i+1; j < l.size(); j++){
                if(l.get(j).getClient().getID().equals(l.get(i).getClient().getID()))
                    nr++;
            }
            aux.add(i);
            R.append(l.get(i).getClient().getName()).append(" ").append(nr.toString()).append("\n");
        }
        return R.toString();
    }

}
