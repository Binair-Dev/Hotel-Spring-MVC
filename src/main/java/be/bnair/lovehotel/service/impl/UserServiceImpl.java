package be.bnair.lovehotel.service.impl;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.entities.Address;
import be.bnair.lovehotel.models.forms.AddressForm;
import be.bnair.lovehotel.models.forms.UserForm;
import be.bnair.lovehotel.repository.AddressRepository;
import be.bnair.lovehotel.repository.UserRepository;
import be.bnair.lovehotel.models.entities.User;
import be.bnair.lovehotel.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void create(UserForm form) {
        User entity = new User();
        BeanUtils.copyProperties(form, entity);

        AddressForm addressForm = form.getAddressForm();

        Address address = new Address();
        BeanUtils.copyProperties(addressForm, address);

        entity.setAddress(address);

        AddressDTO adto = addressRepository.findOneByStreetAndNumberAndBoxAndCityAndZipAndCountry(
            address.getStreet(), address.getNumber(), address.getBox(),
             address.getCity(), address.getZip(), address.getCountry());
        if(adto == null) {
            addressRepository.save(address);
        }

        userRepository.save(entity);
    }

    @Override
    public UserDTO getOne(Long id) {
        return userRepository.findById(id)
                .map(entite -> UserDTO.toDTO(entite))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void update(UserForm form, Long id) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userToUpdate.setFirst_name(form.getFirst_name());
        userToUpdate.setLast_name(form.getLast_name());
        userToUpdate.setEmail(form.getEmail());
        userToUpdate.setPassword(form.getPassword());
        userToUpdate.setBirthDate(form.getBirthDate());

        userRepository.save(userToUpdate);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(u->UserDTO.toDTO(u))
                .collect(Collectors.toList());
    }
}
