package Project.eShop.service;

import Project.eShop.dto.CustomerRequest;
import Project.eShop.dto.CustomerResponse;
import Project.eShop.model.Customer;

import java.util.List;


public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    Customer updateCustomer(CustomerRequest customerRequest, Long id);

    void deleteCustomer(Long id);
}
