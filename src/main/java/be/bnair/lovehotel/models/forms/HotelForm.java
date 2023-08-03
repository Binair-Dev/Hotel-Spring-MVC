package be.bnair.lovehotel.models.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HotelForm {
    @NotNull
    @Size(min=3)
    private String name;

    private long address;

    private long owner;
}
