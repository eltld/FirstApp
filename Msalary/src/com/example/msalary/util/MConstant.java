package com.example.msalary.util;

public class MConstant {
//	115.29.45.6
	public final static String URL_HOME_PATH = "http://192.168.1.111:8080/Web/servlet/";
	
	public final static String URL_TEST = "Test";
	
	public final static String URL_SELECT_COMPANY = URL_HOME_PATH + "QueryCompanyServlet?";
	
	public final static String URL_SELECT_POSITION = URL_HOME_PATH + "QueryJobServlet?";
	
	/**��˾����--�ù�˾�ĸ�λ�б�*/
	public final static String URL_COMPANY_DETAIL = URL_HOME_PATH + "CompanyDetailServlet?";
	/**��˾н������*/
	public final static String URL_COMPANY_SALARY_RANK = URL_HOME_PATH + "CompanySalaryRankServlet";
	
	public final static String URL_COMPANY_COMMENT =  URL_HOME_PATH +"CompanyCommentsServlet?";
	
	public final static String URL_COMPANY_COMMENT_NEW = URL_HOME_PATH + "NewCommentServlet?";
	/***/
	public final static String URL_COMPANYS_OF_JOB = URL_HOME_PATH + "CompanysOfJobServlet?";
	/**��λ����*/
	public final static String URL_JOB_DETAIL = URL_HOME_PATH + "JobDetailServlet?";
	
}
