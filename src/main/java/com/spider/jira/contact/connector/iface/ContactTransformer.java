package com.spider.jira.contact.connector.iface;

import java.util.List;

import com.spider.jira.contact.connector.exception.ContactTransformException;

public interface ContactTransformer<ConnectionType,RawObjectType> extends ContactInterfacePresent<ConnectionType,RawObjectType>  {

	public Contact convertToContact(RawObjectType object) throws ContactTransformException;
	public RawObjectType convertToObject(Contact contact) throws ContactTransformException;
	
	public List<Contact> convertToContacts(List<RawObjectType> objects) throws ContactTransformException;
	public List<RawObjectType> convertToObjects(List<Contact> contacts) throws ContactTransformException;
}

