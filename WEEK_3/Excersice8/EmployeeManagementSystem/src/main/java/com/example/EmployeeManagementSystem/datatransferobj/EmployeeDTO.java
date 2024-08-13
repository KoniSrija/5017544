package com.example.EmployeeManagementSystem.datatransferobj;

  
	public class EmployeeDTO {

	    private String name;
	    private String departmentName;

	    public EmployeeDTO(String name, String departmentName) {
	        this.name = name;
	        this.departmentName = departmentName;
	    }

	    // Getters and Setters

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDepartmentName() {
	        return departmentName;
	    }

	    public void setDepartmentName(String departmentName) {
	        this.departmentName = departmentName;
	    }
	}


