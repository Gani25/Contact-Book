package models;

import java.util.ArrayList;

public class ContactManager {

    ArrayList<Contact> contacts;

    public ContactManager() {
        this.contacts = new ArrayList<Contact>(); // created a new object of arraylist
    }

    public Contact getContacts(int index) {
        return new Contact(contacts.get(index));
    }

    public void setContact(Contact contact, int index) {
        contacts.set(index, new Contact(contact));
    }

    public void addContact(Contact contact) {
        contacts.add(new Contact(contact));
    }

    public void removeContact(String name) {
        // illegalStateException: as we dont have any contact in list and user tries to
        // remove the contact its like calling remove method at wrong time so we have to
        // control this
        if (contacts.isEmpty()) {
            throw new IllegalStateException("Cannot remove from and empty list..");
        }
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                contacts.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        String temp = ""; // empty string
        for (int i = 0; i < contacts.size(); i++) {
            temp += contacts.get(i).toString();
            temp += "\n\n";
        }
        return temp;
    }
}
