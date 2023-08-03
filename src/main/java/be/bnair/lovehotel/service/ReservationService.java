package be.bnair.lovehotel.service;

import be.bnair.lovehotel.models.dto.ReservationDTO;
import be.bnair.lovehotel.models.forms.ReservationForm;

import java.util.List;

public interface ReservationService {

    void create(ReservationForm form);
    ReservationDTO getOne(Long id);
    void update(ReservationForm form, Long id);
    List<ReservationDTO>getAll();
}
