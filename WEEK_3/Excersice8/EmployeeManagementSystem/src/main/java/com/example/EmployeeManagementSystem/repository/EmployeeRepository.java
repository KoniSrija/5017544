package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.datatransferobj.EmployeeDTO;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.projection.EmployeeCustomProjection;
import com.example.EmployeeManagementSystem.projection.EmployeeNameAndEmailProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByDepartmentName(String departmentName);

    List<Employee> findByEmailEndingWith(String domain);

    List<Employee> findByNameAndDepartmentName(String name, String departmentName);
    
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesInDepartment(@Param("departmentName") String departmentName);

    @Query(value = "SELECT * FROM Employee e WHERE e.email LIKE %:email%", nativeQuery = true)
    List<Employee> findByEmailContaining(@Param("email") String email);
    
    @Query(name = "Employee.findByDepartmentName")
    List<Employee> findByNamedQueryAndDepartmentName(@Param("departmentName") String departmentName);

    @Query(name = "Employee.findByEmailDomain")
    List<Employee> findByNamedQueryAndEmailDomain(@Param("domain") String domain);
    
    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);
    
    Page<Employee> findByName(String name, Pageable pageable);

    List<EmployeeNameAndEmailProjection> findAllProjectedBy();
    @Query("SELECT new com.example.EmployeeManagementSystem.dto.EmployeeDTO(e.name, e.department.name) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();
    
    List<EmployeeCustomProjection> findAllCustomProjectedBy();
}
