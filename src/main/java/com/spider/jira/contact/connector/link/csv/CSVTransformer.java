package com.spider.jira.contact.connector.link.csv;

import java.io.File;

import com.spider.jira.contact.connector.base.BaseContact;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactTransformer;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.exception.ContactTransformException;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;

public class CSVTransformer extends BaseContactTransformer<File,String> {

	public CSVTransformer(BaseContactInterface<File,String> contactInterface) {
		super(contactInterface);
	}

	@Override
	public Contact convertToContact(String object) throws ContactTransformException{
//		System.out.println(object);
		ContactFieldMapper<File, String> contactFieldMapper = contactInterface.getContactFieldMapper();
		Contact contact = new BaseContact();
		try {
			String[] lineParts = object.split(DELIM_CSV);
			if (lineParts.length > 1) {
				contact.setId(lineParts[0]);
			}
			if (lineParts.length > 2) {
				contactFieldMapper.mapField(contact,FieldName.NAME,lineParts[1]);
			}
			if (lineParts.length > 3) {
				contactFieldMapper.mapField(contact,FieldName.USERNAME,lineParts[2]);
			}
			if (lineParts.length > 4) {
				contactFieldMapper.mapField(contact,FieldName.LINK,lineParts[3]);
			}
			if (lineParts.length > 5) {
				contactFieldMapper.mapField(contact,FieldName.GENDER,lineParts[4]);
			}
			if (lineParts.length > 6) {
				contactFieldMapper.mapField(contact,FieldName.EMAIL,lineParts[5]);
			}
			if (lineParts.length > 7) {
				contactFieldMapper.mapField(contact,FieldName.LASTUPDATED,lineParts[6]);
			}
			if (lineParts.length > 8) {
				contactFieldMapper.mapField(contact,FieldName.DOB,lineParts[7]);
			}
			if (lineParts.length > 9) {
				contactFieldMapper.mapField(contact,FieldName.CITY,lineParts[8]);
			}
			if (lineParts.length > 10) {
				contactFieldMapper.mapField(contact,FieldName.STATE,lineParts[9]);
			}
			if (lineParts.length > 11) {
				contactFieldMapper.mapField(contact,FieldName.COUNTRY,lineParts[10]);
			}
			if (lineParts.length > 12) {
				contactFieldMapper.mapField(contact,FieldName.WEBSITE,lineParts[11]);
			}
			if (lineParts.length > 13) {
				contactFieldMapper.mapField(contact,FieldName.RELATIONSHIPSTATUS,lineParts[12]);
			}
			if (lineParts.length > 14) {
				contactFieldMapper.mapField(contact,FieldName.RELIGION,lineParts[13]);
			}
		} catch (Exception e) {
			throw new ContactTransformException(e);
		}
//		System.out.println(contact);
		return contact;
	}

	@Override
	public String convertToObject(Contact contact) throws ContactTransformException {
		System.out.println(contact);
		System.err.println("METHOD NOT YET IMPLEMENTED");
		return null;
	}

}
