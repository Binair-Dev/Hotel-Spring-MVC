package be.bnair.lovehotel.controller;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.dto.HotelDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.forms.AddressForm;
import be.bnair.lovehotel.models.forms.HotelForm;
import be.bnair.lovehotel.models.forms.UserForm;
import be.bnair.lovehotel.service.AddressService;
import be.bnair.lovehotel.service.HotelService;
import be.bnair.lovehotel.service.UserService;
import be.bnair.lovehotel.utils.UserEnumRole;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;
    private final AddressService addressService;
    private final UserService userService;

    public HotelController(HotelService hotelService, AddressService addressService, UserService userService) {
        this.hotelService = hotelService;
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/hotels")
    public String displayAll(Model model){
        List<HotelDTO> liste = hotelService.getAll();
        model.addAttribute("list", liste);
        return "hotel/hotels";
    }


    @GetMapping("/{id:[0-9]+}")
    public String displayOne(Model model, @PathVariable Long id){
        HotelDTO choosenOne = hotelService.getOne(id);
        model.addAttribute("hotel", choosenOne);
        return "hotel/hotel";
    }

    @GetMapping("/create-hotel")
    public String create(Model model) {
        model.addAttribute("formulaire", new HotelForm());
        model.addAttribute("addressList", addressService.getAll());
        model.addAttribute("userList", userService.getAll());
        return "hotel/create-hotel";
    }

    @PostMapping("/create-hotel")
    public String processCreate(@ModelAttribute("formulaire") @Valid HotelForm form,
                                BindingResult bindingResult,
                                @RequestParam(name = "selectedAddressId") Long selectedAddressId,
                                @RequestParam(name = "selectedOwnerId") Long selectedOwnerId) {
        if (bindingResult.hasErrors()) {
            return "hotel/create-hotel";
        }

        form.setAddress(selectedAddressId);
        form.setOwner(selectedOwnerId);

        //TODO: set user owner if not already

        hotelService.create(form);
        return "redirect:/hotel/hotels";
    }

    @GetMapping("/update-hotel/{id:[0-9]+}")
    public String update(Model model, @PathVariable Long id){
        HotelForm form = new HotelForm();
        HotelDTO hotel = hotelService.getOne(id);

        form.setName(hotel.getName());
        form.setAddress(hotel.getAddress().getId());
        form.setOwner(hotel.getOwner().getId());

        model.addAttribute("formulaire", form);
        model.addAttribute("id", id);
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("addressList", addressService.getAll());
        return "hotel/update-hotel";
    }

    @PostMapping("/update-hotel/{id:[0-9]+}")
    public String processUpdate(@ModelAttribute("formulaire") @Valid HotelForm form, BindingResult bindingResult, @PathVariable Long id){
        if(bindingResult.hasErrors()){
            return "hotel/update-hotel";
        }
        
        hotelService.update(form,id);
        return "redirect:/hotel/hotels";
    }
}
