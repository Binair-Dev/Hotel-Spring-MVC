package be.bnair.lovehotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.bnair.lovehotel.models.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}