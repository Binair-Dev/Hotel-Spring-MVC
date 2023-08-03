package be.bnair.lovehotel.repository;

import be.bnair.lovehotel.models.entities.Chamber;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamberRepository extends JpaRepository<Chamber, Long> {
    
}