package com.example.larayedhjihed.controllers;

import com.example.larayedhjihed.entities.Type;
import com.example.larayedhjihed.entities.Vehicule;
import com.example.larayedhjihed.entities.Washing_Service;
import com.example.larayedhjihed.entities.Worker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.larayedhjihed.services.IDemoServiceImp;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class DemoController {
    @Autowired
    IDemoServiceImp demoService;


    @PostMapping("/addWorker")
    public Worker addWorker(@RequestBody Worker worker){
        return demoService.addWorker(worker);
    }


    @PostMapping("/addWashingService")
    public void addWashingService(@RequestBody List<Washing_Service> washingServices){
        demoService.addWashingService(washingServices);
    }

    @PostMapping("/addVehiculeReservationAndAffectToWashingservice")
    public Vehicule addVehiculeReservationAndAffectToWashingservice(
            @RequestBody Vehicule vehicule,
            @RequestParam("idService") List<Long> idServices) {
        return demoService.addVehiculeReservationAndAffectToWashingservice(vehicule, idServices);
    }

    @PostMapping("/affectWorkertoReservation")
    public Worker affectWorkertoReservation(
            @RequestParam("nic") String nic,
            @RequestParam("idReservation") List<Long> idReservation) {
        return demoService.affectWorkertoReservation(nic, idReservation);
    }


    @GetMapping("/calculateTotalIncomePerWorker")
    public Float calculateTotalIncomePerWorker(
            @RequestParam("name") String name,
            @RequestParam("type") Type typeService

            ) {
        return demoService.calculateTotalIncomePerWorker(name, typeService);
    }


    @GetMapping("/getListServiceAndNbreservation")
    public Map<String, Long> getListServiceAndNbreservation() {
        return demoService.getListServiceAndNbreservation();
    }

}
