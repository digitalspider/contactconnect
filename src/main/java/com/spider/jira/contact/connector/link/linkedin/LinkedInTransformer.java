package com.spider.jira.contact.connector.link.linkedin;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Person;
import com.spider.jira.contact.connector.base.BaseContact;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactTransformer;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.exception.ContactTransformException;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;

public class LinkedInTransformer extends BaseContactTransformer<LinkedInApiClient,Person> {

	public LinkedInTransformer(BaseContactInterface<LinkedInApiClient,Person> contactInterface) {
		super(contactInterface);
	}

	@Override
	public Contact convertToContact(Person person) throws ContactTransformException{
		System.out.println(person);
		ContactFieldMapper<LinkedInApiClient,Person> contactFieldMapper = contactInterface.getContactFieldMapper();
		Contact contact = new BaseContact();
		try {
			contact.setId(person.getId());
			contactFieldMapper.mapField(contact,FieldName.NAME,person.getName());
			contactFieldMapper.mapField(contact,FieldName.USERNAME,person.getUsername());
			contactFieldMapper.mapField(contact,FieldName.LINK,person.getLink());
			contactFieldMapper.mapField(contact,FieldName.GENDER,person.getGender());
			contactFieldMapper.mapField(contact,FieldName.EMAIL,person.getEmail());
			contactFieldMapper.mapField(contact,FieldName.LASTUPDATED,person.getUpdatedTime());
			contactFieldMapper.mapField(contact,FieldName.DOB,person.getBirthdayAsDate());
			if (person.getLocation() != null) {
				contactFieldMapper.mapField(contact,FieldName.ADDRESS,person.getLocation().getName());
			}
			contactFieldMapper.mapField(contact,FieldName.WEBSITE,person.getWebsite());
			contactFieldMapper.mapField(contact,FieldName.RELATIONSHIPSTATUS,person.getRelationshipStatus());
			contactFieldMapper.mapField(contact,FieldName.RELIGION,person.getReligion());
		} catch (Exception e) {
			throw new ContactTransformException(e);
		}
//		System.out.println(contact);
		return contact;
	}

	@Override
	public Person convertToObject(Contact contact) throws ContactTransformException {
		System.out.println(contact);
		System.err.println("METHOD NOT YET IMPLEMENTED");
		return null;
	}

}
