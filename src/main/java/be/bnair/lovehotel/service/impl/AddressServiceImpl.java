package be.bnair.lovehotel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.entities.Address;
import be.bnair.lovehotel.models.forms.AddressForm;
import be.bnair.lovehotel.repository.AddressRepository;
import be.bnair.lovehotel.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
            this.addressRepository = addressRepository;
        }

    @Override
    public void create(AddressForm form) {
        Address address = new Address();

        address.setStreet(form.getStreet());
        address.setNumber(form.getNumber());
        address.setBox(form.getBox());
        address.setCity(form.getCity());
        address.setZip(form.getZip());
        address.setCountry(form.getCountry());

        addressRepository.save(address);
    }

    @Override
    public AddressDTO getOne(Long id) {
        return addressRepository.findById(id)
        .map(entite -> AddressDTO.toDTO(entite))
        .orElseThrow(() -> new RuntimeException("Address not found"));   
    }

    @Override
    public void update(AddressForm form, Long id) {
        Address addressToUpdate = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        addressToUpdate.setStreet(form.getStreet());
        addressToUpdate.setNumber(form.getNumber());
        addressToUpdate.setBox(form.getBox());
        addressToUpdate.setCity(form.getCity());
        addressToUpdate.setZip(form.getZip());
        addressToUpdate.setCountry(form.getCountry());

        addressRepository.save(addressToUpdate);
    }

    @Override
    public List<AddressDTO> getAll() {
        return addressRepository.findAll().stream()
                .map(u->AddressDTO.toDTO(u))
                .collect(Collectors.toList());
    }
    
}
