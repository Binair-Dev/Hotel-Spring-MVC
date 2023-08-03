package be.bnair.lovehotel.controller;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.dto.ChamberDTO;
import be.bnair.lovehotel.models.dto.HotelDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.entities.Hotel;
import be.bnair.lovehotel.models.forms.ChamberForm;
import be.bnair.lovehotel.models.forms.HotelForm;
import be.bnair.lovehotel.service.AddressService;
import be.bnair.lovehotel.service.ChamberService;
import be.bnair.lovehotel.service.HotelService;
import be.bnair.lovehotel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chamber")
public class ChamberController {

    private final ChamberService chamberService;
    private final HotelService hotelService;
    private final AddressService addressService;
    private final UserService userService;

    public ChamberController(ChamberService chamberService, HotelService hotelService, AddressService addressService, UserService userService) {
        this.chamberService = chamberService;
        this.hotelService = hotelService;
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/chambers")
    public String displayAll(Model model){
        List<ChamberDTO> liste = chamberService.getAll();
        model.addAttribute("list", liste);
        return "chamber/chambers";
    }


    @GetMapping("/{id:[0-9]+}")
    public String displayOne(Model model, @PathVariable Long id){
        ChamberDTO choosenOne = chamberService.getOne(id);
        model.addAttribute("chamber", choosenOne);
        return "chamber/chamber";
    }
    @GetMapping("/create-chamber")
    public String createChamber(Model model) {
        model.addAttribute("formulaire", new ChamberForm());
        model.addAttribute("hotelList", hotelService.getAll());
        return "chamber/create-chamber";
    }

    @PostMapping("/create-chamber")
    public String processCreate(@ModelAttribute("formulaire") @Valid ChamberForm form,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "chamber/create-chamber";
        }

        chamberService.create(form);
        return "redirect:/chamber/chambers";
    }

    @GetMapping("/update-chamber/{id}")
    public String update(@PathVariable Long id, Model model) {
        ChamberDTO chamber = chamberService.getOne(id);
        model.addAttribute("chamber", chamber);
        model.addAttribute("hotelList", hotelService.getAll());
        return "chamber/update-chamber";
    }

    @PostMapping("/update-chamber/{id:[0-9]+}")
    public String processUpdate(@ModelAttribute("formulaire") @Valid ChamberForm form, 
                                BindingResult bindingResult, 
                                @PathVariable Long id){
        if(bindingResult.hasErrors()){
            return "chamber/update-chamber";
        }

        chamberService.update(form, id);
        return "redirect:/chamber/chambers";
    }
}
