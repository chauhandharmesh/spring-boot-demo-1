package com.dc.springbootapp1.controller;

import com.dc.springbootapp1.common.dto.Employee;
import com.dc.springbootapp1.service.IEmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeRestController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("{employeeId}")
    public Employee getEmployeeById(@PathVariable(required = true) String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("register")
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Error occurred while employee registration.");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        employeeService.registerEmployee(new com.dc.springbootapp1.common.domain.Employee(employee));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("retrieve-all-employee")
    public List<Employee> fetchAllEmployee() {
        return employeeService.fetchAllEmployee();
    }

    @PostMapping("register-by-jms")
    public ResponseEntity<?> sendQueueMessage(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Error occurred while employee registration via jms.");
            bindingResult.getAllErrors().stream().forEach(error -> LOGGER.info(error.toString()));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        employeeService.sendMessage(employee);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
