package be.bnair.lovehotel.models.forms;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class ReservationForm {

    private Long user;

    private Long chamber;

    @Future(message = "Tu ne peux pas réserver pour dans le passer!")
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Future(message = "Tu ne peux pas réserver pour dans le passer!")
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}
