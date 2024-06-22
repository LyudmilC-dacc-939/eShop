package eShop.Project_eShop.controller;

import eShop.Project_eShop.dto.CustomerRequest;
import eShop.Project_eShop.dto.CustomerResponse;
import eShop.Project_eShop.model.Customer;
import eShop.Project_eShop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("eShop/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/create-customer")
    ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(customerRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/all-customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.FOUND);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest,
                                                           @PathVariable("id") Long id){
        customerService.updateCustomer(customerRequest, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomerById(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
