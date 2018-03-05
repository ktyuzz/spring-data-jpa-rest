package com.ecis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecis.domain.CustomerCode;
import com.ecis.repository.CustomerCodeRepository;

//@RepositoryRestController
//public class CustomerCodeController {
//    @Autowired
//    private CustomerCodeRepository                customerCodeRepository;
//    @Autowired
//    private PagedResourcesAssembler<CustomerCode> pagedAssembler;
//    // @RequestMapping(value = "/employees/search/all/search/all", method = RequestMethod.GET)
//    // public ResponseEntity<Resources<Resource<CustomerCode>>> getEmployees(CustomerCodeCriteria filterCriteria, Pageable pageable) {
//    // // EmployeeSpecification uses CriteriaAPI to form dynamic query with the fields from filterCriteria
//    // Specification<Employee> specification = new CustomerCodeSpecification(filterCriteria);
//    // Page<CustomerCode> employees = customerCodeRepository.findByLastnameAndFirstname(specification, pageable);
//    // return assembler.toResource(employees);
//    // }
//
////    @GetMapping(value = "/api/customerCodes/?{corpNm}_like=test")
////    public Iterable<CustomerCode> listAllCustomers() {
////        return customerCodeRepository.findBy{corpNm}AndFirstname();
////    }
//}
