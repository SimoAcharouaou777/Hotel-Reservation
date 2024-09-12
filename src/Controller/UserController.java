package Controller;

import Model.User;
import Service.UserService;

import java.util.Scanner;

public class UserController {

    private UserService userService;
    private User currentUser;

    public UserController(){
        this.userService = new UserService();
    }

    public void signUp(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your user name : ");
        String name1 = sc.nextLine();
        System.out.println("Enter your email : ");
        String email1 = sc.nextLine();
        System.out.println("Enter your password : ");
        String password1 = sc.nextLine();
        System.out.println("Enter your CIN : ");
        String cin1 = sc.nextLine();
        userService.signUp(name1,email1,password1,cin1);
        System.out.println("User signed up successfully!");
    }

    public boolean signIn(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email : ");
        String email1 = sc.nextLine();
        System.out.println("Enter your password : ");
        String password1 = sc.nextLine();
        User user = userService.signIn(email1,password1);
        if(user != null){
            System.out.println("User signed in successfully!");
            this.currentUser = user;
            return true;
        }else{
            System.out.println("Invalid email or password!");
            return false;
        }
    }

    public int getCurrentUser(){
        return currentUser != null ? currentUser.getId() : -1;
    }
}
