import Controller.UserController;
import Service.UserService;
import Utils.DBConnection;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBConnection.getInstance();
        Scanner sc = new Scanner(System.in);
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
                  if(userController.signIn()){
                      displayLoggedInMenu(sc, userController);
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

    private static void displayLoggedInMenu(Scanner sc, UserController userController){
        boolean loggedIn = true;
        while(loggedIn){
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. Create Reservation");
            System.out.println("2. Modify Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservation Details");
            System.out.println("5. Check Room Availability");
            System.out.println("6. Log Out");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("reservation selected");
                    break;
                case 2:
                    System.out.println("modify reservation selected");
                    break;
                case 3:
                    System.out.println("cancel reservation selected");
                    break;
                case 4:
                    System.out.println("view reservation details selected");
                    break;
                case 5:
                    System.out.println("check room availability selected");
                    break;
                case 6:
                    loggedIn = false;
                    break;

            }
        }
    }

}