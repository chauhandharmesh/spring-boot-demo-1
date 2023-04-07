package com.dc.springbootapp1;

import com.dc.springbootapp1.common.domain.Employee;
import com.dc.springbootapp1.repository.IEmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBootApp1Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApp1Application.class);
    @Autowired
    private IEmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long count = employeeRepository.count();
        LOGGER.info("Load data into mongo collection, Current Employee collection size: {}", count);
        if (count == 0) {
            Employee employee = new Employee("1", "Dharmesh", "Computer", BigDecimal.valueOf(2400000), 9, LocalDateTime.now());
            employeeRepository.save(employee);
        }
    }
}
