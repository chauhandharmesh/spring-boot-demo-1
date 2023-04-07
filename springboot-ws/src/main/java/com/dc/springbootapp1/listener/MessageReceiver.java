package com.dc.springbootapp1.listener;

import com.dc.springbootapp1.common.dto.Employee;
import com.dc.springbootapp1.service.IEmployeeService;
import com.dc.springbootapp1.util.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private IEmployeeService employeeService;

    @RabbitListener(queues = AppConstant.QUEUE_NAME)
    public void listen(Employee employee) {
        LOGGER.info("Message Received: {}", employee);
        employeeService.registerEmployee(new com.dc.springbootapp1.common.domain.Employee(employee));
    }


}
