package be.bnair.lovehotel.models.dto;

import be.bnair.lovehotel.models.entities.Chamber;
import be.bnair.lovehotel.utils.EnumChamberType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChamberDTO {
    private long id;
    private String name;
    private HotelDTO hotel;
    private EnumChamberType type;
    private double price;

    public static ChamberDTO toDTO(Chamber entity){
        if(entity == null)
            throw  new IllegalArgumentException("Ne peut etre null");

        return ChamberDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .hotel(HotelDTO.toDTO(entity.getHotel()))
                .type(entity.getType())
                .price(entity.getPrice())
                .build();
    }
}
