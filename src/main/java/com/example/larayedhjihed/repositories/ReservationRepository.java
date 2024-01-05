package com.example.larayedhjihed.repositories;

import com.example.larayedhjihed.entities.Reservation;
import com.example.larayedhjihed.entities.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByStatusOrderByTimereservation(Status status);

}