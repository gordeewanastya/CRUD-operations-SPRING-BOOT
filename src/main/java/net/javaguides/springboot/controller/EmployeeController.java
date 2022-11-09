package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}

	//created method handler, which handles the request /showNewEmployeeForm
	//this request comes from button "Add Employee" (from index.html page)
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model){
		//create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";

	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		//save employee to database
		employeeService.saveEmployee(employee);
		//here we just redirect to the home page
		return "redirect:/";

	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
		//get employee from the service
		Employee employee = employeeService.getEmployeeById(id);

		//set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id){

		//call deleteEmployee method from the service

		employeeService.deleteEmployeeById(id);

		return "redirect:/";
	}

}
