package Project.eShop.service.impl;

import Project.eShop.advice.exception.RecordNotFoundException;
import Project.eShop.converter.CustomerConverter;
import Project.eShop.dto.CustomerRequest;
import Project.eShop.dto.CustomerResponse;
import Project.eShop.model.Customer;
import Project.eShop.repository.CustomerRepository;
import Project.eShop.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerConverter customerConverter;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerConverter customerConverter, CustomerRepository customerRepository) {
        this.customerConverter = customerConverter;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerConverter.toCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponse customerResponse = new CustomerResponse();

        BeanUtils.copyProperties(savedCustomer, customerResponse);
        System.out.println(HttpStatus.CREATED);
        return customerResponse;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Customer with id %s not exist", id)));
        CustomerResponse customerResponse = new CustomerResponse();

        BeanUtils.copyProperties(customer, customerResponse);
        System.out.println(HttpStatus.FOUND);
        return customerResponse;
    }

    @Override
    public Customer updateCustomer(CustomerRequest customerRequest, Long id) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Customer with id %s not exist", id)));

        existingCustomer.setFirstName(customerRequest.getFirstName());
        existingCustomer.setLastName(customerRequest.getLastName());
        existingCustomer.setEmail(customerRequest.getEmail());
        //existingCustomer.setAddress(customerRequest.getAddress());

        System.out.println(HttpStatus.ACCEPTED);
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Unable to delete, customer with id %s not exist", id)));

        System.out.println(HttpStatus.OK);
        customerRepository.deleteById(id);
    }
}
