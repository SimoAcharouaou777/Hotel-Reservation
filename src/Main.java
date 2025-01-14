import Controller.ReservationController;
import Controller.RoomController;
import Controller.UserController;
import Controller.StatistiqueController;
import Model.Hotel;
import Repository.HotelRepository;
import Utils.DBConnection;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBConnection.getInstance();
        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController();
        RoomController roomController = new RoomController();
        ReservationController reservationController = new ReservationController();
        HotelRepository hotelRepository = new HotelRepository();
        StatistiqueController statistiqueController = new StatistiqueController();
        Hotel hotel = new Hotel();
        hotel.setName("Al-Adarisa");
        int hotelId = hotelRepository.insertHotelIfNotExists(hotel);
        roomController.initializeRooms(hotelId);
        boolean exit = false;
        while(!exit){
          System.out.println("\n=== Hotel Reservation System ===");
          System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

          switch(choice){
              case 1 :
                  userController.signUp();
                  break;
              case 2 :
                  if(userController.signIn()){
                      displayLoggedInMenu(sc, reservationController,userController, roomController,statistiqueController);
                  }
                  break;
              case 3 :
                  exit = true;
                  break;
              default:
                  System.out.println("Invalid choice!");
          }
        }
    }

    private static void displayLoggedInMenu(Scanner sc, ReservationController reservationController, UserController userController , RoomController roomController, StatistiqueController statistiqueController) {
        boolean loggedIn = true;
        int userId = userController.getCurrentUser();
        while(loggedIn){
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. Create Reservation");
            System.out.println("2. Modify Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservation Details");
            System.out.println("5. Check Room Availability");
            System.out.println("6. See statistiques");
            System.out.println("7. Log Out");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    reservationController.createReservation(sc);
                    break;
                case 2:
                    reservationController.modifyReservation(sc);
                    break;
                case 3:
                    reservationController.cancelReservation(sc);
                    break;
                case 4:
                    reservationController.viewUserReservations(userId);
                    break;
                case 5:
                   roomController.checkAllRoomsAvailability();
                    break;
                case 6:
                    statistiqueController.getStatistique();
                    break;
                case 7:
                    loggedIn = false;
                    break;

            }
        }
    }

}