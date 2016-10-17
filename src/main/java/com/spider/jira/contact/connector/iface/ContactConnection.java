package com.spider.jira.contact.connector.iface;

import com.spider.jira.contact.connector.exception.ConnectionException;

public interface ContactConnection<ConnectionType,RawObjectType> extends ContactInterfacePresent<ConnectionType,RawObjectType> {

	public ConnectionType getConnection() throws ConnectionException;

}

