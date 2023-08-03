package be.bnair.lovehotel.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import be.bnair.lovehotel.models.dto.ChamberDTO;
import be.bnair.lovehotel.models.entities.Chamber;
import be.bnair.lovehotel.models.entities.Hotel;
import be.bnair.lovehotel.models.forms.ChamberForm;
import be.bnair.lovehotel.repository.ChamberRepository;
import be.bnair.lovehotel.repository.HotelRepository;
import be.bnair.lovehotel.service.ChamberService;

@Service
public class ChamberServiceImpl implements ChamberService {

    private final ChamberRepository chamberRepository;
    private final HotelRepository hotelRepository;

    public ChamberServiceImpl(ChamberRepository chamberRepository, HotelRepository hotelRepository) {
        this.chamberRepository = chamberRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void create(ChamberForm form) {
        Chamber chamber = new Chamber();
        chamber.setName(form.getName());
        
        Optional<Hotel> hotel = hotelRepository.findById(form.getHotel());
        if(hotel.isPresent()) {
            chamber.setHotel(hotel.get());
        }
        chamber.setType(form.getType());
        chamber.setPrice(form.getPrice());

        chamberRepository.save(chamber);
    }

    @Override
    public ChamberDTO getOne(Long id) {
        return chamberRepository.findById(id)
                .map(entite -> ChamberDTO.toDTO(entite))
                .orElseThrow(() -> new RuntimeException("Chamber not found"));
    }

    @Override
    public void update(ChamberForm form, Long id) {
        Chamber userToUpdate = chamberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamber not found"));

        userToUpdate.setName(form.getName());
        
        Optional<Hotel> hotel = hotelRepository.findById(form.getHotel());
        if(hotel.isPresent()) {
            userToUpdate.setHotel(hotel.get());
        }
        userToUpdate.setType(form.getType());
        userToUpdate.setPrice(form.getPrice());

        chamberRepository.save(userToUpdate);
    }

    @Override
    public List<ChamberDTO> getAll() {
        return chamberRepository.findAll().stream()
                .map(u->ChamberDTO.toDTO(u))
                .collect(Collectors.toList());
    }
}
