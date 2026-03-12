//package com.example;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class AddressBookApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AddressBookApplication.class, args);
//	}
//
//}


package com.example;

import com.example.model.Contact;
import com.example.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AddressBookApplication implements CommandLineRunner {

    @Autowired
    private AddressBookService service;

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== ADDRESS BOOK SYSTEM =====");

        System.out.print("Enter AddressBook Name: ");
        String bookName = sc.nextLine();

        service.createAddressBook(bookName);

        while(true){

            System.out.println("\nChoose Operation");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search by City");
            System.out.println("5. Sort by Name");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    System.out.println("Enter First Name:");
                    String fn = sc.nextLine();

                    System.out.println("Enter Last Name:");
                    String ln = sc.nextLine();

                    System.out.println("Enter Address:");
                    String address = sc.nextLine();

                    System.out.println("Enter City:");
                    String city = sc.nextLine();

                    System.out.println("Enter State:");
                    String state = sc.nextLine();

                    System.out.println("Enter Zip:");
                    String zip = sc.nextLine();

                    System.out.println("Enter Phone:");
                    String phone = sc.nextLine();

                    System.out.println("Enter Email:");
                    String email = sc.nextLine();

                    Contact contact = new Contact(fn,ln,address,city,state,zip,phone,email);

                    service.addContact(bookName,contact);

                    System.out.println("Contact Added Successfully");
                    break;

                case 2:
                    System.out.println("Enter First Name to Edit:");
                    String editName = sc.nextLine();

                    System.out.println("Enter New City:");
                    String newCity = sc.nextLine();

                    Contact newContact = new Contact();
                    newContact.setCity(newCity);

                    service.editContact(bookName,editName,newContact);

                    System.out.println("Contact Updated");
                    break;

                case 3:
                    System.out.println("Enter First Name to Delete:");
                    String deleteName = sc.nextLine();

                    service.deleteContact(bookName,deleteName);

                    System.out.println("Contact Deleted");
                    break;

                case 4:
                    System.out.println("Enter City:");
                    String searchCity = sc.nextLine();

                    System.out.println(service.searchByCity(searchCity));
                    break;

                case 5:
                    System.out.println(service.sortByName(bookName));
                    break;

                case 6:
                	service.showAll(bookName).forEach(System.out::println);
                	break;
                    
                case 7:
                	System.out.println("Exiting...");
                    return;
                	

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}