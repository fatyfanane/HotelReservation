package org.xproce.gestiondereservation_hotel.Dao.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private String description;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull

    private LocalDate date;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Room room;




}
