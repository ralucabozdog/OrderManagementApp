package bll.validators.customer;

import bll.validators.InvalidDataException;
import bll.validators.Validator;
import model.Customer;

/**
 * Clasa pentru validarea campului Address al unui client
 * @author Raluca - Delia Bozdog
 * @since 20.04.2021
 */
public class AddressValidator implements Validator<Customer> {

    /**
     * Implementarea metodei de validare din interfata Validator
     * @param customer clientul a carui adresa este verificata
     * @throws InvalidDataException exceptie proprie, aruncata in cazul unei adrese invalide
     */
    @Override
    public void validate(Customer customer) throws InvalidDataException {
        if(customer.getAddress().length() == 0)
            throw new InvalidDataException("Fill in address field");
        if(customer.getAddress().length() > 100)
            throw new InvalidDataException("Address has at most 100 characters");
    }
}
