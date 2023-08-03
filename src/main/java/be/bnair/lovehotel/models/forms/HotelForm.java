package be.bnair.lovehotel.models.forms;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HotelForm {
    @NotNull
    @Size(min=3)
    private String name;

    private Long address;

    private Long owner;

    private List<Long> employees;
}
