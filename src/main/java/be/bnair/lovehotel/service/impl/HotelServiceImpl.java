package be.bnair.lovehotel.service.impl;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.dto.HotelDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.entities.Address;
import be.bnair.lovehotel.models.entities.Hotel;
import be.bnair.lovehotel.models.forms.AddressForm;
import be.bnair.lovehotel.models.forms.HotelForm;
import be.bnair.lovehotel.models.forms.UserForm;
import be.bnair.lovehotel.repository.AddressRepository;
import be.bnair.lovehotel.repository.HotelRepository;
import be.bnair.lovehotel.repository.UserRepository;
import be.bnair.lovehotel.models.entities.User;
import be.bnair.lovehotel.service.HotelService;
import be.bnair.lovehotel.service.UserService;
import be.bnair.lovehotel.utils.UserEnumRole;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public HotelServiceImpl(HotelRepository hotelRepository,
                            AddressRepository addressRepository, 
                            UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(HotelForm form) {
        Hotel hotel = new Hotel();
        hotel.setName(form.getName());

        Optional<Address> address = addressRepository.findById(form.getAddress());
        if(address.isPresent()) {
            hotel.setAddress(address.get());
        } else throw new IllegalStateException("Address " + form.getAddress() + " does not exist");

        Optional<User> user = userRepository.findById(form.getOwner());
        if(user.isPresent()) {
            hotel.setOwner(user.get());
        } else throw new IllegalStateException("User " + form.getAddress() + " does not exist");

        hotelRepository.save(hotel);
    }

    @Override
    public HotelDTO getOne(Long id) {
        return hotelRepository.findById(id)
                .map(entite -> HotelDTO.toDTO(entite))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void update(HotelForm form, Long id) {
        Hotel userToUpdate = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userToUpdate.setName(form.getName());

        Optional<Address> address = addressRepository.findById(form.getAddress());
        if(address.isPresent()) {
            userToUpdate.setAddress(address.get());
        } else throw new IllegalStateException("Address " + form.getAddress() + " does not exist");

        Optional<User> user = userRepository.findById(form.getOwner());
        if(user.isPresent()) {
            userToUpdate.setOwner(user.get());
        } else throw new IllegalStateException("User " + form.getAddress() + " does not exist");
        
        hotelRepository.save(userToUpdate);
    }

    @Override
    public List<HotelDTO> getAll() {
        return hotelRepository.findAll().stream()
                .map(u->HotelDTO.toDTO(u))
                .collect(Collectors.toList());
    }
}
