package com.example.service;

import com.example.model.Contact;
import com.example.model.AddressBook;
import com.example.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repo;

    // UC6 create addressbook
    public void createAddressBook(String name){
        repo.addAddressBook(name);
    }

    // UC2 add contact
    public Contact addContact(String bookName, Contact contact){

        AddressBook book = repo.getAddressBook(bookName);

        // UC7 duplicate check
        boolean exists = book.getContacts().stream()
                .anyMatch(c -> c.equals(contact));

        if(exists){
            throw new RuntimeException("Duplicate Contact");
        }

        book.getContacts().add(contact);
        return contact;
    }

    // UC3 edit contact
    public Contact editContact(String bookName,String firstName,Contact newData){

        AddressBook book = repo.getAddressBook(bookName);

        for(Contact c:book.getContacts()){
            if(c.getFirstName().equalsIgnoreCase(firstName)){
                c.setAddress(newData.getAddress());
                c.setCity(newData.getCity());
                c.setState(newData.getState());
                c.setZip(newData.getZip());
                c.setPhoneNumber(newData.getPhoneNumber());
                c.setEmail(newData.getEmail());
                return c;
            }
        }
        return null;
    }

    // UC4 delete contact
    public void deleteContact(String bookName,String firstName){

        AddressBook book = repo.getAddressBook(bookName);

        book.getContacts().removeIf(c->c.getFirstName().equalsIgnoreCase(firstName));
    }

    // UC8 search by city/state
    public List<Contact> searchByCity(String city){

        return repo.getAddressBooks().values()
                .stream()
                .flatMap(b->b.getContacts().stream())
                .filter(c->c.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    // UC9 view by state
    public List<Contact> viewByState(String state){

        return repo.getAddressBooks().values()
                .stream()
                .flatMap(b->b.getContacts().stream())
                .filter(c->c.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    // UC10 count by city
    public long countByCity(String city){

        return repo.getAddressBooks().values()
                .stream()
                .flatMap(b->b.getContacts().stream())
                .filter(c->c.getCity().equalsIgnoreCase(city))
                .count();
    }

    // UC11 sort by name
    public List<Contact> sortByName(String bookName){

        AddressBook book = repo.getAddressBook(bookName);

        return book.getContacts()
                .stream()
                .sorted(Comparator.comparing(Contact::getFirstName))
                .collect(Collectors.toList());
    }

    // UC12 sort by city
    public List<Contact> sortByCity(String bookName){

        AddressBook book = repo.getAddressBook(bookName);

        return book.getContacts()
                .stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .collect(Collectors.toList());
    }

	  // UC5 - Show all contacts in Address Book
	public List<Contact> showAll(String bookName){

	    AddressBook book = repo.getAddressBook(bookName);

	    if(book == null){
	        throw new RuntimeException("AddressBook not found");
	    }

	    return book.getContacts();
	}

//	public Iterable<Contact> showAll(String bookName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}