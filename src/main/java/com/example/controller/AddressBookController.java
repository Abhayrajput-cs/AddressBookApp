package com.example.controller;

import com.example.model.Contact;
import com.example.service.AddressBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    // UC6 create addressbook
    @PostMapping("/create/{name}")
    public String createBook(@PathVariable String name){
        service.createAddressBook(name);
        return "AddressBook Created";
    }

    // UC2 add contact
    @PostMapping("/{bookName}/contact")
    public Contact addContact(@PathVariable String bookName,
                              @RequestBody Contact contact){

        return service.addContact(bookName,contact);
    }

    // UC3 edit
    @PutMapping("/{bookName}/{firstName}")
    public Contact editContact(@PathVariable String bookName,
                               @PathVariable String firstName,
                               @RequestBody Contact contact){

        return service.editContact(bookName,firstName,contact);
    }

    // UC4 delete
    @DeleteMapping("/{bookName}/{firstName}")
    public String deleteContact(@PathVariable String bookName,
                                @PathVariable String firstName){

        service.deleteContact(bookName,firstName);
        return "Deleted";
    }
    @GetMapping("/{bookName}/contacts")
    public List<Contact> getAllContacts(@PathVariable String bookName) {
        return service.showAll(bookName);
    }

    // UC8 search by city
    @GetMapping("/city/{city}")
    public List<Contact> searchCity(@PathVariable String city){
        return service.searchByCity(city);
    }

    // UC10 count by city
    @GetMapping("/count/{city}")
    public long countCity(@PathVariable String city){
        return service.countByCity(city);
    }

    // UC11 sort by name
    @GetMapping("/{bookName}/sort/name")
    public List<Contact> sortName(@PathVariable String bookName){
        return service.sortByName(bookName);
    }

    // UC12 sort by city
    @GetMapping("/{bookName}/sort/city")
    public List<Contact> sortCity(@PathVariable String bookName){
        return service.sortByCity(bookName);
    }
}