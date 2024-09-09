import Controller.UserController;
import DAO.UserDAO;
import Model.User;
import Service.UserService;
import Utils.DBConnection;
import DAO.UserDAO;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBConnection.getInstance();
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        UserController userController = new UserController();
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
                  userController.signIn();
                  break;
              case 3 :
                  exit = true;
                  break;
              default:
                  System.out.println("Invalid choice!");
          }
        }
        while(signedIn){
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. Create Reservation");
            System.out.println("2. Modify Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservation Details");
            System.out.println("5. Check Room Availability");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
        }













        }

}