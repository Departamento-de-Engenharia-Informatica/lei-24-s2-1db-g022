package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.model.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing addresses.
 *
 * @author Group22
 */
public class AddressRepository {
    private final List<Address> addressList;

    public AddressRepository(){
        addressList = new ArrayList<>();
    }

    public Optional<Address> registerAddress(String streetName, String postCode, int doorNumber){
        Optional<Address> optinalAddress =Optional.empty();
        Address address = new Address(streetName,postCode,doorNumber);
        addAddress(address);
        optinalAddress = Optional.of(address);

        return optinalAddress;
    }

    private void addAddress(Address address){
        if(validateAddress(address)){
            addressList.add(address.clone());
        }
    }

    private boolean validateAddress(Address address){
        return !addressList.contains(address);
    }
}
