package Controller;

import Model.Reservation;
import Service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReservationController {
    private ReservationService reservationService;
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
            Date startDate = dateFormat.parse(checkInDate);
            Date endDate = dateFormat.parse(checkOutDate);

            Reservation reservation = new Reservation();
            reservation.setUserId(getUserByCin(cin));
            reservation.setRoomId(roomId);
            reservation.setStartDate((java.sql.Date) startDate);
            reservation.setEndDate((java.sql.Date) endDate);
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
