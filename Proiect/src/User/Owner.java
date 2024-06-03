package User;

import Shop.Shop;

import java.util.Scanner;

public class Owner {
    protected String name;
    protected String email;
    protected String phoneNumber;

    public Owner(){}
    public Owner(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        String output = ".......Owner Information.........\n";
        output += "Name: " + this.name + "\n";
        output += "Email: " + this.email + "\n";
        output += "Phone Number: " + this.phoneNumber + "\n";

        return output;
    }

    public void reader (Shop shop) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the owner: ");
        String name = scanner.nextLine();
        this.name = name;
        System.out.print("Enter the email of the owner: ");
        String email = scanner.nextLine();
        this.email = email;

        String phoneNumber;
        while(true) {
            System.out.print("Enter the phone number of the owner: ");
            phoneNumber = scanner.nextLine();
            boolean ok = phoneNumber.matches("0[0-9]{9}");
            if (ok) {
                break;
            } else {
                System.out.println("Invalid phone number. Try again.");
            }
        }
        this.phoneNumber = phoneNumber;
    }
}
