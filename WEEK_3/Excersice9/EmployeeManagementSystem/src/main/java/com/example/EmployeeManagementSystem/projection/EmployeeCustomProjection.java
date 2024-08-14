package com.example.EmployeeManagementSystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeCustomProjection {
    
    String getName();

    @Value("#{target.department.name}")
    String getDepartmentName();

    @Value("#{target.name + ' (' + target.email + ')'}")
    String getFullNameWithEmail();
}
