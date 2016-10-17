package com.spider.jira.contact.connector.link.csv;

import java.io.File;

import com.spider.jira.contact.connector.base.BaseContactInterface;

public class CSVInterface extends BaseContactInterface<File,String> {
	
	private static CSVInterface csvInterface;
	
	public static CSVInterface getInstance() {
		if (csvInterface == null) {
			csvInterface = new CSVInterface();
		}
		return csvInterface;
	}
	
	private CSVInterface() {
		setContactConnection(new CSVConnection(this));
		setContactReader(new CSVReader(this));
		setContactWriter(new CSVWriter(this));
		setContactSearcher(new CSVSearcher(this));
		setContactTransformer(new CSVTransformer(this));
		setContactFieldMapper(new CSVFieldMapper(this));
	}

}
