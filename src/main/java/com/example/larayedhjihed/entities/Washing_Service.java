package com.example.larayedhjihed.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Washing_Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idService;
    private float price;



    @Column(name="type", nullable=false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Washing_Service_reservations",
            joinColumns = @JoinColumn(name = "washing_Service_idService"),
            inverseJoinColumns = @JoinColumn(name = "reservations_idReservation"))
    private List<Reservation> reservations = new ArrayList<>();



}
