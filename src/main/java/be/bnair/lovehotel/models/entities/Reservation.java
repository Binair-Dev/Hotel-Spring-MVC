package be.bnair.lovehotel.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reserver_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reserved_chamber_id")
    private Chamber chamber;

    private LocalDate startDate;
    private LocalDate endDate;
}