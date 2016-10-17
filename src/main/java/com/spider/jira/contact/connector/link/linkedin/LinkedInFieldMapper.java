package com.spider.jira.contact.connector.link.linkedin;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Person;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.data.Country;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public class LinkedInFieldMapper extends BaseContactInterfacePresent<LinkedInApiClient,Person> implements ContactFieldMapper<LinkedInApiClient,Person>, ConnectorConstants {

	public LinkedInFieldMapper(BaseContactInterface<LinkedInApiClient,Person> contactInterface) {
		super(contactInterface);
	}

	@Override
	public void init(String data) throws Exception {
		System.err.println("init() METHOD NOT YET IMPLEMENTED");
	}

	@Override
	public void mapField(Contact contact, FieldName fieldName, Object value)
			throws Exception {

		if (value != null && StringUtils.isNotBlank(value.toString())) {
			if (fieldName.equals(FieldName.NAME)) {
				contact.setName(value.toString());
			}
			else if (fieldName.equals(FieldName.EMAIL)) {
				contact.setEmail(value.toString());
			}
			else if (fieldName.equals(FieldName.DOB)) {
				if (value instanceof Date) {
					contact.setDob((Date)value);
				}
				else {
					String dateString = value.toString();
					contact.setDob(DATEFORMAT_FACEBOOK_DATETIME.parse(dateString));
				}
			}
			else if (fieldName.equals(FieldName.LASTUPDATED)) {
				if (value instanceof Date) {
					contact.setLastUpdated((Date)value);
				}
				else {
					String dateString = value.toString();
					contact.setLastUpdated(DATEFORMAT_FACEBOOK_DATETIME.parse(dateString));
				}
			}
			else if (fieldName.equals(FieldName.DOB)) {
				if (value instanceof Date) {
					contact.setDob((Date)value);
				} else {
					String birthdayString = value.toString();
					if (birthdayString.lastIndexOf("/") != 6) {
						birthdayString += "/1900";
					}
					contact.setDob(DATEFORMAT_DATE_US.parse(birthdayString));
				}
			}
			else if (fieldName.equals(FieldName.ADDRESS)) {
				String[] locationGroup = value.toString().split(",");
				switch (locationGroup.length) {
					case 3:
						contact.getAddress().setState(locationGroup[0].trim());
						contact.getAddress().setSuburb(locationGroup[1].trim());
						contact.getAddress().setCountry(Country.valueOf(locationGroup[1].trim().toUpperCase()));
						break;
					case 2:
						String suburb = locationGroup[0].trim();
						if (suburb.equalsIgnoreCase("Sydney")) {
							contact.getAddress().setState("NSW");
						}
						contact.getAddress().setSuburb(suburb);
						contact.getAddress().setCountry(Country.valueOf(locationGroup[1].trim().toUpperCase()));
						break;
					default:
						contact.getAddress().setCountry(Country.valueOf(locationGroup[1].trim().toUpperCase()));
						break;
				}
			}
			else if (fieldName.equals(FieldName.WEBSITE)) {
				String website = value.toString();
				website = website.replaceAll("\r\n\r\n\r\n", "?");
				website = website.replaceAll("\r\n\r\n", "?");
				website = website.replaceAll("\r\n", "?");
				contact.getData().put(fieldName.toString(),website);
			}
			else {
				contact.getData().put(fieldName.toString(),value);
			}
		}
	}

}
