package com.sun.hair.utils;

public class MConstant {

	/**���ڵ���key*/
	public static final String KEY = "4073748977";
	/**���ڵ���*/
	public static final String SECRET = "a6b49517c78446c59d0302fd7551d118";
	
	private static final String URL_HOME = "http://api.dianping.com/v1/";
	
	/**�̻�*/
	public static final String URL_BUSINESS = URL_HOME +"business/find_businesses";
	
	/**����*/
	public static final String URL_TYPES = URL_HOME + "metadata/get_categories_with_businesses";
	
	/**�̼�����*/
	public static final String URL_BUSINESS_DETAIL = URL_HOME + "business/get_single_business";
	/**����*/
	public static final String URL_BUSINESS_REVIEW = URL_HOME + "review/get_recent_reviews";
	/**����*/
	public static final String URL_REGION = URL_HOME + "metadata/get_regions_with_businesses";
	
}
