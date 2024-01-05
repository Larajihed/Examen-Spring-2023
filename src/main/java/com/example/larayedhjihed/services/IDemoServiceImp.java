package com.example.larayedhjihed.services;

import com.example.larayedhjihed.entities.*;
import com.example.larayedhjihed.repositories.ReservationRepository;
import com.example.larayedhjihed.repositories.VehiculeRepository;
import com.example.larayedhjihed.repositories.Washing_ServiceRepository;
import com.example.larayedhjihed.repositories.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class IDemoServiceImp implements IDemoService{

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    Washing_ServiceRepository washingServiceRepository;
    @Autowired
    WorkerRepository workerRepository;




    @Override
    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public void addWashingService(List<Washing_Service> washing_service) {
        for(Washing_Service ws : washing_service){
            washingServiceRepository.save(ws);
        }
    }

    @Override
    public Vehicule addVehiculeReservationAndAffectToWashingservice(Vehicule vehicule, List<Long> idService) {
        Reservation reservation = new Reservation();
        reservation.setVehicule(vehicule);
        reservation.setStatus(Status.PENDING);
        reservation.setTimereservation(LocalDateTime.now().plusHours(2));
        reservation = reservationRepository.save(reservation);

        for (Long serviceId : idService) {
            Washing_Service service = washingServiceRepository.findById(serviceId).orElse(null);
            if (service != null) {
                service.getReservations().add(reservation);
                washingServiceRepository.save(service);
            }
        }

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Worker affectWorkertoReservation(String nic, List<Long> idReservation) {
        Worker worker = workerRepository.findByNic(nic);
        if (worker == null) {
            throw new EntityNotFoundException("Worker not found with NIC: " + nic);
        }

        List<Reservation> reservations = new ArrayList<>();
        for (Long id : idReservation) {
            Reservation reservation = reservationRepository.findById(id).orElse(null);
            if (reservation != null && reservation.getStatus() != Status.CONFIRMED) {
                reservations.add(reservation);
            }
        }

        if ((worker.getReservations() != null ? worker.getReservations().size() : 0) + reservations.size() > 5) {
            throw new IllegalStateException("Exceeds maximum reservation limit for worker");
        }
        for (Reservation reservation : reservations) {
            reservation.setWorker(worker);
            reservation.setStatus(Status.CONFIRMED);
            reservationRepository.save(reservation);
        }

        return worker;

    }

    @Override
    public Float calculateTotalIncomePerWorker(String name, Type typeService) {
        Worker worker = workerRepository.findByName(name);
        if (worker == null) {
            throw new EntityNotFoundException("Worker not found with name: " + name);
        }
        float totalIncome = 0.0f;

        LocalDateTime now = LocalDateTime.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        for (Reservation reservation : worker.getReservations()) {
            if (reservation.getStatus() == Status.CONFIRMED &&
                    reservation.getTimereservation().getMonthValue() == currentMonth &&
                    reservation.getTimereservation().getYear() == currentYear) {
                for (Washing_Service service : reservation.getWashing_Services()) {
                    if (service.getType() == typeService) {
                        totalIncome += service.getPrice();
                    }
                }
            }
        }

        return totalIncome;
    }


    @Override
    @Scheduled(cron = "*/15 * * * * *")
    public void getReservation() {
        List<Reservation> pendingReservations = reservationRepository.findByStatusOrderByTimereservation(Status.PENDING);
        for (Reservation reservation : pendingReservations) {
            System.out.println(reservation);
        }
    }
    @Override
    public Map<String, Long> getListServiceAndNbreservation() {
        List<Object[]> results = washingServiceRepository.findServiceTypeAndCountReservations();

        Map<String, Long> serviceTypeToReservationCount = new HashMap<>();
        for (Object[] result : results) {
            String serviceType = ((Type) result[0]).name();
            Long count = (Long) result[1];
            serviceTypeToReservationCount.put(serviceType, count);
        }

        return serviceTypeToReservationCount;
    }









}

