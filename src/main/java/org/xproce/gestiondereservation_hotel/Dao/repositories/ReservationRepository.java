package org.xproce.gestiondereservation_hotel.Dao.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
     List<Reservation> findAll();

}