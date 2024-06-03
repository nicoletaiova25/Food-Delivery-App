package User;

import java.util.Scanner;

public class User {

    protected String name;
    protected String email;
    protected String phoneNumber;
    private String password;

    public User(){}

    public User(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void reader() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        String phoneNumber;

        while (true) {
            System.out.print("Enter your phone number: ");
            phoneNumber = sc.nextLine();
            boolean ok = phoneNumber.matches("0[0-9]{9}");
            if (ok) {
                break;
            } else {
                System.out.println("Invalid phone number. Try again.");
            }
        }
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    @Override
    public String toString(){
        String output = "......User Information......\n";
        output += "Name: " + this.name + "\n";
        output += "Email: " + this.email + "\n";
        output += "Phone Number: " + this.phoneNumber + "\n";

        return output;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
