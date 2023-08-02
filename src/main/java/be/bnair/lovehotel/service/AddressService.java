package be.bnair.lovehotel.service;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.forms.AddressForm;

import java.util.List;

public interface AddressService {

    void create(AddressForm form);
    AddressDTO getOne(Long id);
    void update(AddressForm form, Long id);
    List<AddressDTO>getAll();
}
