package be.bnair.lovehotel.repository;

import be.bnair.lovehotel.models.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}