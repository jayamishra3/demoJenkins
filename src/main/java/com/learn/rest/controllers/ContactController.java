package com.learn.rest.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.rest.dto.ContactDto;
import com.learn.rest.entity.Contact;
import com.learn.rest.exception.ContactNotFoundException;
import com.learn.rest.mapper.ContactMapper;
import com.learn.rest.service.ContactService;

@RestController
@RequestMapping("/api")
public class ContactController {

  
    private ContactService contactService;

    private ContactMapper contactMapper;

    @Autowired
    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    
    @GetMapping("/info")
    public String getInfo() throws UnknownHostException {
        final String lineSeparator = System.lineSeparator();
        final InetAddress localHost = InetAddress.getLocalHost();
        final String hostName = localHost.getHostName();
        final String hostAddress = localHost.getHostAddress();
        StringBuilder info = new StringBuilder();
        info.append("----------------------------------").append(lineSeparator);
        info.append("Contacts API : ").append("Version ").append("V2").append(lineSeparator);
        info.append("Host : ").append(hostName).append(lineSeparator);
        info.append("IP : ").append(hostAddress).append(lineSeparator);
        info.append("----------------------------------").append(lineSeparator);
        return info.toString();
     }

    @GetMapping("/contacts")
    public List<ContactDto> getContacts() {
        return contactMapper.contactsToContactDtos(contactService.getAllContacts());
    }

    @GetMapping("/contacts/{contactId}")
    public ContactDto getContactById(@PathVariable Integer contactId) throws ContactNotFoundException {
        return contactMapper.contactToContactDto(contactService.getContactById(contactId));
    }

    @PostMapping("/contacts")
    public Contact addContact(@RequestBody ContactDto contact) {
        return contactService.createContact(contactMapper.contactDtoToContact(contact));
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<?> addContact(@PathVariable Integer contactId) throws ContactNotFoundException {
        contactService.deleteContact(contactId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/contacts/group/{group}")
    public List<ContactDto> getContactsByGroup(@PathVariable String group) {
        return contactMapper.contactsToContactDtos(contactService.getContactsByGroup(group));
    }

    @GetMapping("/contacts/firstName/{firstName}")
    public List<ContactDto> getContactsByFirstName(@PathVariable String firstName) {
        return contactMapper.contactsToContactDtos(contactService.getContactsByFirstName(firstName));
    }

    @GetMapping("/contacts/lastName/{lastName}")
    public List<ContactDto> getContactsByLastName(@PathVariable String lastName) {
        return contactMapper.contactsToContactDtos(contactService.getContactsByLastName(lastName));
    }

    @GetMapping("/contacts/email/{email}")
    public ContactDto getContactByEmail(@PathVariable String email) {
        return contactMapper.contactToContactDto(contactService.getContactByEmail(email));
    }
}
