package be.bnair.lovehotel.models.dto;

import be.bnair.lovehotel.models.entities.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private long id;
    private String street;
    private int number;
    private String box;
    private String city;
    private int zip;
    private String country;

    public static AddressDTO toDTO(Address entity){
        if(entity == null)
            throw  new IllegalArgumentException("Ne peut etre null");

        return AddressDTO.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .box(entity.getBox())
                .city(entity.getCity())
                .zip(entity.getZip())
                .country(entity.getCountry())
                .build();
    }
}
