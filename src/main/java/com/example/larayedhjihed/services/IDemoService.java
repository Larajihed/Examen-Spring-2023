package com.example.larayedhjihed.services;

import com.example.larayedhjihed.entities.Type;
import com.example.larayedhjihed.entities.Vehicule;
import com.example.larayedhjihed.entities.Washing_Service;
import com.example.larayedhjihed.entities.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface IDemoService {


    public Worker addWorker(Worker worker);

    public void addWashingService(List<Washing_Service> washing_service);


    public Vehicule addVehiculeReservationAndAffectToWashingservice(Vehicule vehicule, List<Long> idService);


    public Worker affectWorkertoReservation(String nic, List<Long> idReservation);


    public Float calculateTotalIncomePerWorker(String name, Type typeService);


    void getReservation();

    public Map<String, Long> getListServiceAndNbreservation();

}
