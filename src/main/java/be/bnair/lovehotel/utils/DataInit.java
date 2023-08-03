package be.bnair.lovehotel.utils;

import java.time.LocalDate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import be.bnair.lovehotel.models.entities.Address;
import be.bnair.lovehotel.models.entities.Chamber;
import be.bnair.lovehotel.models.entities.Hotel;
import be.bnair.lovehotel.models.entities.Reservation;
import be.bnair.lovehotel.models.entities.User;
import be.bnair.lovehotel.repository.AddressRepository;
import be.bnair.lovehotel.repository.ChamberRepository;
import be.bnair.lovehotel.repository.HotelRepository;
import be.bnair.lovehotel.repository.ReservationRepository;
import be.bnair.lovehotel.repository.UserRepository;

@Component
public class DataInit implements InitializingBean {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final HotelRepository hotelRepository;
    private final ChamberRepository chamberRepository;
    private final ReservationRepository reservationRepository;

    public DataInit(UserRepository userRepository, AddressRepository addressRepository, HotelRepository hotelRepository,
                    ChamberRepository chamberRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.hotelRepository = hotelRepository;
        this.chamberRepository = chamberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Address brianAddress = new Address();
        brianAddress.setStreet("Rue de la gare");
        brianAddress.setNumber(128);
        brianAddress.setBox("A");
        brianAddress.setCity("Ham Sur Sambre");
        brianAddress.setZip(5190);
        brianAddress.setCountry("Belgique");
        addressRepository.save(brianAddress);

        Address julieAddress = new Address();
        julieAddress.setStreet("Rue de la tise");
        julieAddress.setNumber(69);
        julieAddress.setBox("A2");
        julieAddress.setCity("VicesVersa");
        julieAddress.setZip(6969);
        julieAddress.setCountry("Belgique");
        addressRepository.save(julieAddress);

        User brian = new User();
        brian.setFirst_name("Van Bellinghen");
        brian.setLast_name("Brian");
        brian.setEmail("van.bellinghen.brian@gmail.com");
        brian.setPassword("unmotdepassedequalite");
        brian.setAddress(brianAddress);
        brian.setRole(EnumUserRole.ADMIN);
        brian.setBirthDate(LocalDate.of(1997, 8, 9));
        brian.setEnabled(true);
        userRepository.save(brian);

        User julie = new User();
        julie.setFirst_name("Frazelle");
        julie.setLast_name("Julie");
        julie.setEmail("frazelle.julie@gmail.com");
        julie.setPassword("unmotdepassedequalite");
        julie.setAddress(julieAddress);
        julie.setRole(EnumUserRole.MEMBER);
        julie.setBirthDate(LocalDate.of(1977, 9, 8));
        julie.setEnabled(true);
        userRepository.save(julie);
        
        Hotel hotel = new Hotel();
        hotel.setName("SexOnTheFloor");
        hotel.setAddress(brianAddress);
        hotel.setOwner(brian);
        hotelRepository.save(hotel);

        Chamber chambre = new Chamber();
        chambre.setName("50 Shades of Grey");
        chambre.setHotel(hotel);
        chambre.setType(EnumChamberType.RED_ROOM);
        chambre.setPrice(75.5);
        chamberRepository.save(chambre);

        Reservation brianReservation = new Reservation();
        brianReservation.setUser(brian);
        brianReservation.setChamber(chambre);
        brianReservation.setStartDate(LocalDate.of(2023, 8, 7));
        brianReservation.setEndDate(LocalDate.of(2023, 8, 11));
        reservationRepository.save(brianReservation);
    }
}
