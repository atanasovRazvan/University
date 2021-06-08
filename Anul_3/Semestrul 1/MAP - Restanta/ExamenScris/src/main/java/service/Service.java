package service;

import model.Hotel;
import model.Reservation;
import model.SpecialOffer;
import repository.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Service implements IService{

    private ClientRepository clientRepository;
    private HotelRepository hotelRepository;
    private LocationRepository locationRepository;
    private ReservationRepository reservationRepository;
    private SpecialOfferRepository specialOfferRepository;

    public Service() {
        this.clientRepository = new ClientRepository();
        this.hotelRepository = new HotelRepository();
        this.locationRepository = new LocationRepository();
        this.reservationRepository = new ReservationRepository();
        this.specialOfferRepository = new SpecialOfferRepository();

    }

    @Override
    public List<Hotel> getAllHotelsFromLocation(String locationName) {
        AtomicReference<Double> locationId = new AtomicReference<>(0D);
        locationRepository.findAll().forEach(l -> {
            if(l.getLocationName().equals(locationName))
                locationId.set(l.getId());
        });
        return hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getLocationId().equals(locationId.get()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialOffer> getSpecialOffersFromHotel(Double hotelId, Date startDate, Date endDate) {
        return specialOfferRepository.findAll().stream()
                .filter(specialOffer -> {
                    if(specialOffer.getHotelId().equals(hotelId) &&
                        startDate.before(specialOffer.getEndDate()) &&
                        specialOffer.getStartDate().before(endDate))
                        return true;
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getClientName(Long clientId) {

        clientRepository.findAll().forEach(c -> System.out.println(c.getName()));

        AtomicReference<String> clientName = new AtomicReference<>("");
        clientRepository.findAll().forEach(client -> {
            if(client.getId().equals(clientId))
                clientName.set(client.getName());
        });
        return clientName.get();
    }

    @Override
    public List<SpecialOffer> showAllAvailableOffers(Long clientId) {

        AtomicReference<Integer> fidelity = new AtomicReference<Integer>(0);
        clientRepository.findAll().forEach(client -> {
            if(client.getId().equals(clientId))
                fidelity.set(client.getFidelityGrade());
        });

        Date nowDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        return specialOfferRepository.findAll().stream()
                .filter(specialOffer -> {
                    if(fidelity.get() > specialOffer.getPercents() &&
                            nowDate.before(specialOffer.getEndDate()) &&
                            nowDate.after(specialOffer.getStartDate()))
                        return true;
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void makeReservation(Reservation reservation) {
        reservationRepository.add(reservation);
    }
}
