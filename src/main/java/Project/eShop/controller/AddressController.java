package Project.eShop.controller;

import Project.eShop.dto.AddressRequest;
import Project.eShop.dto.AddressResponse;
import Project.eShop.model.Address;
import Project.eShop.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/create")
    public ResponseEntity<AddressResponse> createAddress(@Valid @RequestBody AddressRequest addressRequest) {
        return new ResponseEntity<>(addressService.createAddress(addressRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/all-addresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.FOUND);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@Valid @RequestBody AddressRequest addressRequest,
                                                         @PathVariable("id") Long id) {
        addressService.updateAddress(addressRequest, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<AddressResponse> deleteAddressById(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
