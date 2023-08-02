package be.bnair.lovehotel.controller;

import be.bnair.lovehotel.models.dto.AddressDTO;
import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.forms.AddressForm;
import be.bnair.lovehotel.models.forms.UserForm;
import be.bnair.lovehotel.service.AddressService;
import be.bnair.lovehotel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;

    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/users")
    public String displayAll(Model model){
        List<UserDTO> liste = userService.getAll();
        model.addAttribute("list", liste);
        return "user/users";
    }


    @GetMapping("/{id:[0-9]+}")
    public String displayOne(Model model, @PathVariable Long id){
        UserDTO choosenOne = userService.getOne(id);
        model.addAttribute("user", choosenOne);
        return "user/user";
    }

    @GetMapping("/create-user")
    public String create(Model model){
        model.addAttribute("formulaire",new UserForm());
        return "user/create-user";
    }

    @PostMapping("/create-user")
    public String processCreate(@ModelAttribute("formulaire") @Valid UserForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/create-user";
        }

        userService.create(form);
        return "redirect:/user/users";
    }

    @GetMapping("/update-user/{id:[0-9]+}")
    public String update(Model model, @PathVariable Long id){

        UserForm form = new UserForm();
        AddressForm addressForm = new AddressForm();

        UserDTO user = userService.getOne(id);
        form.setFirst_name(user.getFirst_name());
        form.setLast_name(user.getLast_name());
        form.setEmail(user.getEmail());
        form.setPassword(user.getPassword());
        form.setBirthDate(user.getBirthDate());
        

        AddressDTO address = user.getAddress();
        addressForm.setStreet(address.getStreet());
        addressForm.setNumber(address.getNumber());
        addressForm.setBox(address.getBox());
        addressForm.setCity(address.getCity());
        addressForm.setZip(address.getZip());
        addressForm.setCountry(address.getCountry());

        form.setAddressForm(addressForm);

        model.addAttribute("formulaire",form);
        model.addAttribute("id",id);
        return "user/update-user";
    }

    @PostMapping("/update-user/{id:[0-9]+}")
    public String processUpdate(@ModelAttribute("formulaire") @Valid UserForm form, BindingResult bindingResult, @PathVariable Long id){
        if(bindingResult.hasErrors()){
            return "user/update-user";
        }

        addressService.update(form.getAddressForm(), userService.getOne(id).getAddress().getId());
        userService.update(form,id);
        return "redirect:/user/users";
    }
}
