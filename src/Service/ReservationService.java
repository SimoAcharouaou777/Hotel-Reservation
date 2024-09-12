package Service;

import Model.Reservation;
import Repository.ReservationRepository;

import java.util.HashMap;
import java.util.List;

public class ReservationService {
    private ReservationRepository reservationRepository;
    public ReservationService(){
        this.reservationRepository = new ReservationRepository();
    }

    public void createReservation(Reservation reservation){
        reservationRepository.insertReservation(reservation);
    }

    public HashMap<Integer,Reservation> getAllReservations(){
        List<Reservation> reservations = reservationRepository.getAllReservations();
        HashMap<Integer,Reservation> reservationMap = new HashMap<>();
        for(Reservation reservation : reservations){
            reservationMap.put(reservation.getId(),reservation);
        }
        return reservationMap;
    }

    public void updateReservation(Reservation reservation, int oldRoomId){
        reservationRepository.updateReservation(reservation, oldRoomId);
    }

    public void cancelReservation(int reservationId, int roomId){
        reservationRepository.cancelReservation(reservationId, roomId);
    }
}
