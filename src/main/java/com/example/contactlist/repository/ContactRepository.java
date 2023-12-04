package com.example.contactlist.repository;

import com.example.contactlist.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> findAll();

    Optional<Contact> findById(String id);

    Contact save(Contact contact);

    Optional<Contact> update(Contact contact);

    void deleteById(String id);
}
