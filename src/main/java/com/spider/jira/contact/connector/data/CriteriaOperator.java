package com.spider.jira.contact.connector.data;

public enum CriteriaOperator {
	EQUALS ("="), LIKE ("~"), NOTEQUAL ("!="), NOTLIKE ("!~"),
	LESSTHAN ("<"), GREATERTHAN (">");
	
	private final String value;
	CriteriaOperator (String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
	public static CriteriaOperator parse(String criteriaOperatorString) throws Exception {
		try {
			CriteriaOperator criteriaOperator = CriteriaOperator.valueOf(criteriaOperatorString.toUpperCase());
			return criteriaOperator;
		} catch (IllegalArgumentException e) {
			// ignore try the below
		}
		CriteriaOperator[] criteriaOperators = CriteriaOperator.values();
		for (CriteriaOperator criteriaOperator : criteriaOperators) {
			if (criteriaOperator.getValue().equals(criteriaOperatorString)) {
				return criteriaOperator;
			}
		}
		throw new Exception("Invalid operator string in provided Criteria: "+criteriaOperatorString);
	}
	
}
