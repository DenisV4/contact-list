package com.example.contactlist.service;

import com.example.contactlist.exception.ResourceNotFoundException;
import com.example.contactlist.model.Contact;
import com.example.contactlist.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> {
                    var message = MessageFormat.format("Contact with id={0} not found", id);
                    return new ResourceNotFoundException(message);
                });
    }

    @Override
    public void create(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void edit(Contact contact) {
        var optionalContact = contactRepository.update(contact);
        if (optionalContact.isEmpty()) {
            var message = MessageFormat.format("Contact with id={0} not found", contact.getId());
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public void removeById(String id) {
        contactRepository.deleteById(id);
    }
}
