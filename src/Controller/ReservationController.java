package Controller;

import Model.Reservation;
import Repository.ReservationRepository;
import Service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public class ReservationController {
    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    public ReservationController(){
        this.reservationService = new ReservationService();
    }

    public void createReservation(Scanner sc){
        System.out.println("Enter user CIN: ");
        String cin = sc.nextLine();
        System.out.println("Enter room ID: ");
        int roomId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter check-in date (yyyy-mm-dd): ");
        String checkInDate = sc.nextLine();

        System.out.println("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDate = sc.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date startDate = dateFormat.parse(checkInDate);
            java.util.Date endDate = dateFormat.parse(checkOutDate);

            Reservation reservation = new Reservation();
            reservation.setUserId(getUserByCin(cin));
            reservation.setRoomId(roomId);
            reservation.setStartDate(new java.sql.Date(startDate.getTime()));
            reservation.setEndDate(new java.sql.Date(endDate.getTime()));
            reservationService.createReservation(reservation);
            System.out.println("Reservation created successfully!");
        }catch(ParseException e){
            System.out.println("Invalid date format!");
        }
    }

    private int getUserByCin(String cin) {
        return 0;
    }
}
