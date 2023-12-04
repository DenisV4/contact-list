package com.example.contactlist.service;

import com.example.contactlist.model.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAll();

    Contact getById(String id);

    void create(Contact contact);

    void edit(Contact contact);

    void removeById(String id);
}
