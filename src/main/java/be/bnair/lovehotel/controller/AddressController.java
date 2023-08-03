package be.bnair.lovehotel.controller;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.forms.AddressForm;
import be.bnair.lovehotel.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public String displayAll(Model model){
        List<AddressDTO> liste = addressService.getAll();
        model.addAttribute("list", liste);
        return "address/addresses";
    }


    @GetMapping("/{id:[0-9]+}")
    public String displayOne(Model model, @PathVariable Long id){
        AddressDTO choosenOne = addressService.getOne(id);
        model.addAttribute("address", choosenOne);
        return "address/address";
    }

    @GetMapping("/create-address")
    public String create(Model model){
        model.addAttribute("formulaire",new AddressForm());
        return "address/create-address";
    }

    @PostMapping("/create-address")
    public String processCreate(@ModelAttribute("formulaire") @Valid AddressForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "address/create-address";
        }

        addressService.create(form);
        return "redirect:/address/addresses";
    }

    @GetMapping("/update-address/{id:[0-9]+}")
    public String update(Model model, @PathVariable Long id){

        AddressForm form = new AddressForm();

        AddressDTO address = addressService.getOne(id);
        form.setStreet(address.getStreet());
        form.setNumber(address.getNumber());
        form.setBox(address.getBox());
        form.setCity(address.getCity());
        form.setZip(address.getZip());
        form.setCountry(address.getCountry());

        model.addAttribute("formulaire",form);
        model.addAttribute("id",id);
        return "address/update-address";
    }

    @PostMapping("/update-address/{id:[0-9]+}")
    public String processUpdate(@ModelAttribute("formulaire") @Valid AddressForm form, BindingResult bindingResult, @PathVariable Long id){
        if(bindingResult.hasErrors()){
            return "address/update-address";
        }
        addressService.update(form,id);
        return "redirect:/address/addresses";
    }
}
