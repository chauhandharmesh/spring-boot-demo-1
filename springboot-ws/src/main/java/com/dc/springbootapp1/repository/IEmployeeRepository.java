package com.dc.springbootapp1.repository;

import com.dc.springbootapp1.common.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends MongoRepository<Employee, String> {
}
