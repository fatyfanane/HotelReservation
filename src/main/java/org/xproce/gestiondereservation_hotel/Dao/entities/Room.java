package org.xproce.gestiondereservation_hotel.Dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roomNumber;
    private Double pricePerNight;
    private Boolean available;
    private String description;
    private String roomType;



}
