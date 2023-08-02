package be.bnair.lovehotel.service;

import be.bnair.lovehotel.models.dto.UserDTO;
import be.bnair.lovehotel.models.forms.UserForm;

import java.util.List;

public interface UserService {

    void create(UserForm form);
    UserDTO getOne(Long id);
    void update(UserForm form, Long id);
    List<UserDTO>getAll();


}
