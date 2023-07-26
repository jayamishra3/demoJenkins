package com.learn.rest.service;

// Tests for com.learn.rest.service.ContactServiceImpl class using mockito and junit5
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.learn.rest.entity.Contact;
import com.learn.rest.exception.ContactExistException;
import com.learn.rest.repository.ContactRepository;

public class ContactServiceImplTest {

    @Test
    public void testCreateContact() {
        // Arrange
        ContactRepository contactRepository = mock(ContactRepository.class);
        ContactServiceImpl contactServiceImpl = new ContactServiceImpl(contactRepository);
        Contact contact = new Contact();
        contact.setEmail("test@tes.com");
        contact.setFirstName("Test");
        contact.setLastName("Test");
        contact.setContactGroup("Test");
        when(contactRepository.findByEmail(anyString())).thenReturn(null);
        when(contactRepository.save(any())).thenReturn(contact);

        // Act
        Contact result = contactServiceImpl.createContact(contact);

        // Assert
        assertEquals(contact, result);
        verify(contactRepository).findByEmail(anyString());
        verify(contactRepository).save(any());
    }

    @Test
    public void testCreateContactThrowsContactExistException() {
        // Arrange
        ContactRepository contactRepository = mock(ContactRepository.class);
        ContactServiceImpl contactServiceImpl = new ContactServiceImpl(contactRepository);
        Contact contact = new Contact();
        contact.setEmail("test@test.com");
        contact.setFirstName("Test");
        contact.setLastName("Test");
        contact.setContactGroup("Test");
        when(contactRepository.findByEmail(anyString())).thenReturn(contact);

        // Act
        // Assert
        assertThrows(ContactExistException.class, () -> contactServiceImpl.createContact(contact));
        verify(contactRepository).findByEmail(anyString());
    }

}
