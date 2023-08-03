package be.bnair.lovehotel.models.dto;

import java.util.List;
import java.util.stream.Collectors;

import be.bnair.lovehotel.models.entities.Hotel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDTO {
    private long id;
    private String name;
    private AddressDTO address;
    private UserDTO owner;
    private List<UserDTO> employees;

    public static HotelDTO toDTO(Hotel entity) {
    if (entity == null)
        throw new IllegalArgumentException("Ne peut etre null");

    List<UserDTO> employeesDTO = entity.getEmployees().stream()
            .map(UserDTO::toDTO)
            .collect(Collectors.toList());

    return HotelDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .address(AddressDTO.toDTO(entity.getAddress()))
            .owner(UserDTO.toDTO(entity.getOwner()))
            .employees(employeesDTO)
            .build();
    }
}
