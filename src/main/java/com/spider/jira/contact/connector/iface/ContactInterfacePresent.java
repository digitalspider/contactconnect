package com.spider.jira.contact.connector.iface;

import com.spider.jira.contact.connector.base.BaseContactInterface;

public interface ContactInterfacePresent<ConnectionType,RawObjectType> {

	public BaseContactInterface<ConnectionType,RawObjectType> getContactInterface();

}
