package be.bnair.lovehotel.service;

import be.bnair.lovehotel.models.dto.HotelDTO;
import be.bnair.lovehotel.models.forms.HotelForm;

import java.util.List;

public interface HotelService {

    void create(HotelForm form);
    HotelDTO getOne(Long id);
    void update(HotelForm form, Long id);
    List<HotelDTO>getAll();
}
