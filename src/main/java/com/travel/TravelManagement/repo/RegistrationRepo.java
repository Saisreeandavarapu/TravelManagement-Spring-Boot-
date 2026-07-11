package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration,Long> {

    Registration findByEmailAndPassword(String email, String password);

    Long findByrole(String admin);


    Registration findByEmail(String email);
}
