package Project.eShop.service.impl;

import Project.eShop.advice.exception.RecordNotFoundException;
import Project.eShop.converter.AddressConverter;
import Project.eShop.dto.AddressRequest;
import Project.eShop.dto.AddressResponse;
import Project.eShop.model.Address;
import Project.eShop.model.Customer;
import Project.eShop.repository.AddressRepository;
import Project.eShop.repository.CustomerRepository;
import Project.eShop.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AddressConverter addressConverter;
    private CustomerRepository customerRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              AddressConverter addressConverter,
                              CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        Optional<Customer> customer = customerRepository.findById(addressRequest.getCustomerId());
        Customer existingCustomer;

        if (customer.isEmpty()) {
            throw new RecordNotFoundException("Address not found. Invalid email request.");
        } else {
            log.info("Going to use existing customer with identifier: " + addressRequest.getCustomerId());
            existingCustomer = customer.get();
        }

        Address savedAddress = addressConverter.toAddress(addressRequest);
        AddressResponse addressResponse = new AddressResponse();
        savedAddress.setCustomer(existingCustomer);
        addressRepository.save(savedAddress);
        BeanUtils.copyProperties(savedAddress, addressResponse);
        return addressResponse;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public AddressResponse getAddressById(Long id) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Address with id %s not exist", id)));
        AddressResponse addressResponse = new AddressResponse();

        BeanUtils.copyProperties(existingAddress, addressResponse);
        return addressResponse;
    }

    @Override
    public Address updateAddress(AddressRequest addressRequest, Long id) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Unable to update, address with id %s not exist", id)));
        existingAddress.setCountry(addressRequest.getCountry());
        existingAddress.setCity(addressRequest.getCity());
        existingAddress.setStreet(addressRequest.getStreet());
        existingAddress.setPostalCode(addressRequest.getPostalCode());

        return addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Unable to delete, address with id %s not exist", id)));
        addressRepository.deleteById(id);
    }

}
