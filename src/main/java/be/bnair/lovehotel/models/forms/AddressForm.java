package be.bnair.lovehotel.models.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressForm {
    @NotNull
    @Size(min=3)
    private String street;

    @NotNull
    private int number;

    private String box;

    @NotNull
    @Size(min=3)
    private String city;

    @NotNull
    private int zip;

    @NotNull
    @Size(min=3)
    private String country;
}
