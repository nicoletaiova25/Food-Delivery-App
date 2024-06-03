package Servicii;

import java.util.Scanner;

public class Aplication { //singleton

    private static Aplication sg_instance = null;

    private Aplication() {}

    public static synchronized Aplication getInstance() {
        if (sg_instance == null) {
            sg_instance = new Aplication();
        }
        return sg_instance;
    }

    private void listOfActionsforAdmin(){
        System.out.println("Signed in as Admin");
        System.out.println("Your options: ");
        System.out.println("1. Add Shop ");
        System.out.println("2. Delete Shop ");
        System.out.println("3. Add a product");
        System.out.println("4. Delete a product");
        System.out.println("5. List shops");
        System.out.println("6. List one shop of your choice");
        System.out.println("7. Log off");
        System.out.print("Enter the number of your choice: ");
    }

    private void listOfActionsforUser(Servicii servicii){
        System.out.println("Signed in as User" + servicii.getCurrentUsersEmail());
        System.out.println("Your options: ");
        System.out.println("1. List Shops");
        System.out.println("2. List one shop of your choice");
        System.out.println("3. Sort shops by rating ");
        System.out.println("4. Place an order");
        System.out.println("5. Rate one shop");
        System.out.println("6. Log off");
        System.out.print("Enter the number of your choice: ");
    }

    private void adminActions(Servicii servicii){
        Scanner sc = new Scanner(System.in);

        while(true){
            this.listOfActionsforAdmin();
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                servicii.addShop();
            } else if (choice == 2) {
                servicii.deleteShop();
            } else if (choice == 3) {
                servicii.addProdus();
            } else if (choice == 4) {
                servicii.deleteProduct();
            } else if (choice == 5) {
                servicii.listShops();
            } else if (choice == 6) {
                servicii.listOneShop();
            } else if (choice == 7) {
                servicii.logOff();
                //break;
                servicii.logIn();
            }


            System.out.print("\nDo you want to select another action? (yes/no): ");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("no")){
                break;
            }
        }
    }

    private void userActions(Servicii servicii){
        Scanner sc = new Scanner(System.in);

        while(true){
            this.listOfActionsforUser(servicii);
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                servicii.listShops();
            } else if (choice == 2) {
                servicii.listOneShop();
            } else if (choice == 3) {
                servicii.sortShopsByRating();
            } else if (choice == 4) {
                servicii.addOrder();
            } else if (choice == 5) {
                servicii.rateShop();
            } else if (choice == 6) {
                servicii.logOff();
                //break;
                servicii.logIn();
            }

            System.out.print("\nDo you want to select another action? (yes/no): ");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("no")){
                break;
            }
        }
    }

    public void stateActions(){
        Servicii servicii = Servicii.getInstance();

        if(servicii.logIn() == 1){
            this.adminActions(servicii);
        }
        else{
            this.userActions(servicii);
        }
    }
}
