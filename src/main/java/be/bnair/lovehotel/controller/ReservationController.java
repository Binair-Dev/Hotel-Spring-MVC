package be.bnair.lovehotel.controller;

import be.bnair.lovehotel.models.dto.ChamberDTO;
import be.bnair.lovehotel.models.dto.ReservationDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.forms.ReservationForm;
import be.bnair.lovehotel.service.ChamberService;
import be.bnair.lovehotel.service.ReservationService;
import be.bnair.lovehotel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final ChamberService chamberService;

    public ReservationController(ReservationService reservationService, UserService userService, ChamberService chamberService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.chamberService = chamberService;
    }

    @GetMapping("/reservations")
    public String displayAll(Model model){
        List<ReservationDTO> liste = reservationService.getAll();
        model.addAttribute("list", liste);
        return "reservation/reservations";
    }


    @GetMapping("/{id:[0-9]+}")
    public String displayOne(Model model, @PathVariable Long id){
        ReservationDTO choosenOne = reservationService.getOne(id);
        model.addAttribute("reservation", choosenOne);
        return "reservation/reservation";
    }

    @GetMapping("/create-reservation")
    public String create(Model model) {
        List<UserDTO> users = userService.getAll();
        List<ChamberDTO> chambers = chamberService.getAll();

        model.addAttribute("formulaire", new ReservationForm());
        model.addAttribute("users", users);
        model.addAttribute("chambers", chambers);

        return "reservation/create-reservation";
    }

    @PostMapping("/create-reservation")
    public String processCreate(@ModelAttribute("formulaire") @Valid ReservationForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "reservation/create-reservation";
        }
    
        form.setUser(form.getUser());
        form.setChamber(form.getChamber());
        reservationService.create(form);
        return "redirect:/reservation/reservations";
    }
    
    @GetMapping("/update-reservation/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        // Récupérer la réservation à partir de l'ID
        ReservationDTO reservation = reservationService.getOne(id);
    
        // Vérifier si la réservation existe dans la base de données
        if (reservation == null) {
            return "redirect:/reservation/reservations"; // Rediriger vers la liste des réservations
        }
    
        List<UserDTO> users = userService.getAll();
        List<ChamberDTO> chambers = chamberService.getAll();
    
        // Pré-remplir le formulaire avec les détails de la réservation existante
        ReservationForm form = new ReservationForm();
        form.setUser(reservation.getUser().getId());
        form.setChamber(reservation.getChamber().getId());
        form.setStartDate(reservation.getStartDate());
        form.setEndDate(reservation.getEndDate());
    
        model.addAttribute("formulaire", form);
        model.addAttribute("users", users);
        model.addAttribute("chambers", chambers);
    
        return "reservation/update-reservation";
    }
    
    @PostMapping("/update-reservation/{id}")
    public String processUpdate(@PathVariable("id") Long reservationId, @ModelAttribute("formulaire") @Valid ReservationForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reservation/update-reservation";
        }

        ReservationDTO existingReservation = reservationService.getOne(reservationId);

        if (existingReservation == null) {
            return "redirect:/reservation/reservations";
        }

        // Enregistrer les modifications
        reservationService.update(form, reservationId);
        return "redirect:/reservation/reservations";
    }
    
}
