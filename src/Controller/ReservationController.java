package Controller;

import Model.Reservation;
import Repository.ReservationRepository;
import Service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import Utils.DateUtils;
import Utils.PricingUtils;

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

            if(!validateDates(startDate, endDate)){
                System.out.println("Invalid dates!");
                return;
            }

            int userId = getUserByCin(cin);
            if(userId == -1){
                System.out.println("User not found!");
                return;
            }
            if(!reservationRepository.isRoomAvailable(roomId)){
                System.out.println("Room is not available!");
                return;
            }
            String season = DateUtils.getSeason(LocalDate.now());
            double basePrice = reservationRepository.getRoomPriceById(roomId);
            double adjustedPrice = PricingUtils.adjustPriceForSeason(basePrice, season);

            Reservation reservation = new Reservation();
            reservation.setUserId(userId);
            reservation.setRoomId(roomId);
            reservation.setStartDate(new java.sql.Date(startDate.getTime()));
            reservation.setEndDate(new java.sql.Date(endDate.getTime()));
            reservation.setPrice(adjustedPrice);

            reservationService.createReservation(reservation);
            System.out.println("Reservation created successfully with price : "+adjustedPrice);
        }catch(ParseException e){
            System.out.println("Invalid date format!");
        }
    }

    private boolean validateDates(java.util.Date startDate, java.util.Date endDate){
        LocalDate today = LocalDate.now();
        LocalDate startLocalDate = startDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        return !startLocalDate.isAfter(endLocalDate) &&
                !startLocalDate.isBefore(today) &&
                !endLocalDate.isBefore(today);
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

            if(!validateDates(startDate, endDate)){
                System.out.println("Invalid dates!");
                return;
            }

            if(!reservationRepository.isRoomAvailable(newRoomId)){
                System.out.println("Room is not available!");
                return;
            }

            String season = DateUtils.getSeason(LocalDate.now());
            double basePrice = reservationRepository.getRoomPriceById(newRoomId);
            double adjustedPrice = PricingUtils.adjustPriceForSeason(basePrice, season);

            existingReservation.setRoomId(newRoomId);
            existingReservation.setStartDate(new java.sql.Date(startDate.getTime()));
            existingReservation.setEndDate(new java.sql.Date(endDate.getTime()));
            existingReservation.setPrice(adjustedPrice);

            reservationService.updateReservation(existingReservation, oldRoomId);
            System.out.println("Reservation updated successfully with price : "+adjustedPrice);
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

    public void viewUserReservations(int userId) {
        HashMap<Integer, Reservation> reservations = reservationService.getUserReservations(userId);
        if (reservations.isEmpty()) {
            System.out.println("No reservations found!");
            return;
        }

        for (Reservation reservation : reservations.values()) {
            System.out.println("Reservation ID: " + reservation.getId());
            System.out.println("Start Date: " + reservation.getStartDate());
            System.out.println("End Date: " + reservation.getEndDate());
            System.out.println("Room ID: " + reservation.getRoomId());
            System.out.println("Room Type: " + reservationRepository.getRoomTypeById(reservation.getRoomId()));
            System.out.println("Room Price: " + reservationRepository.getRoomPriceById(reservation.getRoomId()));
            System.out.println("-----------------------------");
        }
    }
}


