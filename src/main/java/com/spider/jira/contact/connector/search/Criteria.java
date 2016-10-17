package com.spider.jira.contact.connector.search;

import com.spider.jira.contact.connector.data.Country;
import com.spider.jira.contact.connector.data.CriteriaOperator;
import com.spider.jira.contact.connector.data.FieldName;
import com.spider.jira.contact.connector.iface.Contact;
import com.spider.jira.contact.connector.util.ConnectorConstants;

public class Criteria implements ConnectorConstants {

	private FieldName fieldName;
	private CriteriaOperator operator;
	private String searchTerm;
	
	public Criteria(FieldName fieldName, CriteriaOperator operator, String searchTerm) {
		this.fieldName = fieldName;
		this.operator = operator;
		this.searchTerm = searchTerm;
	}
	
	public Criteria(String criteriaString) throws Exception {
		Criteria tempCriteria = parse(criteriaString);
		this.fieldName = tempCriteria.fieldName;
		this.operator = tempCriteria.operator;
		this.searchTerm = tempCriteria.searchTerm;
	}
	
	public String toString() {
		return "Criteria="+fieldName+operator+searchTerm;
	}
	
	public static Criteria parse(String criteriaTerm) throws Exception {
		try {
			String[] criteriaParts = criteriaTerm.split(" ");
			if (criteriaParts.length == 3) {
				FieldName criteriaFieldName = FieldName.valueOf(criteriaParts[0].trim().toUpperCase());
				CriteriaOperator criteriaOperator = CriteriaOperator.parse(criteriaParts[1]);
				String criteriaSearchTerm = criteriaParts[2].trim();
				if (criteriaSearchTerm.equalsIgnoreCase("NULL")) {
					criteriaSearchTerm = "";
				}
				
				Criteria criteria = new Criteria(criteriaFieldName, criteriaOperator, criteriaSearchTerm);
				return criteria;
			} else {
				throw new Exception("Error parsing given criteria (Expected 3 terms). "+criteriaTerm);
			}
		} catch (Exception e) {
			throw new Exception("Error parsing given criteria. "+criteriaTerm,e);
		}
	}
	
	public boolean evaluate(Contact contact) throws Exception {
		if (searchTerm == null) {
			return false;
		}
		String currentValue = null;
		switch (fieldName) {
			case ID :
				currentValue = contact.getId();
				break;
			case NAME :
				currentValue = contact.getName();
				break;
			case EMAIL :
				currentValue = contact.getEmail();
				break;
			case DOB :
				currentValue = DATEFORMAT_DATE.format(contact.getDob());
				break;
			case LASTUPDATED :
				currentValue = DATEFORMAT_DATE.format(contact.getLastUpdated());
				break;
			case CITY :
				currentValue = contact.getAddress().getSuburb();
				break;
			case STATE :
				currentValue = contact.getAddress().getState();
				break;
			case COUNTRY :
				Country country = contact.getAddress().getCountry();
				if (country != null) {
					currentValue = country.toString();
				}
				break;
		}
		if (currentValue == null) {
			currentValue = "";
		}
		switch (operator) {
			case EQUALS :
				if (currentValue.equals(searchTerm)) {
					return true;
				}
				break;
			case LIKE :
				if (currentValue.toLowerCase().contains(searchTerm.toLowerCase())) {
					return true;
				}
				break;
			case NOTEQUAL :
				if (!currentValue.equals(searchTerm)) {
					return true;
				}
				break;
			case NOTLIKE :
				// TODO
				break;
			case LESSTHAN :
				// TODO
				break;
			case GREATERTHAN :
				// TODO
				break;
		}
		return false;
	}

	public FieldName getFieldName() {
		return fieldName;
	}

	public void setFieldName(FieldName fieldName) {
		this.fieldName = fieldName;
	}

	public CriteriaOperator getOperator() {
		return operator;
	}

	public void setOperator(CriteriaOperator operator) {
		this.operator = operator;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
