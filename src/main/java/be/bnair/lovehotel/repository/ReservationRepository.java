package be.bnair.lovehotel.repository;

import be.bnair.lovehotel.models.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
