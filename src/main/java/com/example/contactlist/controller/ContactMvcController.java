package com.example.contactlist.controller;

import com.example.contactlist.exception.ResourceNotFoundException;
import com.example.contactlist.model.Contact;
import com.example.contactlist.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactMvcController {
    private final ContactService contactService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("contacts", contactService.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("contact", Contact.builder().build());
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable String id, Model model) {
        var dstPage = "edit";
        try {
            var contact = contactService.getById(id);
            model.addAttribute("contact", contact);
        } catch (ResourceNotFoundException e) {
            dstPage = "redirect:/";
        }

        return dstPage;
    }

    @PostMapping("/create")
    public String createContact(@ModelAttribute Contact contact) {
        contactService.create(contact);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editContact(@ModelAttribute Contact contact) {
        contactService.edit(contact);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable String id) {
        contactService.removeById(id);
        return "redirect:/";
    }
}
