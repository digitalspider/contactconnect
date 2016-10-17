package com.spider.jira.contact.connector.iface;

import java.util.List;

public interface ContactWriter<ConnectionType,RawObjectType> extends ContactInterfacePresent<ConnectionType,RawObjectType> {

	public void writeContacts(List<Contact> contacts) throws Exception;

}
