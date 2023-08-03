package be.bnair.lovehotel.models.dto;

import java.time.LocalDate;

import be.bnair.lovehotel.models.entities.Reservation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDTO {
    private long id;
    private UserDTO user;
    private ChamberDTO chamber;
    private LocalDate startDate;
    private LocalDate endDate;

    public static ReservationDTO toDTO(Reservation entity){
        if(entity == null)
            throw  new IllegalArgumentException("Ne peut etre null");

        return ReservationDTO.builder()
                .id(entity.getId())
                .user(UserDTO.toDTO(entity.getUser()))
                .chamber(ChamberDTO.toDTO(entity.getChamber()))
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
