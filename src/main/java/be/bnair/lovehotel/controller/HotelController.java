package be.bnair.lovehotel.controller;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.dto.HotelDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.entities.Hotel;
import be.bnair.lovehotel.models.forms.HotelForm;
import be.bnair.lovehotel.service.AddressService;
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
    public String createHotel(Model model) {
        model.addAttribute("formulaire", new HotelForm());
        model.addAttribute("addressList", addressService.getAll());
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("employeeList", userService.getAll());
        return "hotel/create-hotel";
    }
    
    @PostMapping("/create-hotel")
    public String processCreateHotel(@ModelAttribute("formulaire") @Valid HotelForm form,
                                     BindingResult bindingResult,
                                     @RequestParam("selectedAddressId") Long selectedAddressId,
                                     @RequestParam("selectedOwnerId") Long selectedOwnerIdLong) {
        if (bindingResult.hasErrors()) {
            return "hotel/create-hotel";
        }
    
        form.setAddress(selectedAddressId);
        form.setOwner(selectedAddressId);
    
        hotelService.create(form);
        return "redirect:/hotel/hotels";
    }

    @GetMapping("/update-hotel/{id}")
    public String updateHotel(@PathVariable Long id, Model model) {
        HotelDTO hotel = hotelService.getOne(id);
        HotelForm form = new HotelForm();
        form.setName(hotel.getName());
        form.setAddress(hotel.getAddress().getId());
        form.setOwner(hotel.getOwner().getId());
        form.setEmployees(hotel.getEmployees().stream().map(UserDTO::getId).collect(Collectors.toList()));

        model.addAttribute("formulaire", form);
        model.addAttribute("addressList", addressService.getAll());
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("employeeList", userService.getAll());

        return "hotel/update-hotel";
    }

    @PostMapping("/update-hotel/{id:[0-9]+}")
    public String processUpdate(@ModelAttribute("formulaire") @Valid HotelForm form, 
                                BindingResult bindingResult, 
                                @PathVariable Long id,
                                @RequestParam(name = "selectedAddressId") Long selectedAddressId,
                                @RequestParam(name = "selectedOwnerId") Long selectedOwnerId,
                                @RequestParam("employees") List<Long> selectedEmployees){
        if(bindingResult.hasErrors()){
            return "hotel/update-hotel";
        }

        form.setAddress(selectedAddressId);
        form.setOwner(selectedOwnerId);
        List<Long> mutableEmployees = new ArrayList<>(selectedEmployees);
        form.setEmployees(mutableEmployees);

        hotelService.update(form, id);
        return "redirect:/hotel/hotels";
    }
}
