package com.spider.jira.contact.connector.base;

import com.spider.jira.contact.connector.iface.ContactInterfacePresent;

public class BaseContactInterfacePresent<ConnectionType,RawObjectType> implements ContactInterfacePresent<ConnectionType,RawObjectType> {
	
	protected BaseContactInterface<ConnectionType,RawObjectType> contactInterface;
	
	public BaseContactInterfacePresent(BaseContactInterface<ConnectionType,RawObjectType> contactInterface) {
		this.contactInterface = contactInterface;
	}

	public BaseContactInterface<ConnectionType,RawObjectType> getContactInterface() {
		return contactInterface;
	}

}
