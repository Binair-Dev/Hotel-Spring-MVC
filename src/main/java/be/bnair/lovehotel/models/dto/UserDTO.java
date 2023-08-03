package be.bnair.lovehotel.models.dto;

import be.bnair.lovehotel.models.entities.User;
import be.bnair.lovehotel.utils.UserEnumRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private AddressDTO address;
    private UserEnumRole role;
    private LocalDate birthDate;
    private boolean enabled;

    public static UserDTO toDTO(User entity){
        if(entity == null)
            throw  new IllegalArgumentException("Ne peut etre null");

        return UserDTO.builder()
                .id(entity.getId())
                .first_name(entity.getFirst_name())
                .last_name(entity.getLast_name())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .address(AddressDTO.toDTO(entity.getAddress()))
                .role(entity.getRole())
                .birthDate(entity.getBirthDate())
                .enabled(entity.isEnabled())
                .build();
    }
}
