package be.bnair.lovehotel.repository;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long>{
    AddressDTO findOneByStreetAndNumberAndBoxAndCityAndZipAndCountry(String street, int number, String box, String city, int zip, String country);
}
