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

    /**
     * Constructs a new address repository with an empty list of addresses.
     */
    public AddressRepository() {
        addressList = new ArrayList<>();
    }

    /**
     * Registers a new address with the given street name, post code, and door number.
     *
     * @param streetName the street name of the address
     * @param postCode   the post code of the address
     * @param doorNumber the door number of the address
     * @return an Optional containing the registered address if successful, otherwise an empty Optional
     */
    public Optional<Address> registerAddress(String streetName, String postCode, int doorNumber) {
        Optional<Address> optinalAddress = Optional.empty();
        Address address = new Address(streetName, postCode, doorNumber);
        addAddress(address);
        optinalAddress = Optional.of(address);

        return optinalAddress;
    }

    /**
     * Adds the given address to the repository if it is valid.
     *
     * @param address the address to add
     */
    private void addAddress(Address address) {
        if (validateAddress(address)) {
            addressList.add(address.clone());
        }
    }

    /**
     * Validates the given address by checking if it is already present in the repository.
     *
     * @param address the address to validate
     * @return true if the address is valid (i.e., not already present in the repository); false otherwise
     */
    private boolean validateAddress(Address address) {
        return !addressList.contains(address);
    }
}
