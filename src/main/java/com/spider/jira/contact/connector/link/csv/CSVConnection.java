package com.spider.jira.contact.connector.link.csv;

import java.io.File;

import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.exception.ConnectionException;
import com.spider.jira.contact.connector.iface.ContactConnection;

public class CSVConnection extends BaseContactInterfacePresent<File,String> implements ContactConnection<File,String> {

	public CSVConnection(BaseContactInterface<File,String> contactInterface) {
		super(contactInterface);
	}

	@Override
	public File getConnection() throws ConnectionException {
		File file = null;
		try {
			String folderPath = "C:/Users/Vittor/Documents/My Dropbox/David/Work/Code/ContactConnector/src/test/resources/";
			String filename = folderPath+"test-contacts.csv";
			file = new File(filename);
			if (!file.exists()) {
				throw new ConnectionException("Input file cannot be found. Filename="+filename);
			}
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
		return file;
	}

}
