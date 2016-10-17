package com.spider.jira.contact.connector.link.csv;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.bean.Address;
import com.spider.jira.contact.connector.data.Country;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public class CSVFieldMapper extends BaseContactInterfacePresent<File,String> implements ContactFieldMapper<File,String>, ConnectorConstants {

	public CSVFieldMapper(BaseContactInterface<File,String> contactInterface) {
		super(contactInterface);
	}

	@Override
	public void init(String data) throws Exception {
		System.err.println("init() METHOD NOT YET IMPLEMENTED");
	}

	@Override
	public void mapField(Contact contact, FieldName fieldName, Object value)
			throws Exception {

		if (StringUtils.isNotBlank(value.toString())) {
			if (fieldName.equals(FieldName.NAME)) {
				contact.setName(value.toString());
			}
			else if (fieldName.equals(FieldName.EMAIL)) {
				contact.setEmail(value.toString());
			}
			else if (fieldName.equals(FieldName.DOB)) {
				contact.setDob(DATEFORMAT_DATE.parse(value.toString()));
			}
			else if (fieldName.equals(FieldName.LASTUPDATED)) {
				contact.setLastUpdated(DATEFORMAT_DATE.parse(value.toString()));
			}
			else if (fieldName.equals(FieldName.CITY)) {
				Address address = contact.getAddress();
				address.setSuburb(value.toString());
			}
			else if (fieldName.equals(FieldName.STATE)) {
				Address address = contact.getAddress();
				address.setState(value.toString());
			}
			else if (fieldName.equals(FieldName.COUNTRY)) {
				Address address = contact.getAddress();
				address.setCountry(Country.parse(value.toString()));
			}
			else {
				contact.getData().put(fieldName.toString(),value);
			}
		}
	}

}
