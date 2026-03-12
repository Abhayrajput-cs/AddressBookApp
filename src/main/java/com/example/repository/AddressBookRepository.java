package com.example.repository;

import com.example.model.AddressBook;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AddressBookRepository {

    // UC6 Dictionary of AddressBooks
    private Map<String, AddressBook> addressBooks = new HashMap<>();

    public Map<String, AddressBook> getAddressBooks() {
        return addressBooks;
    }

    public AddressBook getAddressBook(String name) {
        return addressBooks.get(name);
    }

    public void addAddressBook(String name) {
        addressBooks.put(name,new AddressBook(name));
    }
}