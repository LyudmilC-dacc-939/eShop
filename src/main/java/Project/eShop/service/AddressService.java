package Project.eShop.service;

import Project.eShop.dto.AddressRequest;
import Project.eShop.dto.AddressResponse;
import Project.eShop.model.Address;

import java.util.List;

public interface AddressService {

    AddressResponse createAddress(AddressRequest addressRequest);

    List<Address> getAllAddresses();

    AddressResponse getAddressById(Long id);

    Address updateAddress(AddressRequest addressRequest, Long id);

    void deleteAddress(Long id);
}
