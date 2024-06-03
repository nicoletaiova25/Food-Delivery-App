package User;

import java.util.Objects;
import java.util.Scanner;

public class DeliveryGuy extends Owner {

    private String carNumber;

    public DeliveryGuy() {}
    public DeliveryGuy(String name, String email,  String phoneNumber, String carNumber) {
        super(name, email, phoneNumber);
        this.carNumber = carNumber;
    }

    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the delivery Guy: ");
        String name = sc.nextLine();
        this.name = name;
        System.out.println("Enter the email of the delivery Guy: ");
        String email = sc.nextLine();
        this.email = email;

        String phoneNumber;

        while(true) {
            System.out.print("Enter the phone number of the delivery Guy: ");
            phoneNumber = sc.nextLine();
            boolean ok = phoneNumber.matches("0[0-9]{9}");
            if (ok) {
                break;
            } else {
                System.out.println("Invalid phone number. Try again.");
            }
        }
        this.phoneNumber = phoneNumber;

        System.out.println("Enter the car number of the delivery: ");
        String carNumber = sc.nextLine();
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        String output="......Delivery Guy Information........\n";
        output+="Name: " + this.name + "\n";
        output+="Email: " + this.email + "\n";
        output+="Phone Number: " + this.phoneNumber + "\n";
        output+="Car Number: " + this.carNumber + "\n";
        return output;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryGuy)) return false;
        DeliveryGuy deliveryGuy = (DeliveryGuy) o;
        return Objects.equals(carNumber, deliveryGuy.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber);
    }
}
