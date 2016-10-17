package com.spider.jira.contact.connector.link.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.spider.jira.contact.connector.base.BaseContact;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactTransformer;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.exception.ContactTransformException;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.iface.ContactFieldMapper;

public class FacebookTransformer extends BaseContactTransformer<FacebookClient,User> {

	public FacebookTransformer(BaseContactInterface<FacebookClient,User> contactInterface) {
		super(contactInterface);
	}

	@Override
	public Contact convertToContact(User user) throws ContactTransformException{
		System.out.println(user);
		ContactFieldMapper<FacebookClient,User> contactFieldMapper = contactInterface.getContactFieldMapper();
		Contact contact = new BaseContact();
		try {
			contact.setId(user.getId());
			contactFieldMapper.mapField(contact,FieldName.NAME,user.getName());
			contactFieldMapper.mapField(contact,FieldName.USERNAME,user.getUsername());
			contactFieldMapper.mapField(contact,FieldName.LINK,user.getLink());
			contactFieldMapper.mapField(contact,FieldName.GENDER,user.getGender());
			contactFieldMapper.mapField(contact,FieldName.EMAIL,user.getEmail());
			contactFieldMapper.mapField(contact,FieldName.LASTUPDATED,user.getUpdatedTime());
			contactFieldMapper.mapField(contact,FieldName.DOB,user.getBirthdayAsDate());
			if (user.getLocation() != null) {
				contactFieldMapper.mapField(contact,FieldName.ADDRESS,user.getLocation().getName());
			}
			contactFieldMapper.mapField(contact,FieldName.WEBSITE,user.getWebsite());
			contactFieldMapper.mapField(contact,FieldName.RELATIONSHIPSTATUS,user.getRelationshipStatus());
			contactFieldMapper.mapField(contact,FieldName.RELIGION,user.getReligion());
		} catch (Exception e) {
			throw new ContactTransformException(e);
		}
//		System.out.println(contact);
		return contact;
	}

	@Override
	public User convertToObject(Contact contact) throws ContactTransformException {
		System.out.println(contact);
		System.err.println("METHOD NOT YET IMPLEMENTED");
		return null;
	}

}
