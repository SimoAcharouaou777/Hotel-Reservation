package Service;
import Model.Reservation;
import Repository.ReservationRepository;

import java.util.List;

public class StatistiqueService {
    private ReservationRepository reservationRepository;
    public StatistiqueService(){
        this.reservationRepository = new ReservationRepository();
    }

    public void getStatistique(){
        List<Reservation> reservations = reservationRepository.getAllReservations();
        long totalReservations = reservations.size();

        System.out.println("Total reservations: " + totalReservations);

    }
}
