package com.dc.springbootapp1;

import com.dc.springbootapp1.controller.EmployeeRestController;
import com.dc.springbootapp1.common.dto.Employee;
import com.dc.springbootapp1.repository.IEmployeeRepository;
import com.dc.springbootapp1.service.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(controllers = {EmployeeRestController.class})
class SpringBootApp1ApplicationTests {

    @MockBean
    IEmployeeService employeeService;

    @MockBean
    IEmployeeRepository employeeRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        Mockito.when(employeeService.getEmployeeById(Mockito.any())).thenReturn(new Employee());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employee/1")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
