package be.bnair.lovehotel.models.forms;

import be.bnair.lovehotel.validation.constraints.InPast;
import be.bnair.lovehotel.validation.constraints.IsEmail;
import be.bnair.lovehotel.validation.constraints.PasswordMatches;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@PasswordMatches
public class UserForm {
    @NotNull
    @Size(min=3)
    private String first_name;

    @NotNull
    @Size(min=3)
    private String last_name;

    @NotNull
    @Size(min=3)
    @IsEmail
    private String email;

    @NotNull
    @Size(min = 3)
    private String password;

    @NotNull
    @Size(min = 3)
    private String confirmPassword;

    @NotNull
    private AddressForm addressForm;

    @Past(message = "You cannot be born in the futur")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @InPast(amount = 18, unit = ChronoUnit.YEARS)
    private LocalDate birthDate;
}
