package com.example.msalary.entity;


public class JobEntity {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(String startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public int getCompanyCount() {
		return companyCount;
	}

	public void setCompanyCount(int companyCount) {
		this.companyCount = companyCount;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	private int id;
	
	private String name;

	private int companyId;
	/**�иø�λ�Ĺ�˾������*/
	private int companyCount;
	
	private int salary;
	/**����ʱ��*/
	private String createDate;
	/**�û�id����ʱû��*/
	private int userId;
	/**��ʼ����ʱ��*/
	private String startWorkDate;
	
	
}
