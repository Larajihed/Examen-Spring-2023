package com.example.larayedhjihed.repositories;

import com.example.larayedhjihed.entities.Washing_Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Washing_ServiceRepository extends CrudRepository<Washing_Service, Long> {

    @Query("SELECT ws.type, COUNT(r) FROM Washing_Service ws JOIN ws.reservations r GROUP BY ws.type")
    List<Object[]> findServiceTypeAndCountReservations();
}