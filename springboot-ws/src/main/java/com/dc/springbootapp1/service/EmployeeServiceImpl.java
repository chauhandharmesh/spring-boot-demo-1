package com.dc.springbootapp1.service;

import com.dc.springbootapp1.common.dto.Employee;
import com.dc.springbootapp1.repository.IEmployeeRepository;
import com.dc.springbootapp1.util.AppConstant;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static Employee convertDomainObjToDtoObj(com.dc.springbootapp1.common.domain.Employee employee) {
        Employee dest = new Employee();
        try {
            PropertyUtils.copyProperties(dest, employee);
            return dest;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee getEmployeeById(String id) {
        return convertDomainObjToDtoObj(employeeRepository.findById(id).get());
    }

    @Override
    public void registerEmployee(com.dc.springbootapp1.common.domain.Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchAllEmployee() {
        return employeeRepository.findAll().stream().map(EmployeeServiceImpl::convertDomainObjToDtoObj).toList();
    }

    @Override
    public void sendMessage(Employee employee) {
        rabbitTemplate.convertAndSend(AppConstant.DIRECT_EXCHANGE_NAME,"routing.key.1", employee);
    }
}
