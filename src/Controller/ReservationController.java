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
        this.reservationRepository = new ReservationRepository();
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

            int userId = getUserByCin(cin);
            if(userId == -1){
                System.out.println("User not found!");
                return;
            }
            if(!reservationRepository.isRoomAvailable(roomId)){
                System.out.println("Room is not available!");
                return;
            }
            Reservation reservation = new Reservation();
            reservation.setUserId(userId);
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
        return reservationRepository.getUserIdByCin(cin);
    }

    public void modifyReservation(Scanner sc){
        System.out.println("Enter reservation ID: ");
        int reservationId = sc.nextInt();
        sc.nextLine();

        Reservation existingReservation = reservationRepository.getReservationById(reservationId);
        if(existingReservation == null){
            System.out.println("Reservation not found!");
            return;
        }
        int oldRoomId = existingReservation.getRoomId();

        System.out.println("Enter new room ID: ");
        int newRoomId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new check-in date (yyyy-mm-dd): ");
        String newCheckInDate = sc.nextLine();
        System.out.println("Enter new check-out date (yyyy-mm-dd): ");
        String newCheckOutDate = sc.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date startDate = dateFormat.parse(newCheckInDate);
            java.util.Date endDate = dateFormat.parse(newCheckOutDate);

            if(!reservationRepository.isRoomAvailable(newRoomId)){
                System.out.println("Room is not available!");
                return;
            }
            existingReservation.setRoomId(newRoomId);
            existingReservation.setStartDate(new java.sql.Date(startDate.getTime()));
            existingReservation.setEndDate(new java.sql.Date(endDate.getTime()));

            reservationService.updateReservation(existingReservation, oldRoomId);
            System.out.println("Reservation updated successfully!");
        }catch(ParseException e){
            System.out.println("Invalid date format!");
        }
    }

    public void cancelReservation(Scanner sc){
        System.out.println("Enter reservation ID: ");
        int reservationId = sc.nextInt();
        sc.nextLine();
        Reservation existingReservation = reservationRepository.getReservationById(reservationId);
        if(existingReservation == null){
            System.out.println("Reservation not found!");
            return;
        }
        reservationService.cancelReservation(existingReservation.getId(), existingReservation.getRoomId());
        System.out.println("Reservation cancelled successfully!");
    }
}


