package be.bnair.lovehotel.utils;

import java.time.LocalDate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import be.bnair.lovehotel.models.entities.Address;
import be.bnair.lovehotel.models.entities.User;
import be.bnair.lovehotel.repository.AddressRepository;
import be.bnair.lovehotel.repository.UserRepository;

@Component
public class DataInit implements InitializingBean {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;


    public DataInit(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
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

        User brian = new User();
        brian.setFirst_name("Van Bellinghen");
        brian.setLast_name("Brian");
        brian.setEmail("van.bellinghen.brian@gmail.com");
        brian.setPassword("unmotdepassedequalite");
        brian.setAddress(brianAddress);
        brian.setRole(UserEnumRole.O); //OWNER
        brian.setBirthDate(LocalDate.of(1997, 8, 9));
        brian.setEnabled(true);
        userRepository.save(brian);
    }
}
