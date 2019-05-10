package com.dev.rest.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.beans.Employee;

/**
 * Handles requests for the Employee service.
 */
@RestController
public class EmployeeController {
	
	//Map to store employees, ideally we should use database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	@RequestMapping(value = "/rest/emp/dummy", method = RequestMethod.GET)
	public Employee getDummyEmployee() {
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setEmail("emp@mail.com");
		empData.put(9999, emp);
		return emp;
	}
	
	@RequestMapping(value = "/rest/emp/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable("id") int empId) {
		return empData.get(empId);
	}
	
	@RequestMapping(value = "/rest/emps", method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = "/rest/emp/create", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee emp) {
		empData.put(emp.getId(), emp);
		return emp;
	}
	
	@RequestMapping(value = "/rest/emp/delete/{id}", method = RequestMethod.PUT)
	public Employee deleteEmployee(@PathVariable("id") int empId) {
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
}