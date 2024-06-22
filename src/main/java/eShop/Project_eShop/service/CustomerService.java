package eShop.Project_eShop.service;

import eShop.Project_eShop.dto.CustomerRequest;
import eShop.Project_eShop.dto.CustomerResponse;
import eShop.Project_eShop.model.Customer;

import java.util.List;


public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    Customer updateCustomer(CustomerRequest customerRequest, Long id);

    void deleteCustomer(Long id);
}
