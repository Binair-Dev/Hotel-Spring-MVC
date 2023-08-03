package be.bnair.lovehotel.service.impl;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.dto.ReservationDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.entities.Address;
import be.bnair.lovehotel.models.entities.Chamber;
import be.bnair.lovehotel.models.entities.Reservation;
import be.bnair.lovehotel.models.forms.AddressForm;
import be.bnair.lovehotel.models.forms.ReservationForm;
import be.bnair.lovehotel.models.forms.UserForm;
import be.bnair.lovehotel.repository.AddressRepository;
import be.bnair.lovehotel.repository.ChamberRepository;
import be.bnair.lovehotel.repository.ReservationRepository;
import be.bnair.lovehotel.repository.UserRepository;
import be.bnair.lovehotel.models.entities.User;
import be.bnair.lovehotel.service.ReservationService;
import be.bnair.lovehotel.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ChamberRepository chamberRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, ChamberRepository chamberRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.chamberRepository = chamberRepository;
    }

    @Override
    public void create(ReservationForm form) {
        Reservation reservation = new Reservation();
        Optional<User> user = userRepository.findById(form.getUser());

        if(user.isPresent()) {
            reservation.setUser(user.get());
        }
        
        Optional<Chamber> chamber = chamberRepository.findById(form.getChamber());

        if(chamber.isPresent()) {
            reservation.setChamber(chamber.get());
        }

        reservation.setStartDate(form.getStartDate());
        reservation.setEndDate(form.getEndDate());

        reservationRepository.save(reservation);
    }

    @Override
    public ReservationDTO getOne(Long id) {
        return reservationRepository.findById(id)
                .map(entite -> ReservationDTO.toDTO(entite))
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public void update(ReservationForm form, Long id) {
        Reservation userToUpdate = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
                
        Optional<User> user = userRepository.findById(form.getUser());

        if(user.isPresent()) {
            userToUpdate.setUser(user.get());
        }
        
        Optional<Chamber> chamber = chamberRepository.findById(form.getChamber());

        if(chamber.isPresent()) {
            userToUpdate.setChamber(chamber.get());
        }

        userToUpdate.setStartDate(form.getStartDate());
        userToUpdate.setEndDate(form.getEndDate());

        reservationRepository.save(userToUpdate);
    }

    @Override
    public List<ReservationDTO> getAll() {
        return reservationRepository.findAll().stream()
                .map(u->ReservationDTO.toDTO(u))
                .collect(Collectors.toList());
    }
}
