package repository;

import model.Location;
import model.SpecialOffer;

import java.util.List;

public class SpecialOfferRepository extends AbstractRepository<Double, SpecialOffer> {

    public List<SpecialOffer> findAll() {
        return super.findAll("SpecialOffer");
    }

}
