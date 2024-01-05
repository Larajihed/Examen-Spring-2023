package com.example.larayedhjihed.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReservation;
    private LocalDateTime timereservation;



    @Column(name="status", nullable=false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "worker_id_agent")
    private Worker worker;

    @ToString.Exclude
    @ManyToMany(mappedBy = "reservations")
    private List<Washing_Service> washing_Services = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicule_id_vehicule")
    private Vehicule vehicule;

}
