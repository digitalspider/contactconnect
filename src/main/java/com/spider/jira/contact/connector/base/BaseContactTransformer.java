package com.spider.jira.contact.connector.base;

import java.util.ArrayList;
import java.util.List;

import com.spider.jira.contact.connector.exception.ContactTransformException;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactTransformer;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public abstract class BaseContactTransformer<ConnectionType,RawObjectType> extends BaseContactInterfacePresent<ConnectionType,RawObjectType> implements ContactTransformer<ConnectionType,RawObjectType>, ConnectorConstants {

	public BaseContactTransformer(BaseContactInterface<ConnectionType,RawObjectType> contactInterface) {
		super(contactInterface);
	}

	public abstract Contact convertToContact(RawObjectType object) throws ContactTransformException; 
	
	public abstract RawObjectType convertToObject(Contact contact) throws ContactTransformException;
	
	public List<Contact> convertToContacts(List<RawObjectType> objects) throws ContactTransformException {
		List<Contact> contacts = new ArrayList<Contact>();
		for (RawObjectType object : objects) {
			Contact contact = convertToContact(object); 
			contacts.add(contact);
		}
		return contacts;
	}
	
	public List<RawObjectType> convertToObjects(List<Contact> contacts) throws ContactTransformException {
		List<RawObjectType> objects = new ArrayList<RawObjectType>();
		for (Contact contact : contacts) {
			RawObjectType object = convertToObject(contact); 
			objects.add(object);
		}
		return objects;	
	}

}
