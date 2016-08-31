package com.covenant.app.dto;

public class EmployeeDto {
String name;
String salary;
String joiningDate;
String ssn;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public EmployeeDto(String name, String salary, String joiningDate, String ssn) {
	super();
	this.name = name;
	this.salary = salary;
	this.joiningDate = joiningDate;
	this.ssn = ssn;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}
public String getJoiningDate() {
	return joiningDate;
}
public void setJoiningDate(String joiningDate) {
	this.joiningDate = joiningDate;
}
public String getSsn() {
	return ssn;
}
public void setSsn(String ssn) {
	this.ssn = ssn;
}
public EmployeeDto() {
	super();
}

}
