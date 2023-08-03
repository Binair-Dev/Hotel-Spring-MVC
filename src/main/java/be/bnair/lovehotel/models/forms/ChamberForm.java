package be.bnair.lovehotel.models.forms;

import be.bnair.lovehotel.utils.EnumChamberType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChamberForm {
    @NotNull
    @Size(min = 3)
    private String name;

    private Long hotel;

    private EnumChamberType type;

    private double price;
}
