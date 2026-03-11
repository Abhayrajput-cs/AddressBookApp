package com.example.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Contact;

@Service
public class ContactService {

    private List<Contact> contactList = new ArrayList<>();

    public ContactService() 
    {
    	contactList.add(new Contact(1,"Abhay","Singh","Civil Lines", "Prayagraj","UP","211001","9876543210","abhay@gmail.com"));
    	contactList.add(new Contact(2,"Abhishek","Sharma","MAthura","Lines","UPP","281406","8076538928","abc@gmail.com"));
        
    }
    public List<Contact> getAllContacts() {
        return contactList;
    }

    public Contact addContact(Contact contact) {
        contactList.add(contact);
        return contact;
    }
}