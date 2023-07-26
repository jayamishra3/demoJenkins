package com.learn.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.learn.rest.dto.ContactDto;
import com.learn.rest.entity.Contact;

@Mapper
public interface ContactMapper {

    Contact contactDtoToContact(ContactDto contactDto);

    ContactDto contactToContactDto(Contact contact);

    List<ContactDto> contactsToContactDtos(List<Contact> contacts);

    List<Contact> contactDtosToContacts(List<ContactDto> contactDtos);

}