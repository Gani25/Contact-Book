import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import models.Contact;
import models.ContactManager;

public class Main {
    static ContactManager manager = new ContactManager(); // static keyword used

    public static void main(String[] args) {
        try {
            loadContacts("contacts.txt");
            System.out.println("Contacts Loaded\n\n");
            System.out.println(manager);
            manageContacts();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        /*
         * try {
         * // ContactManager manager = new ContactManager(); make this as a class
         * variable so accessible from more than 1 place
         * 
         * manager.addContact(new Contact("Ryan", "6135012424", "11/11/1992"));
         * manager.addContact(new Contact("Gio", "6477092344", "11/11/1992"));
         * manager.addContact(new Contact("Thomas", "8192256979", "11/11/1992"));
         * System.out.println(manager);
         * We will load the contact.txt file directly
         * 
         * // Contact contact = new Contact("ABdul Gani", "457888", "11/26/1999");
         * // System.out.println(contact.toString());
         * } catch (ParseException e) {
         * System.out.println(e.getMessage());
         * } finally {
         * System.out.println("Process complete");
         * }
         */
    }

    /**
     * Name: manageContacts
     *
     * Inside the function:
     * • 1. Starts a new instance of Scanner;
     * • 2. In an infinite loop, the user can choose to a) add b) remove a contact
     * c) exit.
     * • case a: ask for the name, phone number and birthDate.
     * • case b: ask who they'd like to remove.
     * • case c: break the loop.
     * • 3. close Scanner.
     */

    public static void manageContacts() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Would you like to \n\ta) add another contact\n\tb) remove a contact \n\tc) exit");
            String response = sc.nextLine();
            if (response.equals("a")) {
                System.out.print("\tName: ");

                String name = sc.nextLine();

                System.out.print("\tPhone Number: ");

                String phoneNumber = sc.nextLine();

                System.out.print("\tBirth Date: (MM/DD/YYYY)");

                String birthDate = sc.nextLine();
                /*
                 * here user can give name or phonenumber field as blank or empty so this is
                 * unchecked exception so we have to check and add a quality control with
                 * handling such type of exception
                 * If user enters phno which is less than 10 it will again throw Illegal
                 * Argument excp
                 */
                if (name.isBlank() || name.equals("null") || phoneNumber.isBlank() || phoneNumber.equals("null")
                        || phoneNumber.length() < 10) {
                    System.out.println("\nThe input you provided is not valid. Registration Failed");
                } else {
                    try {
                        manager.addContact(new Contact(name, phoneNumber, birthDate));
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("\n\nUPDATED CONTACTS\n\n" + manager);
                    }
                }
            } else if (response.equals("b")) {
                System.out.println("\nWho would you like to remove?");
                manager.removeContact(sc.nextLine());
                System.out.println("\n\nUPDATED CONTACTS\n\n" + manager);
            } else {
                break;
            }
        }
        sc.close();
    }

    /**
     * Name: loadContacts
     * 
     * @param fileName (String)
     * @throws FileNotFoundException
     *
     *                               Inside the function:
     *                               • 1. loads contacts from <fileName>;
     *                               • 2. From the manager object, it adds all
     *                               contacts to the contacts list.
     *                               Hint: use scan.next to grab the next String
     *                               separated by white space.
     */

    // I am throwing the exception so who ever is calling this methor will handle
    // with try and catch block
    public static void loadContacts(String fileName) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(fileName);
        Scanner scFile = new Scanner(fis);
        while (scFile.hasNextLine()) {
            try {
                Contact contact = new Contact(scFile.next(), scFile.next(), scFile.next());
                manager.addContact(contact);

            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        scFile.close();
    }
}
